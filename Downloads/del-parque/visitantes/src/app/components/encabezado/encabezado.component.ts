import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app-encabezado',
  templateUrl: './encabezado.component.html',
  styleUrls: ['./encabezado.component.css']
})
export class EncabezadoComponent implements OnInit {

  @Input('user') user!: User;

  constructor(private sessionService: SessionService) {
  }

  ngOnInit(): void {
  }

  logout() {
    this.sessionService.logout();
  }
}
