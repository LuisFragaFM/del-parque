import {Component, OnInit} from '@angular/core';
import {UsuariosService} from '../../services/usuarios.service';
import {User} from '../../models/user';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {
  users: User[] = [];
  isLoading = true;
  environment = environment.baseUrl;

  constructor(private usuariosService: UsuariosService) {
  }

  ngOnInit(): void {
    this.usuariosService.getAllUsers().subscribe((users) => {
      this.users = users;
      this.users.forEach(user => {
        user.picture = this.environment + '/file/' + user.picture;
      });
      this.isLoading = false;
    });
  }

}
