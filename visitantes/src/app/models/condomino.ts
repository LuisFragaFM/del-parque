import {User} from './user';

export interface Condomino {
  id: string;
  street: string;
  relatives: string;
  houseNumber: string;
  paidStatus: boolean;
  user: User;
}
