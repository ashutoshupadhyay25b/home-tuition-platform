import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  userName = '';

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.checkAuth();
    // Watch for login changes (simplified for now)
    setInterval(() => this.checkAuth(), 2000);
  }

  checkAuth() {
    const user = localStorage.getItem('user');
    if (user) {
      this.isLoggedIn = true;
      this.userName = JSON.parse(user).name;
    } else {
      this.isLoggedIn = false;
    }
  }

  logout() {
    localStorage.removeItem('user');
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }
}
