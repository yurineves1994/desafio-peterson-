import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private router: Router) {}

  public login(credentials: {
    login: string;
    password: string;
  }): Observable<{ token: string }> {
    return this.http
      .post<{ token: string }>(`${this.baseUrl}/auth/login`, credentials)
      .pipe(
        catchError((error) => {
          if (error.status === 400) {
            return throwError(error);
          }
          return throwError('Ocorreu um erro desconhecido');
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login'])
  }
}
