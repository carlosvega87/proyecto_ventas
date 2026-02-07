import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Productos } from '../model/productos.model';

@Injectable({
  providedIn: 'root',
})
export class ProductosService {
  
  private baseUrl = "http://localhost:8080/api/productos"

constructor(private http: HttpClient){  }
  

  findAll(): Observable<Productos[]>{
    return this.http.get<Productos[]>(this.baseUrl);
  }

  findOne(id: number) : Observable<Productos>{
    return this.http.get<Productos>(`${this.baseUrl}/${id}`);
  }

  save(cliente: Productos): Observable<Productos>{
    return this.http.post<Productos>(this.baseUrl, cliente);
  }

  update(id: number, cliente: Productos): Observable<Productos>{
    return this.http.put<Productos>(`${this.baseUrl}/${id}`, cliente);
  }

  delete(id: number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

