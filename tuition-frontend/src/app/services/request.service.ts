import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private baseUrl = `${environment.apiUrl}/requests`;

  constructor(private http: HttpClient) { }

  send(data: { studentId: number, tutorId: number }): Observable<any> {
    return this.http.post(this.baseUrl, data);
  }

  getByStudent(studentId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/student/${studentId}`);
  }

  getByTutor(tutorId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/tutor/${tutorId}`);
  }

  updateStatus(requestId: number, status: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/${requestId}/status`, { status });
  }
}
