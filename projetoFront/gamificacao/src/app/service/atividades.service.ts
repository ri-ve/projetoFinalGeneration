import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Atividades } from '../model/Atividades';


@Injectable({
  providedIn: 'root'
})
export class AtividadesService {

  constructor(private http: HttpClient) { }

token ={
  headers: new HttpHeaders().set('Authorization', environment.token)
}
getAtividadesById(id: number): Observable<Atividades>{
  return this.http.get<Atividades>(`http://localhost:8080/atividades/${id}`, this.token)
}

getAllAtividades(): Observable<Atividades[]>{
  return this.http.get<Atividades[]>('http://localhost:8080/atividades',this.token)
}
}