package dev.watersrcstack;

import com.google.gson.Gson;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.logger.HytaleLogger;


import dev.watersrcstack.events.DisableDrops;
import dev.watersrcstack.events.DisablePickup;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Set;


public class TouchWithEyes extends JavaPlugin {

    public static class TouchWithEyesConfig {
        public Config config;

        public static class Config {
            public Set<String> drop;
            public Set<String> pickup;
        }
    }

    public static TouchWithEyesConfig tweConfig = null;

    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public TouchWithEyes(@Nonnull JavaPluginInit init) {
        super(init);
    }

    private short loadFile() throws IOException {

        Gson gson = new Gson();
        try (FileReader reader = new FileReader("mods/TouchWithEyesConfig.json")){
            tweConfig = gson.fromJson(reader, TouchWithEyesConfig.class);
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }


    @Override
    protected void setup() {

        short isConfigLoaded = 0;
        try {
            isConfigLoaded = this.loadFile();
        } catch (IOException e) {
            LOGGER.atSevere().log("No valid configuration file found. Stopping here. Error code" + isConfigLoaded);
            return;
        }

        // Events, ECS
        this.getEntityStoreRegistry().registerSystem(new DisableDrops());
        this.getEntityStoreRegistry().registerSystem(new DisablePickup());

        LOGGER.atInfo().log("TouchWithEyes loaded.");
        LOGGER.atInfo().log("Debug: mod is updating");
    }
}
