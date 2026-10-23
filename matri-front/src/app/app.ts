import { Component, effect, inject, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { Cargando } from './cargando';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
})
export class App {
  private cargando = inject(Cargando);

  // el servidor gratis de Render se apaga si nadie lo usa y tarda en despertar;
  // el aviso sale solo si el backend demora mas de 2 segundos en responder
  lento = signal(false);
  private espera?: ReturnType<typeof setTimeout>;

  constructor() {
    effect(() => {
      if (this.cargando.pendientes() > 0) {
        this.espera ??= setTimeout(() => this.lento.set(true), 2000);
      } else {
        clearTimeout(this.espera);
        this.espera = undefined;
        this.lento.set(false);
      }
    });
  }
}
