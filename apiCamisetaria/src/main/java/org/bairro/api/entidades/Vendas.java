package org.bairro.api.entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vendas")
public class Vendas {

	@Id
	@Column(name = "id_venda")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVenda;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_camisa1")
	private Camisas camisa1;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_camisa2")
	private Camisas camisa2;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_camisa3")
	private Camisas camisa3;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_funcionario")
	private Funcionarios funcionario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_cliente")
	private Clientes cliente;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_venda")
	private Date dataVenda;

	@Column(nullable = false)
	private BigDecimal totalVenda;

	public BigDecimal getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(BigDecimal totalVenda) {
		this.totalVenda = totalVenda;
	}

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	public Camisas getCamisa1() {
		return camisa1;
	}

	public void setCamisa1(Camisas camisa1) {
		this.camisa1 = camisa1;
	}

	public Camisas getCamisa2() {
		return camisa2;
	}

	public void setCamisa2(Camisas camisa2) {
		this.camisa2 = camisa2;
	}

	public Camisas getCamisa3() {
		return camisa3;
	}

	public void setCamisa3(Camisas camisa3) {
		this.camisa3 = camisa3;
	}

	public Funcionarios getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionarios funcionario) {
		this.funcionario = funcionario;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

}
