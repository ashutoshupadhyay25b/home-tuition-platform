import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['STUDENT', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.registerForm.valid) {
      this.isLoading = true;
      this.apiService.registerUser(this.registerForm.value).subscribe({
        next: (res) => {
          this.isLoading = false;
          this.snackBar.open('Registration Successful!', 'Close', { duration: 3000 });
          this.router.navigate(['/login']);
        },
        error: (err) => {
          this.isLoading = false;
          console.error('Registration error:', err);
          this.snackBar.open('Registration Failed: ' + (err.error?.message || 'Email already exists or server is waking up. Please try again in 30 seconds.'), 'Close', { duration: 5000 });
        }
      });
    }
  }
}
