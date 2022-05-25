import {Component, OnInit} from '@angular/core';
import {Trabajador} from "../../models/trabajador";
import {TrabajadoresService} from "../../services/trabajadores.service";

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
      this.trabajador = {} as Trabajador;
      alert('Trabajador guardado corractamente');
    });
  }

}
