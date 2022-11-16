import {Component, OnInit} from '@angular/core';
import {UploadFilesService} from '../../services/upload-files.service';
import Swal from 'sweetalert2';
import {User} from '../../models/user';
import {GuardiasService} from '../../services/guardias.service';
import {Condomino} from '../../models/condomino';

@Component({
  selector: 'app-registrar-guardia',
  templateUrl: './registrar-guardia.component.html',
  styleUrls: ['./registrar-guardia.component.css']
})
export class RegistrarGuardiaComponent implements OnInit {

  uri: string | ArrayBuffer | null = '';
  files!: FileList;
  guardia: User = {} as User;

  constructor(private uploadFilesService: UploadFilesService,
              private guardiasService: GuardiasService) {
  }

  ngOnInit(): void {
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

  save(): void {
    this.guardiasService.register(this.guardia).subscribe((guardia) => {
      Swal.fire({
        title: `El Guardia ${guardia.name} fue guardado correctamente`,
        icon: 'success',
        showDenyButton: false,
        showCancelButton: false,
        confirmButtonText: `Cerrar`,
      }).then(() => {
        if (this.files) {
          this.uploadFilesService
            .upload(this.files[0], guardia.id)
            .subscribe(() => {
            });
        }
        this.guardia = {} as User;
        this.files = {} as FileList;
        this.uri = '';
      });
    });
  }
}
