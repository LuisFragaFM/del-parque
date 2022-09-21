import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";
import {UploadFilesService} from "../../services/upload-files.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-pagos',
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.css']
})
export class PagosComponent implements OnInit {
  name: string | undefined;
  phone: string | undefined;
  condomino: Condomino = {} as Condomino;
  condominos: Condomino[] = [];
  image: any;
  environment = environment.baseUrl;
  uri!: string;

  constructor(private condominosService: CondominosService, private uploadFilesService: UploadFilesService) {
  }

  ngOnInit(): void {
    this.findImage();
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      console.log(condominos);
      this.condominos = condominos;
    });
  }

  findInquilinoByTelephone() {
    this.condominosService.findByTelefono(this.phone!).subscribe(condomino => {
      this.condomino = condomino;
    });
  }

  modify() {
    if (this.condomino.id) {
      this.condominosService.save(this.condomino).subscribe(condomino => {
        Swal.fire({
          title: `El condomino de ${condomino.name} fue guardado correctamente`,
          icon: 'success',
          showDenyButton: false,
          showCancelButton: false,
          confirmButtonText: `Cerrar`
        }).then(() => {
          this.condomino = {} as Condomino;
        });
        this.condomino = condomino;
      });
    }
  }

  delete() {
    if (this.condomino.id) {

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
  }

  selectInquilino(condomino: Condomino) {
    this.condomino = condomino;
    this.condominos = [];
    this.name = undefined;
    this.phone = undefined;
  }

  submit(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    this.uploadFilesService.upload(files[0]).subscribe(() => {
      this.findImage();
    });
  }

  findImage() {
    this.uploadFilesService.loadFilename().subscribe(({filename}) => {
      this.uri = this.environment + "/file/" + filename;
    });
  }
}
