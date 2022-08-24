import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";

@Component({
  selector: 'app-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.css']
})
export class PagosComponent implements OnInit {
  name: string | undefined;
  phone: string | undefined;
  condomino: Condomino = {} as Condomino;
  condominos: Condomino[] = [];
  constructor(private condominosService: CondominosService) {
  }
  ngOnInit(): void {
    this.condominosService.getCondominos().subscribe(condominos => this.condominos = condominos);
  }

  findInquilino() {

    
    if (this.phone) {
      this.condominosService.findByTelefono(this.phone).subscribe(condomino => {
        this.condomino = condomino;
        

      });
    } else {
      this.condominosService.findByNombre(this.name!).subscribe(condomino => {
        this.condomino = condomino;
      });
    }
  }

  modify() {
    
    if (this.condomino.id) {
      this.condominosService.save(this.condomino).subscribe(condomino => {
        
        Swal.fire({
          title: `El condomino de ${condomino.nombre} fue guardado correctamente`,
          icon: 'success',
          showDenyButton: false,
          showCancelButton: false,
          confirmButtonText: `Cerrar`
        }).then(() => {
          this.condomino = {} as Condomino;

        });
        this.condomino = condomino;
      });
    }
  }

  delete() {
    if (this.condomino.id) {

      Swal.fire({
        title: `Estas seguro que deseas borrar este condomino?`,
        icon: 'warning',
        showDenyButton: true,
        confirmButtonText: `Si`,
        cancelButtonText: `No`
      }).then((value) => {
        if (value.isConfirmed) {
          this.condominosService.delete(this.condomino.id).subscribe(() => {
            Swal.fire({
              title: `El condomino de ${this.condomino.nombre} fue borrado correctamente`,
              icon: 'success',
              showDenyButton: false,
              showCancelButton: false,
              confirmButtonText: `Cerrar`
            }).then(() => {
              this.condomino = {} as Condomino;

            });
          });
        }

      });

    }
  }
}