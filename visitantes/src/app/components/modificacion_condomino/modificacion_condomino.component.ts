import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";
import {UploadFilesService} from "../../services/upload-files.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-modificacion_condomino',
  templateUrl: './modificacion_condomino.component.html',
  styleUrls: ['./modificacion_condomino.component.css']
})
export class Modificacion_CondominoComponent implements OnInit {
  name: string | undefined;
  condomino: Condomino = {} as Condomino;
  condominos: Condomino[] = [];
  image: any;
  environment = environment.baseUrl;
  uri: any = '';
  files!: FileList;
  email!: string;

  constructor(private condominosService: CondominosService, private uploadFilesService: UploadFilesService) {
  }

  ngOnInit(): void {
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      this.condominos = condominos;
    });
  }

  modify() {
    this.condominosService.save(this.condomino).subscribe(condomino => {
      Swal.fire({
        title: `El condomino de ${condomino.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        if (this.files[0]) {
          this.uploadFilesService.upload(this.files[0], condomino.id).subscribe(() => {
          });
        }
      });
      this.condomino = {} as Condomino;
    });
  }

  delete() {
    Swal.fire({
      title: `Estas seguro que deseas borrar este condomino?`,
      icon: 'warning',
      showDenyButton: true,
      confirmButtonText: `Si`,
      cancelButtonText: `No`
    }).then((value) => {
      if (value.isConfirmed) {
        this.condominosService.delete(this.condomino.id).subscribe(() => {
          Swal.fire({
            title: `El condomino de ${this.condomino.name} fue borrado correctamente`,
            icon: 'success',
            showDenyButton: false,
            showCancelButton: false,
            confirmButtonText: `Cerrar`
          }).then(() => {
            this.condomino = {} as Condomino;

          });
        });
      }
    });
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
    this.name = undefined;
    this.uploadFilesService.loadFilename(this.condomino.id).subscribe(({filename}) => {
      this.uri = this.environment + '/file/' + filename;
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
}
