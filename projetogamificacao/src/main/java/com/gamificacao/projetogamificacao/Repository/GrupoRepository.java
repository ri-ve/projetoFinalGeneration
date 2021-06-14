package com.gamificacao.projetogamificacao.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamificacao.projetogamificacao.Models.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {	
	List<Grupo> findByCriador_id (Long criador_id);
	List<Grupo> findByNomeContainingIgnoreCase(String nome);
}
