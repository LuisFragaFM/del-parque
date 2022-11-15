import {Visitante} from './visitante';

export interface VisitantePage {
  empty: boolean;
  first: true;
  last: true;
  number: number;
  numberOfElements: number;
  size: number;
  totalElements: number;
  totalPages: number;
  content: Visitante[];
}
