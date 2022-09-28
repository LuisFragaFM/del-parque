export const validaInput = (regex: any, text: string) => {
  const regRecibo = new RegExp(regex); //Expresion regular a validar
  return regRecibo.test(text);
};
