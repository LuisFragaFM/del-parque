import {CondominoInfo} from "./condominoInfo";

export interface Trabajador {
  id: string;
  type: string;
  name: string;
  condominoInfo: CondominoInfo;
  schedule: string;
  telephoneNumber: string;
  workDays: string[];
}
