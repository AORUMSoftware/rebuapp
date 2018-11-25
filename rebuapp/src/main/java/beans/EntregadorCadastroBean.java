package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Entregador;
import repositories.EntregadorRep;

@ManagedBean(name="entregadorCadastroBean")
@SessionScoped
public class EntregadorCadastroBean {
	
	private Entregador entregador;
	private EntregadorRep rep;
	
	public EntregadorCadastroBean() {
		rep = new EntregadorRep();
	}
	
	public String novoEntregador() {
		entregador = new Entregador();
		return "cadastro-entregador";
	}
	
	public String gravar() {
		try {
			if(entregador.getId() == 0)
			{
				rep.insert(entregador);
			} else {
				rep.update(entregador);
			}
			return "login-entregador";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().validationFailed();
			e.printStackTrace();
			return "login-entregador";
		}
	}
	
	public String cancelar() {
		return "/login.xhtml?faces-redirect=true";
	}
	
	public Entregador getEntregador() {
		return entregador;
	}
	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
	public EntregadorRep getRep() {
		return rep;
	}
	public void setRep(EntregadorRep rep) {
		this.rep = rep;
	}
}
