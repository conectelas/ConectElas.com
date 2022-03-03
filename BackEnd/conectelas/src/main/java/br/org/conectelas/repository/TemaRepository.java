package br.org.conectelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.conectelas.model.TemaModel;

public interface TemaRepository extends JpaRepository<TemaModel, Long>{
	
//	@Query("Select * From tema where ")
	
}
