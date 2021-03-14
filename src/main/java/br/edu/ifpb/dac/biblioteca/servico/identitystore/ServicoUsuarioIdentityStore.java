package br.edu.ifpb.dac.biblioteca.servico.identitystore;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import br.edu.ifpb.dac.biblioteca.modelo.Usuario;
import br.edu.ifpb.dac.biblioteca.servico.ServicoUsuarios;

//Custom Identity Store
@ApplicationScoped
public class ServicoUsuarioIdentityStore implements IdentityStore {

	@Inject
	private ServicoUsuarios servicoUsuarios;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
		CredentialValidationResult resultadoValidacao = CredentialValidationResult.INVALID_RESULT;
		
		String email = login.getCaller();
		String senha = login.getPasswordAsString();
		
		Usuario usuarioRecuperado = servicoUsuarios.buscaPeloEmailESenha(email, senha);
		
		if (usuarioRecuperado!=null) {
			resultadoValidacao = new CredentialValidationResult(new UsuarioPrincipal(usuarioRecuperado),usuarioRecuperado.getPapeisString());
		}

		System.out.println("Resultado Validacao: "+resultadoValidacao.getStatus());
		return resultadoValidacao;
	}

	
}
