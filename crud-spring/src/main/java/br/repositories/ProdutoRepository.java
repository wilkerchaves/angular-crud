package br.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
