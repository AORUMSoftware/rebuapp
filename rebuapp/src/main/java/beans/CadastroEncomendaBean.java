package beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.bean.ManagedBean;

import models.Cliente;
import models.Entregador;
import models.SolicitacaoEntrega;
import models.Status;
import repositories.ClienteRep;
import repositories.SolicitacaoEntregaRep;
import utils.SessionContext;

@ManagedBean
public class CadastroEncomendaBean {

	private SolicitacaoEntrega encomenda = new SolicitacaoEntrega();

	public void entregar(SolicitacaoEntrega solicitacao) {

		solicitacao.setStatus(Status.ENTREGUE);
		
		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();
		
		repository.merge(solicitacao);
	}
	
	public void aceitar(SolicitacaoEntrega solicitacao) {
		
		Entregador entregador = SessionContext.getInstance().getEntregadorLogado();
		
		solicitacao.setEntregador(entregador);
		solicitacao.setStatus(Status.A_CAMINHO);
		
		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();
		
		repository.merge(solicitacao);
		
	}
	
	public List<SolicitacaoEntrega> getSolicitacoesDelivery() {
		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();
		List<SolicitacaoEntrega> resultado = repository.getTodasPendentes();
		return resultado;
	}
	
	public void deletar(SolicitacaoEntrega solicitacao) {
		
		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();
		repository.delete(repository.find(solicitacao.getId()));
	}
	
	public List<SolicitacaoEntrega> getSolicitacoesCustomer() {
		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();
		return repository.getOrdersByCustomer(SessionContext.getInstance().getClienteLogado());
	}
	
	public String gravar() {
		
		ClienteRep customerRepository = new ClienteRep();
		Cliente customer = customerRepository.find(
			SessionContext.getInstance().getClienteLogado().getId()
		);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
	    LocalDateTime now = LocalDateTime.now();  

		encomenda.setCliente(customer);
		encomenda.setData(dtf.format(now));
		encomenda.setStatus(Status.PENDENTE);
		
		SolicitacaoEntregaRep repository = new SolicitacaoEntregaRep();
		repository.merge(encomenda);
		
		return "/cliente/index.xhtml?faces-redirect=true";
	}
	
	public SolicitacaoEntrega getEncomenda() {
		return encomenda;
	}

	public void setEncomenda(SolicitacaoEntrega encomenda) {
		this.encomenda = encomenda;
	}
	
}
