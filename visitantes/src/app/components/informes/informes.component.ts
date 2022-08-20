import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";

@Component({
  selector: 'app-pagos',
  templateUrl: './informes.component.html',
  styleUrls: ['./informes.component.css']
})
export class InformesComponent implements OnInit {

  name: string | undefined;
  phone: string | undefined;
  condomino: Condomino = {} as Condomino;
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
      this.condominosService.findByNombre(this.name!).subscribe(condomino => {
        this.condomino = condomino;
      });
    }
  }


}
