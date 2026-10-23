import { Routes } from '@angular/router';
import { Cursos } from './pages/cursos/cursos';
import { Matricula } from './pages/matricula/matricula';
import { Reporte } from './pages/reporte/reporte';
import { MantCurso } from './pages/mant-curso/mant-curso';

export const routes: Routes = [
  { path: '', redirectTo: 'cursos', pathMatch: 'full' },
  { path: 'cursos', component: Cursos, title: 'Cursos de Extensión' },
  { path: 'matricula/:codcur', component: Matricula, title: 'Matrícula de Alumnos' },
  { path: 'reporte', component: Reporte, title: 'Alumnos por Curso' },
  { path: 'mant-curso', component: MantCurso, title: 'Mantenimiento de Cursos' },
  { path: '**', redirectTo: 'cursos' },
];
