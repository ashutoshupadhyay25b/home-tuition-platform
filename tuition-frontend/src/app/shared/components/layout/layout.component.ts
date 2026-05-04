import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  template: `
    <div class="app-wrapper">
      <app-navbar></app-navbar>
      <main class="content-area">
        <router-outlet></router-outlet>
      </main>
      <app-footer></app-footer>
    </div>
  `,
  styles: [`
    .app-wrapper {
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }
    .content-area {
      flex: 1;
      background-color: #ffffff;
    }
  `]
})
export class LayoutComponent implements OnInit {
  constructor() { }
  ngOnInit(): void { }
}
