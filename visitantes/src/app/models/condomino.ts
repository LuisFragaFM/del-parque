import { UserView } from "./userview";

export interface Condomino {
  id: string;
  street: string;
  houseNumber: string;
  paidStatus: boolean;
  user: UserView;
}
