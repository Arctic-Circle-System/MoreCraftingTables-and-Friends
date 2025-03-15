package me.duart.mctb.blocks;

import com.duart.mctb.blocks.CraftingBlock;
import java.util.Iterator;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(
   modid = "mctb",
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class ModTab {
   public static final RegistryObject<CreativeModeTab> CUSTOM_TAB;

   @SubscribeEvent
   public static void setCustomTabContents(@NotNull BuildCreativeModeTabContentsEvent event) {
      if (event.getTab() == CUSTOM_TAB.get()) {
         int index = 0;

         for(Iterator var2 = ModBlocks.CRAFTING_TABLES.iterator(); var2.hasNext(); ++index) {
            RegistryObject<CraftingBlock> blockObject = (RegistryObject)var2.next();
            if (index >= 0 && index <= 25) {
               event.accept((ItemLike)blockObject.get());
            } else if (index >= 26 && index <= 38 && ModList.get().isLoaded("biomesoplenty")) {
               event.accept((ItemLike)blockObject.get());
            } else if (index >= 39 && index <= 40 && ModList.get().isLoaded("quark")) {
               event.accept((ItemLike)blockObject.get());
            } else if (index >= 41 && index <= 43 && ModList.get().isLoaded("ad_astra")) {
               event.accept((ItemLike)blockObject.get());
            } else if (index == 44 && ModList.get().isLoaded("naturesaura")) {
               event.accept((ItemLike)blockObject.get());
            } else if (index >= 45 && index <= 47 && ModList.get().isLoaded("undergarden")) {
               event.accept((ItemLike)blockObject.get());
            } else if (index >= 48 && index <= 56 && ModList.get().isLoaded("twilightforest")) {
               event.accept((ItemLike)blockObject.get());
            } else if (index >= 57 && ModList.get().isLoaded("regions_unexplored")) {
               event.accept((ItemLike)blockObject.get());
            }
         }
      }

   }

   static {
      CUSTOM_TAB = Registration.TABS.register("mctb_tab", () -> {
         Block blockToUseAsIcon = (Block)((RegistryObject)ModBlocks.CRAFTING_TABLES.get(6)).get();
         return CreativeModeTab.builder().title(Component.translatable("itemGroup.custom_tab")).icon(() -> {
            return new ItemStack(blockToUseAsIcon);
         }).build();
      });
   }
}
