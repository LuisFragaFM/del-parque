import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import Swal from "sweetalert2";

@Component({
  selector: 'app-visitas_agendadas_condomino',
  templateUrl: './visitas_agendadas_condomino.component.html',
  styleUrls: ['./visitas_agendadas_condomino.component.css']
})
export class VisitasAgendadasCondominoComponent implements OnInit {

  // datos usados para realizar la busqueda
  name: string | undefined;
  visitante: Visitante = {} as Visitante;
  visitantes: Visitante[] = [];
  page: number = 0;
  listOfPages: number[] = [];

  constructor(private visitantesService: VisitantesService) {
  }

  ngOnInit(): void {
    //Paginacion
    this.visitantesService.getVisitantesUnauthorized(0).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
    });

  }

  // busqueda de visitante
  findVisitante() {
    if (this.name) {
      this.visitantesService.findByName(this.name).subscribe(visitante => {
        console.log(visitante);
        this.visitante = visitante;
      });
    } else {
      this.visitantesService.findByName(this.name!).subscribe(visitante => {
        this.visitante = visitante;
      });
    }

  }

  updatePage(page: number) {
    this.page = page - 1;
    this.visitantesService.getVisitantesUnauthorized(this.page).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
    })
  }

  delete(id: string) {
    Swal.fire({
      title: `Estas seguro que quieres eliminar esta visita?`,
      icon: 'warning',
      showDenyButton: true,
      showCancelButton: false,
      confirmButtonText: `Si`,
      denyButtonText: 'No'
    }).then((response) => {
      if (response.isConfirmed) {
        this.visitantesService.delete(id).subscribe(() => {
          Swal.fire({
            title: `Eliminado correctamente`,
            icon: 'success',
            showDenyButton: false,
            showCancelButton: false,
          }).then(() => {
            this.visitantesService.getVisitantesUnauthorized(0).subscribe(({content: visitantes}) => {
              this.visitantes = visitantes;
            });
          });
        });
      }
    });
  }
}
