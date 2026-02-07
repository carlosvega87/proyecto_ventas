import { Component,  ElementRef,  OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Productos } from '../../model/productos.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ProductosService } from '../../services/productos';
import { CategoriaService } from '../../services/categoria';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-productos',
  standalone: false,
  templateUrl: './productos.html',
  styleUrl: './productos.css',
})
export class ProductosComponent implements OnInit {
  productos : Productos []= [];
  
  producto: Productos={} as Productos;
  editar : boolean= false;
  idEditar : number| null= null;
  dataSource !: MatTableDataSource<Productos>;
  seleccionarArchivo!: File;
  imagenPrevia: string="";
  productoSeleccionado: Productos | null = null;

  mostrarColumnas: string[]= ['detalles', 'idProductos','titulo','editorial','edicion','idioma','fechaPublicacion'
                              ,'numEjemplares','precio','autor','categoria','acciones'  ];

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
      this.cargarProductos();
    
     }

    findAll(): void{
      this.productosService.findAll().subscribe(data=>{
        this.dataSource= new MatTableDataSource(data);
        this.dataSource.paginator= this.paginator;
        this.dataSource.sort= this.sort;
      });
    } 
    cargarProductos(): void{
      this.productosService.findAll().subscribe(data=>{this.productos=data;});
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
  }
