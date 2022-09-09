import { Component, OnInit } from '@angular/core';
// modelos, servicios y alertas
import {Paquete} from "../../models/paquete"; //Modelo de paquete
import {PaquetesService} from "../../services/paquetes.service"; //Servicio de paquete
import Swal from "sweetalert2";


@Component({
  selector: 'app-entregar-paquete',
  templateUrl: './entregar-paquete.component.html',
  styleUrls: ['./entregar-paquete.component.css']
})
export class EntregarPaqueteComponent implements OnInit {
  // datos para realizar la paginacion 
  paquetes: Paquete[] = [];
  page: number = 0;
  listOfPages: number[] = [];

  constructor(private paquetesService: PaquetesService) {
  }

  ngOnInit(): void {
    //Paginacion
    this.paquetesService.getPaquetes(0).subscribe((paquetes)=>{

      this.paquetes = paquetes.content;
    });
    this.paquetesService.getPaquetes(this.page).subscribe(paquetes=> {
      this.paquetes = paquetes.content;
      const totalOfPages = 10
      for (let i = 0; i < totalOfPages; i++) {
        this.listOfPages.push(i + 1);
      }
    })

  }

  // Notificaciones de usuario
  save(paquete: Paquete) {
    
    this.paquetesService.save(paquete).subscribe(entregaPaquete => {
      Swal.fire({
        title: `El paquete fue recibido por ${entregaPaquete.recibeInquilino} y entregado por ${entregaPaquete.entregaGuardia}`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        entregaPaquete = {} as Paquete;
      });
    });
  }
  updatePage(page: number) {
    this.page = page - 1;
    this.paquetesService.getPaquetes(this.page).subscribe(paquetes => {
      this.paquetes = paquetes.content;
    })
  }
}
