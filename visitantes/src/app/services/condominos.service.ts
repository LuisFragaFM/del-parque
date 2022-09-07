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

  getCondominos(page: number = 0): Observable<Condomino[]> {
    return this.http.get<Condomino[]>(`${environment.baseUrl}/condominos?page=${page}`);
  }

  findById(id: string): Observable<Condomino> {
    return this.http.get<Condomino>(`${environment.baseUrl}/condominos/${id}`);
  }

  findByNombre(nombre: string): Observable<Condomino> {
    return this.http.get<Condomino>(`${environment.baseUrl}/condominos/nombre?nombre=${nombre}`);
  }

  findByTelefono(telefono: string): Observable<Condomino> {
    return this.http.get<Condomino>(`${environment.baseUrl}/condominos/telefono?telefono=${telefono}`);
  }

  save(condomino: Condomino): Observable<Condomino> {
    return this.http.post<Condomino>(`${environment.baseUrl}/condominos/`, condomino);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.baseUrl}/condominos/${id}`);
  }
}
