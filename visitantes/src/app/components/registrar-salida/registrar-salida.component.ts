import {Component, OnInit} from '@angular/core';
import {VisitantesService} from '../../services/visitantes.service';
import {Visitante} from '../../models/visitante';

@Component({
  selector: 'app-registrar-salida',
  templateUrl: './registrar-salida.component.html',
  styleUrls: ['./registrar-salida.component.css']
})
export class RegistrarSalidaComponent implements OnInit {
  visitantes: Visitante[] = [];

  constructor(private visitantesService: VisitantesService) {
  }

  ngOnInit(): void {
    this.visitantesService.getVisitantesByGoneOut(0).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
    });
  }

}
