/*
 * Copyright 2013-2016 Agorava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.agorava.socializer;

import org.agorava.AgoravaContext;
import org.agorava.api.event.SocialEvent;
import org.agorava.api.event.StatusUpdated;
import org.agorava.api.oauth.OAuthSession;
import org.agorava.api.service.OAuthLifeCycleService;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class SocialClient implements Serializable {

    private static final long serialVersionUID = 3723552335163650582L;

    private static final Logger LOGGER = Logger.getLogger(SocialClient.class.getName());
    
    @Inject
    OAuthLifeCycleService lifeCycleService;

    private String Status;

    private String selectedService;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public OAuthSession getCurrentSession() {
        return lifeCycleService.getCurrentSession();
    }

    public void setCurrentSession(OAuthSession currentSession) {
        lifeCycleService.setCurrentSession(currentSession);
    }

    public List<OAuthSession> getSessions() {
        return lifeCycleService.getAllActiveSessions();
    }

    public String getCurrentSessionName() {
        return getCurrentSession().toString();
    }

    public void redirectToAuthorizationURL(String url) throws IOException {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(url);
    }

    public String getTimeLineUrl() {
        if (getCurrentSession() != null && getCurrentSession().isConnected())
            return "/WEB-INF/fragments/" + getCurrentSession().getServiceName().toLowerCase() + ".xhtml";
        return "";
    }

    public void serviceInit() throws IOException {

        redirectToAuthorizationURL(lifeCycleService.startDanceFor(selectedService));

    }

    protected void statusUpdateObserver(@Observes @Any StatusUpdated statusUpdate) {
        if (statusUpdate.getStatus().equals(SocialEvent.Status.SUCCESS)) {
            //log.debugf("Status update with : %s ", statusUpdate.getMessage());
            setStatus("");
        }
    }

    public void resetConnection() {
        lifeCycleService.killCurrentSession();
    }

    /**
     * @return the selectedService
     */
    public String getSelectedService() {
        return selectedService;
    }

    /**
     * @param selectedService the selectedService to set
     */
    public void setSelectedService(String selectedService) {
        this.selectedService = selectedService;
    }

    public List<String> getListOfServices() {
    	List<String> availableServices = AgoravaContext.getListOfServices();
    	List<String> list = new ArrayList<String>();
    	for (String service : availableServices) {
    		LOGGER.log(Level.FINEST, "Available service: " + service);
    		try {
    			SocialFeature feature = SocialFeature.valueOf(service);
    			if (feature.isActive()) {
    				LOGGER.log(Level.INFO, "Adding service: " + service); // TODO change to finer
    				list.add(service);
    			}
    		} catch (Exception e) {
    			LOGGER.log(Level.WARNING, "Error", e);
    		}
    	}
    	return list;
    }
}
