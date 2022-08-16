import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import Swal from "sweetalert2";

@Component({
  selector: 'app-visita-guardia',
  templateUrl: './visita-guardia.component.html',
  styleUrls: ['./visita-guardia.component.css']
})
export class VisitaGuardiaComponent implements OnInit {
  visitante: Visitante = {} as Visitante;
  constructor(private visitantesService: VisitantesService) { }

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
