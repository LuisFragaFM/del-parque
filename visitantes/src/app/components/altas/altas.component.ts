import { Component, OnInit } from '@angular/core';
import { CondominosService } from '../../services/condominos.service';
import { Condomino } from '../../models/condomino';
import { validaInput } from 'src/tools/validation';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-altas',
  templateUrl: './altas.component.html',
  styleUrls: ['./altas.component.css'],
})
export class AltasComponent implements OnInit {
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
  constructor(private condominosService: CondominosService) {}
  ngOnInit(): void {}

  save() {
    this.condominosService.save(this.condomino).subscribe((condomino) => {
      Swal.fire({
        title: `El condomino ${condomino.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        this.condomino = {} as Condomino;
      });
    });
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
    console.log(this.altaMail);
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
