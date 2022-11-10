import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import {ReporteService} from "../../services/reporte.service";
import {UploadFilesService} from "../../services/upload-files.service";
import {environment} from "../../../environments/environment";
import {User} from "../../models/user";
import {VisitantesService} from "../../services/visitantes.service";
import {Visitante} from "../../models/visitante";

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
    const encabezado = ["Nombre", "Vehiculo", "Placas", "Fecha llegada", "Fecha salida"]
    const cuerpo = ["uriel", "mercedes", "HTTPS", "07/11/2022", "08/11/2022"]
    this.reporteService.imprimir(encabezado, cuerpo, "reportetest", true);
  }

}