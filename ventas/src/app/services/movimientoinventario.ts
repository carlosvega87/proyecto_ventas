import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MovimientoInventario } from '../model/movimientoInventario.model';

@Injectable({
  providedIn: 'root',
})
export class MovimientoInventarioService {
  
private baseUrl ="http://localhost:8080/api/movimientoInventario";

constructor( private http: HttpClient){ }

findAll ():Observable <MovimientoInventario[]>{
  return this.http.get<MovimientoInventario[]>(this.baseUrl);
}

findOne (id: number):Observable<MovimientoInventario>{
  return this.http.get<MovimientoInventario>(`${this.baseUrl}/${id}`);
}

save(movimientoInventario:MovimientoInventario): Observable<MovimientoInventario>{
  return this.http.post<MovimientoInventario>(this.baseUrl,movimientoInventario);
}
update ( id: number,movimientoInventario:MovimientoInventario): Observable <MovimientoInventario>{
  return this.http.put<MovimientoInventario>(`${this.baseUrl}/${id}`,movimientoInventario);
  }
delete (id:number): Observable<void>{
  return this.http.delete<void>(`${this.baseUrl}/${id}`);
}
}