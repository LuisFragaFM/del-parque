import { Component, OnInit } from '@angular/core';
// imports para realizar la busqueda y notificaciones
import {TrabajadoresService} from "../../services/trabajadores.service";
import {Trabajador} from "../../models/trabajador";
import Swal from "sweetalert2";
@Component({
  selector: 'app-bitacora-trabajadores',
  templateUrl: './bitacora-trabajadores.component.html',
  styleUrls: ['./bitacora-trabajadores.component.css']
})
export class BitacoraTrabajadoresComponent implements OnInit {
  // datos usados para realizar la busqueda
  name: string | undefined;
  trabajador: Trabajador = {} as Trabajador;
  trabajadores: Trabajador[] = [];
  constructor(private trabajadoresService: TrabajadoresService){ }

  ngOnInit(): void {
    //Paginacion
    this.trabajadoresService.getTrabajadores(1).subscribe((trabajadores)=>{
      
      this.trabajadores = trabajadores.content;
    });
  }
  // busqueda de trabajador
  findTrabajador() {
    console.log(this.name);
    
    if (this.name) {
      this.trabajadoresService.findByNombre(this.name).subscribe(trabajador=> {
        this.trabajador = trabajador;
      });
    } else {
      this.trabajadoresService.findByNombre(this.name!).subscribe(trabajador => {
        this.trabajador = trabajador;
      });
    }
    console.log(this.trabajador);

  }
}
