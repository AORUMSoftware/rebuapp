package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import filters.LoginEntregadorFilter;
import filters.LoginFilter;
import models.Cliente;
import models.Entregador;
import models.SolicitacaoEntrega;
import repositories.SolicitacaoEntregaRep;

@WebServlet("/api/Map")
public class MapServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String destination = request.getParameter("destination");

		if (destination == null) {
			response.getWriter().append("{\"result\":[]}");
			return;
		}

		switch (destination) {

		case "customer":

			Cliente c = LoginFilter.getUserLogged(request);
			
			if (c != null) {

				SolicitacaoEntregaRep customerOrderRepository = new SolicitacaoEntregaRep();
				
				List<SolicitacaoEntrega> entregas = customerOrderRepository.getOrdersByCustomer(c);
				
				// carregar todas as encomendas
				// Lista encomendas ativas
				// Botao para criar nova encomenda
				JSONObject root = new JSONObject();
				JSONArray arry = new JSONArray();
				
				for (SolicitacaoEntrega e : entregas) {
					
					JSONObject element = new JSONObject();
					element.put("id", e.getId());
					element.put("status", e.getStatus());
					element.put("lngOrigem", e.getLngOrigem());
					element.put("latOrigem", e.getLatOrigem());
					element.put("lngDestino", e.getLngDestino());
					element.put("latDestino", e.getLatDestino());
					element.put("data", e.getData());
					element.put("descricaoEncomenda", e.getDescricaoEncomenda());
					
					Cliente cli = e.getCliente();
					if (cli != null)
						element.put("cliente_id", cli.getId());
					
					Entregador ent = e.getEntregador();
					if (ent != null)
						element.put("entregador_id", ent.getId());
					
					arry.put(element);
				}
				
				root.put("result", arry);
				response.getWriter().append(root.toString());

			} else {
				response.getWriter().append("{\"result\":[]}");
			}
			
			break;

		case "deliveryman":

			Entregador e = LoginEntregadorFilter.getUserLogged(request);

			if (e != null) {

				// carregar encomendas aceitas pelo entregador
				// Entregador entra na tela principal
				// Terá uma lista de encomendas
				// Clica para atender a solicitação
				// A encomenda passa a aparecer no mapa
				JSONObject jsonBuilder2 = new JSONObject();
				jsonBuilder2.put("deliveryman", e.getNome());
				response.getWriter().append(jsonBuilder2.toString());
				
			} else {
				response.getWriter().append("{\"result\":[]}");
			}
			break;

		default:
			response.getWriter().append("{\"result\":[]}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
