package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries(value = { 
	@NamedQuery(name = "Entregador.findByEmailSenha", query = "SELECT c FROM Entregador c WHERE c.email = :email AND c.senha = :senha")
})
public class Entregador {

    private static final long serialVersionUID = 1L;
    
    @Transient
    public static final String FIND_BY_EMAIL_SENHA = "Entregador.findByEmailSenha";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String senha;
	private String nome;

    @OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SolicitacaoEntrega> solicitacoes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
