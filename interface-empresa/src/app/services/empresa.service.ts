import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { IEmpresa, IEndereco } from '../interfaces/IEmpresa';
import { IFaleConosco } from '../interfaces/IFaleConosco';

@Injectable({
  providedIn: 'root',
})
export class EmpresaService {
  private baseUrl = 'http://localhost:8080';

  public emitEvent = new EventEmitter();

  constructor(private http: HttpClient) {}

  public empresaList(): Observable<IEmpresa[]> {
    return this.http.get<IEmpresa[]>(`${this.baseUrl}/api/empresas`).pipe(
      catchError((error) => {
        if (error.status === 400) {
          return throwError(error);
        }
        return throwError('Ocorreu um erro desconhecido');
      })
    );
  }

  public addEmpresa(empresa: IEmpresa): Observable<IEmpresa> {
    console.log({ ...empresa });
    return this.http
      .post<IEmpresa>(`${this.baseUrl}/api/empresas`, { ...empresa })
      .pipe(
        catchError((error) => {
          if (error.status === 400) {
            return throwError(error);
          }
          return throwError('Ocorreu um erro desconhecido');
        })
      );
  }

  public addEndereco(endereco: IEndereco, id: number): Observable<IEndereco> {
    return this.http
      .post<IEndereco>(`${this.baseUrl}/api/enderecos/${id}`, { ...endereco })
      .pipe(
        catchError((error) => {
          if (error.status === 400) {
            return throwError(error);
          }
          return throwError('Ocorreu um erro desconhecido');
        })
      );
  }

  public enviarPergunta(
    pergunta: IFaleConosco,
    id: number
  ): Observable<IFaleConosco> {
    return this.http
      .post<IFaleConosco>(`${this.baseUrl}/api/faleconosco/${id}`, {
        ...pergunta,
      })
      .pipe(
        catchError((error) => {
          if (error.status === 400) {
            return throwError(error);
          }
          return throwError('Ocorreu um erro desconhecido');
        })
      );
  }
}
