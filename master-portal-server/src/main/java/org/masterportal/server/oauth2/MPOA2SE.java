package org.masterportal.server.oauth2;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.inject.Provider;

import edu.uiuc.ncsa.myproxy.oa4mp.oauth2.OA2SE;
import edu.uiuc.ncsa.myproxy.oa4mp.server.MyProxyFacadeProvider;
import edu.uiuc.ncsa.myproxy.oa4mp.server.servlet.AuthorizationServletConfig;
import edu.uiuc.ncsa.security.core.util.MyLoggingFacade;
import edu.uiuc.ncsa.security.delegation.server.issuers.AGIssuer;
import edu.uiuc.ncsa.security.delegation.server.issuers.ATIssuer;
import edu.uiuc.ncsa.security.delegation.server.issuers.PAIssuer;
import edu.uiuc.ncsa.security.delegation.server.storage.ClientApprovalStore;
import edu.uiuc.ncsa.security.delegation.server.storage.ClientStore;
import edu.uiuc.ncsa.security.delegation.storage.TransactionStore;
import edu.uiuc.ncsa.security.delegation.token.TokenForge;
import edu.uiuc.ncsa.security.oauth_2_0.server.ScopeHandler;
import edu.uiuc.ncsa.security.servlet.UsernameTransformer;
import edu.uiuc.ncsa.security.util.mail.MailUtilProvider;

public class MPOA2SE extends OA2SE {

	public MPOA2SE(MyLoggingFacade logger, Provider<TransactionStore> tsp, Provider<ClientStore> csp,
			int maxAllowedNewClientRequests, long rtLifetime, Provider<ClientApprovalStore> casp,
			List<MyProxyFacadeProvider> mfp, MailUtilProvider mup, MessagesProvider messagesProvider,
			Provider<AGIssuer> agip, Provider<ATIssuer> atip, Provider<PAIssuer> paip, Provider<PAIssuer> ppip, Provider<TokenForge> tfp,
			HashMap<String, String> constants, AuthorizationServletConfig ac, UsernameTransformer usernameTransformer,
			boolean isPingable, int clientSecretLength, Collection<String> scopes, ScopeHandler scopeHandler,
			boolean isRefreshTokenEnabled) {
		
		super(logger, tsp, csp, maxAllowedNewClientRequests, rtLifetime, casp, mfp, mup, messagesProvider, agip, atip, paip,
				tfp, constants, ac, usernameTransformer, isPingable, clientSecretLength, scopes, scopeHandler,
				isRefreshTokenEnabled);
		
		this.ppip = ppip;
	}

	protected Provider<PAIssuer> ppip;
	
    public PAIssuer getPpIssuer() {
        if (ppIssuer == null) {
            ppIssuer = ppip.get();
        }
        return ppIssuer;
    }

    protected PAIssuer ppIssuer;	
	
}
