package dev.watersrcstack.events;

import com.hypixel.hytale.component.Archetype;
import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;

import com.hypixel.hytale.server.core.entity.LivingEntity;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.InteractivelyPickupItemEvent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.watersrcstack.TouchWithEyes;

import javax.annotation.Nonnull;

public class DisablePickup extends EntityEventSystem<EntityStore, InteractivelyPickupItemEvent> {
    public DisablePickup() {
        super(InteractivelyPickupItemEvent.class);
    }

    @Override
    public void handle(int index,
                       @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
                       @Nonnull Store<EntityStore> store,
                       @Nonnull CommandBuffer<EntityStore> commandBuffer,
                       @Nonnull InteractivelyPickupItemEvent interactivelyPickupItemEvent) {
        TouchWithEyes.LOGGER.atInfo().log("TWE Pickup event");

        if (TouchWithEyes.tweConfig == null)
            return;

        if (TouchWithEyes.tweConfig.config.pickup.contains(interactivelyPickupItemEvent.getItemStack().getItemId())) {
            interactivelyPickupItemEvent.setCancelled(true);
        }

    }

    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(
                Player.getComponentType()
        );
    }}
