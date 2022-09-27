import { Component, OnInit } from '@angular/core';
import { PaquetesService } from '../../services/paquetes.service';
import { Paquete } from '../../models/paquete';
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import Swal from 'sweetalert2';

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
    //variables a utilizar en la validacion de datos
    startEmpresa: boolean = false;
    //variable para validar nombre y activar boton
    validacionEmpresa: boolean = false;
  constructor(
    private paquetesService: PaquetesService,
    private condominosService: CondominosService
  ) {}

  ngOnInit(): void {
    this.paquetesService.getPaquetes(0).subscribe((paquetes) => {});
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
    this.paquete.calle = condomino.calle;
    this.paquete.numeroCasa = condomino.numeroCasa;
  }
  // funcion para validar datos de nombre
  validaEmpresa(empresa: string) {
    this.startEmpresa = true;
    const regMail = new RegExp(/^[A-Za-zÀ-ÿ ,.'-][1-9][0-9]+$/); //Expresion regular a validar
    const testMail = regMail.test(empresa); //validar
    const alertValMail = document.getElementById('alertMail'); //Obtiene el elemento para mostrar la alerta
    if (testMail == false) {
      alertValMail?.classList.remove('hiddenAlertName'); //oculta alerta
      this.validacionEmpresa = false;
    } else {
      alertValMail?.classList.add('hiddenAlertName'); //muestra alerta
      this.validacionEmpresa= true;
    }
  }
  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.validacionEmpresa;
  }
}
