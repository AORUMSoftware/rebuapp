package utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import models.Cliente;
import models.Entregador;

public class SessionContext {
	private static SessionContext instance;

	public static SessionContext getInstance() {
		if (instance == null) {
			instance = new SessionContext();
		}

		return instance;
	}

	private SessionContext() {

	}

	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}

	public Cliente getClienteLogado() {
		return (Cliente) getAttribute("clienteLogado");
	}

	public void setUsuarioLogado(Cliente usuario) {
		setAttribute("clienteLogado", usuario);
	}
	
	public Entregador getEntregadorLogado() {
		return (Entregador) getAttribute("entregadorLogado");
	}

	public void setEntregadorLogado(Entregador usuario) {
		setAttribute("entregadorLogado", usuario);
	}

	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}

	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}

	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}
}
