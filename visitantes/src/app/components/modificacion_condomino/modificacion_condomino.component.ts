import {Component, OnInit} from '@angular/core';
import {CondominosService} from '../../services/condominos.service';
import {Condomino} from '../../models/condomino';
import Swal from 'sweetalert2';
import {UploadFilesService} from '../../services/upload-files.service';
import {environment} from '../../../environments/environment';
import {User} from '../../models/user';
import {validaInput} from 'src/app/tools/validation';
import {UsuariosService} from '../../services/usuarios.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-modificacion_condomino',
  templateUrl: './modificacion_condomino.component.html',
  styleUrls: ['./modificacion_condomino.component.css'],
})
export class Modificacion_CondominoComponent implements OnInit {
  name = '';
  condomino: Condomino = {} as Condomino;
  condominos: Condomino[] = [];
  altaName = true;
  altaTelEmergencia = true;
  altaTelResidente = true;
  altaMail = true;
  altaStreet = true;
  regexName: any = /[\S\s]+[\S]+/;
  regexTel: any = /^\+?[1-9][0-9]{1,12}$/;
  regexMail = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
  image: any;
  environment = environment.baseUrl;
  uri: string | ArrayBuffer | null = '';
  files!: FileList;
  id = '';

  constructor(
    private condominosService: CondominosService,
    private uploadFilesService: UploadFilesService,
    private usuariosService: UsuariosService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.children[0]?.paramMap?.get('id') || '';

    if (this.id) {
      this.condominosService.findById(this.id).subscribe(condomino => {
        this.condomino = condomino;
      });
    }
    this.condomino.user = {} as User;
  }

  findInquilinoByName(): void {
    this.condominos = [];
    this.condominosService.findByName(this.name).subscribe((condominos) => {
      this.condominos = condominos;
    });
  }
// Modifica la informacion de los residentes dentro de la BD y el sistemna
  modify(): void {
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
            .subscribe(() => {
            });
        }
      });
    });
  }
// Borra la informacion de los residentes dentro de la BD y el sistemna
  delete(): void {
    Swal.fire({
      title: `Estas seguro que deseas borrar este condomino?`,
      icon: 'warning',
      showDenyButton: true,
      confirmButtonText: `Si`,
      cancelButtonText: `No`,
    }).then((value) => {
      if (value.isConfirmed) {
        this.condominosService.delete(this.condomino.user.id).subscribe(() => {
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
// Funcion que sirve para la busqueda de inquilinos y mostrar la imagen en el sistema
  selectInquilino(condomino: Condomino): void {
    this.condomino = condomino;
    this.id = condomino.id;
    this.condominos = [];
    this.name = '';
    this.usuariosService.findById(this.condomino.user.id).subscribe(user => {
      if(user.picture === 'assets/perfil.PNG')
      {
        this.uri = user.picture;
      } else{
        this.uri = this.environment + '/file/' + user.picture;
      }
    });
  }
// Cambio de imagenes para el estado de pago
  changeImage(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.files = target.files as FileList;

    const reader = new FileReader();
    reader.readAsDataURL(this.files[0]);

    reader.onload = (_) => {
      this.uri = reader.result;
    };
  }

  // funcion para validacion
  validaResidente(regex: any, nombre: string): void {
    this.altaName = validaInput(regex, nombre);
  }

  validaTel(regex: any, telResidente: string): void {
    this.altaTelResidente = validaInput(regex, telResidente);
  }

  validaCalle(regex: any, calle: string): void {
    this.altaStreet = validaInput(regex, calle);
  }

  validaCorreo(regex: any, correo: string): void {
    this.altaMail = validaInput(regex, correo);
  }

  validaEmergencia(regex: any, telEmergencia: string): void {
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
