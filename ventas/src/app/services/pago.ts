import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';
import { Pago } from '../model/pago.model';

@Injectable({
  providedIn: 'root',
})
export class PagoService {
  
  private baseUrl = "http://localhost:8080/api/pagos"

constructor(private http: HttpClient){  }
  

  findAll(): Observable<Pago[]>{
    return this.http.get<Pago[]>(this.baseUrl);
  }

  findOne(id: number) : Observable<Pago>{
    return this.http.get<Pago>(`${this.baseUrl}/${id}`);
  }

  save(cliente: Pago): Observable<Pago>{
    return this.http.post<Pago>(this.baseUrl, cliente);
  }

  update(id: number, cliente: Pago): Observable<Pago>{
    return this.http.put<Pago>(`${this.baseUrl}/${id}`, cliente);
  }

  delete(id: number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

