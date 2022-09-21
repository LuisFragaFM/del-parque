import {Component, OnInit} from '@angular/core';
import {Trabajador} from "../../models/trabajador";
import {TrabajadoresService} from "../../services/trabajadores.service";
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";

@Component({
  selector: 'app-trabajador',
  templateUrl: './trabajador.component.html',
  styleUrls: ['./trabajador.component.css']
})
export class TrabajadorComponent implements OnInit {
  trabajador: Trabajador = {} as Trabajador;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  constructor(private trabajadoresService: TrabajadoresService, private condominosService: CondominosService) {
  }
  ngOnInit(): void {
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      console.log(condominos);
      this.condominos = condominos;
    });
  }

  save(): void {
    this.trabajadoresService.save(this.trabajador).subscribe(trabajador => {
      Swal.fire({
        title: `El trabajador ${trabajador.nombreTrabajador} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
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
}