import {CondominoInfo} from "./condominoInfo";
import {WorkDay} from "./WorkDay";

export interface Trabajador {
  id: string;
  type: string;
  name: string;
  condominoInfo: CondominoInfo;
  schedule: string;
  telephoneNumber: string;
  workDays: WorkDay[];
}
