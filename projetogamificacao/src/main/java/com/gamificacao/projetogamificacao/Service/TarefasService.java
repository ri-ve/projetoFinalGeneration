package com.gamificacao.projetogamificacao.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamificacao.projetogamificacao.Models.Tarefa;
import com.gamificacao.projetogamificacao.Repository.TarefaRepository;

@Service
public class TarefasService {
	
	private @Autowired TarefaRepository tarefaRepository;
	
	/**
	 * Cria uma tarefa e adiciona a atividades uma nova atividade do usuario.
	 * 
	 * @param novaTarefa uma Entidade Tarefa
	 * @param idUsuario tipo Long
	 * @return Optional com entidade Tarefa, caso usuario existente ou um Optional
	 *         vazio
	 * @since 1.0
	 * @author Rodrigo Teixeira
	 */
	public Optional<Tarefa> criarTarefa(Tarefa novaTarefa){
		return Optional.ofNullable(tarefaRepository.save(novaTarefa));		
	}
	/**
	 * Confirma uma tarefa muda seu status para true.
	 * 
	 * @param idTarefa tipo Long
	 * @return Optional com entidade Tarefa, caso usuario existente ou um Optional
	 *         vazio
	 * @since 1.0
	 * @author Rodrigo Teixeira
	 */
	public Optional<?> confirmarTarefa(Long idTarefa){
		return tarefaRepository.findById(idTarefa)
				.map(tarefa -> {
				tarefa.setStatus(true);
				tarefaRepository.save(tarefa);
				return Optional.of(tarefa);
				}).orElse(Optional.empty());
	}
}
