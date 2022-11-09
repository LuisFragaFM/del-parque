import {Component, OnInit} from '@angular/core';
import {UsuariosService} from "../../services/usuarios.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {
  users: User[] = [];
  isLoading: boolean = true;

  constructor(private usuariosService: UsuariosService) {
  }

  ngOnInit(): void {
    this.usuariosService.getAllUsers().subscribe((users) => {
      this.users = users;
      this.isLoading = false;
    })
  }

}
