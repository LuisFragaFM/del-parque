import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class GuardiasService {

  constructor(private http: HttpClient) {
  }


  register(user: User): Observable<User> {
    return this.http.post<User>(`${environment.baseUrl}/guardias`, user);
  }

}
