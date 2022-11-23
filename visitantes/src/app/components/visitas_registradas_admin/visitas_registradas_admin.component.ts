import {Component, OnInit} from '@angular/core';
import {VisitantesService} from '../../services/visitantes.service';
import {SessionService} from '../../services/session.service';
import {Visitante} from '../../models/visitante';
import Swal from 'sweetalert2';
import {User} from 'src/app/models/user';

@Component({
  selector: 'app-visitas_registradas_admin',
  templateUrl: './visitas_registradas_admin.component.html',
  styleUrls: ['./visitas_registradas_admin.component.css']
})
export class VisitasRegistradasAdminComponent implements OnInit {

  // datos usados para realizar la busqueda
  name: string | undefined;
  visitante: Visitante = {} as Visitante;
  visitantes: Visitante[] = [];
  page = 0;
  listOfPages: number[] = [];
  id: string | undefined;
  user: User = {} as User;
  isLoading = true;

  constructor(private visitantesService: VisitantesService,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.visitantesService.getVisitantesUnauthorized(this.page).subscribe(visitantes => {
      this.visitantes = visitantes.content;
      const totalOfPages = Math.trunc(visitantes.totalElements / visitantes.size);
      for (let i = 0; i < totalOfPages; i++) {
        this.listOfPages.push(i);
      }
      this.isLoading = false;
    });
    this.sessionService.getUser().subscribe(user => {
      this.user = user;
    });
  }

  updatePage(page: number): void {
    this.isLoading = true;
    this.visitantesService.getVisitantesUnauthorized(page).subscribe(visitantes => {
      this.visitantes = visitantes.content;
      this.isLoading = false;
    });
  }

  modify(visitante: Visitante): void {
    visitante.authorized = true;
    visitante.authorization = this.user.id;

    this.visitantesService.save(visitante).subscribe(v => {
      Swal.fire({
        title: `El visitante ${v.name} fue registrado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
      });
      this.updatePage(this.page);
    });
  }
}
