import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Atividades } from '../model/Atividades';
import { PostagemQuiz } from '../model/PostagemQuiz';
import { Usuario } from '../model/Usuario';

@Injectable({
  providedIn: 'root'
})
export class FeedServiceService {

  constructor(
    private http: HttpClient
      ) { }

      token = {
        headers: new HttpHeaders().set('Authorization', environment.token)
      }

      getPostagemByUsuario(id: number): Observable<PostagemQuiz[]>{
        return this.http.get<PostagemQuiz[]>(`http://localhost:8080/postagem-quiz/buscar-post/${id}`, this.token)
      }

      getAtividadesByUsuario(id: number): Observable<Atividades[]>{
        return this.http.get<Atividades[]>(`http://localhost:8080/atividades/atividades-amigos/${id}`, this.token)
      }

      postAtividade(atividade: Atividades): Observable<Atividades>{
        return this.http.post<Atividades>('http://localhost:8080/usuario/post-pensamentos', atividade, this.token)
      }

      getAllPostQuiz(): Observable<PostagemQuiz[]>{
        return this.http.get<PostagemQuiz[]>('http://localhost:8080/postagem-quiz',this.token)
      }
}
