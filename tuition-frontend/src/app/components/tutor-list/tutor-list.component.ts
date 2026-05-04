import { Component, OnInit, ViewChild } from '@angular/core';
import { TutorService } from '../../services/tutor.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-tutor-list',
  templateUrl: './tutor-list.component.html',
  styleUrls: ['./tutor-list.component.css']
})
export class TutorListComponent implements OnInit {
  tutors: any[] = [];
  cityPageName = '';
  isLoading = false;
  
  // Pagination
  totalElements = 0;
  pageSize = 10;
  currentPage = 0;
  
  // Filters
  filters: any = {
    subjects: '',
    classLevel: '',
    city: '',
    minPrice: 0,
    maxPrice: 2000
  };

  // Sorting
  sortBy = 'id';
  sortDirection = 'desc';

  subjectsList = ['Maths', 'Physics', 'Chemistry', 'Biology', 'English', 'Computer Science', 'Accounts', 'Economics'];
  classLevels = ['Class 1-5', 'Class 6-8', 'Class 9-10', 'Class 11-12', 'College/Degree'];

  constructor(
    private tutorService: TutorService,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private titleService: Title,
    private metaService: Meta
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const cityParam = params['city'];
      if (cityParam) {
        this.filters.city = cityParam.charAt(0).toUpperCase() + cityParam.slice(1);
        this.updateSEOMetadata(this.filters.city);
        this.search();
      }
    });

    this.route.queryParams.subscribe(params => {
      if (params['subject']) this.filters.subjects = params['subject'];
      if (params['classLevel']) this.filters.classLevel = params['classLevel'];
      if (params['city']) {
        this.filters.city = params['city'];
        this.updateSEOMetadata(this.filters.city);
      }
      this.search();
    });
  }

  updateSEOMetadata(city: string) {
    this.cityPageName = city;
    const title = `Best Home Tutors in ${city} | Verified Private Teachers`;
    const description = `Find qualified and verified home tutors in ${city} for all subjects and classes. Compare profiles, ratings, and fees. Book a free demo class today!`;
    
    this.titleService.setTitle(title);
    this.metaService.updateTag({ name: 'description', content: description });
    this.metaService.updateTag({ property: 'og:title', content: title });
    this.metaService.updateTag({ property: 'og:description', content: description });
  }

  search() {
    this.isLoading = true;
    this.tutorService.search(this.filters, this.currentPage, this.pageSize, this.sortBy, this.sortDirection).subscribe({
      next: (res) => {
        this.tutors = res.content;
        this.totalElements = res.totalElements;
        this.isLoading = false;
      },
      error: (err) => {
        this.isLoading = false;
        this.snackBar.open('Error fetching tutors', 'Close', { duration: 3000 });
      }
    });
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.search();
  }

  applyFilters() {
    this.currentPage = 0;
    this.search();
  }

  onSortChange(event: any) {
    const val = event.value;
    if (val === 'low') {
      this.sortBy = 'hourlyRate';
      this.sortDirection = 'asc';
    } else if (val === 'high') {
      this.sortBy = 'hourlyRate';
      this.sortDirection = 'desc';
    } else {
      this.sortBy = 'id';
      this.sortDirection = 'desc';
    }
    this.search();
  }
}
