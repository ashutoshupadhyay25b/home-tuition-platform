import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.loginForm.valid) {
      this.apiService.loginUser(this.loginForm.value).subscribe({
        next: (res) => {
          localStorage.setItem('user', JSON.stringify(res));
          this.snackBar.open('Login Successful!', 'Close', { duration: 3000 });
          if (res.role === 'TUTOR') {
            this.router.navigate(['/tutor-profile']);
          } else {
            this.router.navigate(['/tutors']);
          }
        },
        error: (err) => {
          this.snackBar.open('Invalid Email or Password', 'Close', { duration: 3000 });
        }
      });
    }
  }
}
