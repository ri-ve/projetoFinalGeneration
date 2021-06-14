package com.gamificacao.projetogamificacao.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "postagem_quiz")
public class PostagemQuiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 10, max = 200)
	private String pergunta;
	@NotNull
	private String respostaCorreta;

	@NotNull
	private String respostaFalsa;

	@NotNull
	private String respostaFalsa2;
	
	private int pontuacao;
	
	@Enumerated(EnumType.STRING)
	private Mentalidade mentalidade;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn
	private Grupo grupoPostQuiz;
	
	@ManyToMany(mappedBy = "quizRespondido")
	private List<Usuario> usuariosQueResponderam = new ArrayList<Usuario>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getRespostaCorreta() {
		return respostaCorreta;
	}

	public void setRespostaCorreta(String respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

	public String getRespostaFalsa() {
		return respostaFalsa;
	}

	public void setRespostaFalsa(String respostaFalsa) {
		this.respostaFalsa = respostaFalsa;
	}

	public String getRespostaFalsa2() {
		return respostaFalsa2;
	}

	public void setRespostaFalsa2(String respostaFalsa2) {
		this.respostaFalsa2 = respostaFalsa2;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Mentalidade getMentalidade() {
		return mentalidade;
	}

	public void setMentalidade(Mentalidade mentalidade) {
		this.mentalidade = mentalidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Grupo getGrupoPostQuiz() {
		return grupoPostQuiz;
	}

	public void setGrupoPostQuiz(Grupo grupoPostQuiz) {
		this.grupoPostQuiz = grupoPostQuiz;
	}

	public List<Usuario> getUsuariosQueResponderam() {
		return usuariosQueResponderam;
	}

	public void setUsuariosQueResponderam(List<Usuario> usuariosQueResponderam) {
		this.usuariosQueResponderam = usuariosQueResponderam;
	}
	
	
}
