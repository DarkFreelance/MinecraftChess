package com.gmail.laurynas.pazdrazdis.minecraftpart.commands;

import com.gmail.laurynas.pazdrazdis.minecraftpart.buttons.JoinButton;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.MinecraftChessGame;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import java.util.Arrays;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCommandHandler implements CommandExecutor {
   private MinecraftChessMain plugin;

   public ConfigCommandHandler(MinecraftChessMain plugin) {
      this.plugin = plugin;
      plugin.getCommand("mchess").setExecutor(this);
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
            case -490960022:
               if (var6.equals("createboard")) {
                  this.onCreateBoardCommand(p, this.removeArgumentsFromLeft(args, 1));
                  return true;
               }
               break;
            case -466151181:
               if (var6.equals("enableboardedges")) {
                  this.onBoardEdgesCommand(p, this.removeArgumentsFromLeft(args, 1));
                  return true;
               }
               break;
            case -290936606:
               if (var6.equals("removeboard")) {
                  this.onRemoveBoardCommand(p, this.removeArgumentsFromLeft(args, 1));
                  return true;
               }
               break;
            case 3322014:
               if (var6.equals("list")) {
                  if (args.length == 1) {
                     this.onBoardsListCommand(p);
                  } else {
                     p.sendMessage(Utils.chat("&cIncomplete command"));
                  }

                  return true;
               }
               break;
            case 93908710:
               if (var6.equals("board")) {
                  if (args.length != 1 && args.length != 2) {
                     String var7;
                     switch((var7 = args[2]).hashCode()) {
                     case -1824059953:
                        if (var7.equals("setlevitation")) {
                           this.setLevitationCommand(p, this.removeArgumentsFromLeft(args, 3), args[1]);
                           return true;
                        }
                        break;
                     case -1725845414:
                        if (var7.equals("setblackstartlocation")) {
                           this.setBlackStartLocation(p, this.removeArgumentsFromLeft(args, 3), args[1]);
                           return true;
                        }
                        break;
                     case -505531551:
                        if (var7.equals("setoutlocation")) {
                           this.setOutLocation(p, this.removeArgumentsFromLeft(args, 3), args[1]);
                           return true;
                        }
                        break;
                     case -505017823:
                        if (var7.equals("hologram")) {
                           if (args.length == 3) {
                              p.sendMessage(Utils.chat("&cIncomplete command"));
                              return true;
                           }

                           String var9;
                           switch((var9 = args[3]).hashCode()) {
                           case -934610812:
                              if (var9.equals("remove")) {
                                 if (args.length == 4) {
                                    this.removeHologramCommand(p, args[1]);
                                 } else {
                                    p.sendMessage(Utils.chat("&cIncomplete command"));
                                 }

                                 return true;
                              }
                              break;
                           case 113762:
                              if (var9.equals("set")) {
                                 this.setHologramCommand(p, this.removeArgumentsFromLeft(args, 4), args[1]);
                                 return true;
                              }
                           }

                           p.sendMessage(Utils.chat("&cIncomplete command"));
                           return true;
                        }
                        break;
                     case 3237038:
                        if (var7.equals("info")) {
                           if (args.length == 3) {
                              this.showBoardInfoCommand(p, args[1]);
                              return true;
                           }

                           p.sendMessage(Utils.chat("&cIncomplete command"));
                           return true;
                        }
                        break;
                     case 48345358:
                        if (var7.equals("timelimit")) {
                           if (args.length == 3) {
                              p.sendMessage(Utils.chat("&cIncomplete command"));
                              return true;
                           }

                           String var10;
                           switch((var10 = args[3]).hashCode()) {
                           case -934610812:
                              if (var10.equals("remove")) {
                                 if (args.length == 4) {
                                    this.removeTimeLimitCommand(p, args[1]);
                                 } else {
                                    p.sendMessage(Utils.chat("&cIncomplete command"));
                                 }

                                 return true;
                              }
                              break;
                           case 113762:
                              if (var10.equals("set")) {
                                 this.setTimeLimitCommand(p, this.removeArgumentsFromLeft(args, 4), args[1]);
                                 return true;
                              }
                           }

                           p.sendMessage(Utils.chat("&cIncomplete command"));
                           return true;
                        }
                        break;
                     case 794822768:
                        if (var7.equals("setwhitestartlocation")) {
                           this.setWhiteStartLocation(p, this.removeArgumentsFromLeft(args, 3), args[1]);
                           return true;
                        }
                        break;
                     case 984238990:
                        if (var7.equals("setcancelbutton")) {
                           this.setCancelButton(p, this.removeArgumentsFromLeft(args, 3), args[1]);
                           return true;
                        }
                        break;
                     case 1950350556:
                        if (var7.equals("joinbutton")) {
                           if (args.length == 3) {
                              p.sendMessage(Utils.chat("&cIncomplete command"));
                              return true;
                           }

                           String var8;
                           switch((var8 = args[3]).hashCode()) {
                           case -934610812:
                              if (var8.equals("remove")) {
                                 this.onRemoveJoinButtonCommand(p, this.removeArgumentsFromLeft(args, 4), args[1]);
                                 return true;
                              }
                              break;
                           case -905786129:
                              if (var8.equals("setbet")) {
                                 this.onSetBetToJoinButtonCommand(p, this.removeArgumentsFromLeft(args, 4), args[1]);
                                 return true;
                              }
                              break;
                           case 96417:
                              if (var8.equals("add")) {
                                 this.onAddJoinButtonCommand(p, this.removeArgumentsFromLeft(args, 4), args[1]);
                                 return true;
                              }
                              break;
                           case 1282377101:
                              if (var8.equals("removebet")) {
                                 this.removeBetFromJoinButtonCommand(p, this.removeArgumentsFromLeft(args, 4), args[1]);
                                 return true;
                              }
                           }

                           p.sendMessage(Utils.chat("&cIncomplete command"));
                           return true;
                        }
                     }

                     p.sendMessage(Utils.chat("&cIncomplete command"));
                     return true;
                  }

                  p.sendMessage(Utils.chat("&cIncomplete command"));
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

   private void onBoardEdgesCommand(Player p, String[] args) {
      if (args.length != 1) {
         p.sendMessage(Utils.chat("&c/mchess enableboardedges <true/false>"));
      } else if (args[0].equals("true")) {
         this.plugin.getConfig().set("enableboardedges", true);
         this.plugin.saveConfig();
         p.sendMessage(Utils.chat("&cChanges will be shown after server restart"));
      } else if (args[0].equals("false")) {
         this.plugin.getConfig().set("enableboardedges", false);
         this.plugin.saveConfig();
         p.sendMessage(Utils.chat("&cChanges will be shown after server restart"));
      } else {
         p.sendMessage(Utils.chat("&c/mchess enableboardedges <ture/false>"));
      }
   }

   private void onCreateBoardCommand(Player p, String[] args) {
      if (args.length != 4) {
         p.sendMessage(Utils.chat("&c/mchess createboard <board name> <X> <Y> <Z>"));
      } else if (this.plugin.getMinecraftChessFromName(args[0]) != null) {
         p.sendMessage(Utils.chat("&cThere already is a board named " + args[0]));
      } else {
         try {
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            int z = Integer.parseInt(args[3]);
            this.plugin.getConfig().set("boards." + args[0] + ".X", x);
            this.plugin.getConfig().set("boards." + args[0] + ".Y", y);
            this.plugin.getConfig().set("boards." + args[0] + ".Z", z);
            this.plugin.getConfig().set("boards." + args[0] + ".worldname", p.getWorld().getName());
            this.plugin.saveConfig();
            if (p.getLocation().getBlockY() - y < 5 && p.getLocation().getBlockY() - y > -5) {
               p.teleport(new Location(p.getWorld(), (double)p.getLocation().getBlockX(), (double)(p.getLocation().getBlockY() + 10), (double)p.getLocation().getBlockZ()));
            }

            this.plugin.createChessBoard(p, args[0], p.getWorld());
         } catch (NumberFormatException var6) {
            p.sendMessage(Utils.chat("&cCoordinates X, Y and Z must be integers"));
         }

      }
   }

   private void onRemoveBoardCommand(Player p, String[] args) {
      if (args.length != 1) {
         p.sendMessage(Utils.chat("&c/mchess removeboard <board name>"));
      } else {
         if (this.plugin.getMinecraftChessFromName(args[0]) != null) {
            this.plugin.getConfig().set("boards." + args[0], (Object)null);
            this.plugin.saveConfig();
            this.plugin.removeChessBoard(p, this.plugin.getMinecraftChessFromName(args[0]));
         } else {
            p.sendMessage(Utils.chat("&cThere is no board named " + args[0]));
         }

      }
   }

   private void onBoardsListCommand(Player p) {
      p.sendMessage(Utils.chat("&6Boards list:"));

      for(int i = 0; i < this.plugin.getMinecraftChessList().size(); ++i) {
         p.sendMessage(Utils.chat("&b" + ((MinecraftChessGame)this.plugin.getMinecraftChessList().get(i)).getName()));
      }

   }

   private void onAddJoinButtonCommand(Player p, String[] args, String boardName) {
      if (args.length != 4) {
         p.sendMessage(Utils.chat("&c/mchess <board name> joinbutton add <button name> <X> <Y> <Z>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else if (this.plugin.getMinecraftChessFromName(boardName).getJoinButtonFromName(args[0]) != null) {
         p.sendMessage(Utils.chat("&cThere is already a button named " + args[0] + " on the board named " + boardName));
      } else {
         try {
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            int z = Integer.parseInt(args[3]);
            this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".X", x);
            this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".Y", y);
            this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".Z", z);
            this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".worldname", p.getWorld().getName());
            this.plugin.saveConfig();
            this.plugin.addJoinButton(p, this.plugin.getMinecraftChessFromName(boardName), args[0]);
         } catch (NumberFormatException var7) {
            p.sendMessage(Utils.chat("&cCoordinates X, Y and Z must be integers"));
         }

      }
   }

   private void onRemoveJoinButtonCommand(Player p, String[] args, String boardName) {
      if (args.length != 1) {
         p.sendMessage(Utils.chat("&c/mchess board <board name> joinbutton remove <button name>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else if (this.plugin.getMinecraftChessFromName(boardName).getJoinButtonFromName(args[0]) == null) {
         p.sendMessage(Utils.chat("&cThere is no button named " + args[0] + " on the board named " + boardName));
      } else {
         this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0], (Object)null);
         this.plugin.saveConfig();
         this.plugin.removeJoinButton(p, this.plugin.getMinecraftChessFromName(boardName), args[0]);
      }
   }

   private void onSetBetToJoinButtonCommand(Player p, String[] args, String boardName) {
      if (args.length != 3) {
         p.sendMessage(Utils.chat("&c/mchess <board name> joinbutton setbet <button name> <item type> <amount>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else if (this.plugin.getMinecraftChessFromName(boardName).getJoinButtonFromName(args[0]) == null) {
         p.sendMessage(Utils.chat("&cThere is no button named " + args[0] + "on the board named " + boardName));
      } else if (Material.getMaterial(args[1]) == null) {
         p.sendMessage(Utils.chat("&cMaterial \"" + args[1] + "\" doesn't exist"));
      } else {
         try {
            int amount = Integer.parseInt(args[2]);
            if (amount < 0) {
               p.sendMessage(Utils.chat("&cAmount can't be negative"));
               return;
            }
         } catch (NumberFormatException var5) {
            p.sendMessage(Utils.chat("&cAmount must be an integer"));
            return;
         }

         this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".item", args[1]);
         this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".bet", Integer.parseInt(args[2]));
         this.plugin.saveConfig();
         this.plugin.setBetToJoinButton(p, this.plugin.getMinecraftChessFromName(boardName), args[0]);
      }
   }

   private void removeBetFromJoinButtonCommand(Player p, String[] args, String boardName) {
      if (args.length != 1) {
         p.sendMessage(Utils.chat("&c/mcchess board <board name> joinbutton removebet <button name>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else if (this.plugin.getMinecraftChessFromName(boardName).getJoinButtonFromName(args[0]) == null) {
         p.sendMessage(Utils.chat("&cThere is no button named " + args[0] + " on the board named " + boardName));
      } else {
         this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".item", (Object)null);
         this.plugin.getConfig().set("boards." + boardName + ".join_buttons." + args[0] + ".bet", (Object)null);
         this.plugin.saveConfig();
         this.plugin.removeBetFromJoinButton(p, this.plugin.getMinecraftChessFromName(boardName), args[0]);
      }
   }

   private void setHologramCommand(Player p, String[] args, String boardName) {
      if (args.length != 3) {
         p.sendMessage(Utils.chat("&c/mchess board <board name> hologram set <X> <Y> <Z>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);
            this.plugin.getConfig().set("boards." + boardName + ".text.X", x);
            this.plugin.getConfig().set("boards." + boardName + ".text.Y", y);
            this.plugin.getConfig().set("boards." + boardName + ".text.Z", z);
            this.plugin.getConfig().set("boards." + boardName + ".text.worldname", p.getWorld().getName());
            this.plugin.saveConfig();
            this.plugin.setHologram(p, this.plugin.getMinecraftChessFromName(boardName));
         } catch (NumberFormatException var10) {
            p.sendMessage(Utils.chat("&cCoordinates X, Y and Z must be floats or integers"));
         }

      }
   }

   private void removeHologramCommand(Player p, String boardName) {
      if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         this.plugin.getConfig().set("boards." + boardName + ".text", (Object)null);
         this.plugin.saveConfig();
         this.plugin.removeText(p, this.plugin.getMinecraftChessFromName(boardName));
      }
   }

   private void showBoardInfoCommand(Player p, String boardName) {
      if (!this.plugin.getConfig().isConfigurationSection("boards." + boardName)) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         p.sendMessage(Utils.chat("&6Board information of " + boardName + ":"));
         MinecraftChessGame mc = this.plugin.getMinecraftChessFromName(boardName);
         p.sendMessage(Utils.chat("&bBoard location: &aworldname: " + mc.getWorld().getName() + ", X: " + mc.getX() + ", Y: " + mc.getY() + ", Z: " + mc.getZ()));
         if (mc.getTimeLimit() > 0L) {
            p.sendMessage(Utils.chat("&bTime Limit: &a" + mc.getTimeLimit() + "min"));
         } else {
            p.sendMessage(Utils.chat("&bTime Limit: &aNOT SET"));
         }

         if (mc.getLevitationEffect()) {
            p.sendMessage(Utils.chat("&bLevitation effect: &atrue"));
         } else {
            p.sendMessage(Utils.chat("&bLevitation effect: &afalse"));
         }

         if (mc.getHologram() != null) {
            p.sendMessage(Utils.chat("&bHologram text location: &aworldname: " + mc.getHologram().getWorld().getName() + ", X: " + mc.getHologram().getX() + ", Y: " + mc.getHologram().getY() + ", Z: " + mc.getHologram().getZ()));
         } else {
            p.sendMessage(Utils.chat("&bHologram text location: &aNOT SET"));
         }

         if (mc.getWhiteIn() != null) {
            p.sendMessage(Utils.chat("&bStarting location for white: &aworldname: " + mc.getWhiteIn().getWorld().getName() + ", X: " + mc.getWhiteIn().getX() + ", Y: " + mc.getWhiteIn().getY() + ", Z: " + mc.getWhiteIn().getZ()));
         } else {
            p.sendMessage(Utils.chat("&bStarting location for white: &aNOT SET"));
         }

         if (mc.getBlackIn() != null) {
            p.sendMessage(Utils.chat("&bStarting location for black: &aworldname: " + mc.getBlackIn().getWorld().getName() + ", X: " + mc.getBlackIn().getX() + ", Y: " + mc.getBlackIn().getY() + ", Z: " + mc.getBlackIn().getZ()));
         } else {
            p.sendMessage(Utils.chat("&bStarting location for black: &aNOT SET"));
         }

         if (mc.getAllOut() != null) {
            p.sendMessage(Utils.chat("&bTeleport location for both(when game ends): &aworldname: " + mc.getAllOut().getWorld().getName() + ", X: " + mc.getAllOut().getX() + ", Y: " + mc.getAllOut().getY() + ", Z: " + mc.getAllOut().getZ()));
         } else {
            p.sendMessage(Utils.chat("&bTeleport location for both(when game ends): &aNOT SET"));
         }

         if (mc.getCancelButton() != null) {
            p.sendMessage(Utils.chat("&bCancel button location: &aworldname: " + mc.getCancelButton().getWorld().getName() + ", X: " + mc.getCancelButton().getX() + ", Y: " + mc.getCancelButton().getY() + ", Z: " + mc.getCancelButton().getZ()));
         } else {
            p.sendMessage(Utils.chat("&bCancel button location: &aNOT SET"));
         }

         p.sendMessage(Utils.chat("&6Join buttons:"));

         for(int i = 0; i < mc.getJoinButtons().size(); ++i) {
            p.sendMessage(Utils.chat("&9" + ((JoinButton)mc.getJoinButtons().get(i)).getName() + ":"));
            p.sendMessage(Utils.chat("&bLocation: &aworldname: " + ((JoinButton)mc.getJoinButtons().get(i)).getWorld().getName() + ", X: " + ((JoinButton)mc.getJoinButtons().get(i)).getX() + ", Y: " + ((JoinButton)mc.getJoinButtons().get(i)).getY() + ", Z: " + ((JoinButton)mc.getJoinButtons().get(i)).getZ()));
            if (((JoinButton)mc.getJoinButtons().get(i)).getItemType() != null) {
               p.sendMessage(Utils.chat("&bBetting item: &a" + ((JoinButton)mc.getJoinButtons().get(i)).getItemType().name()));
               p.sendMessage(Utils.chat("&bBetting amount: &a" + ((JoinButton)mc.getJoinButtons().get(i)).getBet()));
            } else {
               p.sendMessage(Utils.chat("&bBetting item: &aNOT SET"));
               p.sendMessage(Utils.chat("&bBetting amount: &aNOT SET"));
            }
         }

      }
   }

   private void setTimeLimitCommand(Player p, String[] args, String boardName) {
      if (args.length != 1) {
         p.sendMessage(Utils.chat("&c/mchess board <board name> timelimit <minutes>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         try {
            int time_limit = Integer.parseInt(args[0]);
            if (time_limit <= 0) {
               p.sendMessage(Utils.chat("&cThe time limit must be positive"));
            }

            this.plugin.getConfig().set("boards." + boardName + ".time_limit", time_limit);
            this.plugin.saveConfig();
            this.plugin.setTimeLimit(p, this.plugin.getMinecraftChessFromName(boardName));
         } catch (NumberFormatException var5) {
            p.sendMessage(Utils.chat("&cTime limit must be integer"));
         }

      }
   }

   private void removeTimeLimitCommand(Player p, String boardName) {
      if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         this.plugin.getConfig().set("boards." + boardName + ".time_limit", (Object)null);
         this.plugin.saveConfig();
         this.plugin.removeTimeLimit(p, this.plugin.getMinecraftChessFromName(boardName));
      }
   }

   private void setLevitationCommand(Player p, String[] args, String boardName) {
      if (args.length != 1) {
         p.sendMessage(Utils.chat("&c/mchess board <board name> setlevitation <true/false>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else if (args[0].equalsIgnoreCase("true")) {
         this.plugin.getConfig().set("boards." + boardName + ".levitation_effect", true);
         this.plugin.saveConfig();
         this.plugin.setLevitationEffect(p, this.plugin.getMinecraftChessFromName(boardName));
      } else if (args[0].equalsIgnoreCase("false")) {
         this.plugin.getConfig().set("boards." + boardName + ".levitation_effect", false);
         this.plugin.saveConfig();
         this.plugin.setLevitationEffect(p, this.plugin.getMinecraftChessFromName(boardName));
      } else {
         p.sendMessage(Utils.chat("&cThe true/false argument must be \"true\" or \"false\""));
      }
   }

   private void setWhiteStartLocation(Player p, String[] args, String boardName) {
      if (args.length != 3) {
         p.sendMessage(Utils.chat("&c/mchess board <board name> setwhitestartlocation <X> <Y> <Z>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_white_in.X", x);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_white_in.Y", y);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_white_in.Z", z);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_white_in.worldname", p.getWorld().getName());
            this.plugin.saveConfig();
            this.plugin.setWhiteIn(p, this.plugin.getMinecraftChessFromName(boardName));
         } catch (NumberFormatException var10) {
            p.sendMessage(Utils.chat("&cCoordinates X, Y and Z must be flaots or integers"));
         }

      }
   }

   private void setBlackStartLocation(Player p, String[] args, String boardName) {
      if (args.length != 3) {
         p.sendMessage(Utils.chat("&c/mchess board <board name> setblackstartlocation <X> <Y> <Z>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_black_in.X", x);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_black_in.Y", y);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_black_in.Z", z);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_black_in.worldname", p.getWorld().getName());
            this.plugin.saveConfig();
            this.plugin.setBlackIn(p, this.plugin.getMinecraftChessFromName(boardName));
         } catch (NumberFormatException var10) {
            p.sendMessage(Utils.chat("&cCoordinates X, Y and Z must be flaots or integers"));
         }

      }
   }

   private void setOutLocation(Player p, String[] args, String boardName) {
      if (args.length != 3) {
         p.sendMessage(Utils.chat("&c/mchess board <board name> setoutlocation <X> <Y> <Z>"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_out.X", x);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_out.Y", y);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_out.Z", z);
            this.plugin.getConfig().set("boards." + boardName + ".teleport_out.worldname", p.getWorld().getName());
            this.plugin.saveConfig();
            this.plugin.setAllOut(p, this.plugin.getMinecraftChessFromName(boardName));
         } catch (NumberFormatException var10) {
            p.sendMessage(Utils.chat("&cCoordinates X, Y and Z must be flaots or integers"));
         }

      }
   }

   private void setCancelButton(Player p, String[] args, String boardName) {
      if (args.length != 3) {
         p.sendMessage(Utils.chat("&c/setcancelbutton [board name] [X] [Y] [Z]"));
      } else if (this.plugin.getMinecraftChessFromName(boardName) == null) {
         p.sendMessage(Utils.chat("&cThere is no board named " + boardName));
      } else {
         try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            this.plugin.getConfig().set("boards." + boardName + ".cancel_button.X", x);
            this.plugin.getConfig().set("boards." + boardName + ".cancel_button.Y", y);
            this.plugin.getConfig().set("boards." + boardName + ".cancel_button.Z", z);
            this.plugin.getConfig().set("boards." + boardName + ".cancel_button.worldname", p.getWorld().getName());
            this.plugin.saveConfig();
            this.plugin.setCancelButton(p, this.plugin.getMinecraftChessFromName(boardName));
         } catch (NumberFormatException var7) {
            p.sendMessage(Utils.chat("&cCoordinates X, Y and Z must be integers"));
         }

      }
   }

   private String[] removeArgumentsFromLeft(String[] args, int remove) {
      return remove > args.length ? null : (String[])Arrays.copyOfRange(args, remove, args.length);
   }
}
