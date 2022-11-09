import {Component, OnInit} from '@angular/core';
import {UploadFilesService} from '../../services/upload-files.service';
import Swal from "sweetalert2";
import {User} from "../../models/user";
import {GuardiasService} from "../../services/guardias.service";

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
              private guardiasService: GuardiasService) {
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
    console.log(this.guardia)
    this.guardiasService.register(this.guardia).subscribe((guardia) => {
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
