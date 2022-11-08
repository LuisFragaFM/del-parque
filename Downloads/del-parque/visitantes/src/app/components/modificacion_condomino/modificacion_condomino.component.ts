import {Component, OnInit} from '@angular/core';
import {CondominosService} from "../../services/condominos.service";
import {Condomino} from "../../models/condomino";
import Swal from "sweetalert2";
import {UploadFilesService} from "../../services/upload-files.service";
import {environment} from "../../../environments/environment";
import {UserView} from "../../models/userview";
import {UsuariosService} from "../../services/usuarios.service";

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

  constructor(private condominosService: CondominosService,
              private uploadFilesService: UploadFilesService,
              private usuariosService: UsuariosService) {
  }

  ngOnInit(): void {
    this.condomino.user = {} as UserView;
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe(condominos => {
      this.condominos = condominos;
    });
  }

  modify() {
    this.condominosService.save(this.condomino).subscribe(condomino => {
      Swal.fire({
        title: `El condomino de ${condomino.user.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`
      }).then(() => {
        if (this.files[0]) {
          this.uploadFilesService.upload(this.files[0], condomino.user.id).subscribe(() => {
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
        this.usuariosService.removeRole(this.condomino.user.id, "ROLE_RESIDENT").subscribe();
        this.condominosService.delete(this.condomino.id).subscribe(() => {
          Swal.fire({
            title: `El condomino de ${this.condomino.user.name} fue borrado correctamente`,
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
    this.uploadFilesService.loadFilename(this.condomino.user.id).subscribe(({filename}) => {
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
