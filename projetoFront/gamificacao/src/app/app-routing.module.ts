import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PerfilEditComponent } from './edit/perfil-edit/perfil-edit.component';
import { FeedComponent } from './feed/feed.component';
import { GruposComponent } from './grupos/grupos.component';
import { LoginComponent } from './login/login.component';
import { PerfilVisitanteComponent } from './perfil-visitante/perfil-visitante.component';
import { PerfilComponent } from './perfil/perfil.component';
import { PostQuizComponent } from './post-quiz/post-quiz.component';
import { TarefasComponent } from './tarefas/tarefas.component';

const routes: Routes = [

  {path: '', redirectTo: 'login', pathMatch: 'full'},

  {path: 'login', component: LoginComponent},

  {path: 'feed', component: FeedComponent},
  {path: 'tarefas', component: TarefasComponent},
  {path: 'grupos', component: GruposComponent},
  {path: 'perfil', component: PerfilComponent},
  
  {path: 'grupo/:id', component: PostQuizComponent},
  {path: 'perfil-edit/:id', component: PerfilEditComponent},
  {path: 'perfil-visitante/:id', component: PerfilVisitanteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
