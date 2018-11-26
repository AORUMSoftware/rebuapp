package repositories;

import java.util.List;

import javax.persistence.NoResultException;

import models.Cliente;
import models.SolicitacaoEntrega;

public class SolicitacaoEntregaRep extends Repository<SolicitacaoEntrega> {

	public List<SolicitacaoEntrega> getOrdersByCustomer(Cliente customer) {

		try {
			
			List<SolicitacaoEntrega> result = this.manager
					.createNamedQuery(SolicitacaoEntrega.GET_ALL_BY_CUSTOMER, SolicitacaoEntrega.class)
					.setParameter("cliente", customer).getResultList();

			return result;

		} catch (NoResultException noResultEx) {

			noResultEx.printStackTrace();
			
			return null;

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;

		}
	}
}
