package com.gmail.laurynas.pazdrazdis.minecraftpart.commands;

import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindTextException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RatingCommandHandler implements CommandExecutor {
   private static String ratingtext1;
   private static String ratingtext2;
   private static String ratingtext3;
   private static String ratingtext4;
   private static String ratingtext5;
   private MinecraftChessMain plugin;

   public RatingCommandHandler(MinecraftChessMain plugin) {
      this.plugin = plugin;
      plugin.getCommand("chessratings").setExecutor(this);
   }

   public static void readTextsFromConfig(MinecraftChessMain plugin) throws CannotFindTextException {
      try {
         ratingtext1 = plugin.getConfig().getString("ratingtext1");
      } catch (Exception var6) {
         throw new CannotFindTextException("ratingtext1");
      }

      try {
         ratingtext2 = plugin.getConfig().getString("ratingtext2");
      } catch (Exception var5) {
         throw new CannotFindTextException("ratingtext2");
      }

      try {
         ratingtext3 = plugin.getConfig().getString("ratingtext3");
      } catch (Exception var4) {
         throw new CannotFindTextException("ratingtext3");
      }

      try {
         ratingtext4 = plugin.getConfig().getString("ratingtext4");
      } catch (Exception var3) {
         throw new CannotFindTextException("ratingtext4");
      }

      try {
         ratingtext5 = plugin.getConfig().getString("ratingtext5");
      } catch (Exception var2) {
         throw new CannotFindTextException("ratingtext5");
      }
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (sender instanceof Player) {
         Player p = (Player)sender;
         if (args.length == 0) {
            p.sendMessage(Utils.chat("&cIncomplete command"));
            return true;
         } else {
            String var6;
            switch((var6 = args[0]).hashCode()) {
            case -985752863:
               if (var6.equals("player")) {
                  if (args.length != 2) {
                     p.sendMessage(Utils.chat("&cIncomplete command"));
                     return true;
                  }

                  this.showPlayerRating((Player)sender, args[1]);
                  return true;
               }
               break;
            case 115029:
               if (var6.equals("top")) {
                  if (args.length != 1) {
                     p.sendMessage(Utils.chat("&cIncomplete command"));
                     return true;
                  }

                  this.showTopPlayers((Player)sender);
                  return true;
               }
            }

            p.sendMessage(Utils.chat("&cIncomplete command"));
            return true;
         }
      } else {
         return true;
      }
   }

   private void showPlayerRating(Player player, String playerToShow) {
      Player p = Bukkit.getPlayer(playerToShow);
      if (p != null && p.isOnline()) {
         int rating = this.plugin.getUsersConfig().getInt("Player." + p.getUniqueId().toString() + ".Rating");
         int games = this.plugin.getUsersConfig().getInt("Player." + p.getUniqueId().toString() + ".Games");
         player.sendMessage(Utils.chat(ratingtext1 + " " + p.getName() + ratingtext2 + " " + rating + ratingtext3 + " " + games));
      } else {
         player.sendMessage(Utils.chat(ratingtext5));
      }
   }

   private void showTopPlayers(Player player) {
      String[] top5Names = new String[5];
      int[] top5Ratings = new int[5];
      int[] top5Games = new int[5];
      player.sendMessage(Utils.chat(ratingtext4));
      Iterator var6 = this.plugin.getUsersConfig().getConfigurationSection("Player").getKeys(false).iterator();

      while(true) {
         String tempName;
         int tempRating;
         int tempGames;
         label78:
         do {
            while(true) {
               while(true) {
                  while(true) {
                     while(var6.hasNext()) {
                        String uuid = (String)var6.next();
                        tempName = this.plugin.getUsersConfig().getString("Player." + uuid + ".Name");
                        tempRating = this.plugin.getUsersConfig().getInt("Player." + uuid + ".Rating");
                        tempGames = this.plugin.getUsersConfig().getInt("Player." + uuid + ".Games");
                        if (tempRating <= top5Ratings[0] && (tempRating != top5Ratings[0] || tempGames <= top5Games[0])) {
                           if (tempRating <= top5Ratings[1] && (tempRating != top5Ratings[1] || tempGames <= top5Games[1])) {
                              if (tempRating <= top5Ratings[2] && (tempRating != top5Ratings[2] || tempGames <= top5Games[2])) {
                                 if (tempRating <= top5Ratings[3] && (tempRating != top5Ratings[3] || tempGames <= top5Games[3])) {
                                    continue label78;
                                 }

                                 top5Ratings[4] = top5Ratings[3];
                                 top5Games[4] = top5Games[3];
                                 top5Names[4] = top5Names[3];
                                 top5Ratings[3] = tempRating;
                                 top5Games[3] = tempGames;
                                 top5Names[3] = tempName;
                              } else {
                                 top5Ratings[4] = top5Ratings[3];
                                 top5Games[4] = top5Games[3];
                                 top5Names[4] = top5Names[3];
                                 top5Ratings[3] = top5Ratings[2];
                                 top5Games[3] = top5Games[2];
                                 top5Names[3] = top5Names[2];
                                 top5Ratings[2] = tempRating;
                                 top5Games[2] = tempGames;
                                 top5Names[2] = tempName;
                              }
                           } else {
                              top5Ratings[4] = top5Ratings[3];
                              top5Games[4] = top5Games[3];
                              top5Names[4] = top5Names[3];
                              top5Ratings[3] = top5Ratings[2];
                              top5Games[3] = top5Games[2];
                              top5Names[3] = top5Names[2];
                              top5Ratings[2] = top5Ratings[1];
                              top5Games[2] = top5Games[1];
                              top5Names[2] = top5Names[1];
                              top5Ratings[1] = tempRating;
                              top5Games[1] = tempGames;
                              top5Names[1] = tempName;
                           }
                        } else {
                           top5Ratings[4] = top5Ratings[3];
                           top5Games[4] = top5Games[3];
                           top5Names[4] = top5Names[3];
                           top5Ratings[3] = top5Ratings[2];
                           top5Games[3] = top5Games[2];
                           top5Names[3] = top5Names[2];
                           top5Ratings[2] = top5Ratings[1];
                           top5Games[2] = top5Games[1];
                           top5Names[2] = top5Names[1];
                           top5Ratings[1] = top5Ratings[0];
                           top5Games[1] = top5Games[0];
                           top5Names[1] = top5Names[0];
                           top5Ratings[0] = tempRating;
                           top5Games[0] = tempGames;
                           top5Names[0] = tempName;
                        }
                     }

                     for(int i = 0; i < 5; ++i) {
                        if (top5Names[i] != null) {
                           player.sendMessage(Utils.chat(ratingtext1 + " " + (i + 1) + ". " + top5Names[i] + ratingtext2 + " " + top5Ratings[i] + ratingtext3 + " " + top5Games[i]));
                        }
                     }

                     return;
                  }
               }
            }
         } while(tempRating <= top5Ratings[4] && (tempRating != top5Ratings[4] || tempGames <= top5Games[4]));

         top5Ratings[4] = tempRating;
         top5Games[4] = tempGames;
         top5Names[4] = tempName;
      }
   }
}
