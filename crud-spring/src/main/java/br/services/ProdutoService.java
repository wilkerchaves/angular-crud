package br.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dto.ProdutoDTO;
import br.dto.ProdutoMapper;
import br.dto.ProdutoRequestDTO;
import br.entities.Produto;
import br.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoMapper mapper;
	
	public List<Produto> getAllProducts() {
		return produtoRepository.findAll();
	}
	
	public Produto getProdutoById(Integer id) {
		return produtoRepository.getReferenceById(id);
	}
	
	public ProdutoDTO save(ProdutoRequestDTO dto) {
		Produto produto = mapper.toProduto(dto);
		return mapper.toDto(produtoRepository.save(produto));
	}
	
	public ProdutoDTO updateProduto(Integer id, ProdutoRequestDTO dto) {
		Produto produtoParaAtualizar = getProdutoById(id);
		produtoParaAtualizar.setNome(dto.nome());
		produtoParaAtualizar.setValor(dto.valor());
		
		return mapper.toDto(produtoRepository.saveAndFlush(produtoParaAtualizar));
	}

	public void deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
	}
}
