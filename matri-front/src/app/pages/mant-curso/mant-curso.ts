import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../../api.service';
import { Curso, Especialidad, Respuesta } from '../../models';

const CURSO_VACIO: Curso = { codcur: '', codes: '', nomcur: '', costo: 0, nrovac: 0 };

// Punto 4: mantenimiento de la tabla curso
@Component({
  selector: 'app-mant-curso',
  templateUrl: './mant-curso.html',
})
export class MantCurso implements OnInit {
  private api = inject(ApiService);

  especialidades = signal<Especialidad[]>([]);
  cursos = signal<Curso[]>([]);
  mensaje = signal<Respuesta | null>(null);

  form = signal<Curso>({ ...CURSO_VACIO });
  editando = signal(false); // true = editar, false = nuevo

  // para mostrar el nombre de la especialidad en la tabla
  nombreEsp = computed(() => {
    const mapa = new Map(this.especialidades().map((e) => [e.code, e.nomes]));
    return (code: string) => mapa.get(code) ?? code;
  });

  ngOnInit() {
    this.api.especialidades().subscribe((lista) => this.especialidades.set(lista));
    this.listar();
  }

  listar() {
    this.api.cursos().subscribe((lista) => this.cursos.set(lista));
  }

  campo<K extends keyof Curso>(nombre: K, valor: Curso[K]) {
    this.form.update((f) => ({ ...f, [nombre]: valor }));
  }

  nuevo() {
    this.form.set({ ...CURSO_VACIO });
    this.editando.set(false);
    this.mensaje.set(null);
  }

  editar(c: Curso) {
    this.form.set({ ...c });
    this.editando.set(true);
    this.mensaje.set(null);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  grabar() {
    const c = this.form();
    if (!c.codcur.trim() || !c.codes || !c.nomcur.trim()) {
      this.mensaje.set({ ok: false, mensaje: 'Complete código, especialidad y nombre del curso' });
      return;
    }
    const peticion: Observable<Respuesta> = this.editando()
      ? this.api.editarCurso(c)
      : this.api.crearCurso({ ...c, codcur: c.codcur.trim().toUpperCase() });
    peticion.subscribe((r) => this.terminar(r));
  }

  eliminar(c: Curso) {
    if (!confirm(`¿Eliminar el curso ${c.codcur} - ${c.nomcur}?`)) return;
    this.api.eliminarCurso(c.codcur).subscribe((r) => this.terminar(r));
  }

  private terminar(r: Respuesta) {
    if (r.ok) this.nuevo();
    this.mensaje.set(r);
    this.listar();
  }
}
