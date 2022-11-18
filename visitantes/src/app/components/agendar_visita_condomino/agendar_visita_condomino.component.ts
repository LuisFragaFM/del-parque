import {Component, OnDestroy, OnInit} from '@angular/core';
import {VisitantesService} from '../../services/visitantes.service';
import {Visitante} from '../../models/visitante';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import Swal from 'sweetalert2';
import {SessionService} from '../../services/session.service';
import {CondominoInfo} from '../../models/condominoInfo';
import {validaInput} from 'src/app/tools/validation';
import {User} from '../../models/user';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-agendar_visita_condomino',
  templateUrl: './agendar_visita_condomino.component.html',
  styleUrls: ['./agendar_visita_condomino.component.css'],
})
export class AgendarVisitaCondominoComponent implements OnInit, OnDestroy {
  isChecked = true;
  date: Date = new Date();
  id = '';
  visitante: Visitante = {} as Visitante;
  name = '';
  condominos: Condomino[] = [];
  condomino: Condomino = {} as Condomino;
  visitaName = true;
  regexName: any = /^[A-Za-zÀ-ÿ ,.'-]+$/;
  user!: User;

  constructor(
    private visitantesService: VisitantesService,
    private condominosService: CondominosService,
    private sessionService: SessionService,
    private route: ActivatedRoute
  ) {
  }

  ngOnDestroy(): void {
    this.visitante = {} as Visitante;
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.children[0]?.paramMap?.get('id') || '';

    if (this.id) {
      this.visitantesService.findById(this.id).subscribe(visitante => {
        this.visitante = visitante;
      });
    }

    this.visitante.condomino = {} as CondominoInfo;
    this.sessionService.getUser().subscribe(user => {
      this.user = user;
      this.visitante.authorization = user.id;
      this.visitante.condomino.userId = user.id;
    });
  }

  save(): void {
    this.visitantesService.save(this.visitante).subscribe((visitante) => {
      Swal.fire({
        title: `La visita de ${visitante.name} fue agendada`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
      });
    });
  }

  findInquilinoByName(): void {
    this.condominos = [];
      this.condominosService.findByName(this.name).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  selectInquilino(condomino: Condomino): void {
    this.condomino = condomino;
    this.condominos = [];
  }

  validaVisita(regex: any, name: string): void {
    this.visitaName = validaInput(regex, name);
  }

  isValidForm(): boolean {
    return this.visitaName;
  }
}
