import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Visitante} from "../models/visitante";

@Injectable({
  providedIn: 'root'
})
export class VisitantesService {

  constructor(private http: HttpClient) { }

  getTrabajadores(): Observable<Visitante[]> {
    return this.http.get<Visitante[]>(`${environment.baseUrl}/visitantes`);
  }

  findById(id: string): Observable<Visitante> {
    return this.http.get<Visitante>(`${environment.baseUrl}/visitantes/${id}`);
  }

  save(condomino: Visitante): Observable<Visitante> {
    return this.http.post<Visitante>(`${environment.baseUrl}/visitantes/`, condomino);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.baseUrl}/visitantes/${id}`);
  }
}
