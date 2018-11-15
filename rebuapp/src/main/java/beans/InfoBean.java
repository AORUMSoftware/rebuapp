package beans;

import java.time.Year;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class InfoBean {

	public String getAppName() {
		return "Rebu App";
	}
	
	public String getCopyright() {
		return "Todos os direitos reservados © - " + Year.now().getValue() + " - Rebu Software.";
	}
	
	public String getApiGmap() {
		return "AIzaSyAuAgF-JTMAS3EpwrrqGV5SE9CFq0-M2cc";
	}
	
}
