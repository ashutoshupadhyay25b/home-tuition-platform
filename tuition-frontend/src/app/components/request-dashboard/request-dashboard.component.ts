import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
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

  constructor(
    private apiService: ApiService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    const userStr = localStorage.getItem('user');
    if (userStr) {
      this.currentUser = JSON.parse(userStr);
      this.loadRequests();
      this.setupColumns();
    }
  }

  setupColumns() {
    if (this.currentUser.role === 'STUDENT') {
      this.displayedColumns = ['tutorName', 'status'];
    } else {
      this.displayedColumns = ['studentName', 'status', 'actions'];
    }
  }

  loadRequests() {
    this.loading = true;
    const obs = this.currentUser.role === 'STUDENT' 
      ? this.apiService.getRequestsByStudent(this.currentUser.id)
      : this.apiService.getRequestsByTutor(this.currentUser.id);

    obs.subscribe({
      next: (res) => {
        this.requests = res;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.snackBar.open('Failed to load requests', 'Close', { duration: 3000 });
      }
    });
  }

  updateStatus(requestId: number, status: string) {
    this.apiService.updateRequestStatus(requestId, status).subscribe({
      next: (res) => {
        this.snackBar.open(`Request ${status.toLowerCase()}ed!`, 'Close', { duration: 3000 });
        this.loadRequests(); // Refresh list
      },
      error: (err) => {
        this.snackBar.open('Action failed', 'Close', { duration: 3000 });
      }
    });
  }
}
