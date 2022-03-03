package br.org.conectelas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_usuario")
public class UsuarioModel {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min=5, max=100)
	private String nomeCompleto;
	
	@NotBlank
	@Size(min=10, max=200)
	private String email;
	
	@NotBlank
	@Size(min=8, max=300)
	private String senha;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<PostagemModel> postagem;
	
	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PostagemModel> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<PostagemModel> postagem) {
		this.postagem = postagem;
	}
	
	
}
