import {Injectable} from '@angular/core';
import {CanLoad, Route, UrlSegment, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {User} from "../models/user";
import {SessionService} from "../services/session.service";

@Injectable({
  providedIn: 'root'
})
export class RoleResidentGuard implements CanLoad {
  user!: User;

  constructor(private sessionService: SessionService) {
    sessionService.getUser().subscribe(user => this.user = user)
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.user.role === 'ROLE_RESIDENT';
  }
}
