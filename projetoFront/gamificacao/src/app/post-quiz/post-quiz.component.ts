
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

import { Grupo } from '../model/Grupo';
import { InscricaoGrupo } from '../model/InscricaoGrupo';
import { PostagemQuiz } from '../model/PostagemQuiz';
import { Usuario } from '../model/Usuario';
import { AlertasService } from '../service/alertas.service';

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
  txtBotao: string
  

 
  constructor(
    private postQuizService: GrupoPostService,
    private router:Router, 
    private raute:ActivatedRoute,
    private alertas: AlertasService
  ) { }

  ngOnInit(){
    window.scroll(0,0)
    
    if(environment.token == ''){
      this.alertas.showAlertInfo('Sua seção expirou, faça o login novamente.')
      this.router.navigate(['/login'])
    }

   let id = this.raute.snapshot.params['id']
   this.findGrupoById(id)
   //this.Botao()
  }

  findGrupoById(id: number){
    this.postQuizService.getGrupoById(id).subscribe((resp: Grupo)=>{
      this.grupo= resp
    })
  }

 /* Botao(){
    let id = 0
    this.grupo.listaInscricaoGU.forEach((inscricao: InscricaoGrupo) => {
      if(inscricao.usuarioInscricao.id == this.idUser){
        id = 1
      }
    })

    if (this.idUser == this.grupo.criador.id){
      this.txtBotao = "Novo Quiz"
    } else if (id = 1){
      document.querySelector("#botaoQuiz")?.setAttribute("style", "display: none;")
    } else {
      this.txtBotao = "Se inscrever"
    }
  }*/
  
  publicar(){
    
    this.postQuiz.grupoPostQuiz = this.grupo
    this.postQuizService.postPostagemQuiz(this.postQuiz).subscribe((resp: PostagemQuiz)=>{
      this.postQuiz= resp
      this.alertas.showAlertSuccess ("Postagem realizada com sucesso!")
      this.postQuiz = new PostagemQuiz()
      let id = this.raute.snapshot.params['id']
      this.findGrupoById(id)
    }) 
    
    }

    inscricaoGU(){
      this.user.id = this.idUser
      this.inscricao.usuarioInscricao = this.user
      this.inscricao.grupoInscricao = this.grupo

      this.postQuizService.postInscricao(this.inscricao).subscribe((resp: InscricaoGrupo) => {
        this.inscricao = resp
        this.alertas.showAlertSuccess('Inscrição realizada com sucesso!')
        this.findGrupoById(this.grupo.id)
      })
      
    }

}

