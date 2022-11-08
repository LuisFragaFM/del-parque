import {User} from "./user";

export interface Condomino {
  id: string;
  street: string;
  houseNumber: string;
  paidStatus: boolean;
  user: User;
}
