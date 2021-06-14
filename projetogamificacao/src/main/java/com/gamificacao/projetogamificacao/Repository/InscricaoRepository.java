package com.gamificacao.projetogamificacao.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamificacao.projetogamificacao.Models.InscricaoGrupo;
import com.gamificacao.projetogamificacao.Models.Usuario;


@Repository
public interface InscricaoRepository extends JpaRepository<InscricaoGrupo, Long> {	
	public Optional<List<InscricaoGrupo>> findByUsuarioInscricao(Usuario usuario);
}
