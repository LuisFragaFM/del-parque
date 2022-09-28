import { Component, Input } from '@angular/core';
import { User } from './models/user';
import { SessionService } from './services/session.service';
import { UsuariosService } from './services/usuarios.service';
import { validaInput } from 'src/tools/validation';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'visitantes';
  @Input('user') user: User = {} as User;
  failedLogin = false;
  rememberMe: boolean = false;

  //variable para validar nombre y activar boton
  validacionMail: boolean = true;
  validacionContra: boolean = true;
  // regex a utilizar
  regexMail = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
  regexPssw = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{7,15}$/;

  constructor(
    private sessionService: SessionService,
    private usuariosService: UsuariosService
  ) {}

  ngOnInit(): void {
    this.login();
  }

  apiLogin(email: string, password: string): void {
    this.sessionService.setCredentials(email, password, this.rememberMe);
    this.login();
  }

  login(): void {
    this.sessionService.getUser().subscribe(
      (user) => {
        this.failedLogin = false;
        this.user = user;
        console.log(this.user);
      },
      (error) => {
        this.failedLogin = true;
        console.log(error);
      }
    );
  }

  register() {
    this.usuariosService.register(this.user!).subscribe((user) => {
      this.user = user;
    });
  }

  // funcion para validacion
  validaMail(regex: any, nombreRecibo: string) {
    this.validacionMail = validaInput(regex, nombreRecibo);
  }

  validaPassword(regex: any, nombreRecibo: string) {
    this.validacionContra = validaInput(regex, nombreRecibo);
  }

  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.validacionMail && this.validacionContra;
  }
}
