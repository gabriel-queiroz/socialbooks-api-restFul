package com.gabriel.socialapi.socialbooks.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gabriel.socialapi.socialbooks.model.exceptions.ComentarioNotFoundException;
import com.gabriel.socialapi.socialbooks.services.exceptions.AutorNaoEncontradoException;


@Entity
@Table( name= "livro")
public class Livro implements Serializable {

	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(name = "nome")
	private String nome;

	
	@NotNull
	@Column(name = "publicacao")
	@Temporal(TemporalType.DATE)

	private Date publicacao;

	@NotBlank
	@Column(name = "editora")
	private String editora;

	@NotBlank
	@Column(name = "resumo")
	private String resumo;


	@JsonInclude(value = Include.NON_EMPTY)
	@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinTable(name = "autor_livro",
	joinColumns = @JoinColumn(name = "livro_id"),
	inverseJoinColumns = @JoinColumn(name = "autor_id"))
	private List<Autor> autores = new ArrayList<>();

	
	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany(mappedBy = "livro", cascade =  {CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Comentario> comentarios = new ArrayList<>();


	public void adicionarAutor(Autor autor) {

		autores.add(autor);
		autor.adicionarLivro(this);
	}
	
	public void adicionarComentario(Comentario comentario) {
		
		
		comentarios.add(comentario);
		comentario.setLivro(this);
	}

	public Comentario buscarComentario(Long comentarioId) {

		Optional<Comentario> findFirst = getComentarios()

				.stream()
				.filter(c -> c.getId() == comentarioId)
				.findFirst();

		findFirst.orElseThrow(ComentarioNotFoundException::new);

		return findFirst.get();
	}

	public Autor buscarAutor(Long autorId) {

		Optional<Autor> autor = autores.stream()

				.filter(a -> a.getId() == autorId)
				.findFirst();

		autor.orElseThrow(AutorNaoEncontradoException::new);

		return autor.get();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}


	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		
		for (Autor autor : autores) {
			
			autor.adicionarLivro(this);
			
			adicionarAutor(autor);
		}
		
	}

	public Date getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(List<Comentario> comentarios) {
		
		for (Comentario comentario : comentarios) {
			
			comentario.setLivro(this);
			
			
		}
		this.comentarios = comentarios;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", nome=" + nome + ", publicacao=" + publicacao + ", editora=" + editora
				+ ", resumo=" + resumo + ", autores=" + autores.get(0).getNome() + ", comentarios=" + comentarios.get(0).getUsuario() + "]";
	}
	
	
	
	

}
