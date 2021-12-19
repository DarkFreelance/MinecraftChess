package com.gmail.laurynas.pazdrazdis.minecraftpart.general;

import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindTextException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import java.util.Iterator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Hologram {
   private static String hologramtext1;
   private static String hologramtext2;
   private static String hologramtext3;
   private static String hologramtext4;
   private static String hologramtext5;
   private double x;
   private double y;
   private double z;
   private ArmorStand as1;
   private ArmorStand as2;
   private ArmorStand as3;
   private ArmorStand as4;
   private World world;
   private String whiteName = "";
   private String blackName = "";
   private String bet = null;
   private String time_limit = null;

   public Hologram(double x, double y, double z, World world) {
      this.world = world;
      this.x = x;
      this.y = y;
      this.z = z;
      this.time_limit = null;
      (new Location(this.world, this.x, this.y, this.z)).getChunk().load();
      Iterator var9 = this.world.getEntities().iterator();

      while(var9.hasNext()) {
         Entity entity = (Entity)var9.next();
         if (entity.getLocation().getX() == this.x && entity.getLocation().getZ() == this.z && entity.getLocation().getY() - this.y > -1.0 && entity.getLocation().getY() - this.y < 1.0) {
            entity.remove();
         }
      }

      this.createArmorStands();
   }

   public void removeTimeLimit() {
      this.time_limit = null;
      this.refresh();
   }

   public void destroy() {
      this.as1.remove();
      this.as2.remove();
      this.as3.remove();
      this.as4.remove();
   }

   public static void readTextsFromConfig(MinecraftChessMain plugin) throws CannotFindTextException {
      try {
         hologramtext1 = plugin.getConfig().getString("hologramtext1");
      } catch (Exception var6) {
         throw new CannotFindTextException("hologramtext1");
      }

      try {
         hologramtext2 = plugin.getConfig().getString("hologramtext2");
      } catch (Exception var5) {
         throw new CannotFindTextException("hologramtext2");
      }

      try {
         hologramtext3 = plugin.getConfig().getString("hologramtext3");
      } catch (Exception var4) {
         throw new CannotFindTextException("hologramtext3");
      }

      try {
         hologramtext4 = plugin.getConfig().getString("hologramtext4");
      } catch (Exception var3) {
         throw new CannotFindTextException("hologramtext4");
      }

      try {
         hologramtext5 = plugin.getConfig().getString("hologramtext5");
      } catch (Exception var2) {
         throw new CannotFindTextException("hologramtext5");
      }
   }

   public void createArmorStands() {
      this.as1 = (ArmorStand)this.world.spawnEntity(new Location(this.world, this.x, this.y + 0.30000001192092896, this.z), EntityType.ARMOR_STAND);
      this.as1.setVisible(false);
      this.as1.setGravity(false);
      this.as1.setCustomNameVisible(true);
      this.as1.getLocation().getChunk().setForceLoaded(true);
      this.as2 = (ArmorStand)this.world.spawnEntity(new Location(this.world, this.x, this.y, this.z), EntityType.ARMOR_STAND);
      this.as2.setVisible(false);
      this.as2.setGravity(false);
      this.as2.setCustomNameVisible(true);
      this.as2.getLocation().getChunk().setForceLoaded(true);
      this.as3 = (ArmorStand)this.world.spawnEntity(new Location(this.world, this.x, this.y - 0.30000001192092896, this.z), EntityType.ARMOR_STAND);
      this.as3.setVisible(false);
      this.as3.setGravity(false);
      this.as3.setCustomNameVisible(true);
      this.as3.getLocation().getChunk().setForceLoaded(true);
      this.as4 = (ArmorStand)this.world.spawnEntity(new Location(this.world, this.x, this.y - 0.6000000238418579, this.z), EntityType.ARMOR_STAND);
      this.as4.setVisible(false);
      this.as4.setGravity(false);
      this.as4.setCustomNameVisible(false);
      this.as4.getLocation().getChunk().setForceLoaded(true);
      this.refresh();
   }

   public void clear() {
      this.whiteName = "";
      this.blackName = "";
      this.bet = null;
      this.as4.setCustomNameVisible(false);
      this.refresh();
   }

   private void refresh() {
      this.as1.setCustomName(Utils.chat(hologramtext1 + " " + this.whiteName));
      this.as2.setCustomName(Utils.chat(hologramtext2 + " " + this.blackName));
      if (this.time_limit != null) {
         this.as3.setCustomName(Utils.chat(hologramtext4 + " " + this.time_limit + " " + hologramtext5));
      } else {
         this.as3.setCustomName(Utils.chat(hologramtext4 + " - "));
      }

      if (this.bet != null) {
         this.as4.setCustomName(Utils.chat(hologramtext3 + " " + this.bet));
      }

   }

   public void setTimeLimit(long time_limit) {
      this.time_limit = String.valueOf(time_limit);
      this.refresh();
   }

   public void setWhiteName(String name) {
      this.whiteName = name;
      this.refresh();
   }

   public void setBlackName(String name) {
      this.blackName = name;
      this.refresh();
   }

   public void setBet(String bet) {
      this.bet = bet;
      this.as4.setCustomNameVisible(true);
      this.refresh();
   }

   public Double getX() {
      return this.x;
   }

   public Double getY() {
      return this.y;
   }

   public Double getZ() {
      return this.z;
   }

   public World getWorld() {
      return this.world;
   }
}
