package com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions;

public class ItemFrameSpawnException extends Exception {
   private static final long serialVersionUID = 1L;
   private int x;
   private int y;
   private int z;

   public ItemFrameSpawnException(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
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
