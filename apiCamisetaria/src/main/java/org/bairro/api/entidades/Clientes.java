package org.bairro.api.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "clientes")
@SequenceGenerator(name = "CLIENTE_SEQ", sequenceName = "CLIENTE_SEQ", allocationSize = 1)
public class Clientes {

	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(generator = "CLIENTE_SEQ", strategy = GenerationType.SEQUENCE)
	private Integer idCliente;
	@Column(length = 80, nullable = false)
	private String nome;
	@Column(length = 11, nullable = false)
	private String cpf;
	@Column(length = 12, nullable = false)
	private String telefone;
	@Column(length = 80, nullable = false)
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;
	@Column(length=1, nullable=false)
    private Integer ativo;
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Integer getAtivo() {
		return ativo;
	}
	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}
	
	
	
}
