import {Component, OnInit} from '@angular/core';
import {PaquetesService} from '../../services/paquetes.service';
import {Paquete} from '../../models/paquete';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import Swal from 'sweetalert2';
import {validaInput} from 'src/app/tools/validation';
import {SessionService} from '../../services/session.service';
import {Visitante} from '../../models/visitante';
import {CondominoInfo} from '../../models/condominoInfo';
import {User} from 'src/app/models/user';
import {formatAMPM} from '../../tools/formatAMPM';
import {formatDate} from '../../tools/formatDate';

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
  validacionEmpresa = true;
  validacionGuia = true;
  validacionRecibo = true;
  fechaRecibo = true;
  regexName: any = /[\S\s]+[\S]+/;
  error = false;
  nameAuth!: string;

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

  findInquilinoByName(): void {
    this.condominos = [];
      this.condominosService.findByName(this.name).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  save(): void {
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
        this.condomino = {} as Condomino;
        this.condomino.user = {} as User;
        this.paquete.condomino = {} as CondominoInfo;
        this.name = '';
        this.nameAuth = '';
        this.condominos = [];
      });
    });
  }

  selectInquilino(condomino: Condomino): void {
    this.condomino = condomino;
    this.condominos = [];
    this.condomino.relatives = condomino.relatives; 
    this.name = condomino.user.name;
    this.paquete.condomino.userId = this.condomino.id;
    this.paquete.condomino.houseStreet = condomino.street;
    this.paquete.condomino.houseNumber = condomino.houseNumber;
    this.paquete.arrivalTime = formatAMPM();
    this.paquete.arrivalDate = formatDate();
  }

  // funcion para validacion
  validaName(regex: any, nombreRecibo: string): void {
    this.validacionEmpresa = validaInput(regex, nombreRecibo);
  }

  validaGuia(regex: any, guiaRecibo: string): void {
    this.validacionGuia = validaInput(regex, guiaRecibo);
  }

  validaReci(regex: any, residenteRecibe: string): void {
    this.validacionRecibo = validaInput(regex, residenteRecibe);
  }

  validaFecha(regex: any, fechaRecibe: string): void {
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
}
