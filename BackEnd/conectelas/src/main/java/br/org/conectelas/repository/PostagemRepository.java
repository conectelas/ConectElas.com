package br.org.conectelas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.conectelas.model.PostagemModel;

public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {

	List<PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);

}
