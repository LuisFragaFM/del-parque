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
    console.log(this.visitantes);
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
    const lista:string[][] = [];
    var temp:Array<any> = [];
    
    for (var i = 0; i < this.visitantes.length; i++)
    {
      console.log(lista);
      lista[i][0] = this.visitantes[i].name;
      lista[i][1] = this.visitantes[i].vehicle;
      lista[i][2] = this.visitantes[i].licensePlates;
      lista[i][3] = this.visitantes[i].arrivalDate;
      lista[i][4] = this.visitantes[i].departureDate;
    }
    /*this.visitantes.forEach(element => {
      temp = [element.name, element.vehicle, element.licensePlates, element.arrivalDate, element.departureDate];
      lista.push(temp);
    })*/
    const cuerpo = [["dato1", "dato1","dato1", "dato1"],["dato1", "dato1","dato1", "dato1"]]
    console.log(cuerpo);
    this.reporteService.imprimir(encabezado, lista, "reportetest", true);
  }

}