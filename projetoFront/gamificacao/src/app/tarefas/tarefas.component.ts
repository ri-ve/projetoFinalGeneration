import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Tarefa } from '../model/Tarefa';
import { Usuario } from '../model/Usuario';
import { AlertasService } from '../service/alertas.service';
import {TarefasService} from '../service/tarefas.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.css']
})
export class TarefasComponent implements OnInit {

  tarefaCheck:boolean

  tarefa: Tarefa = new Tarefa()
  listaTarefa: Tarefa[] = [] 
  idTarefa: number

  usuario: Usuario = new Usuario()

  idUser = environment.id
  status = environment.status
  

  constructor(
    private router: Router,
    private tarefasService: TarefasService,
    private usuarioService: UsuarioService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
    
    if(environment.token == ''){
      this.router.navigate(['/login'])
    }
    this.findUsuarioById()

  }

  getAllTarefas(){
    this.tarefasService.getAllTarefas().subscribe((resp: Tarefa[])=>{
      this.listaTarefa = resp
    })
  }

  findUsuarioById(){
    this.usuarioService.getUsuarioById(this.idUser).subscribe((resp: Usuario) => {
      this.usuario = resp
    })
  }

  findTarefaById(id: number){
    this.tarefasService.getTarefaById(id).subscribe((resp: Tarefa)=>{
      this.tarefa = resp
    })
  }
  publicarTarefa(){

    this.usuario.id = this.idUser
    this.tarefa.usuarioResponsavel = this.usuario
    
    this.tarefasService.postTarefa(this.tarefa).subscribe((resp: Tarefa) =>{
      this.tarefa = resp
      this.router.navigate(['/feed'])
  
    })
  }
  
  apagar(id: number){
     this.tarefasService.deleteTarefa(id).subscribe(()=>{
       this.router.navigate(['/feed'])
     }) 
  }

  atualizar(){
    this.tarefasService.editarTarefa(this.tarefa).subscribe((resp: Tarefa)=>{
      this.router.navigate(['/feed'])
    })
  }
  
  confirmar(id: number){
    this.tarefasService.confirmarTarefa(id).subscribe((resp : Tarefa )=>{
      this.status = true})
  }

 /* tarefaConcluida(){
      this.usuario.listaTarefas.forEach((resp: Tarefa) => {
        if(resp.status == false){
          this.listaTarefa.push(resp)
        }
      });
  
    
  }
  
  tarefaConcluida(){
  this.tarefasService.getAllTarefas().subscribe((resp: Tarefa[])=>{
      resp 
      resp.forEach(element => {
        if(this.status == false){
          this.listaTarefa = resp
        }
      });
    })
  }
  */
 
}