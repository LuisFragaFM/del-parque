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
  entregaPaquete: Paquete = {} as Paquete;

  constructor(private paquetesService: PaquetesService) {
  }

  ngOnInit(): void {
  }

  // Notificaciones de usuario
  save() {
    this.paquetesService.save(this.entregaPaquete).subscribe(entregaPaquete => {
      this.entregaPaquete = {} as Paquete;    
      Swal.fire({
        title: `El paquete fue recibido por ${entregaPaquete.nombreCondomino} y entregado por ${entregaPaquete.recibeGuardia}`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        this.entregaPaquete = {} as Paquete;

      });
    });
  }
}
