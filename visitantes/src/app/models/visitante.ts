export interface Visitante {
  id: string;
  name: string;
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
  condominoId: string;
  // condomino: Condomino;
  authorized: boolean;
  goneOut: boolean;
}
