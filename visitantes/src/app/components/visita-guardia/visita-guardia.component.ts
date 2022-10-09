import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";
import {SessionService} from "../../services/session.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-visita-guardia',
  templateUrl: './visita-guardia.component.html',
  styleUrls: ['./visita-guardia.component.css']
})
export class VisitaGuardiaComponent implements OnInit {
  isChecked: boolean = true;
  name!: string;
  autoriza!: User;
  visitante: Visitante = {} as Visitante;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;

  constructor(private visitantesService: VisitantesService,
              private condominosService: CondominosService,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.sessionService.getUser().subscribe(user => {
      this.autoriza = user;
    })

  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      this.condominos = condominos;
    });
  }

  save() {
    if (!this.condomino.id) {
      return;
    }

    this.visitantesService.save(this.visitante).subscribe(visitante => {
      Swal.fire({
        title: `La visita de ${visitante.name} fue agendada`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        this.visitante = {} as Visitante;
      });
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.name = condomino.name;
    this.visitante.condominoId = condomino.id;
    this.visitante.authorization = this.autoriza.id;
    this.condominos = [];
  }
}
