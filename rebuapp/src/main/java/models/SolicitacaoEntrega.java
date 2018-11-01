package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SolicitacaoEntrega {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private Status status;
		private double lngOrigem;
		private double latOrigem;
		private double lngDestino;
		private double latDestino;
		private String data;

	    @ManyToOne(cascade = CascadeType.ALL)
		private Cliente cliente;

	    @ManyToOne(cascade = CascadeType.ALL)
		private Entregador entregador;

	    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<Encomenda> encomendas;
		
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
