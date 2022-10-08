import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {VisitaGuardiaComponent} from './components/visita-guardia/visita-guardia.component';
import {VisitaCondominoComponent} from './components/visita-condomino/visita-condomino.component';
import {TrabajadorComponent} from './components/trabajador/trabajador.component';
import {RegistrarSalidaComponent} from './components/registrar-salida/registrar-salida.component';
import {RegistrarPaqueteriaComponent} from './components/registrar-paqueteria/registrar-paqueteria.component';
import {RegistrarAgendaComponent} from './components/registrar-agenda/registrar-agenda.component';
import {Modificacion_CondominoComponent} from './components/modificacion_condomino/modificacion_condomino.component';
import {InformesComponent} from './components/informes/informes.component';
import {EntregarPaqueteComponent} from './components/entregar-paquete/entregar-paquete.component';
import {BitacoraTrabajadoresComponent} from './components/bitacora-trabajadores/bitacora-trabajadores.component';
import {AgendaCondominoComponent} from './components/agenda-condomino/agenda-condomino.component';
import {AltasComponent} from './components/altas/altas.component';
import {UsuariosComponent} from "./components/usuarios/usuarios.component";

const routes: Routes = [
  {path: 'visita-a-g', component: VisitaGuardiaComponent},
  {path: 'visita-c', component: VisitaCondominoComponent},
  {path: 'trabajador', component: TrabajadorComponent},
  {path: 'salida', component: RegistrarSalidaComponent},
  {path: 'registro-paq', component: RegistrarPaqueteriaComponent},
  {path: 'registro-a', component: RegistrarAgendaComponent},
  {path: 'modificacion_condomino', component: Modificacion_CondominoComponent},
  {path: 'informes', component: InformesComponent},
  {path: 'entregar-paq', component: EntregarPaqueteComponent},
  {path: 'bitacora-tra', component: BitacoraTrabajadoresComponent},
  {path: 'altas', component: AltasComponent},
  {path: 'agenda-cond', component: AgendaCondominoComponent},
  {path: 'usuarios', component: UsuariosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
