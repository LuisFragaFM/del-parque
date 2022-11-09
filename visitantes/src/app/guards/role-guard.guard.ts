import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {SessionService} from "../services/session.service";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardGuard implements CanActivate {

  user!: User;

  constructor(private sessionService: SessionService) {
    sessionService.getUser().subscribe(user => this.user = user)
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.user.role === 'ROLE_GUARD';
  }

}
