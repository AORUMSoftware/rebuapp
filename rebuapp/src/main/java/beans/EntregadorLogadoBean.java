package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Entregador;
import repositories.EntregadorRep;
import utils.SessionContext;

@ManagedBean
@SessionScoped
public class EntregadorLogadoBean {

	private String email = "";
	private String senha = "";
	private Entregador entregador;
	private String errormessage = "";
	
	public String getErrorMessage() {
		return errormessage;
	}

	public void setErrorMessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String doLogin() {
		try {
			
			EntregadorRep repository = new EntregadorRep();

			entregador = repository.isReadyToLogin(email, senha);

			if (entregador == null) {
				FacesContext.getCurrentInstance().validationFailed();
				this.setErrorMessage("Usuário e/ou senha incorretos.");
				return "/login-entregador.xhtml?faces-redirect=true";
			}

			this.setErrorMessage("");
			SessionContext.getInstance().setAttribute("entregadorLogado", entregador);
			return "/entregador/index.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			this.setErrorMessage("Ocorreu um problema durante o login.");
			FacesContext.getCurrentInstance().validationFailed();
			e.printStackTrace();
			return "/login-entregador.xhtml?faces-redirect=true";
		}
	}

	public String doLogout() {
		entregador = null;
		SessionContext.getInstance().encerrarSessao();
		return "/login-entregador.xhtml?faces-redirect=true";
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

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
}

