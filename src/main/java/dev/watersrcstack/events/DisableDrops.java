package dev.watersrcstack.events;

import com.hypixel.hytale.component.Archetype;
import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.DropItemEvent;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.watersrcstack.TouchWithEyes;

import javax.annotation.Nonnull;
import java.util.Objects;

public class DisableDrops extends EntityEventSystem<EntityStore, DropItemEvent.PlayerRequest> {
    public DisableDrops() {
        super(DropItemEvent.PlayerRequest.class);
    }

    @Override
    public void handle(int index,
                       @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
                       @Nonnull Store<EntityStore> store,
                       @Nonnull CommandBuffer<EntityStore> commandBuffer,
                       @Nonnull DropItemEvent.PlayerRequest dropItemRequest) {

        if (TouchWithEyes.tweConfig == null)
            return;

        Player player = store.getComponent(archetypeChunk.getReferenceTo(index), Player.getComponentType());
        if (player == null){
            TouchWithEyes.LOGGER.atWarning().log("DisableDrops: unable to fetch the player");
            return;
        }
        Inventory inventory = player.getInventory();
        ItemStack itemStack = Objects.requireNonNull(inventory.getSectionById(dropItemRequest.getInventorySectionId())).getItemStack(dropItemRequest.getSlotId());

        if (itemStack == null) {
            TouchWithEyes.LOGGER.atWarning().log("DisableDrops: unable to fetch the itemStack");
            return;
        }
        TouchWithEyes.LOGGER.atInfo().log("Item dropped: " + itemStack.getItemId());

        if (TouchWithEyes.tweConfig.config.drop.contains(itemStack.getItemId())) {
            dropItemRequest.setCancelled(true);
        }
    }


    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.empty();
    }
}
