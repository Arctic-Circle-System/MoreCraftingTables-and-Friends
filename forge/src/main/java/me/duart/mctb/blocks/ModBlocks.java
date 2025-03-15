package me.duart.mctb.blocks;

import com.duart.mctb.blocks.BlockIds;
import com.duart.mctb.blocks.CraftingBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ModBlocks {
   public static final List<RegistryObject<CraftingBlock>> CRAFTING_TABLES = createCraftingTables();

   @NotNull
   private static List<RegistryObject<CraftingBlock>> createCraftingTables() {
      List<RegistryObject<CraftingBlock>> craftingTables = new ArrayList();
      String[] var1 = BlockIds.CRAFTING_TABLES;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String tableName = var1[var3];
         craftingTables.add(register(tableName + "_crafting_table"));
      }

      return craftingTables;
   }

   public static RegistryObject<CraftingBlock> register(String name) {
      return Registration.BLOCKS.register(name, ModBlocks::createCraftingBlock);
   }

   @Contract(" -> new")
   @NotNull
   private static CraftingBlock createCraftingBlock() {
      return new CraftingBlock(Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
   }

   public static void registerBlockItems() {
      Iterator var0 = CRAFTING_TABLES.iterator();

      while(var0.hasNext()) {
         RegistryObject<CraftingBlock> blockObject = (RegistryObject)var0.next();
         Registration.ITEMS.register(blockObject.getId().getPath(), () -> {
            return new BlockItem((Block)blockObject.get(), new net.minecraft.world.item.Item.Properties());
         });
      }

   }

   public static void register() {
   }
}
