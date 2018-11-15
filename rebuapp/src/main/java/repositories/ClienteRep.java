package repositories;

import java.util.List;
import javax.persistence.*;
import models.Cliente;

public class ClienteRep extends Repository<Cliente> {

	public Cliente isReadyToLogin(String email, String senha) throws Exception {

		try {
			email = email.toLowerCase().trim();

			Cliente c = this.manager.createNamedQuery(Cliente.FIND_BY_EMAIL_SENHA, Cliente.class)
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();

			if (c != null) {
				return c;
			}

			return null;
		} catch (Exception e) {

			e.printStackTrace();

			throw new Exception(e.getMessage());
		}
	}
}
