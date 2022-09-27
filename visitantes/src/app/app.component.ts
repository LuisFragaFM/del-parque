import { Component, Input } from '@angular/core';
import { User } from './models/user';
import { SessionService } from './services/session.service';
import { UsuariosService } from './services/usuarios.service';

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
  //variables a utilizar en la validacion de datos
  startMail: boolean = false;
  startPassw: boolean = false;
  //variable para validar nombre y activar boton
  validacionMail: boolean = false;
  validacionContra: boolean = false;
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

  // funcion para validar datos de nombre
  validaEmail(correo: string) {
    this.startMail = true;
    const regMail = new RegExp(/^[^@]+@[^@]+\.[a-zA-Z]{2,}$/); //Expresion regular a validar
    const testMail = regMail.test(correo); //validar
    const alertValMail = document.getElementById('alertMail'); //Obtiene el elemento para mostrar la alerta
    if (testMail == false) {
      alertValMail?.classList.remove('hiddenAlertName'); //oculta alerta
      this.validacionMail = false;
      this.startMail = true;
    } else {
      alertValMail?.classList.add('hiddenAlertName'); //muestra alerta
      this.validacionMail = true;
      this.startMail = false;
      
    }
  }
  // funcion para validar datos de contra
  validaPassw(contra: string) {
    this.startPassw = true;
    const regContra = new RegExp(
      /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/
    ); //Expresion regular a validar
    const testContra = regContra.test(contra); //validar
    const alertPassw = document.getElementById('alertMail'); //Obtiene el elemento para mostrar la alerta
    if (testContra == false) {
      alertPassw?.classList.remove('hiddenAlertName'); //oculta alerta
      this.validacionContra= false;
      this.startPassw = true;
      
    } else {
      alertPassw?.classList.add('hiddenAlertName'); //muestra alerta
      this.validacionContra= true;
      this.startPassw = false;
      
    }
  }
  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.validacionMail && this.validacionContra;
  }
}
