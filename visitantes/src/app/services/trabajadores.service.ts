import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Trabajador} from "../models/trabajador";
import { TrabajadorPage } from '../models/trabajador-page';


@Injectable({
  providedIn: 'root'
})
export class TrabajadoresService {

  constructor(private http: HttpClient) { }

  getTrabajadores(page: number = 0): Observable<TrabajadorPage> {
    return this.http.get<TrabajadorPage>(`${environment.baseUrl}/trabajadores?page=${page}`);
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
