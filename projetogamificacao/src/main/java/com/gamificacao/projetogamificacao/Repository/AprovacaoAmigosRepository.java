package com.gamificacao.projetogamificacao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamificacao.projetogamificacao.Models.AprovacaoAmigos;
import com.gamificacao.projetogamificacao.Models.Usuario;

@Repository
public interface AprovacaoAmigosRepository extends JpaRepository<AprovacaoAmigos, Long> {
	List<AprovacaoAmigos> findByUsuarioPrincipal(Usuario usuario);
}
