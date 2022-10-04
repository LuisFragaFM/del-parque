import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {SessionService} from "../../services/session.service";
import {Visitante} from "../../models/visitante";
import Swal from "sweetalert2";
import {User} from 'src/app/models/user';

@Component({
  selector: 'app-registrar-agenda',
  templateUrl: './registrar-agenda.component.html',
  styleUrls: ['./registrar-agenda.component.css']
})
export class RegistrarAgendaComponent implements OnInit {

  // datos usados para realizar la busqueda
  name: string | undefined;
  visitante: Visitante = {} as Visitante;
  visitantes: Visitante[] = [];
  page: number = 0;
  listOfPages: number[] = [];
  id: string | undefined;
  user: User = {} as User;

  constructor(private visitantesService: VisitantesService,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.visitantesService.getVisitantesUnauthorized(1).subscribe((visitantes) => {
      this.visitantes = visitantes.content;
    });

    this.visitantesService.getVisitantesUnauthorized(this.page).subscribe(visitantes => {
      this.visitantes = visitantes.content;
      const totalOfPages = 10 //Math.trunc(trabajadores.totalElements / trabajadores.size);
      for (let i = 0; i < totalOfPages; i++) {
        this.listOfPages.push(i + 1);
      }
    })
    this.sessionService.getUser().subscribe(user => {
      this.user = user;
    })
  }

  updatePage(page: number) {
    this.page = page - 1;
    this.visitantesService.getVisitantesUnauthorized(this.page).subscribe(visitantes => {
      this.visitantes = visitantes.content;
    })
  }

  modify(visitante: Visitante) {
    visitante.authorized = true;
    visitante.authorization = this.user.name;

    this.visitantesService.save(visitante).subscribe(visitante => {
      Swal.fire({
        title: `El visitante ${visitante.name} fue registrado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
      });
      this.updatePage(this.page);
    })
  }

  delete() {
    if (this.visitante.id) {

      Swal.fire({
        title: `Estas seguro que deseas borrar este condomino?`,
        icon: 'warning',
        showDenyButton: true,
        confirmButtonText: `Si`,
        cancelButtonText: `No`
      }).then((value) => {
        if (value.isConfirmed) {
          this.visitantesService.delete(this.visitante.id).subscribe(() => {
            Swal.fire({
              title: `El visitante de ${this.visitante.name} fue borrado correctamente`,
              icon: 'success',
              showDenyButton: false,
              showCancelButton: false,
              confirmButtonText: `Cerrar`
            }).then(() => {
              this.visitante = {} as Visitante;

            });
          });
        }

      });

    }
  }

}
