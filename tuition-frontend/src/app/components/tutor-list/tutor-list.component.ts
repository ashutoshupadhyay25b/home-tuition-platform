import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-tutor-list',
  templateUrl: './tutor-list.component.html',
  styleUrls: ['./tutor-list.component.css']
})
export class TutorListComponent implements OnInit {
  tutors: any[] = [];
  filters = {
    subjects: '',
    classLevel: '',
    city: '',
    minPrice: null,
    maxPrice: null
  };
  isLoading = false;
  sortBy = 'rate-low';

  constructor(
    private apiService: ApiService,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Pick up filters from URL (from Homepage search)
    this.route.queryParams.subscribe(params => {
      if (params['subject']) this.filters.subjects = params['subject'];
      if (params['classLevel']) this.filters.classLevel = params['classLevel'];
      if (params['city']) this.filters.city = params['city'];
      this.search();
    });
  }

  search() {
    this.isLoading = true;
    this.apiService.searchTutors(this.filters).subscribe({
      next: (res) => {
        this.tutors = res;
        this.applySorting();
        this.isLoading = false;
      },
      error: (err) => {
        this.isLoading = false;
        this.snackBar.open('Error searching tutors', 'Close', { duration: 3000 });
      }
    });
  }

  applySorting() {
    if (this.sortBy === 'rate-low') {
      this.tutors.sort((a, b) => a.hourlyRate - b.hourlyRate);
    } else if (this.sortBy === 'rate-high') {
      this.tutors.sort((a, b) => b.hourlyRate - a.hourlyRate);
    } else if (this.sortBy === 'experience') {
      this.tutors.sort((a, b) => b.experience - a.experience);
    }
  }

  sendRequest(tutorId: number) {
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    if (!user.id) {
      this.snackBar.open('Please login to send requests', 'Close', { duration: 3000 });
      return;
    }

    this.apiService.sendRequest({ studentId: user.id, tutorId }).subscribe({
      next: () => {
        this.snackBar.open('Request sent successfully!', 'Close', { duration: 3000 });
      },
      error: (err) => {
        this.snackBar.open('Failed to send request', 'Close', { duration: 3000 });
      }
    });
  }
}
