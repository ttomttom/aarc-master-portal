package org.masterportal.oauth2.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.inject.Provider;

import org.masterportal.oauth2.server.validators.GetProxyRequestValidator;

import edu.uiuc.ncsa.myproxy.oa4mp.oauth2.OA2SE;
import edu.uiuc.ncsa.myproxy.oa4mp.server.MyProxyFacadeProvider;
import edu.uiuc.ncsa.myproxy.oa4mp.server.admin.adminClient.AdminClientStore;
import edu.uiuc.ncsa.myproxy.oa4mp.server.admin.permissions.PermissionsStore;
import edu.uiuc.ncsa.myproxy.oa4mp.server.servlet.AuthorizationServletConfig;
import edu.uiuc.ncsa.security.core.util.MyLoggingFacade;
import edu.uiuc.ncsa.security.delegation.server.issuers.AGIssuer;
import edu.uiuc.ncsa.security.delegation.server.issuers.ATIssuer;
import edu.uiuc.ncsa.security.delegation.server.issuers.PAIssuer;
import edu.uiuc.ncsa.security.delegation.server.storage.ClientApprovalStore;
import edu.uiuc.ncsa.security.delegation.server.storage.ClientStore;
import edu.uiuc.ncsa.security.delegation.storage.TransactionStore;
import edu.uiuc.ncsa.security.delegation.token.TokenForge;
import edu.uiuc.ncsa.security.oauth_2_0.server.LDAPConfiguration;
import edu.uiuc.ncsa.security.oauth_2_0.server.ScopeHandler;
import edu.uiuc.ncsa.security.servlet.UsernameTransformer;
import edu.uiuc.ncsa.security.util.mail.MailUtilProvider;
import edu.uiuc.ncsa.security.util.jwk.JSONWebKeys;

public class MPOA2SE extends OA2SE {

	public MPOA2SE(MyLoggingFacade logger,
		       Provider<TransactionStore> tsp,
		       Provider<ClientStore> csp,
		       int maxAllowedNewClientRequests,
		       long rtLifetime,
		       Provider<ClientApprovalStore> casp,
		       List<MyProxyFacadeProvider> mfp,
		       MailUtilProvider mup,
		       MessagesProvider messagesProvider,
		       Provider<AGIssuer> agip,
		       Provider<ATIssuer> atip,
		       Provider<PAIssuer> paip,
		       Provider<TokenForge> tfp,
		       HashMap<String,
		       String> constants,
		       AuthorizationServletConfig ac,
		       UsernameTransformer usernameTransformer,
		       boolean isPingable,
		       Provider<PermissionsStore> psp,
		       Provider<AdminClientStore> acs,
		       int clientSecretLength,
		       Collection<String> scopes,
		       ScopeHandler scopeHandler,
		       LDAPConfiguration ldapConfiguration2,
		       boolean isRefreshTokenEnabled,
		       boolean twoFactorSupportEnabled,
		       long maxClientRefreshTokenLifetime,
		       JSONWebKeys jsonWebKeys,
		       String myproxyPassword,
		       long myproxyDefaultLifetime,
		       GetProxyRequestValidator[] validators) {
		
		super(logger,
		      tsp,
		      csp,
		      maxAllowedNewClientRequests,
		      rtLifetime,
		      casp,
		      mfp,
		      mup,
		      messagesProvider,
		      agip,
		      atip,
		      paip,
		      tfp,
		      constants,
		      ac,
		      usernameTransformer,
		      isPingable,
		      psp,
		      acs,
		      clientSecretLength,
		      scopes,
		      scopeHandler,
		      ldapConfiguration2,
		      isRefreshTokenEnabled,
		      twoFactorSupportEnabled,
		      maxClientRefreshTokenLifetime,
		      jsonWebKeys);
		
		this.myproxyPassword = myproxyPassword;
		this.myproxyDefaultLifetime = myproxyDefaultLifetime;
		
		this.validators = validators;
	}
	
	protected GetProxyRequestValidator[] validators;
	
	public GetProxyRequestValidator[] getValidators() {
		return validators;
	}
	
    protected String myproxyPassword;
    
    public void setMyproxyPassword(String myproxyPassword) {
		this.myproxyPassword = myproxyPassword;
	}
    
    public String getMyproxyPassword() {
		return myproxyPassword;
	}
    
    long myproxyDefaultLifetime;
    
    public long getMyproxyDefaultLifetime() {
		return myproxyDefaultLifetime;
	}
    
}
