package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Encomenda {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private double peso;
		private double altura;
		private double largura;
		private double profundidade;

	    @ManyToOne(cascade = CascadeType.ALL)
		private SolicitacaoEntrega solicitacao;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getPeso() {
			return peso;
		}
		public void setPeso(double peso) {
			this.peso = peso;
		}
		public double getAltura() {
			return altura;
		}
		public void setAltura(double altura) {
			this.altura = altura;
		}
		public double getLargura() {
			return largura;
		}
		public void setLargura(double largura) {
			this.largura = largura;
		}
		public double getProfundidade() {
			return profundidade;
		}
		public void setProfundidade(double profundidade) {
			this.profundidade = profundidade;
		}
	
	
}
