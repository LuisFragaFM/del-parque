import { Component, OnInit } from '@angular/core';
import { Trabajador } from '../../models/trabajador';
import { TrabajadoresService } from '../../services/trabajadores.service';
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-trabajador',
  templateUrl: './trabajador.component.html',
  styleUrls: ['./trabajador.component.css'],
})
export class TrabajadorComponent implements OnInit {
  trabajador: Trabajador = {} as Trabajador;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  //validaciones para activar boton
  validacionTel: boolean = false; //variable para validar telefono
  validacionName: boolean = false; //variable para validar nombre
  //variable para activar boton
  startName: boolean = false;
  startTel: boolean = false;

  constructor(
    private trabajadoresService: TrabajadoresService,
    private condominosService: CondominosService
  ) {}
  ngOnInit(): void {}
  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe((condominos) => {
      console.log(condominos);
      this.condominos = condominos;
    });
  }

  save(): void {
    this.trabajadoresService.save(this.trabajador).subscribe((trabajador) => {
      Swal.fire({
        title: `El trabajador ${trabajador.nombreTrabajador} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        this.trabajador = {} as Trabajador;
      });
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
    this.name = condomino.name;
    this.trabajador.nombreCondomino = condomino.name;
  }
  // funcion para validar datos de telefono
  validaTel(telefono: string, tel: any) {
    this.startTel = true;
    const regTel = new RegExp(/^\+?[1-9][0-9]{7,14}$/); //Expresion regular a validar
    const testTel = regTel.test(telefono); //validar telefono
    const alert1 = document.getElementById('alertTel'); //Obtiene el elemento para mostrar la alerta
    if (testTel == false) {
      alert1?.classList.remove('hiddenAlert'); //oculta alerta
      this.validacionTel = false;
    } else {
      alert1?.classList.add('hiddenAlert'); //muestra alerta
      this.validacionTel = true;
    }
  }
  // funcion para validar datos de nombre
  validaName(nombre: string) {
    this.startName = true;
    const regName = new RegExp(/^[A-Za-zÀ-ÿ ,.'-]+$/); //Expresion regular a validar
    const testName = regName.test(nombre); //validar
    const alertVal = document.getElementById('alertName'); //Obtiene el elemento para mostrar la alerta
    if (testName == false) {
      alertVal?.classList.remove('hiddenAlertName'); //oculta alerta
      this.validacionName = false;
    } else {
      alertVal?.classList.add('hiddenAlertName'); //muestra alerta
      this.validacionName = true;
    }
  }
  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.validacionTel && this.validacionName;
  }
}
