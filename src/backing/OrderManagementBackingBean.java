package backing;

import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class OrderManagementBackingBean implements OrderManagementInterface {

	protected void addMessage(String msg, Severity severity, String... params) {
		msg = getFormattedMsg(msg, params);
		FacesContext facescontext = FacesContext.getCurrentInstance();
		facescontext.addMessage(null, new FacesMessage(severity, msg, null));
	}

	protected String getFormattedMsg(String msg, String... params) {
		if (params != null) {
			MessageFormat mf = new MessageFormat(msg);
			msg = mf.format(params, new StringBuffer(), null).toString();
		}

		return msg;
	}

}
