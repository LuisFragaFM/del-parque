// Importa los modulos y componentes necesarios para el funcionamiento del programa
import {NgModule} from '@angular/core';
// Importa las rutas de los componentes
import {RouterModule, Routes} from '@angular/router';
// Importa los componentes  del sistema
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
import {UsuariosComponent} from './components/usuarios/usuarios.component';
import {RegistrarGuardiaComponent} from './components/registrar-guardia/registrar-guardia.component';
import {RoleAdminGuard} from './guards/role-admin.guard';
import {RoleResidentGuard} from './guards/role-resident.guard';
import {RoleGuardGuard} from './guards/role-guard.guard';
// Asigna los permisos para que los usuarios puedan acceder a sus respectivas pantallas
const routes: Routes = [
  {
    path: 'registrar_visitas', component: RegistrarVisitasGuardiaComponent,
    canLoad: [RoleGuardGuard] //Rol guardia
  },
  {
    path: 'agendar_visita_condomino', component: AgendarVisitaCondominoComponent,
    children: [
      {
        path: ':id', component: AgendarVisitaCondominoComponent,
      }
    ],
    canLoad: [RoleResidentGuard] //Rol residente y guardia
  },
  {
    path: 'registrar_trabajador', component: RegistrarTrabajadorComponent,
    children: [
      {
        path: ':id', component: RegistrarTrabajadorComponent,
      }
    ],
    canLoad: [RoleGuardGuard] //Rol guardia
  },
  {
    path: 'registrar_salida', component: RegistrarSalidaComponent,
    canLoad: [RoleGuardGuard] //Rol guardia
  },
  {
    path: 'registro-paq', component: RegistrarPaqueteriaComponent,
    canLoad: [RoleGuardGuard] //Rol guardia
  },
  {
    path: 'visitas_registradas', component: VisitasRegistradasAdminComponent,
    canLoad: [RoleGuardGuard] //Rol guardia
  },
  {
    path: 'modificacion_condomino', component: Modificacion_CondominoComponent,
    children: [
      {
        path: ':id', component: RegistrarTrabajadorComponent,
      }
    ],
    canLoad: [RoleAdminGuard] //Rol guardia y administrador
  },
  {
    path: 'reportes', component: ReportesComponent,
    canLoad: [RoleAdminGuard]//Rol guardia y administrador
  },
  {
    path: 'entregar-paq', component: EntregarPaqueteComponent,
    canLoad: [RoleGuardGuard]//Rol guardia 
  },
  {
    path: 'lista_trabajadores', component: ListaTrabajadoresComponent,
    canLoad: [RoleAdminGuard]//Rol guardia y administrador
  },
  {
    path: 'registrar_condomino', component: RegistrarCondominoComponent,
    canLoad: [RoleAdminGuard]//Rol guardia y administrador
  },
  {
    path: 'visitas_agendadas', component: VisitasAgendadasCondominoComponent,
    canLoad: [RoleResidentGuard]//Rol residente y administrador
  },
  {
    path: 'usuarios', component: UsuariosComponent,
    canLoad: [RoleGuardGuard] //Rol guardia
  },
  {
    path: 'registrar_guardia', component: RegistrarGuardiaComponent,
    canLoad: [RoleAdminGuard] //Rol guardia y administrador
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
