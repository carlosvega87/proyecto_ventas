import { Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { MovimientoInventario } from '../../model/movimientoInventario.model';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MovimientoInventarioService } from '../../services/movimientoinventario';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-movimiento-inventario',
  standalone: false,
  templateUrl:'./movimientoinventario.html',
  styleUrl: './movimientoinventario.css',
})
export class MovimientoInventarioComponent implements OnInit {

  @ViewChild('formularioMovimientoInventario') formularioMovimientoInventario!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  movimientoInventarios: MovimientoInventario[] = [];
  movimientoInventario: MovimientoInventario ={} as MovimientoInventario;
  editar:boolean =false ;
  idEditar : number | null=null;
  
  dataSource!: MatTableDataSource<MovimientoInventario>;
  mostrarColumnas: String[]=['idMovimiento','tipo','cantidad','fechaMovimiento','acciones'];

constructor(private movimientoInventarioService: MovimientoInventarioService){}
 
  ngOnInit(): void {
    this.findAll();
  } 

findAll ():void{
  this.movimientoInventarioService.findAll().subscribe(data=>{
      //this.autores = this.data;
       console.log('DATA BACKEND 👉', data);
      this.dataSource =new MatTableDataSource(data);
      this.dataSource.paginator= this.paginator;
      this.dataSource.sort= this.sort;
  });
}

save(): void{
  this.movimientoInventarioService.save(this.movimientoInventario).subscribe(()=>{ 
    this.movimientoInventario = {} as MovimientoInventario;
    this.findAll( );
  });
}

update(): void{
  if(this.idEditar !== null){
    this.movimientoInventarioService.update(this.idEditar, this.movimientoInventario).subscribe(()=>{
      this.movimientoInventario={} as MovimientoInventario;
      this.editar= false;
      this.idEditar= null;
      this.findAll();
    });
  }

}

delete(): void{
  //this.autorService.delete(this.autor.idAutor).subscribe(()=>{
Swal.fire({
  title: 'Desea eliminar  el dato ', 
  text:'Esta accion no se puede deshacer',
  icon: 'warning',
  showCancelButton: true,
  confirmButtonText:'Si,eliminar',
  cancelButtonText:'Cancelar',
  confirmButtonColor: '#d33',
  cancelButtonColor: '#3085d6'
}).then((result )=>{
  if(result.isConfirmed){
    this.movimientoInventarioService.delete(this.movimientoInventario.idMovimiento).subscribe(()=>{
    this.findAll();
    this.movimientoInventario ={} as MovimientoInventario;
    Swal.fire('Eliminando', 'el movimiento ha ido eliminado','success');

  });
}else{
  this.movimientoInventario={} as MovimientoInventario;
}

  });
}

//interaccion con la pagina web
editarMovimientoInventario (movimientoInventario: MovimientoInventario)  :void{
  this.movimientoInventario = {...movimientoInventario};
  this.idEditar=movimientoInventario.idMovimiento;
  this.editar=true;

  setTimeout(()=>{
    this.formularioMovimientoInventario.nativeElement.scrollIntoView({behavior:'smooth',block:'start'});
  });
}

editarMovimientoInventarioCancelar( form: NgForm): void{
  this.movimientoInventario={}as MovimientoInventario,
  this.idEditar=null;
  this.editar=false;
  form.resetForm();
  }

  guardar (form:NgForm):void {
    if(this.editar&& this.idEditar !==null){
      this.update();
      form.resetForm();
    }else{
      this.save();
      form.resetForm();
    }
  }

filtro(event: Event){
  const filtro1 =(event.target as HTMLInputElement).value;
  this.dataSource.filter= filtro1.trim().toLowerCase();
}


}
