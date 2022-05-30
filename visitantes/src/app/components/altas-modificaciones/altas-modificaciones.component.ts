import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from 'sweetalert2';

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
      Swal.fire({
        title: `El condomino ${condomino.nombre} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        this.condomino = {} as Condomino;

      });
    });
  }

}