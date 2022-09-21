import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";

@Component({
  selector: 'app-visita-condomino',
  templateUrl: './visita-condomino.component.html',
  styleUrls: ['./visita-condomino.component.css']
})
export class VisitaCondominoComponent implements OnInit {
  isChecked: boolean = true;
  visitante: Visitante = {} as Visitante;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  constructor(private visitantesService: VisitantesService, private condominosService: CondominosService) {
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

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      console.log(condominos);
      this.condominos = condominos;
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
    this.name = condomino.name;
    this.visitante.autorizacion = condomino.name;
  }
}