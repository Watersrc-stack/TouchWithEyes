package dev.watersrcstack;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.logger.HytaleLogger;


import dev.watersrcstack.events.DisableDropCopper;

import javax.annotation.Nonnull;

public class TouchWithEyes extends JavaPlugin {

    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public TouchWithEyes(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        // Events, ECS
        this.getEntityStoreRegistry().registerSystem(new DisableDropCopper());

        LOGGER.atInfo().log("TouchWithEyes loaded.");
    }
}
