package com.gmail.laurynas.pazdrazdis.minecraftpart.commands;

import com.gmail.laurynas.pazdrazdis.minecraftpart.buttons.JoinButton;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.MinecraftChessGame;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ConfigTabCompleteHandler implements TabCompleter {
   private static final List firstArgumentList = Collections.unmodifiableList(Arrays.asList("createboard", "removeboard", "list", "board", "enableboardedges"));
   private static final List boardArgumentList = Collections.unmodifiableList(Arrays.asList("joinbutton", "hologram", "info", "timelimit", "setlevitation", "setwhitestartlocation", "setblackstartlocation", "setoutlocation", "setcancelbutton"));
   private static final List joinButtonArgumentList = Collections.unmodifiableList(Arrays.asList("add", "remove", "setbet", "removebet"));
   private static final List setOrRemove = Collections.unmodifiableList(Arrays.asList("set", "remove"));
   private static final List trueOrFalse = Collections.unmodifiableList(Arrays.asList("true", "false"));
   private MinecraftChessMain plugin;

   public ConfigTabCompleteHandler(MinecraftChessMain plugin) {
      this.plugin = plugin;
      plugin.getCommand("mchess").setTabCompleter(this);
   }

   public List onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
      String s;
      switch(args.length) {
      case 1:
         List fList = new ArrayList();
         Iterator var17 = firstArgumentList.iterator();

         while(var17.hasNext()) {
            s = (String)var17.next();
            if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
               fList.add(s.toLowerCase());
            }
         }

         return fList;
      case 2:
         String var5;
         switch((var5 = args[0]).hashCode()) {
         case -466151181:
            if (var5.equals("enableboardedges")) {
               List fList2 = new ArrayList();
               Iterator var20 = trueOrFalse.iterator();

               while(var20.hasNext()) {
                  s = (String)var20.next();
                  if (s.toLowerCase().startsWith(args[1].toLowerCase())) {
                     fList2.add(s.toLowerCase());
                  }
               }

               return fList2;
            }
            break;
         case -290936606:
            if (var5.equals("removeboard")) {
               return this.getBoardsNames(args[1]);
            }
            break;
         case 93908710:
            if (var5.equals("board")) {
               return this.getBoardsNames(args[1]);
            }
         }

         return null;
      case 3:
         switch((s = args[0]).hashCode()) {
         case -490960022:
            if (s.equals("createboard")) {
               return this.getTargetingCoordinates(args[2], (Player)sender);
            }
            break;
         case 93908710:
            if (s.equals("board")) {
               List fList3 = new ArrayList();
               Iterator var22 = boardArgumentList.iterator();

               while(var22.hasNext()) {
                  s = (String)var22.next();
                  if (s.toLowerCase().startsWith(args[2].toLowerCase())) {
                     fList3.add(s.toLowerCase());
                  }
               }

               return fList3;
            }
         }

         return null;
      case 4:
         if (args[0].equals("board")) {
            ArrayList fList4;
            Iterator var21;
            switch((s = args[2]).hashCode()) {
            case -1824059953:
               if (s.equals("setlevitation")) {
                  fList4 = new ArrayList();
                  var21 = trueOrFalse.iterator();

                  while(var21.hasNext()) {
                     s = (String)var21.next();
                     if (s.toLowerCase().startsWith(args[3].toLowerCase())) {
                        fList4.add(s.toLowerCase());
                     }
                  }

                  return fList4;
               }
               break;
            case -1725845414:
               if (s.equals("setblackstartlocation")) {
                  return this.getPlayerCoordinates(args[3], (Player)sender);
               }
               break;
            case -505531551:
               if (s.equals("setoutlocation")) {
                  return this.getPlayerCoordinates(args[3], (Player)sender);
               }
               break;
            case -505017823:
               if (s.equals("hologram")) {
                  fList4 = new ArrayList();
                  var21 = setOrRemove.iterator();

                  while(var21.hasNext()) {
                     s = (String)var21.next();
                     if (s.toLowerCase().startsWith(args[3].toLowerCase())) {
                        fList4.add(s.toLowerCase());
                     }
                  }

                  return fList4;
               }
               break;
            case 48345358:
               if (s.equals("timelimit")) {
                  fList4 = new ArrayList();
                  var21 = setOrRemove.iterator();

                  while(var21.hasNext()) {
                     s = (String)var21.next();
                     if (s.toLowerCase().startsWith(args[3].toLowerCase())) {
                        fList4.add(s.toLowerCase());
                     }
                  }

                  return fList4;
               }
               break;
            case 794822768:
               if (s.equals("setwhitestartlocation")) {
                  return this.getPlayerCoordinates(args[3], (Player)sender);
               }
               break;
            case 984238990:
               if (s.equals("setcancelbutton")) {
                  return this.getTargetingCoordinates(args[3], (Player)sender);
               }
               break;
            case 1950350556:
               if (s.equals("joinbutton")) {
                  fList4 = new ArrayList();
                  var21 = joinButtonArgumentList.iterator();

                  while(var21.hasNext()) {
                     s = (String)var21.next();
                     if (s.toLowerCase().startsWith(args[3].toLowerCase())) {
                        fList4.add(s.toLowerCase());
                     }
                  }

                  return fList4;
               }
            }

            return null;
         }

         return null;
      case 5:
         if (args[0].equals("board")) {
            switch((s = args[2]).hashCode()) {
            case -505017823:
               if (s.equals("hologram")) {
                  if (args[3].equals("set")) {
                     return this.getPlayerCoordinates(args[4], (Player)sender);
                  }

                  return null;
               }
               break;
            case 1950350556:
               if (s.equals("joinbutton")) {
                  switch((s = args[3]).hashCode()) {
                  case -934610812:
                     if (s.equals("remove")) {
                        return this.getButtonsNames(args[4], args[1]);
                     }
                     break;
                  case -905786129:
                     if (s.equals("setbet")) {
                        return this.getButtonsNames(args[4], args[1]);
                     }
                     break;
                  case 1282377101:
                     if (s.equals("removebet")) {
                        return this.getButtonsNames(args[4], args[1]);
                     }
                  }

                  return null;
               }
            }

            return null;
         }
      case 6:
         if (args[0].equals("board") && args[2].equals("joinbutton")) {
            if (args[3].equals("setbet")) {
               List fList5 = new ArrayList();
               Material[] var13;
               int var12 = (var13 = Material.values()).length;

               for(int var11 = 0; var11 < var12; ++var11) {
                  Material m = var13[var11];
                  if (m.name().toLowerCase().startsWith(args[5].toLowerCase())) {
                     fList5.add(m.name());
                  }
               }

               return fList5;
            } else {
               if (args[3].equals("add")) {
                  return this.getTargetingCoordinates(args[5], (Player)sender);
               }

               return null;
            }
         }
      default:
         return null;
      }
   }

   private List getBoardsNames(String arg) {
      List list = new ArrayList();
      Iterator var4 = this.plugin.getMinecraftChessList().iterator();

      while(var4.hasNext()) {
         MinecraftChessGame mc = (MinecraftChessGame)var4.next();
         if (mc.getName().toLowerCase().startsWith(arg.toLowerCase())) {
            list.add(mc.getName());
         }
      }

      return list;
   }

   private List getButtonsNames(String arg, String boardName) {
      List list = new ArrayList();
      Iterator var5 = this.plugin.getMinecraftChessFromName(boardName).getJoinButtons().iterator();

      while(var5.hasNext()) {
         JoinButton jb = (JoinButton)var5.next();
         if (jb.getName().toLowerCase().startsWith(arg.toLowerCase())) {
            list.add(jb.getName());
         }
      }

      return list;
   }

   private List getTargetingCoordinates(String arg, Player p) {
      List list = new ArrayList();

      String str;
      try {
         str = p.getTargetBlockExact(50).getX() + " " + p.getTargetBlockExact(50).getY() + " " + p.getTargetBlockExact(50).getZ();
      } catch (NullPointerException var6) {
         return list;
      }

      if (str.startsWith(arg)) {
         list.add(str);
      }

      return list;
   }

   private List getPlayerCoordinates(String arg, Player p) {
      List list = new ArrayList();
      String str = (double)Math.round(p.getLocation().getX() * 100.0) / 100.0 + " " + (double)Math.round(p.getLocation().getY() * 100.0) / 100.0 + " " + (double)Math.round(p.getLocation().getZ() * 100.0) / 100.0;
      if (str.startsWith(arg)) {
         list.add(str);
      }

      return list;
   }
}
