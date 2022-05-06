import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgendaCondominioComponent } from './agenda-condominio/agenda-condominio.component';
import { AltasNotificacionesComponent } from './altas-notificaciones/altas-notificaciones.component';
import { BajasComponent } from './bajas/bajas.component';
import { BitacoraTrabajadoresComponent } from './bitacora-trabajadores/bitacora-trabajadores.component';
import { EntregarPaqueteComponent } from './entregar-paquete/entregar-paquete.component';
import { InformesComponent } from './informes/informes.component';
import { LogInComponent } from './log-in/log-in.component';
import { PagosComponent } from './pagos/pagos.component';
import { RegistrarAgendaComponent } from './registrar-agenda/registrar-agenda.component';
import { RegistrarPaqueteriaComponent } from './registrar-paqueteria/registrar-paqueteria.component';
import { RegistrarSalidaComponent } from './registrar-salida/registrar-salida.component';
import { TrabajadorComponent } from './trabajador/trabajador.component';
import { TrabajadorServicioComponent } from './trabajador-servicio/trabajador-servicio.component';
import { VisitaCondominioComponent } from './visita-condominio/visita-condominio.component';
import { VisitaGuardiaComponent } from './visita-guardia/visita-guardia.component';

@NgModule({
  declarations: [
    AppComponent,
    AgendaCondominioComponent,
    AltasNotificacionesComponent,
    BajasComponent,
    BitacoraTrabajadoresComponent,
    EntregarPaqueteComponent,
    InformesComponent,
    LogInComponent,
    PagosComponent,
    RegistrarAgendaComponent,
    RegistrarPaqueteriaComponent,
    RegistrarSalidaComponent,
    TrabajadorComponent,
    TrabajadorServicioComponent,
    VisitaCondominioComponent,
    VisitaGuardiaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
