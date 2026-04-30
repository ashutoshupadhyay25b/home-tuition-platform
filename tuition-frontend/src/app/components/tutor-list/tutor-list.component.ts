import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-tutor-list',
  templateUrl: './tutor-list.component.html',
  styleUrls: ['./tutor-list.component.css']
})
export class TutorListComponent implements OnInit {
  tutors: any[] = [];
  subject: string = '';
  classLevel: string = '';
  currentUser: any;
  loading: boolean = false;

  constructor(
    private apiService: ApiService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    const userStr = localStorage.getItem('user');
    if (userStr) {
      this.currentUser = JSON.parse(userStr);
    }
  }

  onSearch() {
    if (!this.subject || !this.classLevel) {
      this.snackBar.open('Please enter both subject and class level', 'Close', { duration: 3000 });
      return;
    }

    this.loading = true;
    this.apiService.searchTutors(this.subject, this.classLevel).subscribe({
      next: (res) => {
        this.tutors = res;
        this.loading = false;
        if (this.tutors.length === 0) {
          this.snackBar.open('No tutors found for your search', 'Close', { duration: 3000 });
        }
      },
      error: (err) => {
        this.loading = false;
        this.snackBar.open('Error fetching tutors', 'Close', { duration: 3000 });
      }
    });
  }

  requestTutor(tutorId: number) {
    if (!this.currentUser) {
      this.snackBar.open('Please login first to send requests', 'Close', { duration: 3000 });
      return;
    }

    if (this.currentUser.id === tutorId) {
      this.snackBar.open('You cannot request yourself!', 'Close', { duration: 3000 });
      return;
    }

    const requestData = {
      studentId: this.currentUser.id,
      tutorId: tutorId
    };

    this.apiService.sendRequest(requestData).subscribe({
      next: (res) => {
        this.snackBar.open('Request sent successfully!', 'Close', { duration: 3000 });
      },
      error: (err) => {
        this.snackBar.open('Failed to send request', 'Close', { duration: 3000 });
      }
    });
  }
}
