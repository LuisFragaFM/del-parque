import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";

@Component({
  selector: 'app-visita-guardia',
  templateUrl: './visita-guardia.component.html',
  styleUrls: ['./visita-guardia.component.css']
})
export class VisitaGuardiaComponent implements OnInit {
  name: string | undefined;
  visitante: Visitante = {} as Visitante;
  condomino: Condomino = {} as Condomino;
  constructor(private visitantesService: VisitantesService , private condominosService: CondominosService) { }

  ngOnInit(): void {
  }

  findInquilino() {

    
    if (this.name) {
      this.condominosService.findByNombre(this.name!).subscribe(condomino => {
        this.condomino = condomino;
        

      });
    }
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
