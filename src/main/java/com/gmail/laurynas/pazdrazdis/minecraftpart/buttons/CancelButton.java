package com.gmail.laurynas.pazdrazdis.minecraftpart.buttons;

import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindTextException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.MinecraftChessGame;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CancelButton extends Button {
   private static String successtext3;
   private static String warningtext4;

   public CancelButton(World world, int x, int y, int z, MinecraftChessMain plugin) {
      this.setX(x);
      this.setY(y);
      this.setZ(z);
      this.setPlugin(plugin);
      this.setWorld(world);
   }

   public static void readTextsFromConfig(MinecraftChessMain plugin) throws CannotFindTextException {
      try {
         successtext3 = plugin.getConfig().getString("successtext3");
      } catch (Exception var3) {
         throw new CannotFindTextException("successtext3");
      }

      try {
         warningtext4 = plugin.getConfig().getString("warningtext4");
      } catch (Exception var2) {
         throw new CannotFindTextException("warningtext4");
      }
   }

   public void onPowered(Player player, MinecraftChessGame game) {
      if (game.isPlayerOnBoard(player)) {
         if (game.isCancelable()) {
            player.sendMessage(Utils.chat(successtext3));
            if (game.getItemType() != null) {
               player.getInventory().addItem(new ItemStack[]{new ItemStack(game.getItemType(), game.getBet())});
            }

            this.getPlugin().removePlayerFromList(player);
            game.reset();
         }
      } else if (this.getPlugin().isPlayerInList(player)) {
         player.sendMessage(Utils.chat(warningtext4));
      }

   }
}
