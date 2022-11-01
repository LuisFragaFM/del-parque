import { Component, OnInit } from '@angular/core';
import {UploadFilesService} from '../../services/upload-files.service';

@Component({
  selector: 'app-registrar-guardia',
  templateUrl: './registrar-guardia.component.html',
  styleUrls: ['./registrar-guardia.component.css']
})
export class RegistrarGuardiaComponent implements OnInit {

  uri!: any;
  files!: FileList;

  constructor(
    private uploadFilesService: UploadFilesService
  ) { }

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
}