package br.com.arjconsultoria.service;

import br.com.arjconsultoria.bean.UsuarioCredencial;

public interface AuthenticationService {

	/** login with account and password **/
	public boolean login(String account, String password);

	/** logout current user **/
	public void logout();

	/** get current user credential 
	 * @param session 
	 * @param session 
	 * @param sess **/
	public UsuarioCredencial getUserCredential();

	public boolean isAuthenticated();

}
