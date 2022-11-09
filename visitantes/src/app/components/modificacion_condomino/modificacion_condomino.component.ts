import { Component, OnInit } from '@angular/core';
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import Swal from 'sweetalert2';
import { UploadFilesService } from '../../services/upload-files.service';
import { environment } from '../../../environments/environment';
import { User } from '../../models/user';
import { validaInput } from 'src/app/tools/validation';

@Component({
  selector: 'app-modificacion_condomino',
  templateUrl: './modificacion_condomino.component.html',
  styleUrls: ['./modificacion_condomino.component.css'],
})
export class Modificacion_CondominoComponent implements OnInit {
  name: string | undefined;
  condomino: Condomino = {} as Condomino;
  condominos: Condomino[] = [];
  //Variable para validar nombre y activar boton
  altaName: boolean = true;
  altaTelEmergencia: boolean = true;
  altaTelResidente: boolean = true;
  altaMail: boolean = true;
  altaStreet: boolean = true;
  //Validacion
  regexName: any = /[\S\s]+[\S]+/;
  regexTel: any = /^\+?[1-9][0-9]{1,12}$/; //recibe paquete
  regexMail = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
  image: any;
  environment = environment.baseUrl;
  uri: any = '';
  files!: FileList;

  constructor(
    private condominosService: CondominosService,
    private uploadFilesService: UploadFilesService
  ) {}

  ngOnInit(): void {
    this.condomino.user = {} as User;
  }

  findInquilinoByName() {
    this.condominosService.findByName(this.name!).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }

  modify() {
    this.condominosService.save(this.condomino).subscribe((condomino) => {
      Swal.fire({
        title: `El condomino de ${condomino.user.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        if (this.files) {
          this.uploadFilesService
            .upload(this.files[0], condomino.user.id)
            .subscribe(() => {});
        }
      });
      this.condomino = {} as Condomino;
      this.condomino.user = {} as User;
      this.files = {} as FileList;
      this.uri = '';
    });
  }

  delete() {
    Swal.fire({
      title: `Estas seguro que deseas borrar este condomino?`,
      icon: 'warning',
      showDenyButton: true,
      confirmButtonText: `Si`,
      cancelButtonText: `No`,
    }).then((value) => {
      if (value.isConfirmed) {
        this.condominosService.delete(this.condomino.id).subscribe(() => {
          Swal.fire({
            title: `El condomino de ${this.condomino.user.name} fue borrado correctamente`,
            icon: 'success',
            showDenyButton: false,
            showCancelButton: false,
            confirmButtonText: `Cerrar`,
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
    this.uploadFilesService
      .loadFilename(this.condomino.user.id)
      .subscribe(({ filename }) => {
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
    };
  }
  // funcion para validacion
  validaResidente(regex: any, nombre: string) {
    this.altaName = validaInput(regex, nombre);
  }
  validaTel(regex: any, telResidente: string) {
    this.altaTelResidente = validaInput(regex, telResidente);
  }
  validaCalle(regex: any, calle: string) {
    this.altaStreet = validaInput(regex, calle);
  }

  validaCorreo(regex: any, correo: string) {
    this.altaMail = validaInput(regex, correo);
  }
  validaEmergencia(regex: any, telEmergencia: string) {
    this.altaTelEmergencia = validaInput(regex, telEmergencia);
  }
  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return (
      this.altaName &&
      this.altaTelResidente &&
      this.altaTelEmergencia &&
      this.altaMail &&
      this.altaStreet
    );
  }
}
