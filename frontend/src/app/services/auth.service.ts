import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router, private http: HttpClient) { }

  login(uname: string, pword: string): Observable<number> {
    const url = `http://localhost:8080/users/get/${uname}`;
    return this.http.get(url).pipe(
      map((response: any) => {
        const user = response.data.user;
        if (user.password === pword) {
          return 200;
        } else {
          return 403;
        }
      }),
      catchError(() => {
        return throwError(() => new Error('Invalid credentials'));
      })
    );
  }
  logout() {
    this.router.navigate(['login']);
  }
}
