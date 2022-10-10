import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";
import {SessionService} from "../../services/session.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-registrar_visitas_guardia',
  templateUrl: './registrar_visitas_guardia.component.html',
  styleUrls: ['./registrar_visitas_guardia.component.css']
})
export class RegistrarVisitasGuardiaComponent implements OnInit {
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

    this.visitante.condominoId = this.condomino.id;
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
    this.visitante.checkIn= this.formatAMPM();
    this.visitante.arrivalDate=this.formatDate();

  }

  formatDate() {
    const d = new Date()
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    let year = d.getFullYear();

    if (month.length < 2)
      month = '0' + month;
    if (day.length < 2)
      day = '0' + day;

    return [year, month, day].join('-');
  }

  formatAMPM() {
    const date = new Date();
    let hours = date.getHours();
    let minutes: number | string = date.getMinutes();
    let ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0' + minutes : minutes;
    return hours + ':' + minutes + ' ' + ampm;
  }
}
