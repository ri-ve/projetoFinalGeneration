package com.gamificacao.projetogamificacao.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table (name = "tb_grupo")

public class Grupo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String nome;
	
	private String descricao;
	
	private String foto;
	
	private String icone;
	
	@OneToMany (mappedBy = "grupoInscricao", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties(value = "grupoInscricao", allowSetters = true)
	private Set<InscricaoGrupo> listaInscricaoGU = new HashSet<>();
	
	@OneToMany (mappedBy = "grupoPostQuiz", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties(value = "grupoPostQuiz", allowSetters = true)
	private List<PostagemQuiz> listaPostQuiz = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name = "criador_id")
	@JsonIgnoreProperties(value = "gruposCriados", allowSetters = true)
	private Usuario criador;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public Set<InscricaoGrupo> getListaInscricaoGU() {
		return listaInscricaoGU;
	}

	public void setListaInscricaoGU(Set<InscricaoGrupo> listaInscricaoGU) {
		this.listaInscricaoGU = listaInscricaoGU;
	}

	public List<PostagemQuiz> getListaPostQuiz() {
		return listaPostQuiz;
	}

	public void setListaPostQuiz(List<PostagemQuiz> listaPostQuiz) {
		this.listaPostQuiz = listaPostQuiz;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}



}
