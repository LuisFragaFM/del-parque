import {Component, OnInit} from '@angular/core';
import {VisitantesService} from '../../services/visitantes.service';
import {Visitante} from '../../models/visitante';
import Swal from 'sweetalert2';

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
  page = 0;
  listOfPages: number[] = [];

  constructor(private visitantesService: VisitantesService) {
  }

  ngOnInit(): void {
    this.visitantesService.getVisitantesUnauthorizedCondomino(0).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
    });

  }

  updatePage(page: number): void {
    this.visitantesService.getVisitantesUnauthorizedCondomino(page).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
    });
  }

  delete(id: string): void {
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
            this.visitantesService.getVisitantesUnauthorizedCondomino(0).subscribe(({content: visitantes}) => {
              this.visitantes = visitantes;
            });
          });
        });
      }
    });
  }
}
