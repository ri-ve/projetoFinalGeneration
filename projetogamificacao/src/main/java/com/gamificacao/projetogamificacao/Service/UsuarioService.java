package com.gamificacao.projetogamificacao.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.gamificacao.projetogamificacao.Models.Aprovacao;
import com.gamificacao.projetogamificacao.Models.AprovacaoAmigos;
import com.gamificacao.projetogamificacao.Models.Atividades;
import com.gamificacao.projetogamificacao.Models.Grupo;
import com.gamificacao.projetogamificacao.Models.InscricaoGrupo;
import com.gamificacao.projetogamificacao.Models.PostagemQuiz;
import com.gamificacao.projetogamificacao.Models.Usuario;
import com.gamificacao.projetogamificacao.Models.UsuarioLogin;
import com.gamificacao.projetogamificacao.Repository.AprovacaoAmigosRepository;
import com.gamificacao.projetogamificacao.Repository.AtividadesRepository;
import com.gamificacao.projetogamificacao.Repository.GrupoRepository;
import com.gamificacao.projetogamificacao.Repository.InscricaoRepository;
import com.gamificacao.projetogamificacao.Repository.UsuarioRepository;

@Service
public class UsuarioService {

	private @Autowired UsuarioRepository repository;
	private @Autowired GrupoRepository repositoryGrupo;
	private @Autowired InscricaoRepository inscricaoRepository;
	private @Autowired AtividadesRepository atividadesRepository;
	private @Autowired AprovacaoAmigosRepository aprovacaoAmigosRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByUsuarioOrEmail(usuario.getUsuario(), usuario.getEmail());
		if (usuarioExistente.isPresent()) {
			return Optional.empty();
		} else {
			BCryptPasswordEncoder ecoder = new BCryptPasswordEncoder();
			String senhaEcoder = ecoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEcoder);
			return Optional.ofNullable(repository.save(usuario));
		}
	}
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuarioOrEmail(user.get().getUsuario(), user.get().getUsuario());
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha()));
			String auth = user.get().getUsuario() + ":" + user.get().getSenha();
			byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodeAuth);
			user.get().setToken(authHeader);
			user.get().setUsuario(usuario.get().getUsuario());
			user.get().setId(usuario.get().getId());
			user.get().setNome(usuario.get().getNome());
			user.get().setFoto(usuario.get().getFoto());
			return user;
		}
		return null;
	}
	/**
	 * Cria um Grupo e seta o usuario existente como seu criador
	 * 
	 * @param novoGrupo uma Entidade Grupo
	 * @param idUsuario tipo Long
	 * @return Optional com entidade usuario, caso usuario existente ou um Optional
	 *         vazio
	 * @since 1.0
	 * @author Rive
	 */
	public Optional<Usuario> criarGrupo(Grupo novoGrupo, Long idUsuario) {
		return repository.findById(idUsuario).map(usuarioExistente -> {
			novoGrupo.setCriador(usuarioExistente);
			repositoryGrupo.save(novoGrupo);
			Atividades atividade = new Atividades(usuarioExistente.getNome()+ " criou o grupo " 
			+ novoGrupo.getNome(), usuarioExistente);
			atividadesRepository.save(atividade);
			return repository.findById(idUsuario);
		}).orElse(Optional.empty());
	}
	public Optional<InscricaoGrupo> inscrevendoGrupo(Usuario novoUsuario, Grupo novoGrupo) {
		InscricaoGrupo inscricao = new InscricaoGrupo();
		inscricao.setUsuarioInscricao(novoUsuario);
		inscricao.setGrupoInscricao(novoGrupo);
		inscricao.setAprovacao(Aprovacao.AGUARDANDO);
		return Optional.ofNullable(inscricaoRepository.save(inscricao));
	}
	public Optional<InscricaoGrupo> aceitarNoGrupo(InscricaoGrupo inscricao) {
		inscricao.setAprovacao(Aprovacao.APROVADO);
		return Optional.ofNullable(inscricaoRepository.save(inscricao));
	}
	public Optional<InscricaoGrupo> NegarNoGrupo(InscricaoGrupo inscricao) {
		inscricao.setAprovacao(Aprovacao.NEGADO);
		return Optional.ofNullable(inscricaoRepository.save(inscricao));
	}
	public List<PostagemQuiz> buscarPostQuiz (Long id){
		Optional<List<InscricaoGrupo>> listaInscricao = inscricaoRepository.findByUsuarioInscricao(repository.findById(id).get());
		List<Grupo> grupos = new ArrayList<>();
		List<PostagemQuiz> postagensGrupos = new ArrayList<>();		
		listaInscricao
			.get()
			.stream()
			.forEach(grupo -> {
				if(grupo.getAprovacao().equals(Aprovacao.APROVADO)) {
					grupos.add(grupo.getGrupoInscricao());
				}	
			});	
		grupos
			.forEach(listaPostagens -> listaPostagens
					.getListaPostQuiz()
					.forEach(postagem -> postagensGrupos
							.add(postagem)));	
		return postagensGrupos;
	}
	public Optional<AprovacaoAmigos> pedirAmizade(Usuario usuarioPedindo, Usuario UsuarioPrincipal){
		AprovacaoAmigos aprovacao = new AprovacaoAmigos();
		aprovacao.setUsuarioPedindo(usuarioPedindo);
		aprovacao.setUsuarioPrincipal(UsuarioPrincipal);
		aprovacao.setAprovacaoEnum(Aprovacao.AGUARDANDO);
		return Optional.ofNullable(aprovacaoAmigosRepository.save(aprovacao));
	}
	public Optional<AprovacaoAmigos> aceitarAmizade(AprovacaoAmigos aprovacao){
		aprovacao.setAprovacaoEnum(Aprovacao.APROVADO);
		return Optional.ofNullable(aprovacaoAmigosRepository.save(aprovacao));
	}
	public Optional<AprovacaoAmigos> negarAmizade(AprovacaoAmigos aprovacao){
		aprovacao.setAprovacaoEnum(Aprovacao.NEGADO);
		return Optional.ofNullable(aprovacaoAmigosRepository.save(aprovacao));
	}
	public Optional<Usuario> responderQuiz(Long id, PostagemQuiz postQuiz){
		repository.findById(id).get().getQuizRespondido().add(postQuiz);
		return Optional.ofNullable(repository.save(repository.findById(id).get()));
	}
	public List<AprovacaoAmigos> listaAprovacaoAmigos(Usuario usuario){
		return aprovacaoAmigosRepository.findByUsuarioPrincipal(usuario);
	}
}
