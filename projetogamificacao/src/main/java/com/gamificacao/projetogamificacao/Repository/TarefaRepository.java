package com.gamificacao.projetogamificacao.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamificacao.projetogamificacao.Models.Tarefa;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
