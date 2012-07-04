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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.agorava.Facebook;
import org.agorava.FacebookServicesHub;
import org.agorava.LinkedIn;
import org.agorava.LinkedInServicesHub;
import org.agorava.Twitter;
import org.agorava.TwitterServicesHub;
import org.agorava.core.cdi.oauth.OAuthApplication;

/**
 * @author Antoine Sabot-Durand
 */
public class HubProducer {

    @Twitter
    @ApplicationScoped
    @OAuthApplication(apiKey = "${twitter.key}", apiSecret = "${twitter.secret}", callback = "${callback}")
    @Produces
    public TwitterServicesHub twitterProducer(TwitterServicesHub service) {
        return service;
    }

    @LinkedIn
    @ApplicationScoped
    @OAuthApplication(apiKey = "${linkedin.key}", apiSecret = "${linkedin.secret}", callback = "${callback}")
    @Produces
    public LinkedInServicesHub linkedInProducer(LinkedInServicesHub service) {
        return service;
    }

    @Facebook
    @ApplicationScoped
    @OAuthApplication(apiKey = "${facebook.key}", apiSecret = "${facebook.secret}", callback = "${callback}", scope = "read_stream publish_stream")
    @Produces
    public FacebookServicesHub facebookProducer(FacebookServicesHub service) {
        return service;
    }

}
