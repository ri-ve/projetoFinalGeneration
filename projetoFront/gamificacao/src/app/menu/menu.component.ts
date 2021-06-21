import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Usuario } from '../model/Usuario';
import { AlertasService } from '../service/alertas.service';
import { AuthService } from '../service/auth.service';
import { MenuService } from '../service/menu.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
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
    private menuService: MenuService,
    private alertas: AlertasService


  ) { }

  ngOnInit() {

    window.scroll(0, 0)

    if (environment.token == '') {
      this.router.navigate(['/login'])

    }
    this.findByIdUsuario(this.id)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }
  sair (){
    environment.token = ''
    environment.nome = '' 
    environment.foto = ''
    environment.id = 0
  
    this.router.navigate(['/login'])

  }
  atualizar() {

    if (this.usuario.senha != this.confirmarSenha) {
      this.alertas.showAlertDanger('As senhas estão incorretas.')
    } else {
      this.menuService.atualizarUsuario(this.usuario).subscribe((resp: Usuario) => {
        this.usuario = resp
      
        this.alertas.showAlertSuccess('Usuário atualizado com sucesso, faça o login novamente!')
        environment.token = ''
        environment.nome = '' 
        environment.foto = ''
        environment.id = 0
      
        this.router.navigate(['/login'])
      })
    }
  }
  findByIdUsuario(id: number) {
    this.usuarioService.getUsuarioById(id).subscribe((resp: Usuario) => {
      this.usuario = resp
    })

  }
}