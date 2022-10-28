import { Component, OnInit } from '@angular/core';
import { Trabajador } from '../../models/trabajador';
import { TrabajadoresService } from '../../services/trabajadores.service';
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import { validaInput } from 'src/app/tools/validation';
import Swal from 'sweetalert2';
import { UserView } from 'src/app/models/userview';
import {CondominoInfo} from "../../models/condominoInfo";

@Component({
  selector: 'app-registrar_trabajador',
  templateUrl: './registrar_trabajador.component.html',
  styleUrls: ['./registrar_trabajador.component.css'],
})
export class RegistrarTrabajadorComponent implements OnInit {
  trabajador: Trabajador = {} as Trabajador;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  //Variable para validar nombre y activar boton
  trabajadorName: boolean = true;
  validacionTel: boolean = true;
  //Validacion de fechas
  regexName: any = /^[A-Za-zÀ-ÿ ,.'-]+$/; //nombre
  regexTel: any = /^\+?[1-9][0-9]{5,12}$/; //recibe paquete

  constructor(
    private trabajadoresService: TrabajadoresService,
    private condominosService: CondominosService
  ) {}

  ngOnInit(): void {
    this.trabajador.workDays = [];
    this.trabajador.condominoInfo = {} as CondominoInfo;
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe((condominos) => {
      console.log(condominos);
      this.condominos = condominos;
    });
  }

  save(): void {
    if(!this.trabajador.condominoInfo.userId) {
      return;
    }

    this.trabajadoresService.save(this.trabajador).subscribe((trabajador) => {
      Swal.fire({
        title: `El trabajador ${trabajador.name} fue guardado correctamente`,
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
    this.name = condomino.user.name;
    this.trabajador.condominoInfo.userId = condomino.id;
  }

  // funcion para validacion
  validaTrabajador(regex: any, name: string) {
    this.trabajadorName = validaInput(regex, name);
  }

  validaTel(regex: any, trabajadorTel: string) {
    this.validacionTel = validaInput(regex, trabajadorTel);
  }

  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.trabajadorName && this.validacionTel;
  }

  addDay(day: string) {
    this.trabajador.workDays.push(day);
  }
}
