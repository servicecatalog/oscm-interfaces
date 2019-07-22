/*******************************************************************************
 *
 *  Copyright FUJITSU LIMITED 2018
 *
 *  Author: Paulina Badziak
 *
 *  Creation Date: 30.08.2016
 *
 *******************************************************************************/
package org.oscm.internal.vo;

public class VOTenant extends BaseVO {

    private static final long serialVersionUID = 213660588792712056L;
	private String tenantId;
    private String name;
    private String description;
    
    public VOTenant() {
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
