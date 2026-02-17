  import { Component,  ElementRef,  OnInit, TemplateRef, ViewChild } from '@angular/core';
  import { Productos } from '../../model/productos.model';
  import { MovimientoInventario } from '../../model/movimientoInventario.model';
  import { MatTableDataSource } from '@angular/material/table';
  import { MatPaginator } from '@angular/material/paginator';
  import { MatSort } from '@angular/material/sort';
  import { ProductosService } from '../../services/productos';
  import { MovimientoInventarioService } from '../../services/movimientoinventario';
  import { HttpClient } from '@angular/common/http';
  import { MatDialog } from '@angular/material/dialog';
  import Swal from 'sweetalert2';
  import { NgForm } from '@angular/forms';  

@Component({
  selector: 'app-movimientoinventario',
  standalone: false,
  templateUrl: './movimientoinventario.html',
  styleUrl: './movimientoinventario.css',
})
export class MovimientoInventarioComponent implements OnInit {
    movimientoInventarios : MovimientoInventario []= [];
    productos : Productos []= [];
    movimientoInventario: MovimientoInventario={} as MovimientoInventario;
  editar : boolean= false;
  idEditar : number| null= null;
  dataSource !: MatTableDataSource<MovimientoInventario>;
  seleccionarArchivo!: File;
  imagenPrevia: string="";
  movimientoInventarioSeleccionado: MovimientoInventario | null = null;

  mostrarColumnas: string[]= ['detalles', 'idMovimiento','tipo','cantidad','fechaMovimiento','productos','acciones'  ];

  @ViewChild('formularioMovimientoInventario')formularioMovimientoInventario !: ElementRef;
  @ViewChild  (MatPaginator)paginator !: MatPaginator;
  @ViewChild (MatSort)sort !:MatSort;
  @ViewChild('modalMovimientoInventario')modalMovimientoInventario!: TemplateRef<any>;
  @ViewChild ('modalDetalles')modalDetalles !: TemplateRef<any>;

  constructor(
    private movimientoinventarioService : MovimientoInventarioService,
    private productosService : ProductosService,
   
    private dialog : MatDialog,
    private http: HttpClient

  ){}
  ngOnInit(): void {

    this.findAll();
      this.cargarProductos();
    
     }

    findAll(): void{
      this.movimientoinventarioService.findAll().subscribe(data=>{
        this.dataSource= new MatTableDataSource(data);
        this.dataSource.paginator= this.paginator;
        this.dataSource.sort= this.sort;
      });
    } 
    cargarProductos(): void{
      this.productosService.findAll().subscribe(data=>{this.productos=data;});
    }
    

    save (): void {
      this.movimientoinventarioService.save(this.movimientoInventario).subscribe(()=>{
        this.movimientoInventario={ }as MovimientoInventario;
        this.findAll();
      });
    }

    update(): void{
      if(this.idEditar!==null){
        this.movimientoinventarioService.update(this.idEditar, this.movimientoInventario).subscribe(()=>{
          this.movimientoInventario= {} as MovimientoInventario;
          this.editar=false;
          this.idEditar=null;
          this.findAll();
        });
      }
    }
    delete(): void {
      Swal.fire({
        title:'Desea eliminar el registro',
        text: 'Esta accion no se puede deshacer',
        icon: 'warning',
        showCancelButton:true,
        confirmButtonText: 'Si, eliminar',
        cancelButtonText:'Cancelar',
        confirmButtonColor:'#d33',
        cancelButtonColor:'#3085d6'
      }).then((result)=>{
        if(result.isConfirmed){
          this.movimientoinventarioService.delete(this.movimientoInventario.idMovimiento).subscribe(()=>{
            this.findAll();
            this.movimientoInventario={} as MovimientoInventario;
            Swal.fire('Eliminado','El registro ha sido eliminado','success');
          });
        }else{
          this.movimientoInventario={} as MovimientoInventario;
        }
      });
    }
  

  
    //interaccion en la pagina
    editarmovimientoInventario(movimientoInventario : MovimientoInventario):void {
      this.movimientoInventario={...movimientoInventario};
      this.idEditar =movimientoInventario.idMovimiento;
      this.editar=true;
      setTimeout(() => {
        this.formularioMovimientoInventario.nativeElement.scrollIntoView({behavior: 'smooth',block:'start'})
      }, 100);
    }
editarMovimientoInventarioCancelar(form : NgForm):void{
  this.movimientoInventario={} as MovimientoInventario;
  this.idEditar=null;
  this.editar=false;
  form.resetForm();
}

guardarMovimientoInventario():void{
  if(this.editar&& this.editar!==null){
    this.update();
}else{
  this.save();
}
this.dialog.closeAll();
}

filtroMovimientoInventario(event : Event):void{
  const filtro =(event.target as HTMLInputElement).value;
  this.dataSource.filter=filtro.trim().toLocaleLowerCase();
}

nombreCompletoProductos(productos: Productos): string{
  return` ${productos.nombre}`;
}
abrirModal(movimientoInventario?: MovimientoInventario) : void{
  if(movimientoInventario){
    this.movimientoInventario={...movimientoInventario};
    this.editar=true;
    this.idEditar=movimientoInventario.idMovimiento;
  }else{
    this.movimientoInventario={}as MovimientoInventario;
    this.editar= false;
    this.idEditar=null;
 }
 this.dialog.open(this.modalMovimientoInventario,{
  width: '800px',
  disableClose: true
 });
}

compareProductos(c1: Productos, c2:Productos): boolean{
  return c1 && c2 ? c1.idProducto === c2.idProducto : c1 === c2;
}

onSeleccionarArchivo(event: any){
  this.seleccionarArchivo=event.target.files[0];
}

subirImagen(): void {
  const formData = new FormData();
  formData.append("file",this.seleccionarArchivo);

  if ( this.movimientoInventario["portada"]){
    formData.append("oldImage",this.movimientoInventario["portada"]);
  }

  this.http.post<{ruta:string}>('http://localhost:8080/api/upload-portada',formData).subscribe(res=>{ 
    this.movimientoInventario["portada"]= res.ruta;
    this.imagenPrevia= res.ruta;
  });
}

  abrirModalDEtalles (movimientoInventario: MovimientoInventario): void{
    this.movimientoInventarioSeleccionado= movimientoInventario;
    this.dialog.open(this.modalDetalles,{
    width:'500px' 
    });
  }

cerrarModal():void{
  this.dialog.closeAll();
  this.movimientoInventarioSeleccionado=null;
}

}


