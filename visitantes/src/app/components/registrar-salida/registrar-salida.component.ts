import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";

@Component({
  selector: 'app-registrar-salida',
  templateUrl: './registrar-salida.component.html',
  styleUrls: ['./registrar-salida.component.css']
})
export class RegistrarSalidaComponent implements OnInit {

  constructor(private visitantesService: VisitantesService) {
  }

  ngOnInit(): void {
    this.visitantesService.getVisitantesNoLeft(0).subscribe(({content: visitantes}) => {
      console.log(visitantes);
    });
  }

}
