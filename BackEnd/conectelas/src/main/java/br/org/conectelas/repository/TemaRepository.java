package br.org.conectelas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.conectelas.model.TemaModel;

public interface TemaRepository extends JpaRepository<TemaModel, Long> {

	public Optional<TemaModel> findByNome(String nome);

}
