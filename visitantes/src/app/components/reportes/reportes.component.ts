import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import {ReporteService} from "../../services/reporte.service";
import {UploadFilesService} from "../../services/upload-files.service";
import {environment} from "../../../environments/environment";
import {User} from "../../models/user";
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";


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
    private visitantesService: VisitantesService) {
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
    this.uploadFilesService.loadFilename(this.condomino.user.id).subscribe(({filename}) => {
      this.uri = this.environment + '/file/' + filename;
    });
    this.visitantesService.findAll().subscribe(visitantes  => {
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

  printReport(){
    const encabezado = ["Nombre", "Vehiculo", "Placas", "Fecha llegada", "Fecha salida"];

    const cuerpo  = this.visitantes.map((visitante) => {
      const visitanteReporte: VisitanteReporte = {
        name: visitante.name,
        vehicle: visitante.vehicle,
        licensePlates: visitante.licensePlates,
        arrivalDate: visitante.arrivalDate,
        departureDate: visitante.departureDate
        
      }
      return Object.values(visitanteReporte);
    })
    console.log(cuerpo);
    
    const test = [["dato", "dato", "dato", "dato", "dato"], ["dato", "dato", "dato", "dato", "dato"]];
    this.reporteService.imprimir(encabezado, cuerpo, "reportetest", true);
  }

}