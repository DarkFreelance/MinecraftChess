package com.gmail.laurynas.pazdrazdis.minecraftpart.commands;

import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class RatingTabCompleteHandler implements TabCompleter {
   private static final List firstArgumentList = Collections.unmodifiableList(Arrays.asList("top", "player"));

   public RatingTabCompleteHandler(MinecraftChessMain plugin) {
      plugin.getCommand("chessratings").setTabCompleter(this);
   }

   public List onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
      switch(args.length) {
      case 1:
         List fList = new ArrayList();
         Iterator var7 = firstArgumentList.iterator();

         while(var7.hasNext()) {
            String s = (String)var7.next();
            if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
               fList.add(s.toLowerCase());
            }
         }

         return fList;
      default:
         return null;
      }
   }
}
