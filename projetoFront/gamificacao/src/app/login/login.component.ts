import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Usuario } from '../model/Usuario';
import { UsuarioLogin } from '../model/UsuarioLogin';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuarioLogin: UsuarioLogin = new UsuarioLogin()
  usuario: Usuario = new Usuario
  confirmarSenha: string

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0,0) 
  }

  login(){
    this.authService.login(this.usuarioLogin).subscribe((resp: UsuarioLogin)=>{
      this.usuarioLogin = resp
      environment.token = this.usuarioLogin.token
      environment.foto = this.usuarioLogin.foto
      environment.nome = this.usuarioLogin.nome
      environment.id = this.usuarioLogin.id

      this.router.navigate(['/feed'])
    }, erro => {
      if(erro.status == 500){
        alert('Usuario ou senha incorretos')
      }
    })
  }

  confirmSenha(event: any){
    this.confirmarSenha = event.target.value
  }

  cadastrar(){

    if(this.usuario.senha != this.confirmarSenha){
      alert('As senhas estão incorretas!')
    } else {
      this.authService.cadastrar(this.usuario).subscribe((resp: Usuario)=>{
        this.usuario = resp
        alert('Usuario cadastrado com sucesso!')
        this.usuario = new Usuario
      })
    }
  }

}
