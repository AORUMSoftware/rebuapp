package repositories;

import javax.persistence.NoResultException;

import models.Entregador;

public class EntregadorRep extends Repository<Entregador> {
	public Entregador isReadyToLogin(String email, String senha) throws Exception {

		try {
			email = email.toLowerCase().trim();

			Entregador c = this.manager.createNamedQuery(Entregador.FIND_BY_EMAIL_SENHA, Entregador.class)
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();

			if (c != null) {
				return c;
			}

			return null;

		} catch (NoResultException noResultEx) {
			
			return null;
			
		} catch (Exception e) {

			e.printStackTrace();

			throw new Exception(e.getMessage());
		}
	}
}
