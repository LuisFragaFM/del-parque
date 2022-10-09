import {Component, OnInit} from '@angular/core';
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
    this.paquetesService.getPaquetes().subscribe(paquetes => {
      this.paquetes = paquetes.content;
      const totalOfPages = 10
      for (let i = 0; i < totalOfPages; i++) {
        this.listOfPages.push(i + 1);
      }
    })

  }

  // Notificaciones de usuario
  save(paquete: Paquete) {
    paquete.deliveryDate = this.formatDate();
    paquete.deliveryTime = this.formatAMPM();
    paquete.delivery = true;
    this.paquetesService.save(paquete).subscribe(entregaPaquete => {
      Swal.fire({
        title: `El paquete fue recibido por ${entregaPaquete.receivesResident} y entregado por ${entregaPaquete.deliveryGuard}`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        entregaPaquete = {} as Paquete;
        this.updatePage(this.page);
      });
    });
  }

  updatePage(page: number) {
    this.page = page - 1;
    this.paquetesService.getPaquetes(this.page).subscribe(paquetes => {
      this.paquetes = paquetes.content;
    })
  }

  formatDate() {
    const d = new Date()
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    let year = d.getFullYear();

    if (month.length < 2)
      month = '0' + month;
    if (day.length < 2)
      day = '0' + day;

    return [year, month, day].join('-');
  }

  formatAMPM() {
    const date = new Date();
    let hours = date.getHours();
    let minutes: number | string = date.getMinutes();
    let ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0' + minutes : minutes;
    return hours + ':' + minutes + ' ' + ampm;
  }
}
