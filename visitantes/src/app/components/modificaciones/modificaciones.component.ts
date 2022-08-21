import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modificaciones',
  templateUrl: './modificaciones.component.html',
  styleUrls: ['./modificaciones.component.css']
})
export class ModificacionesComponent implements OnInit {
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