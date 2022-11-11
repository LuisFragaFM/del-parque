import {Component, OnInit} from '@angular/core';
import {VisitantesService} from '../../services/visitantes.service';
import {Visitante} from '../../models/visitante';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import Swal from 'sweetalert2';
import {SessionService} from '../../services/session.service';
import {User} from '../../models/user';
import {validaInput} from 'src/app/tools/validation';
import {CondominoInfo} from "../../models/condominoInfo";

@Component({
  selector: 'app-registrar_visitas_guardia',
  templateUrl: './registrar_visitas_guardia.component.html',
  styleUrls: ['./registrar_visitas_guardia.component.css'],
})
export class RegistrarVisitasGuardiaComponent implements OnInit {
  isChecked: boolean = true;
  name!: string;
  autoriza!: User;
  visitante: Visitante = {} as Visitante;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  //Variable para validar nombre y activar boton
  visitaName: boolean = true;
  //Validacion de fechas
  regexName: any = /^[A-Za-zÀ-ÿ ,.'-]+$/; //nombre
  error: boolean = false;
  nameAuth!: string;

  constructor(
    private visitantesService: VisitantesService,
    private condominosService: CondominosService,
    private sessionService: SessionService
  ) {
  }

  ngOnInit(): void {
    this.visitante.condomino = {} as CondominoInfo;
    this.sessionService.getUser().subscribe((user) => {
      this.autoriza = user;
      this.nameAuth = this.autoriza.name;
    });
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  save() {
    if (!this.condomino.id) {
      this.error = true;
      return;
    }

    this.visitantesService.save(this.visitante).subscribe((visitante) => {
      Swal.fire({
        title: `La visita de ${visitante.name} fue agendada`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        this.visitante = {} as Visitante;
        this.condomino = {} as Condomino;
        this.visitante.condomino = {} as CondominoInfo;
        this.name = '';
        this.condominos = [];
      });
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.name = condomino.user.name;
    this.visitante.condomino.userId = condomino.user.id;
    this.visitante.authorization = this.autoriza.id;
    this.condominos = [];
    this.visitante.checkIn = this.formatAMPM();
    this.visitante.arrivalDate = this.formatDate();
  }

  formatDate() {
    const d = new Date();
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    let year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

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

  // funcion para validacion
  validaNombre(regex: any, name: string) {
    this.visitaName = validaInput(regex, name);
  }

  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.visitaName;
  }
}
