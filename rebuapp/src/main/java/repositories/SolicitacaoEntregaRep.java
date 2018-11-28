package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import models.Cliente;
import models.Entregador;
import models.SolicitacaoEntrega;
import models.Status;

public class SolicitacaoEntregaRep extends Repository<SolicitacaoEntrega> {

	public List<SolicitacaoEntrega> getHistoricoCliente(Cliente c) {

		try {
			
	        Query query = manager.createQuery("from SolicitacaoEntrega s where s.status = 2 and s.cliente = :cliente").setParameter("cliente", c);
			List<SolicitacaoEntrega> entities = query.getResultList();
	        
			return entities;

		} catch (NoResultException noResultEx) {

			noResultEx.printStackTrace();
			
			return  new ArrayList<SolicitacaoEntrega>();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;

		}
	}
	
	public List<SolicitacaoEntrega> getHistoricoEntregador(Entregador e) {

		try {
			
	        Query query = manager.createQuery("from SolicitacaoEntrega s where s.status = 2 and s.entregador = :entregador").setParameter("entregador", e);
			List<SolicitacaoEntrega> entities = query.getResultList();
	        
			return entities;

		} catch (NoResultException noResultEx) {

			noResultEx.printStackTrace();
			
			return  new ArrayList<SolicitacaoEntrega>();

		} catch (Exception ex) {
			
			ex.printStackTrace();
			
			return null;

		}
	}
	
	public List<SolicitacaoEntrega> getTodasPendentes() {

		try {
			
	        Query query = manager.createQuery("from SolicitacaoEntrega s where s.status = 0");
			List<SolicitacaoEntrega> entities = query.getResultList();
	        
			return entities;

		} catch (NoResultException noResultEx) {

			noResultEx.printStackTrace();
			
			return  new ArrayList<SolicitacaoEntrega>();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;

		}

	}
	
	public List<SolicitacaoEntrega> getOrdersByCustomer(Cliente customer) {

		try {
			
			List<SolicitacaoEntrega> result = this.manager
					.createNamedQuery(SolicitacaoEntrega.GET_ALL_BY_CUSTOMER, SolicitacaoEntrega.class)
					.setParameter("cliente", customer).getResultList();

			result.removeIf(p -> p.getStatus() == Status.ENTREGUE);
			
			return result;

		} catch (NoResultException noResultEx) {

			noResultEx.printStackTrace();
			
			return  new ArrayList<SolicitacaoEntrega>();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;

		}
	}

	public List<SolicitacaoEntrega> getOrdersByDeliveryman(Entregador deliveryman) {

		try {
			
			List<SolicitacaoEntrega> result = this.manager
					.createNamedQuery(SolicitacaoEntrega.GET_ALL_BY_DELIVERYMAN, SolicitacaoEntrega.class)
					.setParameter("entregador", deliveryman).getResultList();

			result.removeIf(p -> p.getStatus() == Status.ENTREGUE);
			
			return result;

		} catch (NoResultException noResultEx) {

			noResultEx.printStackTrace();
			
			return new ArrayList<SolicitacaoEntrega>();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;

		}
	}
}
