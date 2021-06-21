import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { RodapeComponent } from './rodape/rodape.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { FeedComponent } from './feed/feed.component';
import { TarefasComponent } from './tarefas/tarefas.component';
import { PostQuizComponent } from './post-quiz/post-quiz.component';
import { GruposComponent } from './grupos/grupos.component';
import { PerfilComponent } from './perfil/perfil.component';
import { PerfilVisitanteComponent } from './perfil-visitante/perfil-visitante.component';
import { PerfilEditComponent } from './edit/perfil-edit/perfil-edit.component';
import { AlertasComponent } from './alertas/alertas.component';
import { OrderModule } from 'ngx-order-pipe';
import { ModalModule } from 'ngx-bootstrap/modal';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    RodapeComponent,
    LoginComponent,
    FeedComponent,
    TarefasComponent,
    PostQuizComponent,
    GruposComponent,
    PerfilComponent,
    PerfilVisitanteComponent,
    PerfilEditComponent,
    AlertasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ModalModule.forRoot(),
    OrderModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
