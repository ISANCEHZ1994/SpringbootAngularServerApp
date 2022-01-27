import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { CustomResponse } from '../interface/custom-response';
import { Observable, throwError } from 'rxjs';
import { tap,catchError } from 'rxjs/operators';
import { Server } from '../interface/server';

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
