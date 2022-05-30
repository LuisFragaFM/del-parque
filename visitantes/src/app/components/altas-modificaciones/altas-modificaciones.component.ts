import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";

@Component({
  selector: 'app-altas-modificaciones',
  templateUrl: './altas-modificaciones.component.html',
  styleUrls: ['./altas-modificaciones.component.css']
})
export class AltasModificacionesComponent implements OnInit {
  condomino: Condomino = {} as Condomino;

  constructor(private condominosService: CondominosService) {
  }

  ngOnInit(): void {
  }

  save() {
    this.condominosService.save(this.condomino).subscribe(condomino => {
      this.condomino = {} as Condomino;
      alert('Agregado con exito');
    });
  }


}
