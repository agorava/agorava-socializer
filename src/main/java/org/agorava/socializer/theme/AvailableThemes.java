package org.agorava.socializer.theme;


import org.jboss.solder.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mask_hot
 * Date: 27/01/12
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
@Named
@Singleton
@Startup
public class AvailableThemes implements Serializable {
    private static final long serialVersionUID = -7546671249075892398L;

    @Inject
    private Logger LOGGER;

    private HashMap<String, Theme> themesAsMap;
    private List<Theme> themes;

    public AvailableThemes() {
//        init();
    }

    @PostConstruct
    void init() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("init");
        }
        List<String> themeNames = new ArrayList<String>();
        themeNames.add("aristo");
        themeNames.add("black-tie");
        themeNames.add("blitzer");
        themeNames.add("bluesky");
        themeNames.add("casablanca");
        themeNames.add("cupertino");
        themeNames.add("dark-hive");
        themeNames.add("dot-luv");
        themeNames.add("eggplant");
        themeNames.add("excite-bike");
        themeNames.add("flick");
        themeNames.add("glass-x");
        themeNames.add("home");
        themeNames.add("hot-sneaks");
        themeNames.add("humanity");
        themeNames.add("le-frog");
        themeNames.add("midnight");
        themeNames.add("mint-choc");
        themeNames.add("overcast");
        themeNames.add("pepper-grinder");
        themeNames.add("redmond");
        themeNames.add("rocket");
        themeNames.add("sam");
        themeNames.add("smoothness");
        themeNames.add("south-street");
        themeNames.add("start");
        themeNames.add("swanky-purse");
        themeNames.add("trontastic");
        themeNames.add("ui-darkness");
        themeNames.add("ui-lightness");
        themeNames.add("vader");

        themesAsMap = new HashMap<String, Theme>();
        themes = new ArrayList<Theme>();

        for (final String themeName : themeNames) {
            final Theme theme = new Theme();
            theme.setName(themeName);
            theme.setImage(themeName + ".png");

            themes.add(theme);
            themesAsMap.put(theme.getName(), theme);
        }

        LOGGER.debug(themes.size() + " themes loaded");
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public Theme getThemeForName(final String name) {
        return themesAsMap.get(name);
    }

    public void setLOGGER(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }
}
