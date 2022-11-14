import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import {ReporteService} from "../../services/reporte.service";
import {UploadFilesService} from "../../services/upload-files.service";
import {environment} from "../../../environments/environment";
import {User} from "../../models/user";
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";
import {UsuariosService} from "../../services/usuarios.service";


interface VisitanteReporte {
  name: string;
  licensePlates: string;
  vehicle: string;
  arrivalDate: string;
  departureDate: string;
}

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css']
})
export class ReportesComponent implements OnInit {

  name: string | undefined;
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
    this.visitantes[0].licensePlates
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      this.condominos = condominos;
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
    this.name = undefined;
    this.usuariosService.findById(this.condomino.user.id).subscribe(user => {
      this.uri = this.environment + '/file/' + user.picture;
    });
    this.visitantesService.findAllByUserId(this.condomino.user.id).subscribe(visitantes => {
      this.visitantes = visitantes;
    });
  }

  changeImage(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.files = target.files as FileList;

    const reader = new FileReader();
    reader.readAsDataURL(this.files[0]);

    reader.onload = (_event) => {
      this.uri = reader.result;
    }
  }

  printReport() {
    const encabezado = ["Nombre", "Vehiculo", "Placas", "Fecha llegada", "Fecha salida"];

    const cuerpo = this.visitantes.map((visitante) => {
      const visitanteReporte: VisitanteReporte = {
        name: visitante.name,
        vehicle: visitante.vehicle,
        licensePlates: visitante.licensePlates,
        arrivalDate: visitante.arrivalDate,
        departureDate: visitante.departureDate

      }
      return Object.values(visitanteReporte);
    })

    this.reporteService.imprimir(encabezado, cuerpo, "Reporte de visitas " + this.condomino.user.name, true);
  }

}
