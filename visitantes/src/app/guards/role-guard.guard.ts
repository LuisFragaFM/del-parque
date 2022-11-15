import {Injectable} from '@angular/core';
import {CanLoad, Route, UrlSegment, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {SessionService} from '../services/session.service';
import {User} from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardGuard implements CanLoad {

  user!: User;

  constructor(private sessionService: SessionService) {
    sessionService.getUser().subscribe(user => this.user = user);
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.user.role === 'ROLE_GUARD';
  }
}
