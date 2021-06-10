import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Tarefa } from '../model/Tarefa';
import { Usuario } from '../model/Usuario';
import {TarefasService} from '../service/tarefas.service';

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.css']
})
export class TarefasComponent implements OnInit {

  tarefa: Tarefa = new Tarefa()
  listaTarefas: Tarefa[]
  idTarefa: number

  user: Usuario = new Usuario()
  idUser = environment.id

  constructor(
    private router: Router,
    private tarefasService: TarefasService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
    
    if(environment.token == ''){
      alert('Sua seção expirou, faça o login novamente.')
      this.router.navigate(['/login'])
    }

  }

  getAllTarefas(){
    this.tarefasService.getAllTarefas().subscribe((resp: Tarefa[])=>{
      this.listaTarefas = resp
    })
  }

  findTarefaById(id: number){
    this.tarefasService.getTarefaById(id).subscribe((resp: Tarefa)=>{
      this.tarefa = resp
    })
  }
  publicarTarefa(){

    this.user.id = this.idUser
    this.tarefa.usuarioResponsavel = this.user
    
    this.tarefasService.postTarefa(this.tarefa).subscribe((resp: Tarefa) =>{
      this.tarefa = resp
      alert('Tarefa adicionada!')
      this.getAllTarefas()
  
    })
  }

  apagar(){
     this.tarefasService.deleteTarefa(this.idTarefa).subscribe(()=>{
       alert('Tarefa Deletada!')
       this.router.navigate(['/tarefa'])
     }) 
  }

  atualizar(){
    this.tarefasService.editarTarefa(this.tarefa).subscribe((resp: Tarefa)=>{
      this.router.navigate(['/tarefa'])
    })
  }
  

}
