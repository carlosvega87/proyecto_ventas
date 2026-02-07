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


@Component({
  selector: 'app-movimientoinventario',
  standalone: false,
  templateUrl: './movimientoinventario.html',
  styleUrl: './movimientoinventario.css',
})
export class MovimientoInventarioComponent implements OnInit {
  productos : Productos []= [];
  
  movimientoinventario: MovimientoInventario={} as MovimientoInventario;
  editar : boolean= false;
  idEditar : number| null= null;
  dataSource !: MatTableDataSource<MovimientoInventario>;
  seleccionarArchivo!: File;
  imagenPrevia: string="";
  libroSeleccionado: MovimientoInventario | null = null;

  mostrarColumnas: string[]= ['detalles', 'idMovimientoInventario','titulo','editorial','edicion','idioma','fechaPublicacion'
                              ,'numEjemplares','precio','autor','categoria','acciones'  ];

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
      this.movimientoinventarioService.save(this.movimientoinventario).subscribe(()=>{
        this.movimientoinventario={ }as MovimientoInventario;
        this.findAll();
      });
    }

    update(): void{
      if(this.idEditar!==null){
        this.movimientoinventarioService.update(this.idEditar, this.movimientoinventario).subscribe(()=>{
          this.movimientoinventario= {} as MovimientoInventario;
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
          this.movimientoinventarioService.delete(this.movimientoinventario.idMovimiento).subscribe(()=>{
            this.findAll();
            this.movimientoinventario={} as MovimientoInventario;
            Swal.fire('Eliminado','El registro ha sido eliminado','success');
          });
        }else{
          this.movimientoinventario={} as MovimientoInventario;
        }
      });
    }
  }
