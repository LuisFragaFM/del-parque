import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {SessionService} from "../../services/session.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-encabezado',
  templateUrl: './encabezado.component.html',
  styleUrls: ['./encabezado.component.css']
})
export class EncabezadoComponent implements OnInit {

  @Input('user') user!: User;
  src!: string;
  environment = environment.baseUrl;

  constructor(private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.src = this.user.picture ? this.environment + '/file/' + this.user.picture : "assets/perfil.PNG";
    console.log(this.src)
  }

  logout() {
    this.sessionService.logout();
  }
}
