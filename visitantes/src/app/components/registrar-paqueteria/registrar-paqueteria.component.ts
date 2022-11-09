import {Component, OnInit} from '@angular/core';
import {PaquetesService} from '../../services/paquetes.service';
import {Paquete} from '../../models/paquete';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import Swal from 'sweetalert2';
import {validaInput} from 'src/app/tools/validation';
import {SessionService} from '../../services/session.service';
import {Visitante} from '../../models/visitante';
import {CondominoInfo} from "../../models/condominoInfo";

@Component({
  selector: 'app-registrar-paqueteria',
  templateUrl: './registrar-paqueteria.component.html',
  styleUrls: ['./registrar-paqueteria.component.css'],
})
export class RegistrarPaqueteriaComponent implements OnInit {
  paquete: Paquete = {} as Paquete;
  visitante: Visitante = {} as Visitante;
  name!: string;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  //Variable para validar nombre y activar boton
  validacionEmpresa: boolean = true;
  validacionGuia: boolean = true;
  validacionRecibo: boolean = true;
  fechaRecibo: boolean = true;
  //Validaciones
  regexName: any = /[\S\s]+[\S]+/; //empresa
  error: boolean = false;

  constructor(
    private paquetesService: PaquetesService,
    private condominosService: CondominosService,
    private sessionService: SessionService
  ) {
  }

  ngOnInit(): void {
    this.paquete.condomino = {} as CondominoInfo;
    this.sessionService.getUser().subscribe((user) => {
      this.visitante.authorization = user.name;
    });
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  save() {
    if (!this.paquete.condomino.userId) {
      this.error = true;
      return;
    }
    this.paquetesService.save(this.paquete).subscribe(() => {
      Swal.fire({
        title: `El paquete para la casa ${this.paquete.condomino.houseNumber} en la calle ${this.paquete.condomino.houseStreet} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        this.paquete = {} as Paquete;
      });
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
    this.name = condomino.user.name;
    this.paquete.condomino.userId = this.condomino.id;
    this.paquete.condomino.houseStreet = condomino.street;
    this.paquete.condomino.houseNumber = condomino.houseNumber;
  }

  // funcion para validacion
  validaName(regex: any, nombreRecibo: string) {
    this.validacionEmpresa = validaInput(regex, nombreRecibo);
  }

  validaGuia(regex: any, guiaRecibo: string) {
    this.validacionGuia = validaInput(regex, guiaRecibo);
  }

  validaReci(regex: any, residenteRecibe: string) {
    this.validacionRecibo = validaInput(regex, residenteRecibe);
  }

  validaFecha(regex: any, fechaRecibe: string) {
    this.fechaRecibo = validaInput(regex, fechaRecibe);
  }

  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return (
      this.validacionEmpresa &&
      this.validacionGuia &&
      this.validacionRecibo &&
      this.fechaRecibo
    );
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
}
