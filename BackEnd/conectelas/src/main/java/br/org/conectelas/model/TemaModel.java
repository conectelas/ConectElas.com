package br.org.conectelas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tema")
public class TemaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private boolean home;

	@NotNull
	private boolean empregabilidade;

	@NotNull
	private boolean marketplace;

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isEmpregabilidade() {
		return empregabilidade;
	}

	public void setEmpregabilidade(boolean empregabilidade) {
		this.empregabilidade = empregabilidade;
	}

	public boolean isMarketplace() {
		return marketplace;
	}

	public void setMarketplace(boolean marketplace) {
		this.marketplace = marketplace;
	}

}
