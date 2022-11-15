import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {Paquete} from '../models/paquete';
import {PaquetePage} from '../models/paquete-page';

@Injectable({
  providedIn: 'root'
})
export class PaquetesService {

  constructor(private http: HttpClient) {
  }

  getPaquetes(page: number = 0): Observable<PaquetePage> {
    return this.http.get<PaquetePage>(`${environment.baseUrl}/paquetes?page=${page}`);
  }

  findById(id: string): Observable<Paquete> {
    return this.http.get<Paquete>(`${environment.baseUrl}/paquetes/${id}`);
  }

  save(condomino: Paquete): Observable<Paquete> {
    return this.http.post<Paquete>(`${environment.baseUrl}/paquetes/`, condomino);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.baseUrl}/paquetes/${id}`);
  }
}
