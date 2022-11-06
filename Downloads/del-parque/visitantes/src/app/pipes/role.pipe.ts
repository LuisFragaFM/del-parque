import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'role'
})
export class RolePipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    return {
      ROLE_ADMIN: 'Administrador de Fraccionamiento Del Parque',
      ROLE_GUARD: 'Guardia',
      ROLE_RESIDENT: 'Residente'
    }[value];
  }

}
