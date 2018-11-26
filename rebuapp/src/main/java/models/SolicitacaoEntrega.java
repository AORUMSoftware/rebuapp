package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity

@NamedQueries(value = {
		@NamedQuery(name = "SolicitacaoEntrega.getAllByCustomer", query = "SELECT c FROM SolicitacaoEntrega c WHERE c.cliente = :cliente") })
public class SolicitacaoEntrega {

	private static final long serialVersionUID = 1L;

	@Transient
	public static final String GET_ALL_BY_CUSTOMER = "SolicitacaoEntrega.getAllByCustomer";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Status status;
	private double lngOrigem;
	private double latOrigem;
	private double lngDestino;
	private double latDestino;
	private String data;
	
	@Column(columnDefinition = "bit default 0")
	private boolean entregue;
	
	@Column(name = "descricao_encomenda")
	private String descricaoEncomenda;

	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	@ManyToOne(cascade = CascadeType.ALL)
	private Entregador entregador;

	public boolean isEntregue() {
		return entregue;
	}
	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}
	public String getDescricaoEncomenda() {
		return descricaoEncomenda;
	}
	public void setDescricaoEncomenda(String descricaoEncomenda) {
		this.descricaoEncomenda = descricaoEncomenda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Entregador getEntregador() {
		return entregador;
	}
	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public double getLngOrigem() {
		return lngOrigem;
	}
	public void setLngOrigem(double lngOrigem) {
		this.lngOrigem = lngOrigem;
	}

	public double getLatOrigem() {
		return latOrigem;
	}

	public void setLatOrigem(double latOrigem) {
		this.latOrigem = latOrigem;
	}

	public double getLngDestino() {
		return lngDestino;
	}

	public void setLngDestino(double lngDestino) {
		this.lngDestino = lngDestino;
	}

	public double getLatDestino() {
		return latDestino;
	}

	public void setLatDestino(double latDestino) {
		this.latDestino = latDestino;
	}

}
