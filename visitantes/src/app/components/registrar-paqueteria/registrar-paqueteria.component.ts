import {Component, OnInit} from '@angular/core';
import {PaquetesService} from "../../services/paquetes.service";
import {Paquete} from "../../models/paquete";

@Component({
  selector: 'app-registrar-paqueteria',
  templateUrl: './registrar-paqueteria.component.html',
  styleUrls: ['./registrar-paqueteria.component.css']
})
export class RegistrarPaqueteriaComponent implements OnInit {
  paquete: Paquete = {} as Paquete;

  constructor(private paquetesService: PaquetesService) {
  }

  ngOnInit(): void {
  }

  save() {
    this.paquetesService.save(this.paquete).subscribe(paquete => {
      this.paquete = {} as Paquete;
      alert('Guardado con exito');
    });
  }
}
