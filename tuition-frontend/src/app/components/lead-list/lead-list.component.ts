import { Component, OnInit } from '@angular/core';
import { LeadService } from '../../services/lead.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-lead-list',
  templateUrl: './lead-list.component.html',
  styleUrls: ['./lead-list.component.css']
})
export class LeadListComponent implements OnInit {
  leads: any[] = [];
  isLoading = false;

  constructor(
    private leadService: LeadService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loadLeads();
  }

  loadLeads() {
    this.isLoading = true;
    this.leadService.getActive().subscribe({
      next: (res) => {
        this.leads = res;
        this.isLoading = false;
      },
      error: (err) => {
        this.isLoading = false;
        this.snackBar.open('Failed to load leads', 'Close', { duration: 3000 });
      }
    });
  }

  respondToLead(leadId: number) {
    this.snackBar.open('Responding feature coming soon! You can contact the student directly.', 'Close', { duration: 5000 });
  }
}
