import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'day'
})
export class DayPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    return {
      monday: 'Lunes',
      tuesday: 'Martes',
      wednesday: 'Miercoles',
      thursday: 'Jueves',
      friday: 'Viernes',
      saturday: 'Sabado',
      sunday: 'Domingo'
    }[value];
  }
}
