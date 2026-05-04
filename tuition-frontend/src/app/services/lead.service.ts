import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LeadService {
  private baseUrl = `${environment.apiUrl}/requirements`;

  constructor(private http: HttpClient) { }

  post(studentId: number, data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/${studentId}`, data);
  }

  getActive(): Observable<any> {
    return this.http.get(`${this.baseUrl}/active`);
  }

  getByStudent(studentId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/student/${studentId}`);
  }
}
