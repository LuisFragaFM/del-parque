import {Paquete} from './paquete';

export interface PaquetePage {
  empty: boolean;
  first: true;
  last: true;
  number: number;
  numberOfElements: number;
  size: number;
  totalElements: number;
  totalPages: number;
  content: Paquete[];
}
