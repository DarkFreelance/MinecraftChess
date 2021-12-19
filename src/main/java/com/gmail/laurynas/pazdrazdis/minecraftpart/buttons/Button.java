package com.gmail.laurynas.pazdrazdis.minecraftpart.buttons;

import com.gmail.laurynas.pazdrazdis.minecraftpart.general.MinecraftChessGame;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class Button {
   private int x;
   private int y;
   private int z;
   private MinecraftChessMain plugin;
   private World world;

   public abstract void onPowered(Player var1, MinecraftChessGame var2);

   public void setWorld(World world) {
      this.world = world;
   }

   public void setPlugin(MinecraftChessMain plugin) {
      this.plugin = plugin;
   }

   public void setZ(int z) {
      this.z = z;
   }

   public void setY(int y) {
      this.y = y;
   }

   public void setX(int x) {
      this.x = x;
   }

   public World getWorld() {
      return this.world;
   }

   public MinecraftChessMain getPlugin() {
      return this.plugin;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getZ() {
      return this.z;
   }
}
