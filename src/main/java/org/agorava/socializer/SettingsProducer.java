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

import org.agorava.api.oauth.application.OAuthAppSettings;
import org.agorava.api.oauth.application.OAuthAppSettingsBuilder;
import org.agorava.api.oauth.application.OAuthApplication;
import org.agorava.api.oauth.application.Param;
import org.agorava.facebook.Facebook;
import org.agorava.linkedin.LinkedIn;
import org.agorava.twitter.Twitter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * @author Antoine Sabot-Durand
 */
public class SettingsProducer {

    @ApplicationScoped
    @Produces
    @Twitter
    @OAuthApplication(params = {@Param(name = OAuthAppSettingsBuilder.PREFIX, value = "twitter")})
    public OAuthAppSettings twitterSettings;

    @ApplicationScoped
    @Produces
    @LinkedIn
    @OAuthApplication(params = {@Param(name = OAuthAppSettingsBuilder.PREFIX, value = "linkedin")})
    public OAuthAppSettings linkedInSettings;

    @ApplicationScoped
    @Produces
    @Facebook
    @OAuthApplication(params = {@Param(name = OAuthAppSettingsBuilder.PREFIX, value = "facebook")})
    public OAuthAppSettings facebookSettings;


}
