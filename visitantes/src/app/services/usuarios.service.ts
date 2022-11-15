import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {User} from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<User> {
    return this.http.get<User>(`${environment.baseUrl}/users/${id}`);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${environment.baseUrl}/users`);
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${environment.baseUrl}/register`, user);
  }

}
