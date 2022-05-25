import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";

@Component({
  selector: 'app-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.css']
})
export class PagosComponent implements OnInit {
  name: string | undefined;
  phone: string | undefined;
  condomino: Condomino = {} as Condomino;

  constructor(private condominosService: CondominosService) {
  }

  ngOnInit(): void {
  }

  findInquilino() {

    if (!this.name) {
      this.condominosService.findByTelefono(this.phone!).subscribe(condomino => {
        this.condomino = condomino;
        console.log(this.condomino)

      });
    } else {
      this.condominosService.findByNombre(this.name!).subscribe(condomino => {
        this.condomino = condomino;
      });
    }
  }

  modify() {
    if (this.condomino) {
      this.condominosService.save(this.condomino).subscribe(condomino => {
        alert('Modificado con exito');
        this.condomino = condomino;
      });
    }
  }

  delete() {
    if (this.condomino) {
      this.condominosService.delete(this.condomino.id).subscribe(() => {
        alert('Borrado con exito');
        this.condomino = {} as Condomino;
      });
    }
  }
}
