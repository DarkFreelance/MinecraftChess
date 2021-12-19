package com.gmail.laurynas.pazdrazdis.minecraftpart.listeners;

import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {
   private MinecraftChessMain plugin;

   public Listeners(MinecraftChessMain main) {
      this.plugin = main;
      Bukkit.getPluginManager().registerEvents(this, main);
   }

   @EventHandler
   public void onPlayerClicks(PlayerInteractEvent event) {
      if (event.getHand() == EquipmentSlot.HAND) {
         if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (this.plugin.checkButtons(event.getClickedBlock().getWorld().getName(), event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ(), event.getPlayer())) {
               return;
            }
         } else if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) && this.plugin.isPlayerInList(event.getPlayer())) {
            try {
               this.plugin.doAction(event.getPlayer(), event.getPlayer().getTargetBlockExact(50).getX(), event.getPlayer().getTargetBlockExact(50).getY(), event.getPlayer().getTargetBlockExact(50).getZ());
            } catch (NullPointerException var3) {
               event.getPlayer().sendMessage(Utils.chat("&cBoard is too far"));
            }
         }
      }

   }

   @EventHandler
   public void onBlockPlace(BlockPlaceEvent event) {
      if (this.plugin.isPlayerInList(event.getPlayer()) && !event.isCancelled()) {
         event.setCancelled(true);
      }

   }

   @EventHandler
   public void onBlockBreak(BlockBreakEvent event) {
      if (this.plugin.isPlayerInList(event.getPlayer()) && !event.isCancelled()) {
         event.setCancelled(true);
      }

   }

   @EventHandler
   public void onItemFrameClick(PlayerInteractEntityEvent e) {
      if (e.getRightClicked().getCustomName() != null && e.getRightClicked().getType() == EntityType.ITEM_FRAME && e.getRightClicked().getCustomName().equals("chessplugin9b14") && !e.isCancelled()) {
         e.setCancelled(true);
      }

   }

   @EventHandler
   public void itemFrameRemoval(EntityDamageByEntityEvent event) {
      if (event.getEntity().getCustomName() != null && event.getEntityType() == EntityType.ITEM_FRAME && event.getEntity().getCustomName().equals("chessplugin9b14")) {
         if (event.getDamager() instanceof Player) {
            if (this.plugin.isPlayerInList((Player)event.getDamager())) {
               this.plugin.doAction((Player)event.getDamager(), event.getEntity().getLocation().getBlockX(), event.getEntity().getLocation().getBlockY() - 1, event.getEntity().getLocation().getBlockZ());
               if (!event.isCancelled()) {
                  event.setCancelled(true);
               }
            } else if (!event.isCancelled()) {
               event.setCancelled(true);
            }
         } else if (!event.isCancelled()) {
            event.setCancelled(true);
         }
      }

   }

   @EventHandler
   public void onItemFrameBreak(HangingBreakByEntityEvent e) {
      if (e.getEntity().getCustomName() != null && e.getEntity().getType() == EntityType.ITEM_FRAME && e.getEntity().getCustomName().equals("chessplugin9b14") && !e.isCancelled()) {
         e.setCancelled(true);
      }

   }

   @EventHandler
   public void onPlayerQuit(PlayerQuitEvent event) {
      if (this.plugin.getPlayerColor(event.getPlayer()) == Color.WHITE) {
         if (!this.plugin.getPlayerBoard(event.getPlayer()).isCancelable()) {
            this.plugin.getPlayerBoard(event.getPlayer()).endGame(Color.BLACK);
         } else {
            if (this.plugin.getPlayerBoard(event.getPlayer()).getItemType() != null) {
               event.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(this.plugin.getPlayerBoard(event.getPlayer()).getItemType(), this.plugin.getPlayerBoard(event.getPlayer()).getBet())});
            }

            this.plugin.removePlayerFromList(event.getPlayer());
            this.plugin.getPlayerBoard(event.getPlayer()).reset();
         }
      } else if (this.plugin.getPlayerColor(event.getPlayer()) == Color.BLACK) {
         this.plugin.getPlayerBoard(event.getPlayer()).endGame(Color.WHITE);
      }

   }

   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent event) {
      String uuid = event.getPlayer().getUniqueId().toString();
      if (!this.plugin.getUsersConfig().isString("Player." + uuid + ".Name")) {
         this.plugin.getUsersConfig().set("Player." + uuid + ".Name", event.getPlayer().getName().toString());
         this.plugin.getUsersConfig().set("Player." + uuid + ".Rating", 1000);
         this.plugin.getUsersConfig().set("Player." + uuid + ".Games", 0);
         this.plugin.saveUsersConfig();
      }
   }
}
