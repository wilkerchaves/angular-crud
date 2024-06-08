package br.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProdutoDTO(@JsonProperty("id") Integer id, String nome, Double valor) {

}
