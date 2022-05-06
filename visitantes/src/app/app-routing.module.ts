import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { VisitaGuardiaComponent } from './visita-guardia/visita-guardia.component';
import { VisitaCondominoComponent } from './visita-condomino/visita-condomino.component';
import { TrabajadorServicioComponent } from './trabajador-servicio/trabajador-servicio.component';
import { TrabajadorComponent } from './trabajador/trabajador.component';
import { RegistrarSalidaComponent } from './registrar-salida/registrar-salida.component';
import { RegistrarPaqueteriaComponent } from './registrar-paqueteria/registrar-paqueteria.component';
import { RegistrarAgendaComponent } from './registrar-agenda/registrar-agenda.component';
import { PagosComponent } from './pagos/pagos.component';
import { InformesComponent } from './informes/informes.component';
import { EntregarPaqueteComponent } from './entregar-paquete/entregar-paquete.component';
import { BitacoraTrabajadoresComponent } from './bitacora-trabajadores/bitacora-trabajadores.component';
import { BajasComponent } from './bajas/bajas.component';
import { AltasModificacionesComponent } from './altas-modificaciones/altas-modificaciones.component';
import { AgendaCondominoComponent } from './agenda-condomino/agenda-condomino.component';

const routes: Routes = [
  {path: 'login', component: LogInComponent},
  {path: 'visita-a-g', component: VisitaGuardiaComponent},
  {path: 'visita-c', component: VisitaCondominoComponent},
  {path: 'trabajador-s', component: TrabajadorServicioComponent},
  {path: 'trabajador', component: TrabajadorComponent},
  {path: 'salida', component: RegistrarSalidaComponent},
  {path: 'registro-paq', component: RegistrarPaqueteriaComponent},
  {path: 'registro-a', component: RegistrarAgendaComponent},
  {path: 'pagos', component: PagosComponent},
  {path: 'informes', component: InformesComponent},
  {path: 'entregar-paq', component: EntregarPaqueteComponent},
  {path: 'bitacora-tra', component: BitacoraTrabajadoresComponent},
  {path: 'bajas', component: BajasComponent},
  {path: 'altas-mod', component: AltasModificacionesComponent},
  {path: 'agenda-cond', component: AgendaCondominoComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }