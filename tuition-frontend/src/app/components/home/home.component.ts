import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  searchQuery = {
    subject: '',
    classLevel: '',
    city: ''
  };

  popularSubjects = [
    { name: 'Mathematics', icon: 'functions', count: '500+ Tutors' },
    { name: 'Physics', icon: 'science', count: '300+ Tutors' },
    { name: 'English', icon: 'menu_book', count: '450+ Tutors' },
    { name: 'Chemistry', icon: 'biotech', count: '280+ Tutors' },
    { name: 'Biology', icon: 'eco', count: '200+ Tutors' },
    { name: 'Computer Science', icon: 'code', count: '150+ Tutors' }
  ];

  featuredTutors = [
    { name: 'Dr. Amit Singh', subject: 'Mathematics', rate: '₹500/hr', rating: 4.9, image: 'assets/tutor1.jpg' },
    { name: 'Sarah Khan', subject: 'English', rate: '₹400/hr', rating: 4.8, image: 'assets/tutor2.jpg' },
    { name: 'Rahul Verma', subject: 'Physics', rate: '₹600/hr', rating: 4.9, image: 'assets/tutor3.jpg' }
  ];

  testimonials = [
    { text: 'Found the perfect math tutor for my son. His grades improved significantly within 2 months!', author: 'Priya Sharma', role: 'Parent' },
    { text: 'The platform is so easy to use. I found a physics tutor in my neighborhood in just 5 minutes.', author: 'Vikram Das', role: 'Student' }
  ];

  constructor(private router: Router) { }

  ngOnInit(): void { }

  onSearch() {
    this.router.navigate(['/tutors'], { 
      queryParams: { 
        subject: this.searchQuery.subject, 
        classLevel: this.searchQuery.classLevel 
      } 
    });
  }
}
