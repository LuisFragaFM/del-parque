import {CondominoInfo} from './condominoInfo';

export interface Visitante {
  id: string;
  name: string;
  type_visitor: string;
  licensePlates: string;
  vehicle: string;
  card: string;
  arrivalDate: string;
  departureDate: string;
  checkIn: string;
  departureTime: string;
  authorization: string;
  departureBooth: string;
  arrivalBooth: string;
  condomino: CondominoInfo;
  authorized: boolean;
  checkOut: boolean;
}
