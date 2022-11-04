import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ListaTrabajadoresComponent} from './components/lista_trabajadores/lista_trabajadores.component';
import {EntregarPaqueteComponent} from './components/entregar-paquete/entregar-paquete.component';
import {ReportesComponent} from './components/reportes/reportes.component';
import {Modificacion_CondominoComponent} from './components/modificacion_condomino/modificacion_condomino.component';
import {VisitasRegistradasAdminComponent} from './components/visitas_registradas_admin/visitas_registradas_admin.component';
import {RegistrarPaqueteriaComponent} from './components/registrar-paqueteria/registrar-paqueteria.component';
import {RegistrarSalidaComponent} from './components/registrar-salida/registrar-salida.component';
import {RegistrarTrabajadorComponent} from './components/registrar_trabajador/registrar_trabajador.component';
import {RegistrarVisitasGuardiaComponent} from './components/registrar_visitas_guardia/registrar_visitas_guardia.component';
import {VisitasAgendadasCondominoComponent} from './components/visitas_agendadas_condomino/visitas_agendadas_condomino.component';
import {AgendarVisitaCondominoComponent} from './components/agendar_visita_condomino/agendar_visita_condomino.component';
import {EncabezadoComponent} from './components/encabezado/encabezado.component';
import {PiePaginaComponent} from './components/pie-pagina/pie-pagina.component';
import {MenuGlobalComponent} from './components/menu-global/menu-global.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptorService} from "./services/auth-interceptor.service";
import {CookieService} from 'ngx-cookie-service';
import {RegistrarCondominoComponent} from './components/registrar_condomino/registrar_condomino.component';
import {UsuariosComponent} from './components/usuarios/usuarios.component';
import {RolePipe} from './pipes/role.pipe';
import { RegistrarGuardiaComponent } from './components/registrar-guardia/registrar-guardia.component';
import { DayPipe } from './pipes/day.pipe';

@NgModule({
  declarations: [
    AppComponent,
    ListaTrabajadoresComponent,
    EntregarPaqueteComponent,
    ReportesComponent,
    Modificacion_CondominoComponent,
    VisitasRegistradasAdminComponent,
    RegistrarPaqueteriaComponent,
    RegistrarSalidaComponent,
    RegistrarTrabajadorComponent,
    RegistrarVisitasGuardiaComponent,
    VisitasAgendadasCondominoComponent,
    AgendarVisitaCondominoComponent,
    EncabezadoComponent,
    PiePaginaComponent,
    MenuGlobalComponent,
    RegistrarCondominoComponent,
    UsuariosComponent,
    RolePipe,
    RegistrarGuardiaComponent,
    DayPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],

  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true},
    CookieService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
