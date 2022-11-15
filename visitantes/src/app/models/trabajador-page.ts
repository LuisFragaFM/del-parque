import {Trabajador} from './trabajador';

export interface TrabajadorPage {
  empty: boolean;
  first: true;
  last: true;
  number: number;
  numberOfElements: number;
  size: number;
  totalElements: number;
  totalPages: number;
  content: Trabajador[];
}
