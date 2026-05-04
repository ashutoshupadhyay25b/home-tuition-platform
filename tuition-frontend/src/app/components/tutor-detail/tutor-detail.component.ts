import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TutorService } from '../../services/tutor.service';
import { RequestService } from '../../services/request.service';
import { AuthService } from '../../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-tutor-detail',
  templateUrl: './tutor-detail.component.html',
  styleUrls: ['./tutor-detail.component.css']
})
export class TutorDetailComponent implements OnInit {
  tutor: any;
  isLoading = true;
  relatedTutors: any[] = [];
  reviews: any[] = [];
  
  newReview = {
    rating: 5,
    comment: ''
  };
  isSubmittingReview = false;

  constructor(
    private route: ActivatedRoute,
    private tutorService: TutorService,
    private requestService: RequestService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.loadTutorProfile(id);
      }
    });
  }

  loadTutorProfile(id: number) {
    this.isLoading = true;
    this.tutorService.getProfile(id).subscribe({
      next: (res) => {
        this.tutor = res;
        this.isLoading = false;
        this.loadReviews();
        this.loadRelatedTutors();
      },
      error: (err) => {
        this.isLoading = false;
        this.snackBar.open('Error loading tutor profile', 'Close', { duration: 3000 });
        this.router.navigate(['/tutors']);
      }
    });
  }

  loadReviews() {
    this.tutorService.getReviews(this.tutor.userId).subscribe(res => {
      this.reviews = res;
    });
  }

  loadRelatedTutors() {
    this.tutorService.search({ subjects: this.tutor.subjects, city: this.tutor.city }).subscribe(res => {
      this.relatedTutors = res.content.filter((t: any) => t.id !== this.tutor.id).slice(0, 3);
    });
  }

  submitReview() {
    const user = this.authService.getCurrentUser();
    if (!user) {
      this.snackBar.open('Please login to write a review', 'Close', { duration: 3000 });
      this.router.navigate(['/login']);
      return;
    }

    if (!this.newReview.comment) {
      this.snackBar.open('Please write a comment', 'Close', { duration: 3000 });
      return;
    }

    this.isSubmittingReview = true;
    this.tutorService.postReview(this.tutor.userId, user.id, this.newReview).subscribe({
      next: (res) => {
        this.isSubmittingReview = false;
        this.snackBar.open('Review posted successfully!', 'Close', { duration: 3000 });
        this.newReview = { rating: 5, comment: '' };
        this.loadReviews();
        this.loadTutorProfile(this.tutor.id); // Reload to update average
      },
      error: (err) => {
        this.isSubmittingReview = false;
        this.snackBar.open('Failed to post review', 'Close', { duration: 3000 });
      }
    });
  }

  sendRequest() {
    const user = this.authService.getCurrentUser();
    if (!user) {
      this.snackBar.open('Please login to send a request', 'Close', { duration: 3000 });
      this.router.navigate(['/login']);
      return;
    }

    this.requestService.send({ studentId: user.id, tutorId: this.tutor.id }).subscribe({
      next: () => {
        this.snackBar.open('Request sent successfully!', 'Close', { duration: 5000 });
      },
      error: (err) => {
        this.snackBar.open('Failed to send request', 'Close', { duration: 3000 });
      }
    });
  }
}
