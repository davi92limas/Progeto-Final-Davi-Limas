package org.bairro.api.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "camisas")
@SequenceGenerator(name = "CAMISA_SEQ", sequenceName = "CAMISA_SEQ", allocationSize = 1)
public class Camisas {

	@Id
	@Column(name = "id_camisa")
	@GeneratedValue(generator = "CAMISA_SEQ", strategy = GenerationType.SEQUENCE)
	private Integer idCamisa;
	@Column(length = 80, nullable = false)
	private String descricao;
	@Column(length = 80, nullable = false)
	private String modelo;
	@Column(length = 6, nullable = false)
	private BigDecimal preco;
	@Column(length = 3, nullable = false)
	private String tamanho;

	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marcas marca;

	public Integer getIdCamisa() {
		return idCamisa;
	}

	public void setIdCamisa(Integer idCamisa) {
		this.idCamisa = idCamisa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Marcas getMarca() {
		return marca;
	}

	public void setMarca(Marcas marca) {
		this.marca = marca;
	}
	

	
}
