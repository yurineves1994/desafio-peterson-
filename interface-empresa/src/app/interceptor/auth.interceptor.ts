import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
 intercept(req: HttpRequest<any>, next: HttpHandler) {
   const token = localStorage.getItem('token');

   if (token) {
     const cloned = req.clone({
       headers: req.headers.set("Authorization", "Bearer " + token)
     });

     return next.handle(cloned);
   } else {
     return next.handle(req);
   }
 }
}
