import { HttpInterceptorFn } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { finalize } from 'rxjs';

// cuenta las peticiones al backend que siguen esperando respuesta
@Injectable({ providedIn: 'root' })
export class Cargando {
  pendientes = signal(0);
}

export const cargandoInterceptor: HttpInterceptorFn = (req, next) => {
  const cargando = inject(Cargando);
  cargando.pendientes.update((n) => n + 1);
  return next(req).pipe(finalize(() => cargando.pendientes.update((n) => n - 1)));
};
