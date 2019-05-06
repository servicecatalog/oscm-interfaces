/*******************************************************************************
 *
 *  Copyright FUJITSU LIMITED 2018
 *
 *******************************************************************************/
package org.oscm.internal.intf;

import javax.ejb.Remote;

/**
 * Remote interface providing the functionality to retrieve and manipulate
 * configuration settings of tenants.
 *
 * @author PLGrubskiM on 2017-07-03.
 *
 */
@Remote
public interface TenantConfigurationService {

    /**
     * Retrieves the value of HTTP request method for the specified tenant ID.
     *
     * @param tenantId the id of the tenant
     * @return GET or POST
     */
    String getHttpMethodForTenant(String tenantId);

    /**
     * Retrieves the value of the SAML Issuer for the specified tenant ID.
     *
     * @param tenantId the id of the tenant
     * @return Issuer for SAML assertion
     */
    String getIssuerForTenant(String tenantId);

    /**
     * Retrieves the value of the IDP URL for the specified tenant ID.
     *
     * @param tenantId the id of the tenant
     * @return IDP URL
     */
    String getIdpUrlForTenant(String tenantId);
}
