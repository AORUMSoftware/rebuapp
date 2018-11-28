package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import models.Cliente;
import models.Entregador;
import models.SolicitacaoEntrega;
import repositories.SolicitacaoEntregaRep;
import utils.SessionContext;

@ManagedBean
public class DetalhesBean {

	public List<SolicitacaoEntrega> getHistoricoCliente() {
		
		Cliente cliente = SessionContext.getInstance().getClienteLogado();

		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();

		List<SolicitacaoEntrega> r = 
				repository.getHistoricoCliente(cliente);
		
		return r;
	}

	public List<SolicitacaoEntrega> getHistoricoEntregador() {

		Entregador entregador = SessionContext.getInstance().getEntregadorLogado();

		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();
		
		List<SolicitacaoEntrega> r = 
				repository.getHistoricoEntregador(entregador);
		
		return r;
	}
	
}
