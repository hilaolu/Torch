package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.torch.event.MutableEvent;

/**
 * Thrown when a block physics check is called
 */
public class BlockPhysicsEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    // Torch start
    protected int changed;
    private boolean cancel = false;
    
    private static BlockPhysicsEvent instance;
    
    /**
    * @param block the block involved in this event
    * @param changed the changed block's type id
    */
    public static BlockPhysicsEvent of(final Block block, final int changed) {
        MutableEvent.init(instance);
        
        if (instance == null) {
            instance = new BlockPhysicsEvent(block, changed);
            return instance;
        }
        
        instance.block = block;
        instance.changed = changed;
        
        return instance;
    }
    // Torch end

    /**
     *
     * @deprecated Magic value
     * @param block the block involved in this event
     * @param changed the changed block's type id
     */
    @Deprecated
    public BlockPhysicsEvent(final Block block, final int changed) {
        super(block);
        this.changed = changed;
    }

    /**
     * Gets the type of block that changed, causing this event
     *
     * @return Changed block's type id
     * @deprecated Magic value
     */
    @Deprecated
    public int getChangedTypeId() {
        return changed;
    }

    /**
     * Gets the type of block that changed, causing this event
     *
     * @return Changed block's type
     */
    public Material getChangedType() {
        return Material.getMaterial(changed);
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}