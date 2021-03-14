package br.edu.ifpb.dac.biblioteca.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(
		  loginToContinue = @LoginToContinue(
		    loginPage = "login.xhtml",
		    errorPage = "login.xhtml"))

@ApplicationScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class JSF2_3Ativator {

}
