
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

import { Grupo } from '../model/Grupo';
import { InscricaoGrupo } from '../model/InscricaoGrupo';
import { PostagemQuiz } from '../model/PostagemQuiz';
import { Usuario } from '../model/Usuario';

import { GrupoPostService } from '../service/grupo-post.service';

@Component({
  selector: 'app-post-quiz',
  templateUrl: './post-quiz.component.html',
  styleUrls: ['./post-quiz.component.css']
})
export class PostQuizComponent implements OnInit {
   
  grupo:Grupo = new Grupo()
  inscricao: InscricaoGrupo = new InscricaoGrupo()
  postQuiz:PostagemQuiz = new PostagemQuiz
  user: Usuario = new Usuario()
  idUser = environment.id
  
  

 
  constructor(
    private postQuizService: GrupoPostService,
    private rauter:Router, 
    private raute:ActivatedRoute
  ) { }

  ngOnInit(){

   let id = this.raute.snapshot.params['id']
   this.findGrupoById(id)
  }

  findGrupoById(id: number){
    this.postQuizService.getGrupoById(id).subscribe((resp: Grupo)=>{
      this.grupo= resp
    })
  }
  publicar(){
    
    this.postQuiz.grupoPostQuiz = this.grupo
    this.postQuizService.postPostagemQuiz(this.postQuiz).subscribe((resp: PostagemQuiz)=>{
      this.postQuiz= resp
      alert ("Postagem realizada com sucesso!")
      this.postQuiz = new PostagemQuiz()
    }) 
    
    }

    inscricaoGU(){
      this.user.id = this.idUser
      this.inscricao.usuarioInscricao = this.user
      this.inscricao.grupoInscricao = this.grupo

      this.postQuizService.postInscricao(this.inscricao).subscribe((resp: InscricaoGrupo) => {
        this.inscricao = resp
        alert('Inscrição realizada com sucesso!')
        this.findGrupoById(this.grupo.id)
      })
      
    }

}

