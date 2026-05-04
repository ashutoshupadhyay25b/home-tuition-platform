import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TutorService {
  private baseUrl = `${environment.apiUrl}/tutors`;

  constructor(private http: HttpClient) { }

  getProfile(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createProfile(userId: number, profileData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/profile?userId=${userId}`, profileData);
  }

  search(filters: any, page: number = 0, size: number = 10, sortBy: string = 'id', direction: string = 'desc'): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sortBy', sortBy)
      .set('direction', direction);

    if (filters.subjects) params = params.set('subjects', filters.subjects);
    if (filters.classLevel) params = params.set('classLevel', filters.classLevel);
    if (filters.city) params = params.set('city', filters.city);
    if (filters.minPrice != null) params = params.set('minPrice', filters.minPrice.toString());
    if (filters.maxPrice != null) params = params.set('maxPrice', filters.maxPrice.toString());

    return this.http.get(`${this.baseUrl}/search`, { params });
  }

  getReviews(tutorUserId: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/reviews/tutor/${tutorUserId}`);
  }

  postReview(tutorUserId: number, studentId: number, review: any): Observable<any> {
    return this.http.post(`${environment.apiUrl}/reviews/tutor/${tutorUserId}?studentId=${studentId}`, review);
  }
}
