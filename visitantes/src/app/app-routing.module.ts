import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {
  RegistrarVisitasGuardiaComponent
} from './components/registrar_visitas_guardia/registrar_visitas_guardia.component';
import {
  AgendarVisitaCondominoComponent
} from './components/agendar_visita_condomino/agendar_visita_condomino.component';
import {RegistrarTrabajadorComponent} from './components/registrar_trabajador/registrar_trabajador.component';
import {RegistrarSalidaComponent} from './components/registrar-salida/registrar-salida.component';
import {RegistrarPaqueteriaComponent} from './components/registrar-paqueteria/registrar-paqueteria.component';
import {
  VisitasRegistradasAdminComponent
} from './components/visitas_registradas_admin/visitas_registradas_admin.component';
import {Modificacion_CondominoComponent} from './components/modificacion_condomino/modificacion_condomino.component';
import {ReportesComponent} from './components/reportes/reportes.component';
import {EntregarPaqueteComponent} from './components/entregar-paquete/entregar-paquete.component';
import {ListaTrabajadoresComponent} from './components/lista_trabajadores/lista_trabajadores.component';
import {
  VisitasAgendadasCondominoComponent
} from './components/visitas_agendadas_condomino/visitas_agendadas_condomino.component';
import {RegistrarCondominoComponent} from './components/registrar_condomino/registrar_condomino.component';
import {UsuariosComponent} from "./components/usuarios/usuarios.component";
import {RegistrarGuardiaComponent} from './components/registrar-guardia/registrar-guardia.component';

const routes: Routes = [
  {path: 'registrar_visitas', component: RegistrarVisitasGuardiaComponent},
  {
    path: 'agendar_visita_condomino', component: AgendarVisitaCondominoComponent,
    children: [
      {
        path: ':id', component: AgendarVisitaCondominoComponent,
      }
    ]
  },
  {
    path: 'registrar_trabajador', component: RegistrarTrabajadorComponent,
    children: [
      {
        path: ':id', component: RegistrarTrabajadorComponent,
      }
    ]
  },
  {path: 'registrar_salida', component: RegistrarSalidaComponent},
  {path: 'registro-paq', component: RegistrarPaqueteriaComponent},
  {path: 'visitas_registradas', component: VisitasRegistradasAdminComponent},
  {path: 'modificacion_condomino', component: Modificacion_CondominoComponent},
  {path: 'reportes', component: ReportesComponent},
  {path: 'entregar-paq', component: EntregarPaqueteComponent},
  {path: 'lista_trabajadores', component: ListaTrabajadoresComponent},
  {path: 'registrar_condomino', component: RegistrarCondominoComponent},
  {path: 'visitas_agendadas', component: VisitasAgendadasCondominoComponent},
  {path: 'usuarios', component: UsuariosComponent},
  {path: 'registrar_guardia', component: RegistrarGuardiaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
