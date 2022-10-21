import {Component, OnInit} from '@angular/core';
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";

@Component({
  selector: 'app-visitas_agendadas_condomino',
  templateUrl: './visitas_agendadas_condomino.component.html',
  styleUrls: ['./visitas_agendadas_condomino.component.css']
})
export class VisitasAgendadasCondominoComponent implements OnInit {

  // datos usados para realizar la busqueda
  name: string | undefined;
  visitante: Visitante = {} as Visitante;
  visitantes: Visitante[] = [];
  page: number = 0;
  listOfPages: number[] = [];

  constructor(private visitantesService: VisitantesService) {
  }

  ngOnInit(): void {
    //Paginacion
    this.visitantesService.getVisitantesUnauthorized(0).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
    });

    // this.visitantesService.getVisitantesUnauthorized(this.page).subscribe(visitantes => {
    //   this.visitantes = visitantes.content;
    //   const totalOfPages = 10 //Math.trunc(trabajadores.totalElements / trabajadores.size);
    //   for (let i = 0; i < totalOfPages; i++) {
    //     this.listOfPages.push(i + 1);
    //   }
    // })
  }

  // busqueda de visitante
  findVisitante() {
    if (this.name) {
      this.visitantesService.findByName(this.name).subscribe(visitante => {
        console.log(visitante);
        this.visitante = visitante;
      });
    } else {
      this.visitantesService.findByName(this.name!).subscribe(visitante => {
        this.visitante = visitante;
      });
    }

  }

  updatePage(page: number) {
    this.page = page - 1;
    this.visitantesService.getVisitantesUnauthorized(this.page).subscribe(({content: visitantes}) => {
      this.visitantes = visitantes;
    })
  }

}
