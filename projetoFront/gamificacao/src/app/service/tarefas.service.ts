import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Tarefa } from '../model/Tarefa';
import { Usuario } from '../model/Usuario';

@Injectable({
  providedIn: 'root'
})
export class TarefasService {

  

  constructor(private http: HttpClient) { }

  token ={
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllTarefas(): Observable<Tarefa[]>{
    return this.http.get<Tarefa[]>('http://localhost:8080/tarefa/', this.token)
  }

  getTarefaById(id: number): Observable<Tarefa>{
    return this.http.get<Tarefa>(`http://localhost:8080/tarefa/${id}`, this.token)
  }

  postTarefa(tarefa: Tarefa): Observable<Tarefa>{
    return this.http.post<Tarefa>('http://localhost:8080/tarefa/',tarefa, this.token)
  }

  editarTarefa(tarefa: Tarefa): Observable<Tarefa>{
    return this.http.put<Tarefa>('http://localhost:8080/tarefa',tarefa, this.token)
  }

  confirmarTarefa(id: number): Observable<Tarefa>{
    return this.http.put<Tarefa>(`http://localhost:8080/tarefa/confirmar/${id}`, this.token)
  }

  deleteTarefa(id:number){
    return this.http.delete(`http://localhost:8080/tarefa/${id}`, this.token)
  }

}