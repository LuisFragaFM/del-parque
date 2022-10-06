import { Component, OnInit } from '@angular/core';
import { PaquetesService } from '../../services/paquetes.service';
import { Paquete } from '../../models/paquete';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import Swal from 'sweetalert2';
import { validaInput } from 'src/app/tools/validation';
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app-registrar-paqueteria',
  templateUrl: './registrar-paqueteria.component.html',
  styleUrls: ['./registrar-paqueteria.component.css'],
})
export class RegistrarPaqueteriaComponent implements OnInit {
  paquete: Paquete = {} as Paquete;
  visitante: Visitante = {} as Visitante;
  numberOfPages: number = 1;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  //Variable para validar nombre y activar boton
  validacionEmpresa: boolean = true;
  validacionGuia: boolean = true;
  validacionRecibo: boolean = true;
  fechaRecibo: boolean = true;
  //Validacion de fechas
  regexName: any = /^[A-Za-z0-9_@./#&+-\s]+$/; //empresa
  regexRecibe: any = /^[A-Za-zÀ-ÿ ,.'-]+$/; //recibe paquete
  regexDate: any =
    /^(?:3[01]|[12][0-9]|0?[1-9])([\-/.])(0?[1-9]|1[1-2])\1\d{4}$/; //fecha

  constructor(
    private paquetesService: PaquetesService,
    private condominosService: CondominosService,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.paquetesService.getPaquetes(0).subscribe((paquetes) => {});
    this.sessionService.getUser().subscribe(user => {
      this.visitante.authorization = user.name;
    })
  }

  findInquilinoByName() {
    if (this.name == '') {
      this.condominos = [];
    }
    this.condominosService.findByName(this.name!).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  save() {
    this.paquetesService.save(this.paquete).subscribe((paquete) => {
      this.paquete = {} as Paquete;
      Swal.fire({
        title: `El paquete para la casa ${paquete.numeroCasa} en la calle ${paquete.calle} fue guardado correctamente`,
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
    this.name = condomino.name;
    this.paquete.nombreCondomino = condomino.name;
    this.paquete.calle = condomino.street;
    this.paquete.numeroCasa = condomino.houseNumber;
    this.visitante.authorization = condomino.name;
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
}
