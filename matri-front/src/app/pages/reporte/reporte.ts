import { Component, OnInit, inject, signal } from '@angular/core';
import { ApiService } from '../../api.service';
import { Especialidad, FilaReporte } from '../../models';

// Punto 3: cantidad de alumnos por curso y si inicia o no
@Component({
  selector: 'app-reporte',
  templateUrl: './reporte.html',
})
export class Reporte implements OnInit {
  private api = inject(ApiService);

  especialidades = signal<Especialidad[]>([]);
  filas = signal<FilaReporte[]>([]);
  elegida = signal('');

  ngOnInit() {
    this.api.especialidades().subscribe((lista) => this.especialidades.set(lista));
  }

  cambiarEspecialidad(codesp: string) {
    this.elegida.set(codesp);
    if (!codesp) {
      this.filas.set([]);
      return;
    }
    this.api.reporte(codesp).subscribe((lista) => this.filas.set(lista));
  }
}
