import { Component, OnInit } from '@angular/core';
import { VisitantesService } from '../../services/visitantes.service';
import { Visitante } from '../../models/visitante';
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import Swal from 'sweetalert2';
import { SessionService } from '../../services/session.service';
import { validaInput } from 'src/app/tools/validation';
@Component({
  selector: 'app-agendar_visita_condomino',
  templateUrl: './agendar_visita_condomino.component.html',
  styleUrls: ['./agendar_visita_condomino.component.css'],
})
export class AgendarVisitaCondominoComponent implements OnInit {
  isChecked: boolean = true;
  visitante: Visitante = {} as Visitante;
  name: string | undefined;
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  //Variable para validar nombre y activar boton
  visitaName: boolean = true;
  //Validacion de fechas
  regexName: any = /^[A-Za-zÀ-ÿ ,.'-]+$/; //nombre

  constructor(
    private visitantesService: VisitantesService,
    private condominosService: CondominosService,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.sessionService.getUser().subscribe((user) => {
      this.visitante.authorization = user.name;
    });
  }

  save() {
    this.visitantesService.save(this.visitante).subscribe((visitante) => {
      Swal.fire({
        title: `La visita de ${visitante.name} fue agendada`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {});
    });
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe((condominos) => {
      console.log(condominos);
      this.condominos = condominos;
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
  }
  // funcion para validacion
  validaVisita(regex: any, name: string) {
    this.visitaName = validaInput(regex, name);
  }
  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return this.visitaName;
  }
}
