/*******************************************************************************
 *
 *  Copyright FUJITSU LIMITED 2018
 *
 *  Creation Date: 2014-03-05
 *
 *******************************************************************************/
package org.oscm.app.v2_0;

import org.oscm.app.v2_0.data.PasswordAuthentication;
import org.oscm.app.v2_0.exceptions.ConfigurationException;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Factory for creating instances of OSCM Web services in the context of the
 * <code>APPlatformService</code>.
 */
public class BSSWebServiceFactory {

    /**
     * Creates a OSCM Web service with the given parameters.
     *
     * @param serviceClass   the class of the Web service to be created
     * @param authentication a <code>PasswordAuthentication</code> object specifying the
     *                       credentials to be used for authentication
     * @return the service class
     * @throws ConfigurationException if the configuration of the platform is incorrect
     * @throws MalformedURLException  if the base URL of the OSCM configuration is malformed
     */
    public static <T> T getBSSWebService(Class<T> serviceClass,
                                         PasswordAuthentication authentication)
            throws ConfigurationException, MalformedURLException {

        String targetNamespace = serviceClass.getAnnotation(WebService.class)
                .targetNamespace();
        QName serviceQName = new QName(targetNamespace,
                serviceClass.getSimpleName());

        String wsdlUrl = APPlatformServiceFactory.getInstance()
                .getBSSWebServiceWSDLUrl();
        wsdlUrl = wsdlUrl.replace("{SERVICE}", serviceClass.getSimpleName());

        Service service = Service.create(new URL(wsdlUrl), serviceQName);

        T client = 
                service.getPort(serviceClass);
                

        setBinding((BindingProvider) client, authentication.getUserName(),
                authentication.getPassword());
        return client;
    }

    private static void setBinding(BindingProvider client, String userName,
                                   String password) throws ConfigurationException {

        boolean isSsoMode = APPlatformServiceFactory.getInstance().isSsoMode();

        if (isSsoMode) {
            password = addPasswordPrefix(password);
        }

        final Binding binding = client.getBinding();
        @SuppressWarnings("rawtypes")
        List<Handler> handlerList = binding.getHandlerChain();
        if (handlerList == null)
            handlerList = new ArrayList<>();
        handlerList.add(new SOAPSecurityHandler(userName, password));
        binding.setHandlerChain(handlerList);
    }

    private static String addPasswordPrefix(String password) {
        return "WS" + password;
    }

}
