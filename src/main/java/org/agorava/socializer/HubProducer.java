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
import org.agorava.core.api.oauth.SettingsBuilder;
import org.agorava.core.oauth.OAuthApplication;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * @author Antoine Sabot-Durand
 */
public class HubProducer {

    @Twitter
    @ApplicationScoped
    @OAuthApplication(params = {@Param(name = SettingsBuilder.API_KEY, value = "${twitter.key}"),
            @Param(name = SettingsBuilder.API_SECRET, value = "${twitter.secret}"),
            @Param(name = SettingsBuilder.CALLBACK, value = "${callback}")})
    @Produces
    public TwitterServicesHub twitterProducer(TwitterServicesHub service) {
        return service;
    }

    @LinkedIn
    @ApplicationScoped
    @OAuthApplication(params = {@Param(name = SettingsBuilder.API_KEY, value = "${linkedin.key}"),
            @Param(name = SettingsBuilder.API_SECRET, value = "${linkedin.secret}"),
            @Param(name = SettingsBuilder.CALLBACK, value = "${callback}")})

    @Produces
    public LinkedInServicesHub linkedInProducer(LinkedInServicesHub service) {
        return service;
    }

    @Facebook
    @ApplicationScoped
    @OAuthApplication(params = {@Param(name = SettingsBuilder.API_KEY, value = "${facebook.key}"),
            @Param(name = SettingsBuilder.API_SECRET, value = "${facebook.secret}"),
            @Param(name = SettingsBuilder.CALLBACK, value = "${callback}"),
            @Param(name = SettingsBuilder.SCOPE, value = "read_stream publish_stream")})


    @Produces
    public FacebookServicesHub facebookProducer(FacebookServicesHub service) {
        return service;
    }

}
