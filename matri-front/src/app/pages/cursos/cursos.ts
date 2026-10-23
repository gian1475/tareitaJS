import { Component, OnInit, inject, input, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ApiService } from '../../api.service';
import { Curso, Especialidad } from '../../models';

// Punto 1: cursos de extension por especialidad
@Component({
  selector: 'app-cursos',
  imports: [RouterLink],
  templateUrl: './cursos.html',
})
export class Cursos implements OnInit {
  private api = inject(ApiService);
  private router = inject(Router);

  // ?esp=01 en la URL, para volver con la especialidad ya elegida
  esp = input<string>();

  especialidades = signal<Especialidad[]>([]);
  cursos = signal<Curso[]>([]);
  elegida = signal('');
  error = signal('');

  ngOnInit() {
    this.api.especialidades().subscribe({
      next: (lista) => this.especialidades.set(lista),
      error: () => this.error.set('No se pudo conectar con el servidor'),
    });
    if (this.esp()) this.cambiarEspecialidad(this.esp()!);
  }

  cambiarEspecialidad(codesp: string) {
    this.elegida.set(codesp);
    this.router.navigate([], { queryParams: { esp: codesp || null } });
    if (!codesp) {
      this.cursos.set([]);
      return;
    }
    this.api.cursosPorEspecialidad(codesp).subscribe((lista) => this.cursos.set(lista));
  }
}
