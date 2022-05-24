import { Component, OnInit } from '@angular/core';
import {TrabajadoresService} from "../../services/trabajadores.service";

@Component({
  selector: 'app-trabajador-servicio',
  templateUrl: './trabajador-servicio.component.html',
  styleUrls: ['./trabajador-servicio.component.css']
})
export class TrabajadorServicioComponent implements OnInit {

  constructor(private trabajadoresService: TrabajadoresService) { }

  ngOnInit(): void {
    this.trabajadoresService.getTrabajadores().subscribe(console.log)
  }

}
