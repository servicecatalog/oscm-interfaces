[![Build Status](https://travis-ci.org/servicecatalog/oscm-interfaces.svg?branch=master)](https://travis-ci.org/servicecatalog/oscm-interfaces)
[![artifacts](https://jitpack.io/v/servicecatalog/oscm-interfaces.svg)](https://jitpack.io/#servicecatalog/oscm-interfaces)
[![linesOfCode](https://sonarcloud.io/api/badges/measure?key=org.oscm:oscm-interfaces&metric=ncloc)](https://sonarcloud.io/dashboard/index/org.oscm:oscm-interfaces)
[![coverage](https://sonarcloud.io/api/badges/measure?key=org.oscm:oscm-interfaces&metric=coverage)](https://sonarcloud.io/dashboard/index/org.oscm:oscm-interfaces)
[![bugs](https://sonarcloud.io/api/badges/measure?key=org.oscm:oscm-interfaces&metric=bugs)](https://sonarcloud.io/dashboard/index/org.oscm:oscm-interfaces)
[![vulnerabilities](https://sonarcloud.io/api/badges/measure?key=org.oscm:oscm-interfaces&metric=vulnerabilities)](https://sonarcloud.io/dashboard/index/org.oscm:oscm-interfaces)
[![sqale_debt_ratio](https://sonarcloud.io/api/badges/measure?key=org.oscm:oscm-interfaces&metric=sqale_debt_ratio)](https://sonarcloud.io/dashboard/index/org.oscm:oscm-interfaces)
[![code_smells](https://sonarcloud.io/api/badges/measure?key=org.oscm:oscm-interfaces&metric=code_smells)](https://sonarcloud.io/dashboard/index/org.oscm:oscm-interfaces)
[![duplicated_lines_density](https://sonarcloud.io/api/badges/measure?key=org.oscm:oscm-interfaces&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard/index/org.oscm:oscm-interfaces)

# oscm-interfaces
This repository contains interfaces for integrating your applications with 
[Open Service Catalog Manager](https://github.com/servicecatalog/oscm#open-service-catalog-manager).


**REST APIs**
 * Most needed OSCM Platform functionality is exposed with REST APIs. 
Find them [here](https://github.com/servicecatalog/oscm-rest-api).
	
**Web Service API**
 * oscm-extsvc - OSCM Platform Service API (inbound)
 * oscm-extsvc-provisioning - Provisioning Service API (outbound)
 * oscm-extsvc-notification - Notification Service API (outbound)
 * oscm-extsvc-operation - Operation Service API (outbound)
 
**IaaS Provisioning Proxy**  
 * oscm-app-extsvc - Java API for implementing cloud adapter
  
**OSCM internal Java API**
 * oscm-extsvc-internal - internal API based on oscm-extsvc

## How to use ##

For integrating with your project simply include them in your Maven pom. 

Example:
```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.servicecatalog.oscm-interfaces</groupId>
  <artifactId>oscm-extsvc-provisioning</artifactId>
  <version>1.0</version>
</dependency>
```
All binaries and javadoc (TODO) as well as Web Service description (WSDL+XSD) can be downloaded [here](https://github.com/servicecatalog/oscm-interfaces/releases/tag/1.0). 

## Documentation
Detailed documentation describing the public OSCM Web services and application programming interfaces and how to integrate applications and external systems with OSCM can be found in the OSCM Developer Guide (*Link to developer guide*).




  
