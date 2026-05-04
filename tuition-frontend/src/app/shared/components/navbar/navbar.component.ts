import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  get isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  get user() {
    return this.authService.getCurrentUser();
  }

  get userName(): string {
    return this.user?.name || 'User';
  }

  get userRole(): string {
    return this.user?.role || '';
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
