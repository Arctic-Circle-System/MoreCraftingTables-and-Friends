package me.duart.mctb;

import java.util.Iterator;
import me.duart.mctb.blocks.ModBlocks;
import me.duart.mctb.blocks.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod("mctb")
public class MoreCraftingTables {
   private static final Logger LOGGER = LogManager.getLogger();
   private final IEventBus modEventBus;

   public MoreCraftingTables(IEventBus modEventBus) {
      this.modEventBus = modEventBus;
      this.register();
      ModBlocks.registerBlockItems();
      NeoForge.EVENT_BUS.register(this);
      LOGGER.info("MoreCraftingTables Loaded");
   }

   public void register() {
      Registration.TABS.register(this.modEventBus);
      Registration.BLOCKS.register(this.modEventBus);
      Registration.ITEMS.register(this.modEventBus);
      ModBlocks.register();
   }

   @SubscribeEvent
   public void onFurnaceFuelBurnTime(@NotNull FurnaceFuelBurnTimeEvent event) {
      Item item = event.getItemStack().getItem();
      if (this.isCustomCraftingTableItem(item)) {
         event.setBurnTime(300);
      }

   }

   private boolean isCustomCraftingTableItem(Item item) {
      Iterator var2 = ModBlocks.CRAFTING_TABLES.iterator();

      DeferredBlock blockObject;
      ResourceLocation blockLocation;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         blockObject = (DeferredBlock)var2.next();
         blockLocation = blockObject.getId();
      } while("mctb:warped_crafting_table".equals(blockLocation.toString()) || "mctb:crimson_crafting_table".equals(blockLocation.toString()) || item != Item.BY_BLOCK.get(blockObject.get()));

      return true;
   }
}
