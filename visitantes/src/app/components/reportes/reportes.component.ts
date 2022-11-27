import {Component, OnInit} from '@angular/core';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import {ReporteService} from '../../services/reporte.service';
import {UploadFilesService} from '../../services/upload-files.service';
import {environment} from '../../../environments/environment';
import {User} from '../../models/user';
import {VisitantesService} from '../../services/visitantes.service';
import {Visitante} from '../../models/visitante';
import {UsuariosService} from '../../services/usuarios.service';


interface VisitanteReporte {
  name: string;
  typevisitor: string;
  licensePlates: string;
  vehicle: string;
  arrivalDate: string;
  checkIn: string;
  departureTime: string;
  departureDate: string;
}

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css']
})
export class ReportesComponent implements OnInit {

  name = '';
  phone: string | undefined;
  condomino: Condomino = {} as Condomino;
  condominos: Condomino[] = [];
  visitantes: Visitante[] = [];
  image: any;
  environment = environment.baseUrl;
  uri: any = '';
  files!: FileList;

  constructor(private condominosService: CondominosService,
              private reporteService: ReporteService,
              private uploadFilesService: UploadFilesService,
              private visitantesService: VisitantesService,
              private usuariosService: UsuariosService) {
  }

  ngOnInit(): void {
    this.condomino.user = {} as User;
  }

  findInquilinoByName(): void {
    this.condominosService.findByName(this.name).subscribe(condominos => {
      this.condominos = condominos;
    });
  }

  selectInquilino(condomino: Condomino): void {
    this.condomino = condomino;
    this.condominos = [];
    this.name = '';
    this.usuariosService.findById(this.condomino.user.id).subscribe(user => {
      if(user.picture === 'assets/perfil.PNG')
      {
        this.uri = user.picture;
      } else{
        this.uri = this.environment + '/file/' + user.picture;
      }
    });
    this.visitantesService.findAllByUserId(this.condomino.user.id).subscribe(visitantes => {
      this.visitantes = visitantes;
    });
  }

  printReport(): void {
    const encabezado = ['Nombre', 'Tipo de visita', 'Vehiculo', 'Placas', 'Fecha llegada', 'Hora llegada', 'Fecha salida', 'Hora de salida'];

    const cuerpo = this.visitantes.map((visitante) => {
      const visitanteReporte: VisitanteReporte = {
        name: visitante.name,
        typevisitor: visitante.type_visitor,
        vehicle: visitante.vehicle,
        licensePlates: visitante.licensePlates,
        arrivalDate: visitante.arrivalDate,
        checkIn: visitante.checkIn,
        departureDate: visitante.departureDate,
        departureTime: visitante.departureTime

      };
      return Object.values(visitanteReporte);
    });

    this.reporteService.imprimir(encabezado, cuerpo, 'Reporte de visitas ' + this.condomino.user.name, true);
  }

}
