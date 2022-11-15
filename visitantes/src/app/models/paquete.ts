import {CondominoInfo} from './condominoInfo';

export interface Paquete {
  id: string;
  condomino: CondominoInfo;
  companyName: string;
  guideNumber: string;
  receivesGuard: string;
  deliveryDate: string;
  arrivalDate: string;
  receivesBooth: string;
  deliveryBooth: string;
  receivesResident: string;
  deliveryGuard: string;
  deliveryTime: string;
  arrivalTime: string;
  delivery: boolean;
}
