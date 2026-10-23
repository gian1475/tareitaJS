import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from '../environments/environment';
import { Alumno, Curso, Especialidad, FilaReporte, Respuesta } from './models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private http = inject(HttpClient);
  private url = environment.apiUrl;

  // especialidades y cursos
  especialidades() {
    return this.http.get<Especialidad[]>(`${this.url}/especialidades`);
  }

  cursosPorEspecialidad(codesp: string) {
    return this.http.get<Curso[]>(`${this.url}/cursos/${codesp}`);
  }

  // matricula
  alumnosPorCurso(codcur: string) {
    return this.http.get<Alumno[]>(`${this.url}/alumnos/${codcur}`);
  }

  siguienteNromat() {
    return this.http.get<{ nromat: string }>(`${this.url}/siguiente`);
  }

  matricular(alumno: Alumno) {
    return this.http.post<Respuesta>(`${this.url}/adiciona`, alumno);
  }

  anular(nromat: string) {
    return this.http.delete<Respuesta>(`${this.url}/borra/${nromat}`);
  }

  // reporte
  reporte(codesp: string) {
    return this.http.get<FilaReporte[]>(`${this.url}/reporte/${codesp}`);
  }

  // mantenimiento de curso
  cursos() {
    return this.http.get<Curso[]>(`${this.url}/curso`);
  }

  curso(codcur: string) {
    return this.http.get<Curso>(`${this.url}/curso/${codcur}`);
  }

  crearCurso(curso: Curso) {
    return this.http.post<Respuesta>(`${this.url}/curso`, curso);
  }

  editarCurso(curso: Curso) {
    return this.http.put<Respuesta>(`${this.url}/curso/${curso.codcur}`, curso);
  }

  eliminarCurso(codcur: string) {
    return this.http.delete<Respuesta>(`${this.url}/curso/${codcur}`);
  }
}
