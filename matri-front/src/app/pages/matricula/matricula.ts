import { Component, OnInit, computed, inject, input, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../api.service';
import { Alumno, Curso, Respuesta, TIPOS_ALUMNO } from '../../models';

const POR_PAGINA = 5;

// Punto 2: matricula de alumnos de un curso
@Component({
  selector: 'app-matricula',
  imports: [RouterLink],
  templateUrl: './matricula.html',
})
export class Matricula implements OnInit {
  private api = inject(ApiService);

  // viene de la ruta /matricula/:codcur
  codcur = input.required<string>();

  tipos = TIPOS_ALUMNO;
  curso = signal<Curso | null>(null);
  alumnos = signal<Alumno[]>([]);
  mensaje = signal<Respuesta | null>(null);
  guardando = signal(false);

  // formulario
  nromat = signal('');
  apenom = signal('');
  tipo = signal('');

  // el pago se calcula segun el tipo de alumno y el costo del curso
  pago = computed(() => {
    const t = this.tipos.find((x) => x.valor === this.tipo());
    const costo = this.curso()?.costo ?? 0;
    return t ? costo * t.factor : 0;
  });

  // paginacion de la lista
  pagina = signal(1);
  totalPaginas = computed(() => Math.max(1, Math.ceil(this.alumnos().length / POR_PAGINA)));
  paginas = computed(() => Array.from({ length: this.totalPaginas() }, (_, i) => i + 1));
  alumnosPagina = computed(() => {
    const inicio = (this.pagina() - 1) * POR_PAGINA;
    return this.alumnos().slice(inicio, inicio + POR_PAGINA);
  });

  ngOnInit() {
    this.cargar();
  }

  cargar() {
    this.api.curso(this.codcur()).subscribe((c) => this.curso.set(c));
    this.api.alumnosPorCurso(this.codcur()).subscribe((lista) => {
      this.alumnos.set(lista);
      if (this.pagina() > this.totalPaginas()) this.pagina.set(this.totalPaginas());
    });
    this.api.siguienteNromat().subscribe((r) => this.nromat.set(r.nromat));
  }

  nuevo() {
    this.apenom.set('');
    this.tipo.set('');
    this.mensaje.set(null);
  }

  grabar() {
    if (!this.apenom().trim() || !this.tipo()) {
      this.mensaje.set({ ok: false, mensaje: 'Ingrese el nombre y elija el tipo de alumno' });
      return;
    }
    const alumno: Alumno = {
      codcur: this.codcur(),
      apenom: this.apenom().trim(),
      tipo: this.tipo(),
      pago: this.pago(),
    };
    this.guardando.set(true);
    this.api.matricular(alumno).subscribe({
      next: (r) => {
        this.guardando.set(false);
        this.mensaje.set(r);
        if (r.ok) {
          this.apenom.set('');
          this.tipo.set('');
          this.cargar();
        }
      },
      error: () => {
        this.guardando.set(false);
        this.mensaje.set({ ok: false, mensaje: 'No se pudo conectar con el servidor' });
      },
    });
  }

  eliminar(a: Alumno) {
    if (!confirm(`¿Anular la matrícula ${a.nromat} de ${a.apenom}?`)) return;
    this.api.anular(a.nromat!).subscribe((r) => {
      this.mensaje.set(r);
      this.cargar();
    });
  }
}
