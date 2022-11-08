import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable, of} from "rxjs";
import {User} from "../models/user";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private user: User | undefined;
  private _authorizationHeader!: string | null;
  private rememberMe!: boolean;

  constructor(private http: HttpClient) {
  }

  getUser(): Observable<User> {
    if (localStorage.getItem('user')) {
      return of(JSON.parse(localStorage.getItem('user')!));
    }

    if (this.user) {
      return of(this.user);
    }

    return this.http.get<User>(`${environment.baseUrl}/loggedUser`)
      .pipe(map(user => {
        if (this.rememberMe) {
          localStorage.setItem('user', JSON.stringify(user));
          localStorage.setItem('auth', this.authorizationHeader!);
        }
        this.user = user;
        return this.user;
      }));
  }

  get authorizationHeader(): string | null {
    if (localStorage.getItem('auth')) {
      this._authorizationHeader = localStorage.getItem('auth');
    }
    return this._authorizationHeader;
  }

  setCredentials(email: string, password: string, rememberMe: boolean = false): void {
    this.rememberMe = rememberMe;
    this._authorizationHeader = btoa(email + ':' + password);
  }

  logout(): void {
    this._authorizationHeader = '';
    localStorage.removeItem('auth');
    localStorage.removeItem('user');
    window.location.href = '/logout';
  }

}
