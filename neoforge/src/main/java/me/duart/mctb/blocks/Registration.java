package me.duart.mctb.blocks;

import com.duart.mctb.blocks.CraftingBlock;
import java.util.Iterator;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Blocks;
import net.neoforged.neoforge.registries.DeferredRegister.Items;

public class Registration {
   public static final Blocks BLOCKS = DeferredRegister.createBlocks("mctb");
   public static final Items ITEMS = DeferredRegister.createItems("mctb");
   public static final DeferredRegister<CreativeModeTab> TABS;
   public static final Supplier<CreativeModeTab> CUSTOM_TAB;

   public static DeferredBlock<CraftingBlock> registerBlock(String name, Supplier<CraftingBlock> blockSupplier) {
      return BLOCKS.register(name, blockSupplier);
   }

   static {
      TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "mctb");
      CUSTOM_TAB = TABS.register("mctb_tab", () -> {
         return CreativeModeTab.builder().title(Component.translatable("itemGroup.custom_tab")).icon(() -> {
            return new ItemStack((ItemLike)((DeferredBlock)ModBlocks.CRAFTING_TABLES.get(6)).get());
         }).displayItems((params, output) -> {
            int index = 0;

            for(Iterator var3 = ModBlocks.CRAFTING_TABLES.iterator(); var3.hasNext(); ++index) {
               DeferredBlock<CraftingBlock> blockObject = (DeferredBlock)var3.next();
               if (index >= 0 && index <= 25) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index >= 26 && index <= 38 && ModList.get().isLoaded("biomesoplenty")) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index >= 39 && index <= 40 && ModList.get().isLoaded("quark")) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index >= 41 && index <= 43 && ModList.get().isLoaded("ad_astra")) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index == 44 && ModList.get().isLoaded("naturesaura")) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index >= 45 && index <= 47 && ModList.get().isLoaded("undergarden")) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index >= 48 && index <= 56 && ModList.get().isLoaded("twilightforest")) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index >= 57 && index <= 79 && ModList.get().isLoaded("regions_unexplored")) {
                  output.accept((ItemLike)blockObject.get());
               } else if (index >= 80 && index <= 97 && ModList.get().isLoaded("dyenamics")) {
                  output.accept((ItemLike)blockObject.get());
               }
            }

         }).build();
      });
   }
}
