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

import filters.LoginFilter;
import models.Cliente;
import models.Entregador;
import models.SolicitacaoEntrega;
import repositories.SolicitacaoEntregaRep;
import utils.SessionContext;

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

			Cliente c = (Cliente) LoginFilter.getUserLogged(request, "clienteLogado");

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

			Entregador ent = (Entregador) LoginFilter.getUserLogged(request, "entregadorLogado");

			if (ent != null) {

				SolicitacaoEntregaRep orderRepository = new SolicitacaoEntregaRep();

				List<SolicitacaoEntrega> entregas = orderRepository.getOrdersByDeliveryman(ent);

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

					Entregador et = e.getEntregador();
					if (et != null)
						element.put("entregador_id", et.getId());

					arry.put(element);
				}

				root.put("result", arry);
				response.getWriter().append(root.toString());
			} else {
				response.getWriter().append("{\"result\":[]}");
			}
			break;

		case "getpodeaceitar":

			Entregador ent1 = (Entregador) LoginFilter.getUserLogged(request, "entregadorLogado");

			if (ent1 != null) {

				SolicitacaoEntregaRep orderRepository = new SolicitacaoEntregaRep();

				List<SolicitacaoEntrega> entregas = orderRepository.getTodasPendentes();

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

					Entregador et = e.getEntregador();
					if (et != null)
						element.put("entregador_id", et.getId());

					arry.put(element);
				}

				root.put("result", arry);

				response.getWriter().append(root.toString());
				
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
