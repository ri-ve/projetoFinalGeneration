package com.gamificacao.projetogamificacao.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamificacao.projetogamificacao.Models.AprovacaoAmigos;
import com.gamificacao.projetogamificacao.Models.Atividades;
import com.gamificacao.projetogamificacao.Models.Grupo;
import com.gamificacao.projetogamificacao.Models.InscricaoGrupo;
import com.gamificacao.projetogamificacao.Models.PostagemQuiz;
import com.gamificacao.projetogamificacao.Models.Usuario;
import com.gamificacao.projetogamificacao.Models.UsuarioLogin;
import com.gamificacao.projetogamificacao.Repository.AtividadesRepository;
import com.gamificacao.projetogamificacao.Repository.GrupoRepository;

import com.gamificacao.projetogamificacao.Repository.UsuarioRepository;
import com.gamificacao.projetogamificacao.Service.UsuarioService;


@RestController
@CrossOrigin ("*")
@RequestMapping ("/usuario")
public class UsuarioController  {

	private @Autowired UsuarioRepository repositoryUser;
	private @Autowired UsuarioService usuarioService;
	private @Autowired GrupoRepository grupoRepository;
	private @Autowired AtividadesRepository atividadesRepository;

	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		return ResponseEntity.ok(repositoryUser.findAll());
	}
	@GetMapping ("/{id}")
	public ResponseEntity<Usuario> getUsuarioById (@PathVariable long id){
		return repositoryUser.findById(id).map(usuario -> ResponseEntity.ok(usuario)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping ("/nome-sobrenome")
	public ResponseEntity <?> getByNomeOrSobrenome (@RequestBody String pesquisa){   
		return ResponseEntity.ok(repositoryUser.findAllByNome(pesquisa)); 
	}
	@GetMapping("/lista-aprovacao")
	public ResponseEntity<List<AprovacaoAmigos>> listaAprovacaoAmigos(Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listaAprovacaoAmigos(usuario));
	}
	@PutMapping
	public ResponseEntity<Optional<Usuario>> atualizarUsuario(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuario(usuario));
	}
	@DeleteMapping ("/deletar/{idUsuario}")
	public void delete (@PathVariable long idUsuario) {
		repositoryUser.deleteById(idUsuario);
	}
	@PostMapping ("/logar")
	public ResponseEntity<UsuarioLogin> logar(@RequestBody Optional<UsuarioLogin> user){
		return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> cadastrarUsuario(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario));
	}
	@PostMapping("/novo-grupo")
	public ResponseEntity<?> criarGrupo(@RequestBody Grupo novoGrupo){
		return usuarioService.criarGrupo(novoGrupo)
				.map(usuarioCriador -> ResponseEntity.status(201).body(usuarioCriador))
				.orElse(ResponseEntity.status(400).build());	
	}
	@PostMapping("/inscricao")
	public ResponseEntity<?> inscrevendoGrupo(@RequestBody InscricaoGrupo inscricao){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.inscrevendoGrupo(inscricao));
	}
	@PostMapping("/post-pensamentos")
	public ResponseEntity<Atividades> postagemPensamentos(@RequestBody Atividades postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(atividadesRepository.save(postagem));
	}
	@PutMapping("/resposta/{id}")
	public ResponseEntity<Usuario> respostaQuiz(
			@RequestBody PostagemQuiz postQuiz,
			@PathVariable(value = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.responderQuiz(id, postQuiz).get());
	}
	@PostMapping("/pedir-amizade")
	public ResponseEntity<Optional<AprovacaoAmigos>> pedirAmizade(
			@RequestBody Usuario usuarioPedindo, 
			Usuario usuarioPrincipal){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.pedirAmizade(usuarioPedindo, usuarioPrincipal));
	}
	@PutMapping("/aceitar-amizade")
	public ResponseEntity<Optional<AprovacaoAmigos>> aceitarAmizade(@RequestBody AprovacaoAmigos aprovacao){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.aceitarAmizade(aprovacao));
	}
	@PutMapping("/negar-amizade")
	public ResponseEntity<Optional<AprovacaoAmigos>> negarAmizade(@RequestBody AprovacaoAmigos aprovacao){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.negarAmizade(aprovacao));
	}
}




