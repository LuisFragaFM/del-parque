import {Component, OnInit} from '@angular/core';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import {validaInput} from 'src/app/tools/validation';
import Swal from 'sweetalert2';
import {UploadFilesService} from '../../services/upload-files.service';
import {User} from '../../models/user';

@Component({
  selector: 'app-registrar_condomino',
  templateUrl: './registrar_condomino.component.html',
  styleUrls: ['./registrar_condomino.component.css'],
})
export class RegistrarCondominoComponent implements OnInit {
  condomino: Condomino = {} as Condomino;

  altaName = true;
  altaTelEmergencia = true;
  altaTelResidente = true;
  altaMail = true;
  altaStreet = true;
  regexName: any = /[\S\s]+[\S]+/;
  regexTel: any = /^\+?[1-9][0-9]{1,12}$/;
  regexMail = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
  uri!: any;
  files!: FileList;
  
  constructor(
    private condominosService: CondominosService,
    private uploadFilesService: UploadFilesService
  ) {
  }

  ngOnInit(): void {
    this.condomino.user = {} as User;
  }

  save(): void {
    this.condomino.user.picture = 'assets/perfil.PNG';
    this.condominosService.save(this.condomino).subscribe((condomino) => {
      Swal.fire({
        title: `El condomino ${condomino.user.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then();
      if (this.files) {
        this.uploadFilesService
          .upload(this.files[0], condomino.user.id)
          .subscribe(() => {
          });
      }
      this.condomino = {} as Condomino;
      this.condomino.user = {} as User;
      this.files = {} as FileList;
      this.uri = '';
    });
  }

  showImage(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.files = target.files as FileList;

    const reader = new FileReader();
    reader.readAsDataURL(this.files[0]);

    reader.onload = (_event) => {
      this.uri = reader.result;
    };
  }

  // funcion para validacion
  validaResidente(regex: any, nombre: string): void {
    this.altaName = validaInput(regex, nombre);
  }

  validaEmergencia(regex: any, telEmergencia: string): void {
    this.altaTelEmergencia = validaInput(regex, telEmergencia);
  }

  validaTel(regex: any, telResidente: string): void {
    this.altaTelResidente = validaInput(regex, telResidente);
  }

  validaCorreo(regex: any, correo: string): void {
    this.altaMail = validaInput(regex, correo);
  }

  validaCalle(regex: any, calle: string): void {
    this.altaStreet = validaInput(regex, calle);
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

