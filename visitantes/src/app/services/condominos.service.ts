import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Condomino} from "../models/condomino";

@Injectable({
  providedIn: 'root'
})
export class CondominosService {

  constructor(private http: HttpClient) {
  }

  getCondominos(): Observable<Condomino[]> {
    return this.http.get<Condomino[]>(`${environment.baseUrl}/condominos`);
  }

  findById(id: string): Observable<Condomino> {
    return this.http.get<Condomino>(`${environment.baseUrl}/condominos/${id}`);
  }

  findByNombre(nombre: string): Observable<Condomino> {
    return this.http.get<Condomino>(`${environment.baseUrl}/condominos/${nombre}`);
  }

  findByTelefono(telefono: string): Observable<Condomino> {
    return this.http.get<Condomino>(`${environment.baseUrl}/condominos/${telefono}`);
  }

  save(condomino: Condomino): Observable<Condomino> {
    return this.http.post<Condomino>(`${environment.baseUrl}/condominos/`, condomino);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.baseUrl}/condominos/${id}`);
  }
}
