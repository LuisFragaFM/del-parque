import {Component, OnInit} from '@angular/core';
import {UploadFilesService} from '../../services/upload-files.service';
import Swal from "sweetalert2";
import {UsuariosService} from "../../services/usuarios.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-registrar-guardia',
  templateUrl: './registrar-guardia.component.html',
  styleUrls: ['./registrar-guardia.component.css']
})
export class RegistrarGuardiaComponent implements OnInit {

  uri!: any;
  files!: FileList;
  guardia: User = {} as User;

  constructor(private uploadFilesService: UploadFilesService,
              private usuariosService: UsuariosService) {
  }

  ngOnInit(): void {
  }

  showImage(event: Event) {
    const target = event.target as HTMLInputElement;
    this.files = target.files as FileList;

    const reader = new FileReader();
    reader.readAsDataURL(this.files[0]);

    reader.onload = (_event) => {
      this.uri = reader.result;
    };

  }

  save() {
    this.guardia.role = "ROLE_GUARD";
    this.usuariosService.register(this.guardia).subscribe((guardia) => {
      Swal.fire({
        title: `El Guardia ${guardia.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        this.guardia = {} as User;
      });
    });
  }
}
