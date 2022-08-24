import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Trabajador} from "../models/trabajador";

@Injectable({
  providedIn: 'root'
})
export class TrabajadoresService {

  constructor(private http: HttpClient) { }

  getTrabajadores(): Observable<Trabajador[]> {
    return this.http.get<Trabajador[]>(`${environment.baseUrl}/trabajadores`);
  }

  findById(id: string): Observable<Trabajador> {
    return this.http.get<Trabajador>(`${environment.baseUrl}/trabajadores/${id}`);
  }

  // Busqueda de nombre
  findByNombre(nombre: string): Observable<Trabajador> {
    return this.http.get<Trabajador>(`${environment.baseUrl}/trabajadores/nombre?nombre=${nombre}`);
  }

  save(trabajador: Trabajador): Observable<Trabajador> {
    return this.http.post<Trabajador>(`${environment.baseUrl}/trabajadores/`, trabajador);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.baseUrl}/trabajadores/${id}`);
  }
}
