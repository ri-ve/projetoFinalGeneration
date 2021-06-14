import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Grupo } from '../model/Grupo';
import { InscricaoGrupo } from '../model/InscricaoGrupo';
import { PostagemQuiz } from '../model/PostagemQuiz';

@Injectable({
  providedIn: 'root'
})
export class GrupoPostService {

  constructor(private http:HttpClient) { }
  token = {
    headers: new HttpHeaders().set('Authorization',environment.token)

  }


  getGrupoById(id: number): Observable<Grupo>{
    return this.http.get<Grupo>(`http://localhost:8080/grupo/${id}`, this.token)
  }
  postPostagemQuiz(postagem: PostagemQuiz): Observable<PostagemQuiz>{
    return this.http.post<PostagemQuiz>('http://localhost:8080/postagem-quiz', postagem, this.token)
  }  

  postInscricao(inscricao: InscricaoGrupo): Observable<InscricaoGrupo>{
    return this.http.post<InscricaoGrupo>('http://localhost:8080/usuario/inscricao', inscricao, this.token)
  }
}

