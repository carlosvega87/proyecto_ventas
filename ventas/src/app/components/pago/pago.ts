import { Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Pago } from '../../model/pago.model';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { PagoService } from '../../services/pago';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-pagos',
  standalone: false,
  templateUrl: './pago.html',
  styleUrl: './pago.css',
})
export class PagoComponent implements OnInit {

  @ViewChild('formularioPago') formularioPago!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  pagos:Pago[] = [];
  pago:Pago ={} as Pago;
  editar:boolean =false ;
  idEditar : number | null=null;
  
  dataSource!: MatTableDataSource<Pago>;
  mostrarColumnas: String[]=['idPago','descripcion','fechaPago','acciones'];

constructor(private pagoService: PagoService){}
 
  ngOnInit(): void {
    this.findAll();
  } 

findAll ():void{
  this.pagoService.findAll().subscribe(data=>{
      //this pagos = this.data;
      this.dataSource =new MatTableDataSource(data);
      this.dataSource.paginator= this.paginator;
      this.dataSource.sort= this.sort;
  });
}

save(): void{
  this.pagoService.save(this.pago).subscribe(()=>{ 
    this.pago = {} as Pago;
    this.findAll( );
  });
}

update(): void{
  if(this.idEditar !== null){
    this.pagoService.update(this.idEditar, this.pago).subscribe(()=>{
      this.pago={} as Pago;
      this.editar= false;
      this.idEditar= null;
      this.findAll();
    });
  }

}

delete(): void{
  //this pagoService.delete(this pago.ioPago).subscribe(()=>{
Swal.fire({
  title: 'Desea eliminar  el dato ', 
  text:'Eta acion no se puede deshacer',
  icon: 'warning',
  showCancelButton: true,
  confirmButtonText:'Si,eliminar',
  cancelButtonText:'Cancelar',
  confirmButtonColor: '#d33',
  cancelButtonColor: '#3085d6'
}).then((result )=>{
  if(result.isConfirmed){
    this.pagoService.delete(this.pago.idPago).subscribe(()=>{
    this.findAll();
    this.pago ={} as Pago;
    Swal.fire('Elimindado', 'el pago ha ido eliminado','success');

  });
}else{
  this.pago={} as Pago;
}

  });
}

//interaccion con la pagina web
editarPago  (pago:Pago)  :void{
  this.pago = {...pago};
  this.idEditar=pago.idPago;
  this.editar=true;

  setTimeout(()=>{
    this.formularioPago.nativeElement.scrollIntoView({behavior:'smooth',block:'start'});
  });
}

editarpagoCancelar( form: NgForm): void{
  this.pago={}as Pago,
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
