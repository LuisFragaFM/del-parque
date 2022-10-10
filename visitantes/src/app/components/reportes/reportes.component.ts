import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css']
})
export class ReportesComponent implements OnInit {

  name: string | undefined;
  phone: string | undefined;
  condomino: Condomino = {} as Condomino;
  condominos: Condomino[] = [];
  constructor(private condominosService: CondominosService) {
  }
  ngOnInit(): void {
  }
  findInquilino() {


    if (this.phone) {
      this.condominosService.findByTelefono(this.phone).subscribe(condomino => {
        this.condomino = condomino;


      });
    } else {
      this.condominosService.findByName(this.name!).subscribe(condominos => {
        this.condominos = condominos;
      });
    }
  }


}