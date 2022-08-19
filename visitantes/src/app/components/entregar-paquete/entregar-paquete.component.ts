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
  Entrega_paquete: Paquete = {} as Paquete;
  constructor(private PaquetesService: PaquetesService) {
  }
  ngOnInit(): void {
  }

  // Notificaciones de usuario
  save() {
    this.PaquetesService.save(this.Entrega_paquete).subscribe(Entrega_paquete => {
      this. Entrega_paquete = {} as Paquete;
      Swal.fire({
        title: `El paquete fue recibido por ${Entrega_paquete.recibeGuardia} y guardado en ${Entrega_paquete.casetaEntrega}`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        this.Entrega_paquete = {} as Paquete;

      });
    });
  }
}
