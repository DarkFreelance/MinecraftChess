package com.gmail.laurynas.pazdrazdis.minecraftpart.buttons;

import com.gmail.laurynas.pazdrazdis.minecraftpart.enums.PlayerJoinFeedback;
import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindTextException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindValueException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.MinecraftChessGame;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class JoinButton extends Button {
   private static String warningtext1;
   private static String warningtext3;
   private static String warningtext2;
   private static String successtext1;
   private static String successtext2;
   private static String kicktext;
   private static int kickAfter;
   private int bet = 0;
   private Material itemType = null;
   private String name;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$PlayerJoinFeedback;

   public JoinButton(String buttonName, World world, int x, int y, int z, MinecraftChessMain plugin, int bet, String itemType) {
      this.setItemType(Material.getMaterial(itemType));
      this.setBet(bet);
      this.setX(x);
      this.setY(y);
      this.setZ(z);
      this.setPlugin(plugin);
      this.setWorld(world);
      this.setName(buttonName);
   }

   public JoinButton(String buttonName, World world, int x, int y, int z, MinecraftChessMain plugin) {
      this.setX(x);
      this.setY(y);
      this.setZ(z);
      this.setPlugin(plugin);
      this.setWorld(world);
      this.setName(buttonName);
   }

   public static void readValuesFromConfig(MinecraftChessMain plugin) throws CannotFindValueException {
      try {
         kickAfter = plugin.getConfig().getInt("kickafter");
      } catch (Exception var2) {
         throw new CannotFindValueException("kickafter");
      }
   }

   public static void readTextsFromConfig(MinecraftChessMain plugin) throws CannotFindTextException {
      try {
         warningtext1 = plugin.getConfig().getString("warningtext1");
      } catch (Exception var7) {
         throw new CannotFindTextException("warningtext1");
      }

      try {
         warningtext3 = plugin.getConfig().getString("warningtext3");
      } catch (Exception var6) {
         throw new CannotFindTextException("warningtext3");
      }

      try {
         warningtext2 = plugin.getConfig().getString("warningtext2");
      } catch (Exception var5) {
         throw new CannotFindTextException("warningtext2");
      }

      try {
         successtext1 = plugin.getConfig().getString("successtext1");
      } catch (Exception var4) {
         throw new CannotFindTextException("successtext1");
      }

      try {
         successtext2 = plugin.getConfig().getString("successtext2");
      } catch (Exception var3) {
         throw new CannotFindTextException("successtext2");
      }

      try {
         kicktext = plugin.getConfig().getString("kicktext");
      } catch (Exception var2) {
         throw new CannotFindTextException("kicktext");
      }
   }

   public void onPowered(Player player, MinecraftChessGame game) {
      if (this.getPlugin().isPlayerInList(player)) {
         player.sendMessage(Utils.chat(warningtext1));
      } else {
         int rating;
         if (this.getItemType() == null) {
            if (game.getItemType() != null) {
               player.sendMessage(Utils.chat(warningtext3));
            } else {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$PlayerJoinFeedback()[game.addPlayer(player).ordinal()]) {
               case 1:
                  if (game.getHologram() != null) {
                     rating = this.getPlugin().getUsersConfig().getInt("Player." + player.getUniqueId().toString() + ".Rating");
                     game.getHologram().setWhiteName(player.getName() + " [" + rating + "]");
                  }

                  this.getPlugin().addPlayerToList(player);
                  player.sendMessage(Utils.chat(successtext1));
                  this.SetTimerToKickPlayerFromBoard(player, game);
                  return;
               case 2:
                  if (game.getHologram() != null) {
                     rating = this.getPlugin().getUsersConfig().getInt("Player." + player.getUniqueId().toString() + ".Rating");
                     game.getHologram().setBlackName(player.getName() + " [" + rating + "]");
                  }

                  this.getPlugin().addPlayerToList(player);
                  game.rotateItemFramesForBlack();
                  player.sendMessage(Utils.chat(successtext2));
                  return;
               default:
               }
            }
         } else {
            if (game.getItemType() != null) {
               if (!game.getItemType().name().equalsIgnoreCase(this.getItemType().name()) || game.getBet() != this.getBet()) {
                  player.sendMessage(Utils.chat(warningtext3));
                  return;
               }
            } else if (game.getWhitePlayer() != null) {
               player.sendMessage(Utils.chat(warningtext3));
               return;
            }

            if (!player.getInventory().containsAtLeast(new ItemStack(this.getItemType()), this.getBet())) {
               player.sendMessage(Utils.chat(warningtext2 + " " + this.getItemType().name()));
            } else {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$PlayerJoinFeedback()[game.addPlayer(player).ordinal()]) {
               case 1:
                  game.setBet(this.getBet());
                  game.setItemType(this.getItemType());
                  if (game.getHologram() != null) {
                     rating = this.getPlugin().getUsersConfig().getInt("Player." + player.getUniqueId().toString() + ".Rating");
                     game.getHologram().setWhiteName(player.getName() + " [" + rating + "]");
                     game.getHologram().setBet(this.getBet() + " " + this.getItemType().name());
                  }

                  player.getInventory().removeItem(new ItemStack[]{new ItemStack(this.getItemType(), this.getBet())});
                  this.getPlugin().addPlayerToList(player);
                  player.sendMessage(Utils.chat(successtext1));
                  this.SetTimerToKickPlayerFromBoard(player, game);
                  return;
               case 2:
                  if (game.getHologram() != null) {
                     rating = this.getPlugin().getUsersConfig().getInt("Player." + player.getUniqueId().toString() + ".Rating");
                     game.getHologram().setBlackName(player.getName() + " [" + rating + "]");
                  }

                  player.getInventory().removeItem(new ItemStack[]{new ItemStack(this.getItemType(), this.getBet())});
                  this.getPlugin().addPlayerToList(player);
                  game.rotateItemFramesForBlack();
                  player.sendMessage(Utils.chat(successtext2));
                  return;
               default:
               }
            }
         }
      }
   }

   private void SetTimerToKickPlayerFromBoard(final Player player, final MinecraftChessGame game) {
      BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
      int taskid = scheduler.scheduleSyncDelayedTask(this.getPlugin(), new Runnable() {
         public void run() {
            if (player.isOnline() && game.isPlayerOnBoard(player) && game.isCancelable()) {
               player.sendMessage(Utils.chat(JoinButton.kicktext));
               if (game.getItemType() != null) {
                  player.getInventory().addItem(new ItemStack[]{new ItemStack(game.getItemType(), game.getBet())});
               }

               JoinButton.this.getPlugin().removePlayerFromList(player);
               game.reset();
            }

         }
      }, 1200L * (long)kickAfter);
      this.getPlugin().addPlayerKickOffTheBoardTaskId(player, taskid);
   }

   public void removeBet() {
      this.bet = 0;
      this.itemType = null;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setItemType(Material itemType) {
      this.itemType = itemType;
   }

   public void setBet(int bet) {
      this.bet = bet;
   }

   public String getName() {
      return this.name;
   }

   public int getBet() {
      return this.bet;
   }

   public Material getItemType() {
      return this.itemType;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$PlayerJoinFeedback() {
      int[] var10000 = $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$PlayerJoinFeedback;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[PlayerJoinFeedback.values().length];

         try {
            var0[PlayerJoinFeedback.BOARD_IS_FULL.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[PlayerJoinFeedback.FIRST_PLAYER_ADDED.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[PlayerJoinFeedback.SECOND_PLAYER_ADDED.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$PlayerJoinFeedback = var0;
         return var0;
      }
   }
}
