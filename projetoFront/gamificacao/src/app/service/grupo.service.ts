import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Grupo } from '../model/Grupo';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllGrupos(): Observable<Grupo[]>{
    return this.http.get<Grupo[]>('http://localhost:8080/grupo',this.token)
  }

  getGrupoById(id: number): Observable<Grupo>{
    return this.http.get<Grupo>(`http://localhost:8080/grupo/${id}`)
  }

  postGrupo(grupo: Grupo): Observable<Grupo>{
    return this.http.post<Grupo>('http://localhost:8080/usuario/novo-grupo', grupo, this.token)
  }
}
