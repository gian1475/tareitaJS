// los nombres de campos son los mismos que devuelve el backend (Spring Boot)

export interface Especialidad {
  code: string;
  nomes: string;
}

export interface Curso {
  codcur: string;
  codes: string; // codigo de especialidad
  nomcur: string;
  costo: number;
  nrovac: number;
}

export interface Alumno {
  nromat?: string;
  codcur: string;
  apenom: string;
  tipo: string; // 1 = 1/2 Beca, 2 = 1/4 Beca, 3 = Regular
  pago: number;
  ctipo?: string;
}

export interface FilaReporte {
  codcur: string;
  nomcur: string;
  cantidad: number;
  estado: string;
}

export interface Respuesta {
  ok: boolean;
  mensaje: string;
}

export const TIPOS_ALUMNO = [
  { valor: '1', texto: '1/2 Beca', factor: 0.5 },
  { valor: '2', texto: '1/4 Beca', factor: 0.75 },
  { valor: '3', texto: 'Regular', factor: 1 },
];
