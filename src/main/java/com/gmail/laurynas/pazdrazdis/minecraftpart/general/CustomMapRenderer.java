package com.gmail.laurynas.pazdrazdis.minecraftpart.general;

import java.awt.image.BufferedImage;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class CustomMapRenderer extends MapRenderer {
   BufferedImage bi;
   boolean toRender = true;

   public CustomMapRenderer(BufferedImage bi) {
      this.bi = bi;
   }

   public void render(MapView wm, MapCanvas mc, Player p) {
      if (this.toRender) {
         mc.drawImage(0, 0, this.bi);
         this.toRender = false;
      }

   }
}
