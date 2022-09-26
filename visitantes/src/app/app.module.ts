import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BajasComponent} from './components/bajas/bajas.component';
import {BitacoraTrabajadoresComponent} from './components/bitacora-trabajadores/bitacora-trabajadores.component';
import {EntregarPaqueteComponent} from './components/entregar-paquete/entregar-paquete.component';
import {InformesComponent} from './components/informes/informes.component';
import {PagosComponent} from './components/pagos/pagos.component';
import {RegistrarAgendaComponent} from './components/registrar-agenda/registrar-agenda.component';
import {RegistrarPaqueteriaComponent} from './components/registrar-paqueteria/registrar-paqueteria.component';
import {RegistrarSalidaComponent} from './components/registrar-salida/registrar-salida.component';
import {TrabajadorComponent} from './components/trabajador/trabajador.component';
import {VisitaGuardiaComponent} from './components/visita-guardia/visita-guardia.component';
import {AltasModificacionesComponent} from './components/altas-modificaciones/altas-modificaciones.component';
import {AgendaCondominoComponent} from './components/agenda-condomino/agenda-condomino.component';
import {VisitaCondominoComponent} from './components/visita-condomino/visita-condomino.component';
import {EncabezadoComponent} from './components/encabezado/encabezado.component';
import {PiePaginaComponent} from './components/pie-pagina/pie-pagina.component';
import {MenuGlobalComponent} from './components/menu-global/menu-global.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptorService} from "./services/auth-interceptor.service";
import {CookieService} from 'ngx-cookie-service';
import {ModificacionesComponent} from './components/modificaciones/modificaciones.component';
import {AltasComponent} from './components/altas/altas.component';
import {UsuariosComponent} from './components/usuarios/usuarios.component';
import {RolePipe} from './pipes/role.pipe';

@NgModule({
  declarations: [
    AppComponent,
    BajasComponent,
    BitacoraTrabajadoresComponent,
    EntregarPaqueteComponent,
    InformesComponent,
    PagosComponent,
    RegistrarAgendaComponent,
    RegistrarPaqueteriaComponent,
    RegistrarSalidaComponent,
    TrabajadorComponent,
    VisitaGuardiaComponent,
    AltasModificacionesComponent,
    AgendaCondominoComponent,
    VisitaCondominoComponent,
    EncabezadoComponent,
    PiePaginaComponent,
    MenuGlobalComponent,
    ModificacionesComponent,
    AltasComponent,
    UsuariosComponent,
    RolePipe,
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
