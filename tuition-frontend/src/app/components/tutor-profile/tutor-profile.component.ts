import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TutorService } from '../../services/tutor.service';
import { AuthService } from '../../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tutor-profile',
  templateUrl: './tutor-profile.component.html',
  styleUrls: ['./tutor-profile.component.css']
})
export class TutorProfileComponent implements OnInit {
  profileForm: FormGroup;
  currentUser: any;

  subjects = [
    'Mathematics', 'Physics', 'Chemistry', 'Biology', 
    'English', 'Computer Science', 'History', 'Geography',
    'Accountancy', 'Economics', 'Coding (Java/Python)', 'Spoken English'
  ];

  classLevels = [
    'Class 1-5 (Primary)', 
    'Class 6-8 (Lower Secondary)', 
    'Class 9-10 (Secondary)', 
    'Class 11-12 (Higher Secondary)', 
    'Engineering/Medical Entrance',
    'College/University Level'
  ];

  teachingModes = ['Online', 'At Student\'s Home', 'At My Home', 'Both Online & Offline'];

  constructor(
    private fb: FormBuilder,
    private tutorService: TutorService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.profileForm = this.fb.group({
      subject: ['', Validators.required],
      classLevel: ['', Validators.required],
      fees: ['', [Validators.required, Validators.min(100)]],
      location: ['', Validators.required],
      experience: ['', [Validators.required, Validators.min(0)]],
      teachingMode: ['Online', Validators.required]
    });
  }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    if (!this.currentUser) {
      this.router.navigate(['/login']);
    }
  }

  upgradeAccount() {
    if (this.currentUser) {
      this.authService.updateRole(this.currentUser.id, 'TUTOR').subscribe({
        next: (res) => {
          this.currentUser.role = 'TUTOR';
          localStorage.setItem('user', JSON.stringify(this.currentUser));
          this.snackBar.open('Account Upgraded to Tutor! You can now save your profile.', 'Close', { duration: 5000 });
        },
        error: (err) => {
          this.snackBar.open('Failed to upgrade account. Please try again.', 'Close', { duration: 5000 });
        }
      });
    }
  }

  onSubmit() {
    if (this.profileForm.valid && this.currentUser) {
      if (this.currentUser.role !== 'TUTOR') {
        this.snackBar.open('Please upgrade your account to Tutor first.', 'Close', { duration: 5000 });
        return;
      }
      this.tutorService.createProfile(this.currentUser.id, this.profileForm.value).subscribe({
        next: (res) => {
          this.snackBar.open('Professional Profile Saved Successfully!', 'Close', { 
            duration: 5000
          });
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          console.error('Profile Update Error:', err);
          const message = err.error?.message || 'Failed to save profile. Please try again.';
          this.snackBar.open(message, 'Close', { duration: 7000 });
        }
      });
    }
  }
}
