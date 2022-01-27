import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { CustomResponse } from '../interface/custom-response';
import { Observable, Subscriber, throwError } from 'rxjs';
import { tap,catchError } from 'rxjs/operators';
import { Server } from '../interface/server';
import { Status } from '../enum/status.enum';

@Injectable({
  providedIn: 'root'
})
export class ServerService {
  private readonly apiURL = 'any';

  constructor(private http: HttpClient) { }
  // getServers(): Observable<CustomResponse>{     
  //   return this.http.get<CustomResponse>(`http://localhost:8080/server/list`);
  // };

  // define an observable use dollar sign
  servers$ = <Observable <CustomResponse>>
  this.http.get<CustomResponse>(`${this.apiURL}/server/list`)
  .pipe(
      tap(console.log),
      catchError(this.handleError)
  );
  
  save$ = (server: Server) => <Observable <CustomResponse>>
  this.http.post<CustomResponse>(`${this.apiURL}/server/save`, server) // bring the whole server object with all the data
  .pipe(
      tap(console.log),
      catchError(this.handleError)
  );
  
  ping$ = (ipAddress: string) => <Observable <CustomResponse>>
  this.http.get<CustomResponse>(`${this.apiURL}/server/ping/${ipAddress}`) 
  .pipe(
      tap(console.log),
      catchError(this.handleError)
  );

  filter$ = (status: Status, response: CustomResponse) => <Observable <CustomResponse>>
  new Observable<CustomResponse>(
    subscriber => {
      console.log(response);
      subscriber.next(
        // note the terrinaries - where the strings end and start - 
        status === Status.ALL ? { ...response, message: `Servers filtered by ${status} status` } :
        { 
          ...response, 
          message: response.data.servers
          .filter( server => server.status === status ).length > 0 ? 
          `Servers filtered by ${ status === Status.SERVER_UP ? 'SERVER_UP' : 'SERVER_DOWN' } status` : 
          `No servers of ${status} found`, 
          data: { 
            servers: response.data.servers
            .filter( server => server.status === status) 
          }
        } 
      );
        subscriber.complete();
    }
  )
  // this.http.get<CustomResponse>(`${this.apiURL}/server/ping/${ipAddress}`) 
  .pipe(
      tap(console.log),
      catchError(this.handleError)
  );

  delete$ = (id: number) => <Observable <CustomResponse>>
  this.http.delete<CustomResponse>(`${this.apiURL}/server/delete/${id}`) 
  .pipe(
      tap(console.log),
      catchError(this.handleError)
  );

  private handleError(e: HttpErrorResponse): Observable<never>{
    console.log(e);
    return throwError(`An error occurred - Error Code: ${e.status}`);
  };

};
