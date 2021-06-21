import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/model/Usuario';
import { AlertasService } from 'src/app/service/alertas.service';
import { AuthService } from 'src/app/service/auth.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-perfil-edit',
  templateUrl: './perfil-edit.component.html',
  styleUrls: ['./perfil-edit.component.css']
})
export class PerfilEditComponent implements OnInit {
  nome = environment.nome
  foto = environment.foto
  id = environment.id

  usuario: Usuario = new Usuario()
  idUsuario: number
  confirmarSenha: string
  

  constructor(
    private usuarioService: UsuarioService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private alertas: AlertasService

  ) { }

  ngOnInit() {

    window.scroll(0,0)

    if(environment.token == ''){
      this.router.navigate(['/login'])
    }
    this.idUsuario = this.route.snapshot.params['id']
    this.findByIdUsuario(this.idUsuario)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

atualizar() {

  if(this.usuario.senha != this.confirmarSenha) {
    alert('As senhas estão incorretas.')
  } else {
    this.authService.cadastrar(this.usuario).subscribe((resp: Usuario) => {
      this.usuario = resp
      this.router.navigate(['/login'])
      this.alertas.showAlertSuccess('Usuário atualizado com sucesso!') 
    })
  } 
  }
  findByIdUsuario(id: number) {
    this.usuarioService.getUsuarioById(id).subscribe((resp: Usuario) => {
      this.usuario = resp
    })
  
  }
  
}


