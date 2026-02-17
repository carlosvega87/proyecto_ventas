import { Component,  ElementRef,  OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Productos } from '../../model/productos.model';
import { Categoria } from '../../model/categoria.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ProductosService } from '../../services/productos';
import { CategoriaService } from '../../services/categoria';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';  

@Component({
  selector: 'app-productos',
  standalone: false,
  templateUrl: './productos.html',
  styleUrl: './productos.css',
})
export class ProductosComponent implements OnInit {
 productos : Productos []= [];
   categoria: Categoria[]=[];
  producto: Productos={} as Productos;
  editar : boolean= false;
  idEditar : number| null= null;
  dataSource !: MatTableDataSource<Productos>;
  seleccionarArchivo!: File;
  imagenPrevia: string="";
  productoSeleccionado: Productos| null = null;

  mostrarColumnas: string[]= ['detalles', 'idProducto','nombre','precio','stock','fechaRegistro','estado'
                              ,'categoria','acciones'  ];

  @ViewChild('formularioProductos')formularioProductos !: ElementRef;
  @ViewChild  (MatPaginator)paginator !: MatPaginator;
  @ViewChild (MatSort)sort !:MatSort;
  @ViewChild('modalProductos')modalProductos!: TemplateRef<any>;
  @ViewChild ('modalDetalles')modalDetalles !: TemplateRef<any>;

  constructor(
    private productosService : ProductosService,
    private categoriaService : CategoriaService,
   
    private dialog : MatDialog,
    private http: HttpClient

  ){}
  ngOnInit(): void {

    this.findAll();
      this.cargarCategorias();
    
     }

    findAll(): void{
      this.productosService.findAll().subscribe(data=>{
        this.dataSource= new MatTableDataSource(data);
        this.dataSource.paginator= this.paginator;
        this.dataSource.sort= this.sort;
      });
    } 
    cargarCategorias(): void{
      this.categoriaService.findAll().subscribe(data=>{this.categoria=data;});
    }
    

    save (): void {
      this.productosService.save(this.producto).subscribe(()=>{
        this.producto={ }as Productos;
        this.findAll();
      });
    }

    update(): void{
      if(this.idEditar!==null){
        this.productosService.update(this.idEditar, this.producto).subscribe(()=>{
          this.producto= {} as Productos;
          this.editar=false;
          this.idEditar=null;
          this.findAll();
        });
      }
    }
    delete(): void {
      Swal.fire({
        title:'Desea eliminar el producto',
        text: 'Esta accion no se puede deshacer',
        icon: 'warning',
        showCancelButton:true,
        confirmButtonText: 'Si, eliminar',
        cancelButtonText:'Cancelar',
        confirmButtonColor:'#d33',
        cancelButtonColor:'#3085d6'
      }).then((result)=>{
        if(result.isConfirmed){
          this.productosService.delete(this.producto.idProducto).subscribe(()=>{
            this.findAll();
            this.producto={} as Productos;
            Swal.fire('Eliminado','El registro ha sido eliminado','success');
          });
        }else{
          this.producto={} as Productos;
        }
      });
    }
    //interaccion en la pagina
    editarproductos(productos : Productos):void {
      this.producto={...productos};
      this.idEditar =productos.idProducto;
      this.editar=true;
      setTimeout(() => {
        this.formularioProductos.nativeElement.scrollIntoView({behavior: 'smooth',block:'start'})
      }, 100);
    }
editarProductosCancelar(form : NgForm):void{
  this.producto={} as Productos;
  this.idEditar=null;
  this.editar=false;
  form.resetForm();
}

guardarProductos():void{
  if(this.editar&& this.editar!==null){
    this.update();
}else{
  this.save();
}
this.dialog.closeAll();
}

filtroProductos(event : Event):void{
  const filtro =(event.target as HTMLInputElement).value;
  this.dataSource.filter=filtro.trim().toLocaleLowerCase();
}

nombreCompletoCategorias(categoria: Categoria): string{
  return` ${categoria.nombreCategoria}`;
} 
abrirModal(productos?: Productos) : void{
  if(productos){
    this.producto={...productos};
    this.editar=true;
    this.idEditar=productos.idProducto;
  }else{
    this.producto={}as Productos;
    this.editar= false;
    this.idEditar=null;
 }
 this.dialog.open(this.modalProductos,{
  width: '800px',
  disableClose: true
 });
}

compareCategorias(c1: Categoria, c2:Categoria): boolean{
  return c1 && c2 ? c1.idCategoria === c2.idCategoria : c1 === c2;
}

onSeleccionarArchivo(event: any){
  this.seleccionarArchivo=event.target.files[0];
}

subirImagen(): void {
  const formData = new FormData();
  formData.append("file",this.seleccionarArchivo);

  if ( this.producto["portada"]){
    formData.append("oldImage",this.producto["portada"]);
  }

  this.http.post<{ruta:string}>('http://localhost:8080/api/upload-portada',formData).subscribe(res=>{ 
    this.producto["portada"]= res.ruta;
    this.imagenPrevia= res.ruta;
  });
}

abrirModalDEtalles (productos: Productos): void{
  this.productoSeleccionado= productos;
  this.dialog.open(this.modalDetalles,{
   width:'500px' 
  });
}

cerrarModal():void{
  this.dialog.closeAll();
  this.productoSeleccionado=null;
}

}



  

