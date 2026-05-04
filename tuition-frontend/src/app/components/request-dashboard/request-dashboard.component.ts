import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../services/request.service';
import { AuthService } from '../../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-request-dashboard',
  templateUrl: './request-dashboard.component.html',
  styleUrls: ['./request-dashboard.component.css']
})
export class RequestDashboardComponent implements OnInit {
  requests: any[] = [];
  currentUser: any;
  loading: boolean = false;
  displayedColumns: string[] = [];
  
  stats = {
    total: 0,
    pending: 0,
    accepted: 0,
    rejected: 0
  };

  constructor(
    private requestService: RequestService,
    private authService: AuthService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    if (this.currentUser) {
      this.loadRequests();
      this.setupColumns();
    }
  }

  setupColumns() {
    if (this.currentUser.role === 'STUDENT') {
      this.displayedColumns = ['tutorName', 'date', 'status'];
    } else {
      this.displayedColumns = ['studentName', 'date', 'status', 'actions'];
    }
  }

  loadRequests() {
    this.loading = true;
    const obs = this.currentUser.role === 'STUDENT' 
      ? this.requestService.getByStudent(this.currentUser.id)
      : this.requestService.getByTutor(this.currentUser.id);

    obs.subscribe({
      next: (res) => {
        this.requests = res;
        this.calculateStats();
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.snackBar.open('Failed to load requests', 'Close', { duration: 3000 });
      }
    });
  }

  calculateStats() {
    this.stats.total = this.requests.length;
    this.stats.pending = this.requests.filter(r => r.status === 'PENDING').length;
    this.stats.accepted = this.requests.filter(r => r.status === 'ACCEPTED').length;
    this.stats.rejected = this.requests.filter(r => r.status === 'REJECTED').length;
  }

  updateStatus(requestId: number, status: string) {
    this.requestService.updateStatus(requestId, status).subscribe({
      next: (res) => {
        this.snackBar.open(`Request ${status.toLowerCase()}ed successfully!`, 'Close', { 
          duration: 3000,
          panelClass: status === 'ACCEPTED' ? ['success-snackbar'] : ['error-snackbar']
        });
        this.loadRequests();
      },
      error: (err) => {
        this.snackBar.open('Action failed. Please try again.', 'Close', { duration: 3000 });
      }
    });
  }
}
