package dev.watersrcstack.events;

import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerInteractEvent;

import dev.watersrcstack.TouchWithEyes;

public class DeprecatedDisablePickup {

    public static void onDeprecatedInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        InteractionType type = event.getActionType();

        TouchWithEyes.LOGGER.atWarning().log("Deprecated PlayerInteractEvent of type: " + type);
    }
}
