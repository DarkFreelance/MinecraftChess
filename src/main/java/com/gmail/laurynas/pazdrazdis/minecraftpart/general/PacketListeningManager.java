package com.gmail.laurynas.pazdrazdis.minecraftpart.general;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.v1_16_R3.DataWatcherRegistry;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_16_R3.DataWatcher.Item;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketListeningManager {
   private static PacketListeningManager instance = null;

   private PacketListeningManager() {
   }

   public static PacketListeningManager getPacketManager() {
      if (instance == null) {
         instance = new PacketListeningManager();
      }

      return instance;
   }

   public void removePlayer(Player player) {
      Channel channel = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel;
      channel.eventLoop().submit(() -> {
         channel.pipeline().remove(player.getName());
         return null;
      });
   }

   public void injectPlayer(Player player, ArrayList idList) {
      ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
         public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
            super.channelRead(channelHandlerContext, packet);
         }

         public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
            if (packet instanceof PacketPlayOutEntityMetadata) {
               PacketPlayOutEntityMetadata p = (PacketPlayOutEntityMetadata)packet;
               Field field = p.getClass().getDeclaredField("b");
               field.setAccessible(true);
               List b = (List)field.get(p);
               Iterator var8 = b.iterator();

               while(var8.hasNext()) {
                  Item i = (Item)var8.next();
                  if (i.a().a() == 8 && i.a().b().equals(DataWatcherRegistry.b)) {
                     i.a(6);
                     break;
                  }
               }
            }

            super.write(channelHandlerContext, packet, channelPromise);
         }
      };
      ChannelPipeline pipeline = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel.pipeline();
      pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
   }
}
