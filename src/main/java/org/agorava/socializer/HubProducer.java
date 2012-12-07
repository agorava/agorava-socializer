/*
 * Copyright 2012 Agorava
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

import org.agorava.*;
import org.agorava.core.api.oauth.Param;
import org.agorava.core.cdi.OAuthApplication;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import static org.agorava.core.oauth.PropertyOAuthAppSettingsBuilder.PREFIX;

/**
 * @author Antoine Sabot-Durand
 */
public class HubProducer {

    @Twitter
    @ApplicationScoped
    @OAuthApplication(params = {@Param(name = PREFIX, value = "twitter")})
    @Produces
    public TwitterServicesHub twitterProducer(TwitterServicesHub service) {
        return service;
    }

    @LinkedIn
    @ApplicationScoped
    @OAuthApplication(params = {@Param(name = PREFIX, value = "linkedin")})
    @Produces
    public LinkedInServicesHub linkedInProducer(LinkedInServicesHub service) {
        return service;
    }

    @Facebook
    @ApplicationScoped
    @OAuthApplication(params = {@Param(name = PREFIX, value = "facebook")})
    @Produces
    public FacebookServicesHub facebookProducer(FacebookServicesHub service) {
        return service;
    }

}
