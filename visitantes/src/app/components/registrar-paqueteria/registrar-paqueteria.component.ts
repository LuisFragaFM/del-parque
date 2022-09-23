import {Component, OnInit} from '@angular/core';
import {PaquetesService} from "../../services/paquetes.service";
import {Paquete} from "../../models/paquete";
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";

@Component({
  selector: 'app-registrar-paqueteria',
  templateUrl: './registrar-paqueteria.component.html',
  styleUrls: ['./registrar-paqueteria.component.css']
})
export class RegistrarPaqueteriaComponent implements OnInit {
  paquete: Paquete = {} as Paquete;
  numberOfPages: number = 1;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  constructor(private paquetesService: PaquetesService, private condominosService: CondominosService) {
  }

  ngOnInit(): void {
    this.paquetesService.getPaquetes(0).subscribe(paquetes => {      
    });
  }

  findInquilinoByName() {
    if(this.name == "" ) {
      this.condominos = [];
    }
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      this.condominos = condominos;
    });
  }
  save() {
    
    this.paquetesService.save(this.paquete).subscribe(paquete => {
      this.paquete = {} as Paquete;
      Swal.fire({
        title: `El paquete para la casa ${paquete.numeroCasa} en la calle ${paquete.calle} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        this.paquete = {} as Paquete;

      });
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
    this.name = condomino.name;
    this.paquete.nombreCondomino = condomino.name;
    this.paquete.calle = condomino.calle;
    this.paquete.numeroCasa = condomino.numeroCasa;

  }
}