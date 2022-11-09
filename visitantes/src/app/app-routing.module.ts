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
import {RoleGuardGuard} from "./guards/role-guard.guard";
import {RoleAdminGuard} from "./guards/role-admin.guard";
import {RoleResidentGuard} from "./guards/role-resident.guard";

const routes: Routes = [
  {
    path: 'registrar_visitas', component: RegistrarVisitasGuardiaComponent,
    canActivate: [RoleGuardGuard, RoleAdminGuard]
  },
  {
    path: 'agendar_visita_condomino', component: AgendarVisitaCondominoComponent,
    children: [
      {
        path: ':id', component: AgendarVisitaCondominoComponent,
      }
    ],
    canActivate: [RoleResidentGuard]
  },
  {
    path: 'registrar_trabajador', component: RegistrarTrabajadorComponent,
    children: [
      {
        path: ':id', component: RegistrarTrabajadorComponent,
      }
    ],
    canActivate: [RoleGuardGuard, RoleAdminGuard]
  },
  {
    path: 'registrar_salida', component: RegistrarSalidaComponent,
    canActivate: [RoleGuardGuard, RoleAdminGuard]
  },
  {
    path: 'registro-paq', component: RegistrarPaqueteriaComponent,
    canActivate: [RoleGuardGuard, RoleAdminGuard]
  },
  {
    path: 'visitas_registradas', component: VisitasRegistradasAdminComponent,
    canActivate: [RoleGuardGuard, RoleAdminGuard]
  },
  {
    path: 'modificacion_condomino', component: Modificacion_CondominoComponent,
    canActivate: [RoleAdminGuard]
  },
  {
    path: 'reportes', component: ReportesComponent,
    canActivate: [RoleAdminGuard]
  },
  {
    path: 'entregar-paq', component: EntregarPaqueteComponent,
    canActivate: [RoleGuardGuard, RoleAdminGuard]
  },
  {
    path: 'lista_trabajadores', component: ListaTrabajadoresComponent,
    canActivate: [RoleAdminGuard]
  },
  {
    path: 'registrar_condomino', component: RegistrarCondominoComponent,
    canActivate: [RoleAdminGuard]
  },
  {
    path: 'visitas_agendadas', component: VisitasAgendadasCondominoComponent,
    canActivate: [RoleResidentGuard]
  },
  {
    path: 'usuarios', component: UsuariosComponent,
    canActivate: [RoleGuardGuard]
  },
  {
    path: 'registrar_guardia', component: RegistrarGuardiaComponent,
    canActivate: [RoleAdminGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
