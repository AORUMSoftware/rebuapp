package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Cliente;
import repositories.ClienteRep;
import utils.SessionContext;

@ManagedBean
@SessionScoped
public class ClienteLogadoBean {

	private String email = "";
	private String senha = "";
	private Cliente cliente;
	private String errormessage = "";
	
	public String getErrorMessage() {
		return errormessage;
	}

	public void setErrorMessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String doLogin() {
		try {
			
			ClienteRep repository = new ClienteRep();

			cliente = repository.isReadyToLogin(email, senha);

			if (cliente == null) {
				FacesContext.getCurrentInstance().validationFailed();
				this.setErrorMessage("Usuário e/ou senha incorretos.");

				return "/login.xhtml?faces-redirect=true";
			}

			this.setErrorMessage("");
			SessionContext.getInstance().setAttribute("clienteLogado", cliente);

			return "/cliente/index.xhtml?faces-redirect=true";
		} 
		catch (Exception e) {
			this.setErrorMessage("Ocorreu um problema durante o login.");
			FacesContext.getCurrentInstance().validationFailed();
			e.printStackTrace();

			return "/login.xhtml?faces-redirect=true";
		}
	}

	public String doLogout() {
		cliente = null;
		SessionContext.getInstance().encerrarSessao();

		return "/login.xhtml?faces-redirect=true";
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
