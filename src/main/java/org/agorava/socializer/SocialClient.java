/*
 * Copyright 2013 Agorava
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

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.agorava.AgoravaContext;
import org.agorava.api.atinject.Current;
import org.agorava.api.event.SocialEvent;
import org.agorava.api.event.StatusUpdated;
import org.agorava.api.oauth.OAuthSession;
import org.agorava.api.service.SessionService;
import org.agorava.api.storage.UserSessionRepository;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

//import org.jboss.solder.logging.Logger;

@Named
@SessionScoped
public class SocialClient implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3723552335163650582L;

    private static final String DEFAULT_THEME = "aristo";

    @Inject
    SessionService sessionService;

    private String Status;

    private String selectedService;

    private String currentTheme = DEFAULT_THEME;

    @Inject
    @Current
    private UserSessionRepository repository;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }

    public UserSessionRepository getRepository() {
        return repository;
    }

    public void setRepository(UserSessionRepository repository) {
        this.repository = repository;
    }

    public OAuthSession getCurrentSession() {
        return repository.getCurrent();
    }

    public void setCurrentSession(OAuthSession currentSession) {
        repository.setCurrent(currentSession);
    }

    public Map<String, OAuthSession> getSessionsMap() {
        return Maps.uniqueIndex(getSessions(), new Function<OAuthSession, String>() {

            @Override
            public String apply(OAuthSession arg0) {

                return arg0.toString();
            }

        });
    }

    public List<OAuthSession> getSessions() {
        return newArrayList(repository.getAll());
    }

    public String getCurrentSessionName() {
        return repository.getCurrent() == null ? "" : repository.getCurrent().toString();
    }

    public void setCurrentSessionName(String cursrvHdlStr) {
        setCurrentSession(getSessionsMap().get(cursrvHdlStr));
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

        redirectToAuthorizationURL(sessionService.initNewSession(selectedService));

    }

    protected void statusUpdateObserver(@Observes @Any StatusUpdated statusUpdate) {
        if (statusUpdate.getStatus().equals(SocialEvent.Status.SUCCESS)) {
            //log.debugf("Status update with : %s ", statusUpdate.getMessage());
            setStatus("");
        }
    }

    public void resetConnection() {
        repository.removeCurrent();
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
        return AgoravaContext.getListOfServices();
    }

}
