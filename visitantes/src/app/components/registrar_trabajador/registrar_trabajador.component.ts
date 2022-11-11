import {Component, OnInit} from '@angular/core';
import {Trabajador} from '../../models/trabajador';
import {TrabajadoresService} from '../../services/trabajadores.service';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import {validaInput} from 'src/app/tools/validation';
import Swal from 'sweetalert2';
import {CondominoInfo} from "../../models/condominoInfo";
import {ActivatedRoute} from "@angular/router";
import {WorkDay} from "../../models/WorkDay";

@Component({
  selector: 'app-registrar_trabajador',
  templateUrl: './registrar_trabajador.component.html',
  styleUrls: ['./registrar_trabajador.component.css'],
})
export class RegistrarTrabajadorComponent implements OnInit {
  trabajador: Trabajador = {} as Trabajador;
  workDays: Map<string, WorkDay> = new Map();
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  //Variable para validar nombre y activar boton
  trabajadorName: boolean = true;
  validacionTel: boolean = true;
  //Validaciones
  regexName: any = /[\S\s]+[\S]+/;
  regexTel: any = /^\+?[1-9][0-9]{5,12}$/; //recibe paquete
  id: string = '';

  constructor(
    private trabajadoresService: TrabajadoresService,
    private condominosService: CondominosService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.trabajador.workDays = [];
    this.id = this.route.snapshot.children[0]?.paramMap?.get('id') || '';

    if (this.id) {
      this.trabajadoresService.findById(this.id).subscribe(trabajador => {
        this.trabajador = trabajador;
        trabajador.workDays.forEach(wd => this.workDays.set(wd.dayName, wd));
      })
    }

    this.trabajador.workDays = [];
    this.trabajador.condominoInfo = {} as CondominoInfo;
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.trabajador.condominoInfo.owner).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  save(): void {
    if (!this.trabajador.condominoInfo.userId) {
      return;
    }

    this.trabajador.workDays = [];
    this.workDays.forEach(value => this.trabajador.workDays.push(value))

    this.trabajadoresService.save(this.trabajador).subscribe((trabajador) => {
      Swal.fire({
        title: `El trabajador ${trabajador.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        if (!this.id) {
          this.trabajador = {} as Trabajador;
          this.condomino = {} as Condomino;
          this.name = '';
        }
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

    if (this.workDays.get(day)) {
      this.workDays.delete(day);
    } else {
      const workDay: WorkDay = {dayName: day}
      this.workDays.set(day, workDay);
    }
  }

  hasDay(day: string) {
    return this.trabajador.workDays.find(d => d.dayName === day);
  }

}
