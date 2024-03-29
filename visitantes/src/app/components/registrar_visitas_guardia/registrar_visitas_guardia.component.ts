import {Component, OnInit} from '@angular/core';
import {VisitantesService} from '../../services/visitantes.service';
import {Visitante} from '../../models/visitante';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import Swal from 'sweetalert2';
import {SessionService} from '../../services/session.service';
import {User} from '../../models/user';
import {validaInput} from 'src/app/tools/validation';
import {CondominoInfo} from '../../models/condominoInfo';
import {formatAMPM} from '../../tools/formatAMPM';
import {formatDate} from '../../tools/formatDate';

@Component({
  selector: 'app-registrar_visitas_guardia',
  templateUrl: './registrar_visitas_guardia.component.html',
  styleUrls: ['./registrar_visitas_guardia.component.css'],
})
export class RegistrarVisitasGuardiaComponent implements OnInit {
  isChecked = true;
  name!: string;
  autoriza!: User;
  visitante: Visitante = {} as Visitante;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  // Variables de activacion del boton de registro
  visitaName = true;
  visitaVehiculo = true;
  visitaTarjeton = true;
  visitaPlacas = true;
  visitaCasilla = true;
  regexName: any = /[\S\s]+\S+/;
  error = false;
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

  findInquilinoByName(): void {
    this.condominosService.findByName(this.name).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  save(): void {
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

  selectInquilino(condomino: Condomino): void {
    this.condomino = condomino;
    this.name = condomino.user.name;
    this.visitante.condomino.userId = condomino.user.id;
    this.visitante.authorization = this.autoriza.id;
    this.condominos = []; 
    this.condomino.user.telephoneNumber = condomino.user.telephoneNumber;
    this.visitante.checkIn = formatAMPM();
    this.visitante.arrivalDate = formatDate();
  }

  // funcion para validacion
  validaNombre(regex: any, name: string): void {
    this.visitaName = validaInput(regex, name);
  }

  validaVehiculo(regex: any, name: string): void {
    this.visitaVehiculo = validaInput(regex, name);
  }

  validaTarjeton(regex: any, name: string): void {
    this.visitaTarjeton = validaInput(regex, name);
  }

  validaPlacas(regex: any, name: string): void {
    this.visitaPlacas = validaInput(regex, name);
  }

  validaCasilla(regex: any, name: string): void {
    this.visitaCasilla = validaInput(regex, name);
  }

  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return (
      this.visitaName &&
      this.visitaTarjeton &&
      this.visitaCasilla
    );
  }
}
