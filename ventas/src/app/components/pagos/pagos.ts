import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Pagos } from '../../model/pagos.model';
import { MatTableDataSource } from '@angular/material/table';
import { PagoService } from '../../services/pagos';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pagos',
  standalone: false,
  templateUrl: './pagos.html',
  styleUrl: './pagos.css',
})
export class PagosComponent implements OnInit{

@ViewChild('formularioPagos') formularioPagos!: ElementRef;
@ViewChild(MatPaginator)paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;

pagos: Pagos[] = [];
pago: Pagos = { } as Pagos;
editar: boolean = false;
idEditar: number | null = null;

dataSource!: MatTableDataSource<Pagos>;
mostrarColumnas: String[] = ['idPago', 'descripcion', 'fechaPago','acciones'];
  
constructor(private pagoService: PagoService) { }


  ngOnInit(): void {
    this.findAll();
  }

  findAll() : void{
    this.pagoService.findAll().subscribe(data => { 

    //this.pagos = data;
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    });
  }

  save(): void{
    this.pagoService.save(this.pago).subscribe(() => { 
      this.pago = { } as Pagos;
      this.findAll();
    });
  }

  update(): void{
    if(this.idEditar !== null){
      this.pagoService.update(this.idEditar, this.pago).subscribe(() => { 
        this.pago = { } as Pagos;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }


  delete(): void{
    //this.pagoService.delete(this.pago.idCliente).subscribe(() => {});

    Swal.fire({ 
      title: '¿Desea eliminar el dato?',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if(result.isConfirmed){
        this.pagoService.delete(this.pago.idPago).subscribe( () => {
          this.findAll();
          this.pago = { } as Pagos;
          Swal.fire('Eliminado', 'El pago ha sido eliminado', 'success');
         });
      } else {
        this.pago = { } as Pagos;
      }
     });
  }

  //interaccion con la pagina web

  editarPagos(pagos: Pagos): void {
    this.pago = { ...pagos};
    this.idEditar = pagos.idPago;
    this.editar = true;

    setTimeout(() => {
      this.formularioPagos.nativeElement.scrollIntoView({ behavior: 'smooh', block: 'start' });
    }, 100);
  }

  editarPagosCancelar(from: NgForm): void{
    this.pago = { } as Pagos;
    this.idEditar =  null;
    this.editar = false;
    from.resetForm;
  }

  guardar(from: NgForm): void{
    if(this.editar && this.idEditar !==  null){
      this.update();
      from.resetForm();
    }else{
      this.save();
      from.resetForm();
    }
  }

  filtro(event: Event){
    const filtro1 =  (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro1.trim().toLocaleLowerCase();
  }

}
