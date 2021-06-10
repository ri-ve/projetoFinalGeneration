package com.gamificacao.projetogamificacao.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table (name = "tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String usuario;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String sobrenome;
	
	@NotNull
	private String email;
	
	
	private long celular;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private int responsabilidadePessoal;
	
	private int mentalidadeCrescimento;
	
	private int atencaoDetalhes;
	
	private int persistencia;
	
	private int comunicacao;
	
	private int planejamento;
	
	private int proatividade;
	
	@OneToMany (mappedBy = "usuarioPrincipal", cascade = CascadeType.REMOVE)
	private List<AprovacaoAmigos> aprovacao = new ArrayList<>();
	
	@OneToMany (mappedBy = "usuarioPedindo", cascade = CascadeType.REMOVE)
	private List<AprovacaoAmigos> meusPedidosAmizade = new ArrayList<>();
	
	@OneToMany(mappedBy = "criador")
	@JsonIgnoreProperties({"criador", "listaInscricaoGU"})
	private List<Grupo> gruposCriados = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuarioAtividade")
	@JsonIgnoreProperties ("usuarioAtividade")
	private List<Atividades> atividadesUsuario = new ArrayList<>();
	
	@NotNull
	private String senha;
	
	private String foto;
		
	@OneToMany (mappedBy = "usuarioInscricao", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"usuarioInscricao" })
	private List<InscricaoGrupo> listaInscricaoUG = new ArrayList<>();
	
	@OneToMany (mappedBy = "usuarioResponsavel", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"usuarioResponsavel" })
	private List<Tarefa> listaTarefas = new ArrayList<>();
	
	@ManyToMany
	private List<PostagemQuiz> quizRespondido = new ArrayList<PostagemQuiz>();
	
	private String descricao;
	
	private String linkedin;
	
	private String gitHub;
	
	

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getGitHub() {
		return gitHub;
	}

	public void setGitHub(String gitHub) {
		this.gitHub = gitHub;
	}

	public long getId() {
		return id;
	}

	public void setId(long idUsuario) {
		this.id = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCelular() {
		return celular;
	}

	public void setCelular(long celular) {
		this.celular = celular;
	}

	

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getResponsabilidadePessoal() {
		return responsabilidadePessoal;
	}

	public void setResponsabilidadePessoal(int responsabilidadePessoal) {
		this.responsabilidadePessoal = responsabilidadePessoal;
	}

	public int getMentalidadeCrescimento() {
		return mentalidadeCrescimento;
	}

	public void setMentalidadeCrescimento(int mentalidadeCrescimento) {
		this.mentalidadeCrescimento = mentalidadeCrescimento;
	}

	public int getPersistencia() {
		return persistencia;
	}

	public void setPersistencia(int persistencia) {
		this.persistencia = persistencia;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<InscricaoGrupo> getListaInscricaoUG() {
		return listaInscricaoUG;
	}

	public void setListaInscricaoUG(List<InscricaoGrupo> listaInscricaoUG) {
		this.listaInscricaoUG = listaInscricaoUG;
	}


	public List<Grupo> getGruposCriados() {
		return gruposCriados;
	}

	public void setGruposCriados(List<Grupo> gruposCriados) {
		this.gruposCriados = gruposCriados;
	}

	public List<Atividades> getAtividadesUsuario() {
		return atividadesUsuario;
	}

	public void setAtividadesUsuario(List<Atividades> atividadesUsuario) {
		this.atividadesUsuario = atividadesUsuario;
	}


	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}


	public int getAtencaoDetalhes() {
		return atencaoDetalhes;
	}

	public void setAtencaoDetalhes(int atencaoDetalhes) {
		this.atencaoDetalhes = atencaoDetalhes;
	}

	public int getComunicacao() {
		return comunicacao;
	}

	public void setComunicacao(int comunicacao) {
		this.comunicacao = comunicacao;
	}

	public int getPlanejamento() {
		return planejamento;
	}

	public void setPlanejamento(int planejamento) {
		this.planejamento = planejamento;
	}

	public int getProatividade() {
		return proatividade;
	}

	public void setProatividade(int proatividade) {
		this.proatividade = proatividade;
	}

	public List<PostagemQuiz> getQuizRespondido() {
		return quizRespondido;
	}

	public void setQuizRespondido(List<PostagemQuiz> quizRespondido) {
		this.quizRespondido = quizRespondido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<AprovacaoAmigos> getAprovacao() {
		return aprovacao;
	}

	public void setAprovacao(List<AprovacaoAmigos> aprovacao) {
		this.aprovacao = aprovacao;
	}

	public List<AprovacaoAmigos> getMeusPedidosAmizade() {
		return meusPedidosAmizade;
	}

	public void setMeusPedidosAmizade(List<AprovacaoAmigos> meusPedidosAmizade) {
		this.meusPedidosAmizade = meusPedidosAmizade;
	}
	
	
	
	
}
