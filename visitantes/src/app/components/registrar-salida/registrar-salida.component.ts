import {Component, OnInit} from '@angular/core';
import {VisitantesService} from '../../services/visitantes.service';
import {Visitante} from '../../models/visitante';
import Swal from 'sweetalert2';
import {formatAMPM} from '../../tools/formatAMPM';
import {formatDate} from '../../tools/formatDate';

@Component({
  selector: 'app-registrar-salida',
  templateUrl: './registrar-salida.component.html',
  styleUrls: ['./registrar-salida.component.css']
})
export class RegistrarSalidaComponent implements OnInit {
  visitantes: Visitante[] = [];
  departureTime!: string;
  isLoading = true;
  page = 0;

  constructor(private visitantesService: VisitantesService) {
  }

  ngOnInit(): void {
    this.visitantesService.getVisitantesByCheckOut(0).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
      this.departureTime = formatAMPM();
    });
  }

  update(page: number): void {
    this.isLoading = true;
    this.visitantesService.getVisitantesByCheckOut(page).subscribe(visitantes => {
      this.visitantes = visitantes.content;
      this.departureTime = formatAMPM();
      this.isLoading = false;
    });
  }

  save( visitante: Visitante): void {
    visitante.departureTime = formatAMPM();
    visitante.departureDate = formatDate();
    visitante.checkOut = true;
    this.visitantesService.save(visitante).subscribe((visitante) => {
      Swal.fire({
        title: `La visita de ${visitante.name} fue guardada`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
      });
      this.update(this.page);
    });
  }
}
