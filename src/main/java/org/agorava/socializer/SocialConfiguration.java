/*
 * Copyright 2016 Agorava
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

import java.io.File;

import javax.enterprise.context.ApplicationScoped;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

@ApplicationScoped
public class SocialConfiguration implements TogglzConfig {

	private static final String USER_HOME = System.getProperty("user.home",
			"/temp");

	public Class<? extends Feature> getFeatureClass() {
		return SocialFeature.class;
	}

	public StateRepository getStateRepository() {
		return new FileBasedStateRepository(new File(USER_HOME + File.separator
				+ ".socializer-features.properties"));
	}

	public UserProvider getUserProvider() {
		return new UserProvider() {
			@Override
			public FeatureUser getCurrentUser() {
				return new SimpleFeatureUser("admin", true);
			}
		};
	}
}