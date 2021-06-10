package com.gamificacao.projetogamificacao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.gamificacao.projetogamificacao.Models.Atividades;
import com.gamificacao.projetogamificacao.Models.Usuario;
import com.gamificacao.projetogamificacao.Repository.AtividadesRepository;
import com.gamificacao.projetogamificacao.Service.AtividadesService;

@RestController
@CrossOrigin ("*")
@RequestMapping ("/atividades")
public class AtividadesController {
	
	private @Autowired AtividadesRepository atividadesRepository;
	private @Autowired AtividadesService atividadesService;
	
	@GetMapping ("/{id_atividades}")
	public Optional<Atividades> getAtividadeById (@PathVariable Long id_atividades){
		return atividadesRepository.findById(id_atividades);
	}	
	@GetMapping
	public Optional<List<Atividades>> getAllAtividades(){
		return Optional.ofNullable(atividadesRepository.findAll());
	}
	@GetMapping ("/atividades-amigos/{id}")
	public ResponseEntity<List<Atividades>> buscarAtividadesAmigos(@PathVariable(value = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(atividadesService.atividadesAmigos(id));
	}
	@PostMapping
	public ResponseEntity<Optional<Atividades>> postAtividade(@Valid @RequestBody Atividades status){
		return ResponseEntity.status(201).body(atividadesService.criarAtividade(status));
	}
	public @PutMapping("/editar-status") 
	ResponseEntity<Atividades> editarAtividade(@RequestBody Atividades novaAtividade){
		return ResponseEntity.status(202).body(atividadesRepository.save(novaAtividade));
	}	
	@DeleteMapping("/deletar-status/{id_atividades}") 
	public void  deleteAtividade(@PathVariable Long id_atividades) {
		atividadesRepository.deleteById(id_atividades);
	}
}
