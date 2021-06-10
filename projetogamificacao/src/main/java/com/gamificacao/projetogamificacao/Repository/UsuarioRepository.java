package com.gamificacao.projetogamificacao.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.gamificacao.projetogamificacao.Models.Usuario;

@Repository
@RepositoryRestResource(path = "Usuario", collectionResourceRel = "Usuario", itemResourceRel = "Usuario")
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public List<Usuario> findAllByNome(String nome);
	public Optional<Usuario> findByUsuarioOrEmail(String userName, String email);
}
