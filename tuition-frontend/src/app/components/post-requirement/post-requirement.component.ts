import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LeadService } from '../../services/lead.service';
import { AuthService } from '../../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-requirement',
  templateUrl: './post-requirement.component.html',
  styleUrls: ['./post-requirement.component.css']
})
export class PostRequirementComponent implements OnInit {
  reqForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private leadService: LeadService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.reqForm = this.fb.group({
      subject: ['', Validators.required],
      classLevel: ['', Validators.required],
      city: ['', Validators.required],
      budget: ['', [Validators.required, Validators.min(100)]],
      description: ['', [Validators.required, Validators.minLength(20)]]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.reqForm.valid) {
      const user = this.authService.getCurrentUser();
      if (!user) {
        this.router.navigate(['/login']);
        return;
      }

      this.isLoading = true;
      this.leadService.post(user.id, this.reqForm.value).subscribe({
        next: () => {
          this.isLoading = false;
          this.snackBar.open('Requirement Posted Successfully! Tutors will contact you soon.', 'Close', { duration: 5000 });
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          this.isLoading = false;
          this.snackBar.open('Failed to post requirement', 'Close', { duration: 3000 });
        }
      });
    }
  }
}
