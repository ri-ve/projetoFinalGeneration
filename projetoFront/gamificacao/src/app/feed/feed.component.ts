import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Atividades } from '../model/Atividades';
import { PostagemQuiz } from '../model/PostagemQuiz';
import { Usuario } from '../model/Usuario';
import { FeedServiceService } from '../service/feed-service.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
  nome = environment.nome
  foto = environment.foto
  id = environment.id
  descricao = "colocar a descrição aqui"

  usuario: Usuario = new Usuario
  atividades: Atividades = new Atividades

  listaPostagens: any[]
  listaAtividades: any[]

  constructor(
    private router: Router,
    private feedService: FeedServiceService,
    private usuarioService: UsuarioService
  ) { }

  ngOnInit(){
    window.scroll(0,0)
    
    if(environment.token == ''){
      alert('Sua seção expirou, faça o login novamente.')
      this.router.navigate(['/login'])
    }

    //this.findAllPostagens()
    //this.findAllAtividades()
    //this.juntarListas()
    this.findUsuarioById()
  }

  findUsuarioById(){
    this.usuarioService.getUsuarioById(this.id).subscribe((resp: Usuario) => {
      this.usuario = resp
    })
  }

  findAllPostagens(){
    this.feedService.getPostagemByUsuario(this.id).subscribe((resp: PostagemQuiz[]) =>{
      this.listaPostagens = resp
    })
    }

  findAllAtividades(){
    this.feedService.getAtividadesByUsuario(this.id).subscribe((resp: Atividades[]) =>{
      this.listaAtividades = resp
    })
  }

  juntarListas(){
    this.listaPostagens.concat(this.listaAtividades)
    console.log(this.listaPostagens)
  }

  publicar(){
    this.atividades.usuarioAtividade = this.usuario

    this.feedService.postAtividade(this.atividades).subscribe((resp: Atividades) => {
      this.atividades = resp
      alert('Postagem realizada com sucesso!')
      this.atividades = new Atividades()
    })
  }

}
