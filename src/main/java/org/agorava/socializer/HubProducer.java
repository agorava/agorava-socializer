/**
 *  Copyright 2012 Agorava
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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
 * 
 */
public class HubProducer {

    @Twitter
    @ApplicationScoped
    @OAuthApplication(apiKey = "FQzlQC49UhvbMZoxUIvHTQ", apiSecret = "VQ5CZHG4qUoAkUUmckPn4iN4yyjBKcORTW0wnok4r1k", callback = "http://localhost:8080/agorava-socializer/callback.jsf")
    @Produces
    public TwitterServicesHub twitterProducer(TwitterServicesHub service) {
        return service;
    }

    @LinkedIn
    @ApplicationScoped
    @OAuthApplication(apiKey = "ympq1JR_oxeC3qZE4VwiDEr-9Rc9Am0YE1AJwwXJNREfqaF7J6hXfsncu_JFd13W", apiSecret = "RwDk21M6qQeGT_zi2icmZV6tc5VsjRZPm7DWDIVt0Wsqu2eYBXt4Csg-FUbBZIIH", callback = "http://localhost:8080/agorava-socializer/callback.jsf")
    @Produces
    public LinkedInServicesHub linkedInProducer(LinkedInServicesHub service) {
        return service;
    }

    @Facebook
    @ApplicationScoped
    @OAuthApplication(apiKey = "204631749555557", apiSecret = "5d3132b1448a66d28e5c420b267cd65e", callback = "http://localhost:8080/agorava-socializer/callback.jsf", scope = "read_stream publish_stream")
    @Produces
    public FacebookServicesHub facebookProducer(FacebookServicesHub service) {
        return service;
    }

}
