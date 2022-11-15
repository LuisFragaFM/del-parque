import {Component, OnInit} from '@angular/core';
import {Paquete} from '../../models/paquete';
import {PaquetesService} from '../../services/paquetes.service';
import Swal from 'sweetalert2';
import {SessionService} from '../../services/session.service';
import {Visitante} from '../../models/visitante';
import {formatDate} from '../../tools/formatDate';
import {formatAMPM} from '../../tools/formatAMPM';

@Component({
  selector: 'app-entregar-paquete',
  templateUrl: './entregar-paquete.component.html',
  styleUrls: ['./entregar-paquete.component.css']
})
export class EntregarPaqueteComponent implements OnInit {
  // datos para realizar la paginacion
  visitante: Visitante = {} as Visitante;
  paquetes: Paquete[] = [];
  page = 0;
  listOfPages: number[] = [];
  isLoading = true;

  constructor(private paquetesService: PaquetesService,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.paquetesService.getPaquetes().subscribe((paquetes) => {
      this.isLoading = false;
      this.paquetes = paquetes.content;
      const totalOfPages = Math.trunc(paquetes.totalElements / paquetes.size);
      for (let i = 0; i < totalOfPages; i++) {
        this.listOfPages.push(i);
      }
    });
    this.sessionService.getUser().subscribe(user => {
      this.visitante.authorization = user.name;
    });

  }

  // Notificaciones de usuario
  save(paquete: Paquete): void {
    paquete.deliveryDate = formatDate();
    paquete.deliveryTime = formatAMPM();
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

  updatePage(page: number): void {
    this.isLoading = true;
    this.paquetesService.getPaquetes(page).subscribe(({content: paquetes}) => {
      this.paquetes = paquetes;
      this.isLoading = false;
    });
  }
}
