package com.gmail.laurynas.pazdrazdis.minecraftpart.general;

public class Timer {
   private long whiteTimeBank;
   private long blackTimeBank;
   private long whiteStartTime = 0L;
   private long blackStartTime = 0L;

   public Timer(int time_limit) {
      this.blackTimeBank = (long)(time_limit * 60 * 1000);
      this.whiteTimeBank = (long)(time_limit * 60 * 1000);
   }

   public void startWhite() {
      this.whiteStartTime = System.currentTimeMillis();
   }

   public void startBlack() {
      this.blackStartTime = System.currentTimeMillis();
   }

   public void finishWhite() {
      if (this.whiteStartTime != 0L) {
         this.whiteTimeBank -= System.currentTimeMillis() - this.whiteStartTime;
         this.whiteStartTime = 0L;
         if (this.whiteTimeBank <= 0L) {
            this.whiteTimeBank = 0L;
         }

      }
   }

   public void finishBlack() {
      if (this.blackStartTime != 0L) {
         this.blackTimeBank -= System.currentTimeMillis() - this.blackStartTime;
         this.blackStartTime = 0L;
         if (this.blackTimeBank <= 0L) {
            this.blackTimeBank = 0L;
         }

      }
   }

   public long getWhiteTimeBank() {
      if (this.whiteStartTime == 0L) {
         return this.whiteTimeBank;
      } else {
         this.whiteTimeBank -= System.currentTimeMillis() - this.whiteStartTime;
         this.whiteStartTime = System.currentTimeMillis();
         if (this.whiteTimeBank <= 0L) {
            this.whiteTimeBank = 0L;
         }

         return this.whiteTimeBank;
      }
   }

   public long getBlackTimeBank() {
      if (this.blackStartTime == 0L) {
         return this.blackTimeBank;
      } else {
         this.blackTimeBank -= System.currentTimeMillis() - this.blackStartTime;
         this.blackStartTime = System.currentTimeMillis();
         if (this.blackTimeBank <= 0L) {
            this.blackTimeBank = 0L;
         }

         return this.blackTimeBank;
      }
   }
}
