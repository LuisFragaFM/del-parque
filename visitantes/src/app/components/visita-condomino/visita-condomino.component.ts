import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import Swal from "sweetalert2";

@Component({
  selector: 'app-visita-condomino',
  templateUrl: './visita-condomino.component.html',
  styleUrls: ['./visita-condomino.component.css']
})
export class VisitaCondominoComponent implements OnInit {
  visitante: Visitante = {} as Visitante;
  constructor(private visitantesService: VisitantesService) {
  }
  ngOnInit(): void {
  }

  save() {
    this.visitantesService.save(this.visitante).subscribe(visitante => {
      Swal.fire({
        title: `La visita de ${visitante.nombreVisitante} fue agendada`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {});
    });
  }
}