import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import {catchError, Observable} from 'rxjs';
import {SessionService} from "./session.service";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private sessionService: SessionService) {
  }

  // private static handleError(error: any): Observable<never> {
  //   let errorMessage: string;
  //   console.log('handling error');
  //   console.log(error);
  //   if (error instanceof ErrorEvent) {
  //     // client-side error
  //     errorMessage = `Client-side error: ${error.error.message}`;
  //   } else {
  //     // backend error
  //     errorMessage = `Server-side error: ${error.status} ${error.message}`;
  //   }
  //
  //   if (error?.error?.code) {
  //     errorMessage = error.error.message;
  //     Swal.fire({
  //       title: error.error.message,
  //       icon: 'error',
  //       showDenyButton: false,
  //       showCancelButton: false,
  //       confirmButtonText: `Cerrar`
  //     }).then(() => {});
  //   }
  //
  // return throwError(errorMessage);
  // }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.sessionService.authorizationHeader) {
      const req2 = req.clone({
        headers: req.headers.set('Authorization', `Basic ${this.sessionService.authorizationHeader}`)
      });
      // @ts-ignore
      return next.handle(req2).pipe(catchError(error => AuthInterceptorService.handleError(error)));
    }

    // @ts-ignore
    return next.handle(req).pipe(catchError(error => AuthInterceptorService.handleError(error)));
  }
}
