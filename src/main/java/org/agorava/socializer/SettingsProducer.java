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

import org.agorava.Facebook;
import org.agorava.LinkedIn;
import org.agorava.Twitter;
import org.agorava.core.api.oauth.OAuthAppSettings;
import org.agorava.core.oauth.PropertyOAuthAppSettingsBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * @author Antoine Sabot-Durand
 */
public class SettingsProducer {

    @ApplicationScoped
    @Produces
    @Twitter
    public OAuthAppSettings twitterProducer() {
        PropertyOAuthAppSettingsBuilder builder = new PropertyOAuthAppSettingsBuilder();
        return builder.prefix("twitter").build();
    }

    @ApplicationScoped
    @Produces
    @LinkedIn
    public OAuthAppSettings linkedInProducer() {
        PropertyOAuthAppSettingsBuilder builder = new PropertyOAuthAppSettingsBuilder();
        return builder.prefix("linkedin").build();
    }

    @ApplicationScoped
    @Produces
    @Facebook
    public OAuthAppSettings facebookProducer() {
        PropertyOAuthAppSettingsBuilder builder = new PropertyOAuthAppSettingsBuilder();
        return builder.prefix("facebook").build();
    }


}
