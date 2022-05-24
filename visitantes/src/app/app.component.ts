import { Component } from '@angular/core';
import {User} from "./models/user";
import {SessionService} from "./services/session.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'visitantes';
  user: User | undefined;
  failedLogin = false;

  constructor(private sessionService: SessionService, private router: Router) {
  }

  ngOnInit(): void {
    this.login();
  }

  apiLogin(user: string, password: string): void {
    this.sessionService.setCredentials(user, password);
    this.login();
  }

  login(): void {
    this.failedLogin = false;
    this.sessionService.getUser().subscribe(user => {
      this.user = user;
    }, error => {
      this.failedLogin = true;
      console.log(error);
    });
  }

}
