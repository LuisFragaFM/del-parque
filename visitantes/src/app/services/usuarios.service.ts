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

  addRole(user: User, role: string): Observable<User[]> {
    return this.http.patch<User[]>(`${environment.baseUrl}/users/${user.id}/${role}`, user);
  }

  removeRole(userId: string, role: string): Observable<User[]> {
    return this.http.delete<User[]>(`${environment.baseUrl}/users/${userId}/${role}`);
  }
}
