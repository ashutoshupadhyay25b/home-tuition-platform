import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  // --- Auth Methods ---
  registerUser(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/register`, userData);
  }

  loginUser(credentials: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/login`, credentials);
  }

  // --- Tutor Methods ---
  createTutorProfile(userId: number, profileData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/tutors/profile?userId=${userId}`, profileData);
  }

  searchTutors(filters: { subjects?: string, classLevel?: string, city?: string, minPrice?: number, maxPrice?: number }): Observable<any> {
    let params = [];
    if (filters.subjects) params.push(`subjects=${filters.subjects}`);
    if (filters.classLevel) params.push(`classLevel=${filters.classLevel}`);
    if (filters.city) params.push(`city=${filters.city}`);
    if (filters.minPrice) params.push(`minPrice=${filters.minPrice}`);
    if (filters.maxPrice) params.push(`maxPrice=${filters.maxPrice}`);
    
    const queryString = params.length > 0 ? `?${params.join('&')}` : '';
    return this.http.get(`${this.baseUrl}/tutors/search${queryString}`);
  }

  // --- Request Methods ---
  sendRequest(requestData: { studentId: number, tutorId: number }): Observable<any> {
    return this.http.post(`${this.baseUrl}/requests`, requestData);
  }

  getRequestsByStudent(studentId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/requests/student/${studentId}`);
  }

  getRequestsByTutor(tutorId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/requests/tutor/${tutorId}`);
  }

  updateRequestStatus(requestId: number, status: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/requests/${requestId}/status`, { status });
  }

  updateUserRole(userId: number, role: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/auth/role/${userId}?role=${role}`, {});
  }
}
