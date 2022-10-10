import {Component, OnInit} from '@angular/core';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import {validaInput} from 'src/app/tools/validation';
import Swal from 'sweetalert2';
import {UploadFilesService} from "../../services/upload-files.service";

@Component({
  selector: 'app-registrar_condomino',
  templateUrl: './registrar_condomino.component.html',
  styleUrls: ['./registrar_condomino.component.css'],
})
export class RegistrarCondominoComponent implements OnInit {
  condomino: Condomino = {} as Condomino;
  //Variable para validar nombre y activar boton
  altaName: boolean = true;
  altaTelEmergencia: boolean = true;
  altaTelResidente: boolean = true;
  altaMail: boolean = true;
  //Validacion de fechas
  regexName: any = /^[A-Za-zÀ-ÿ ,.'-]+$/; //nombre
  regexTel: any = /^\+?[1-9][0-9]{1,12}$/; //recibe paquete
  regexMail = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
  uri!: any;
  files!: FileList;

  constructor(private condominosService: CondominosService,
              private uploadFilesService: UploadFilesService) {
  }

  ngOnInit(): void {
  }

  save() {
    this.condomino.role = 'ROLE_RESIDENT';
    this.condominosService.save(this.condomino).subscribe((condomino) => {
      Swal.fire({
        title: `El condomino ${condomino.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        this.uploadFilesService.upload(this.files[0], condomino.id).subscribe(() => {
          this.condomino = {} as Condomino;
          this.files = {} as FileList;
          this.uri = '';
        });
      });
    });
  }

  showImage(event: Event) {
    const target = event.target as HTMLInputElement;
    this.files = target.files as FileList;

    const reader = new FileReader();
    reader.readAsDataURL(this.files[0]);

    reader.onload = (_event) => {
      this.uri = reader.result;
    }

  }

  // funcion para validacion
  validaResidente(regex: any, nombre: string) {
    this.altaName = validaInput(regex, nombre);
  }

  validaEmergencia(regex: any, telEmergencia: string) {
    this.altaTelEmergencia = validaInput(regex, telEmergencia);
  }

  validaTel(regex: any, telResidente: string) {
    this.altaTelResidente = validaInput(regex, telResidente);
  }

  validaCorreo(regex: any, correo: string) {
    this.altaMail = validaInput(regex, correo);
  }

  // deshabilitar o habilitar boton
  isValidForm(): boolean {
    return (
      this.altaName &&
      this.altaTelResidente &&
      this.altaTelEmergencia &&
      this.altaMail
    );
  }

}