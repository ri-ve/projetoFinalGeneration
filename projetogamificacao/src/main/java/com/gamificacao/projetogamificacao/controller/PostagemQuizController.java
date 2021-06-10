package com.gamificacao.projetogamificacao.controller;

import java.util.List;

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

import com.gamificacao.projetogamificacao.Models.PostagemQuiz;
import com.gamificacao.projetogamificacao.Models.Usuario;
import com.gamificacao.projetogamificacao.Repository.PostagemQuizRepository;
import com.gamificacao.projetogamificacao.Service.UsuarioService;

@RestController
@RequestMapping("/postagem-quiz")
@CrossOrigin("*")
public class PostagemQuizController {
	
	private @Autowired PostagemQuizRepository repository;
	private @Autowired UsuarioService usuarioService;
	
	@GetMapping 
	public ResponseEntity<List<PostagemQuiz>> getAllPostagens(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}") 
	public ResponseEntity<PostagemQuiz> getPostagemById(@PathVariable long id){
		return repository.findById(id).map (postQuiz -> ResponseEntity.ok(postQuiz)).orElse(ResponseEntity.notFound().build());
	}	
	@GetMapping("/buscar-post/{id}")
	public ResponseEntity<List<PostagemQuiz>> buscarPostQuizUsuario(@PathVariable(value = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarPostQuiz(id));
	}
	@PostMapping 
	public ResponseEntity<PostagemQuiz> postPostagem(@RequestBody PostagemQuiz postagemQuiz){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagemQuiz));
	}
	@PutMapping 
	public ResponseEntity<PostagemQuiz> putPostagem(@RequestBody PostagemQuiz postagemQuiz){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagemQuiz));
	}
	@DeleteMapping ("/deletar/{idPostagem}")
	public void deletePostagem(@PathVariable long idPostagem) {
		repository.deleteById(idPostagem);
	}
	
}
