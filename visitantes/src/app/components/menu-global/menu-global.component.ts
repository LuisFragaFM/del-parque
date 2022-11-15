import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../models/user';

@Component({
  selector: 'app-menu-global',
  templateUrl: './menu-global.component.html',
  styleUrls: ['./menu-global.component.css']
})
export class MenuGlobalComponent implements OnInit {
  @Input() user!: User;

  constructor() {
  }

  ngOnInit(): void {
  }

  hasRoles(roles: string []): boolean {
    return roles.some(r => r === this.user.role);
  }

}
