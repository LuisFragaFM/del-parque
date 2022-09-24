import {Component, Input} from '@angular/core';
import {User} from "./models/user";
import {SessionService} from "./services/session.service";
import {UsuariosService} from "./services/usuarios.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'visitantes';
  @Input('user') user: User = {} as User;
  failedLogin = false;
  isLogin: boolean = true;

  constructor(private sessionService: SessionService, private usuariosService: UsuariosService) {
  }

  ngOnInit(): void {
    this.login();
  }

  apiLogin(email: string, password: string): void {
    this.sessionService.setCredentials(email, password);
    this.login();
  }

  login(): void {
    this.sessionService.getUser().subscribe(user => {
      this.failedLogin = false;
      this.user = user;
      console.log(this.user)
    }, error => {
      this.failedLogin = true;
      console.log(error);
    });
  }

  register() {
    this.usuariosService.register(this.user!).subscribe((user) => {
      this.user = user;
    })
  }


  changeToRegister() {
    this.isLogin = false;
  }

  changeToLogin() {
    this.isLogin = true;
  }
}
