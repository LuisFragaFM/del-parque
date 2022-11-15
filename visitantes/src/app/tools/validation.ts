export const validaInput = (regex: any, text: string) => {
  return new RegExp(regex).test(text);
};
