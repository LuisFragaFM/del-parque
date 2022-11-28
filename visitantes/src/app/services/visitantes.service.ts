import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Visitante} from '../models/visitante';
import {VisitantePage} from '../models/visitante-page';

@Injectable({
  providedIn: 'root'
})
export class VisitantesService {

  constructor(private http: HttpClient) {
  }

  findAllByUserId(userId: string): Observable<Visitante[]> {
    return this.http.get<Visitante[]>(`${environment.baseUrl}/visitantes?userId=${userId}`);
  }

  getVisitantesUnauthorizedCondomino(page: number = 0): Observable<VisitantePage> {
    return this.http.get<VisitantePage>(`${environment.baseUrl}/visitantes/un-authorized/condomino?page=${page}`);
  }

  getVisitantesUnauthorized(page: number = 0): Observable<VisitantePage> {
    return this.http.get<VisitantePage>(`${environment.baseUrl}/visitantes/un-authorized/?page=${page}`);
  }

  getVisitantesByCheckOut(page: number = 0): Observable<VisitantePage> {
    return this.http.get<VisitantePage>(`${environment.baseUrl}/visitantes/check-out?page=${page}`);
  }

  findById(id: string): Observable<Visitante> {
    return this.http.get<Visitante>(`${environment.baseUrl}/visitantes/${id}`);
  }

  findByName(name: string): Observable<Visitante> {
    return this.http.get<Visitante>(`${environment.baseUrl}/visitantes/name/${name}`);
  }

  save(condomino: Visitante): Observable<Visitante> {
    return this.http.post<Visitante>(`${environment.baseUrl}/visitantes/`, condomino);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.baseUrl}/visitantes/${id}`);
  }
}
