package me.duart.mctb.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
   public static final DeferredRegister<Block> BLOCKS;
   public static final DeferredRegister<Item> ITEMS;
   public static final DeferredRegister<CreativeModeTab> TABS;

   public static void register() {
      IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
      TABS.register(modEventBus);
      BLOCKS.register(modEventBus);
      ITEMS.register(modEventBus);
      ModBlocks.register();
   }

   static {
      BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "mctb");
      ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "mctb");
      TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "mctb");
   }
}
