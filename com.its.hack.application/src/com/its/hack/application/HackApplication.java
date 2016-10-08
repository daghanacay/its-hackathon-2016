package com.its.hack.application;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.its.hack.model.Merchant;

import osgi.enroute.configurer.api.RequireConfigurerExtender;
import osgi.enroute.google.angular.capabilities.RequireAngularWebResource;
import osgi.enroute.rest.api.REST;
import osgi.enroute.twitter.bootstrap.capabilities.RequireBootstrapWebResource;
import osgi.enroute.webserver.capabilities.RequireWebServerExtender;

@RequireAngularWebResource(resource={"angular.js","angular-resource.js", "angular-route.js"}, priority=1000)
@RequireBootstrapWebResource(resource="css/bootstrap.css")
@RequireWebServerExtender
@RequireConfigurerExtender
@Component(name="com.its.hack")
public class HackApplication implements REST {

	public String getUpper(String string) {
		return string.toUpperCase();
	}
	
	public List<Merchant> getMerchants(String latitude, String longitude){
		return Collections.<Merchant>emptyList();
	}


}
