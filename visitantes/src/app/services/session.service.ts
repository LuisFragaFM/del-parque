import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, of} from "rxjs";
import {User} from "../models/user";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private user: User | undefined;
  private _authorizationHeader!: string;

  constructor(private http: HttpClient) {
  }

  getUser() {
    if (this.user) {
      return of(this.user);
    }
    return this.http.get<User>(`${environment.baseUrl}/loggedUser`)
      .pipe(map(user => {
        this.user = user;
        return this.user;
      }));
  }

  get authorizationHeader(): string {
    return this._authorizationHeader;
  }

  setCredentials(email: string, password: string): void {
    this._authorizationHeader = btoa(email + ':' + password);
  }

  logout(): void {
    this._authorizationHeader = '';
    window.location.href = '/logout';
  }

}
