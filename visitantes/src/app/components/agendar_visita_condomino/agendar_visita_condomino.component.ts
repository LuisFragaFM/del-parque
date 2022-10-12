import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";
import {SessionService} from "../../services/session.service";
import { CondominoInfo } from 'src/app/models/condominoInfo';

@Component({
  selector: 'app-agendar_visita_condomino',
  templateUrl: './agendar_visita_condomino.component.html',
  styleUrls: ['./agendar_visita_condomino.component.css']
})
export class AgendarVisitaCondominoComponent implements OnInit {
  isChecked: boolean = true;
  visitante: Visitante = {} as Visitante;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;

  constructor(private visitantesService: VisitantesService,
              private condominosService: CondominosService,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.visitante.condomino= {} as CondominoInfo;
    this.sessionService.getUser().subscribe(user => {
      this.visitante.authorization = user.name;
    })
  }

  save() {
    console.log(this.visitante);
    
    this.visitantesService.save(this.visitante).subscribe(visitante => {
      Swal.fire({
        title: `La visita de ${visitante.name} fue agendada`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
      });
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

    this.name = condomino.user.name;
    this.visitante.authorization = condomino.user.id;
  }
}
