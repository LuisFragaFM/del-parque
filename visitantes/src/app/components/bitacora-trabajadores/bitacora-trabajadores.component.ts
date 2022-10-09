import { Component, OnInit } from '@angular/core';
// imports para realizar la busqueda y notificaciones
import {TrabajadoresService} from "../../services/trabajadores.service";
import {Trabajador} from "../../models/trabajador";

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
  page: number = 0;
  listOfPages: number[] = [];

  constructor(private trabajadoresService: TrabajadoresService) {
  }

  ngOnInit(): void {
    //Paginacion
    this.trabajadoresService.getTrabajadores(this.page).subscribe(trabajadores => {
      this.trabajadores = trabajadores.content;
      const totalOfPages = 10 //Math.trunc(trabajadores.totalElements / trabajadores.size);
      for (let i = 0; i < totalOfPages; i++) {
        this.listOfPages.push(i + 1);
      }
    })

  }

  // busqueda de trabajador
  findTrabajador() {
    if (this.name) {
      this.trabajadoresService.findByNombre(this.name).subscribe(trabajador=> {
        this.trabajador = trabajador;
      });
    } else {
      this.trabajadoresService.findByNombre(this.name!).subscribe(trabajador => {
        this.trabajador = trabajador;
      });
    }

  }

  updatePage(page: number) {
    this.page = page - 1;
    this.trabajadoresService.getTrabajadores(this.page).subscribe(trabajadores => {
      this.trabajadores = trabajadores.content;
    })
  }
}
