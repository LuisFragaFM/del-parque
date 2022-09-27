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
  startGuia: boolean = false;
  startRecibo: boolean = false;
  //variable para validar nombre y activar boton
  validacionEmpresa: boolean = false;
  validacionGuia: boolean = false;
  validacionRecibo: boolean = false;
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
    const regEmpresa = new RegExp(/^[A-Za-z0-9\s]+$/); //Expresion regular a validar
    const testEmpresa = regEmpresa.test(empresa); //validar
    const alertValEmpresa = document.getElementById('alertMail'); //Obtiene el elemento para mostrar la alerta
    if (testEmpresa == false) {
      alertValEmpresa?.classList.remove('hiddenAlertName'); //oculta alerta
      this.validacionEmpresa = false;
      this.startEmpresa = true;
      //
      console.log(this.validacionEmpresa);
    } else {
      alertValEmpresa?.classList.add('hiddenAlertName'); //muestra alerta
      this.validacionEmpresa = true;
      this.startEmpresa = false;
      //
      console.log(this.validacionEmpresa);
    }
  }
  // funcion para validar datos de la guia
  validaGuia(guia: string) {
    this.startGuia = true;
    const regGuia = new RegExp(/^[A-Za-z0-9\s]+$/); //Expresion regular a validar
    const testGuia = regGuia.test(guia); //validar
    const alertValGuia = document.getElementById('alertGuia'); //Obtiene el elemento para mostrar la alerta
    if (testGuia == false) {
      alertValGuia?.classList.remove('hiddenAlertName'); //oculta alerta
      this.validacionGuia = false;
      this.startGuia = true;
      //
      console.log(this.validacionGuia);
    } else {
      alertValGuia?.classList.add('hiddenAlertName'); //muestra alerta
      this.validacionGuia = true;
      this.startGuia = false;
      //
      console.log(this.validacionGuia);
    }
  }
  // funcion para validar el nombre de recibido
  validaRecibo(nombreRecibo: string) {
    this.startRecibo = true;
    const regRecibo = new RegExp(/^[A-Za-zÀ-ÿ ,.'-]+$/); //Expresion regular a validar
    const testRecibo = regRecibo.test(nombreRecibo); //validar
    const alertValRecibo = document.getElementById('alertRecibo'); //Obtiene el elemento para mostrar la alerta
    if (testRecibo == false) {
      alertValRecibo?.classList.remove('hiddenAlertName'); //oculta alerta
      this.validacionRecibo = false;
      this.startRecibo = true;
      // 
      console.log(this.validacionRecibo);
    } else {
      alertValRecibo?.classList.add('hiddenAlertName'); //muestra alerta
      this.validacionRecibo = true;
      this.startRecibo = false;
      // 
      console.log(this.validacionRecibo);
    }
  }
  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return (
      this.validacionEmpresa && this.validacionGuia && this.validacionRecibo
    );
  }
}
