package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Cliente;
import repositories.ClienteRep;
import utils.SessionContext;

@ManagedBean(name="clienteCadastroBean")
@SessionScoped
public class ClienteCadastroBean {
	
	
	private Cliente cliente;
	private ClienteRep rep;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public String novoCliente() {
		cliente = new Cliente();
		return "cadastro";
	}
	
	public String gravar() {
		try {
			if(cliente.getId() == 0)
			{
				rep.insert(cliente);
			} else {
				rep.update(cliente);
			}
			return "/login.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().validationFailed();
			e.printStackTrace();
			return "login";
		}
	}
	
	public String cancelar() {
		//return "/login.xhtml?faces-redirect=true";
		return "login";
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteRep getRep() {
		return rep;
	}

	public void setRep(ClienteRep rep) {
		this.rep = rep;
	}

	public ClienteCadastroBean () {
		rep = new ClienteRep();
	}
	
	
	
}
