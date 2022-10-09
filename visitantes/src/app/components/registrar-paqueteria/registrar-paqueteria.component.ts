import { Component, OnInit } from '@angular/core';
import { PaquetesService } from '../../services/paquetes.service';
import { Paquete } from '../../models/paquete';
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import Swal from 'sweetalert2';
import { validaInput } from 'src/app/tools/validation';

@Component({
  selector: 'app-registrar-paqueteria',
  templateUrl: './registrar-paqueteria.component.html',
  styleUrls: ['./registrar-paqueteria.component.css'],
})
export class RegistrarPaqueteriaComponent implements OnInit {
  paquete: Paquete = {} as Paquete;
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
    private condominosService: CondominosService
  ) {}

  ngOnInit(): void {
    this.paquetesService.getPaquetes(0).subscribe(() => {});
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
    this.paquete.condominoId = this.condomino.id;
    this.paquetesService.save(this.paquete).subscribe((paquete) => {
      Swal.fire({
        title: `El paquete para la casa ${this.paquete.houseNumber} en la calle ${this.paquete.houseStreet} fue guardado correctamente`,
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
    console.log(condomino)
    this.paquete.houseStreet = condomino.street;
    this.paquete.houseNumber = condomino.houseNumber;
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
