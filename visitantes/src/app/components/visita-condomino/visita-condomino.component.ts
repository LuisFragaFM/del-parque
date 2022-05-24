import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";

@Component({
  selector: 'app-visita-condomino',
  templateUrl: './visita-condomino.component.html',
  styleUrls: ['./visita-condomino.component.css']
})
export class VisitaCondominoComponent implements OnInit {
  visitante: Visitante = {} as Visitante;

  constructor(private visitantesService: VisitantesService) {
  }

  ngOnInit(): void {
  }

  save() {
    this.visitantesService.save(this.visitante).subscribe(visitante => {
      console.log(visitante);
    });
  }
}
