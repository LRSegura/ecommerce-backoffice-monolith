package controller.common;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class BaseController {

    protected void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(summary);
        message.setSeverity(severity);
        getFacesContext().addMessage(null, message);
    }

    protected void addInfoMessage(String summary) {
        addMessage(summary, FacesMessage.SEVERITY_INFO);
    }

    protected void addErrorMessage(String summary) {
        addMessage(summary, FacesMessage.SEVERITY_ERROR);
    }

    protected void addWarnMessage(String summary) {
        addMessage(summary, FacesMessage.SEVERITY_WARN);
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected void executeScript(String script){
        org.primefaces.PrimeFaces.current().executeScript(script);
    }
}
