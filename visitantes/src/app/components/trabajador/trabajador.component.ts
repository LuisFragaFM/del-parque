import {Component, OnInit} from '@angular/core';
import {Trabajador} from "../../models/trabajador";
import {TrabajadoresService} from "../../services/trabajadores.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-trabajador',
  templateUrl: './trabajador.component.html',
  styleUrls: ['./trabajador.component.css']
})
export class TrabajadorComponent implements OnInit {
  trabajador: Trabajador = {} as Trabajador;

  constructor(private trabajadoresService: TrabajadoresService) {
  }

  ngOnInit(): void {
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

}
