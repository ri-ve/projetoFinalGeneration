package com.gamificacao.projetogamificacao.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamificacao.projetogamificacao.Models.InscricaoGrupo;
import com.gamificacao.projetogamificacao.Repository.InscricaoRepository;

@RestController
@RequestMapping("/inscricao")
@CrossOrigin("*")
public class InscricaoController {

	private @Autowired InscricaoRepository repository;

	@GetMapping
	public ResponseEntity<List<InscricaoGrupo>> getAllInscricao() {
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<InscricaoGrupo> getInscricaoById(@PathVariable Long id) {
		return repository.findById(id).map(inscricao -> ResponseEntity.ok(inscricao))
				.orElse(ResponseEntity.notFound().build());
	}
	@DeleteMapping("/{id}")
	public void deleteInscricao(@PathVariable long id) {
		repository.deleteById(id);
	}
}
