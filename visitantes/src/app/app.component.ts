import {Component, Input, OnInit} from '@angular/core';
import {User} from './models/user';
import {SessionService} from './services/session.service';
import {validaInput} from 'src/app/tools/validation';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'visitantes';
  @Input('user') user: User = {} as User;
  failedLogin = false;
  rememberMe = false;
  touched = false;

  validacionMail = true;
  validacionContra = true;
  // regex a utilizar
  regexMail = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
  regexPssw = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{7,15}$/;

  constructor(
    private sessionService: SessionService,
  ) {
  }

  ngOnInit(): void {
    this.login();
  }
// verifica que las credenciales sean correctas asi como tambien valida que los datos se recuerden
  apiLogin(email: string, password: string): void {
    this.touched = true;
    this.sessionService.setCredentials(email, password, this.rememberMe);
    this.login();
  }
// asigna valores a login acorde al estado del usuario 
  login(): void {
    this.failedLogin = false;
    this.sessionService.getUser().subscribe(
      (user) => {
        this.failedLogin = false;
        this.user = user;
      },
      (error) => {
        this.failedLogin = true;
        console.log(error);
      }
    );
  }

  // funcion para validacion
  validaMail(regex: any, nombreRecibo: string): void {
    this.validacionMail = validaInput(regex, nombreRecibo);
  }

  validaPassword(regex: any, nombreRecibo: string): void {
    this.validacionContra = validaInput(regex, nombreRecibo);
  }

  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.validacionMail && this.validacionContra;
  }
}
