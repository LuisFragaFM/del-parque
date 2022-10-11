import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private http: HttpClient) {
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${environment.baseUrl}/users`);
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${environment.baseUrl}/users/register`, user);
  }

  addRole(userId: string, role: string): Observable<string[]> {
    return this.http.patch<string[]>(`${environment.baseUrl}/users/${userId}/${role}`, null);
  }

  removeRole(userId: string, role: string): Observable<User[]> {
    return this.http.delete<User[]>(`${environment.baseUrl}/users/${userId}/${role}`);
  }
}
