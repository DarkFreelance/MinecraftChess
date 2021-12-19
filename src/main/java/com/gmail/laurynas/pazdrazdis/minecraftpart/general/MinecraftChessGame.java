package com.gmail.laurynas.pazdrazdis.minecraftpart.general;

import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.GameFeedback;
import com.gmail.laurynas.pazdrazdis.chess.game.ChessGame;
import com.gmail.laurynas.pazdrazdis.minecraftpart.buttons.Button;
import com.gmail.laurynas.pazdrazdis.minecraftpart.buttons.JoinButton;
import com.gmail.laurynas.pazdrazdis.minecraftpart.enums.ChessPlayerAction;
import com.gmail.laurynas.pazdrazdis.minecraftpart.enums.PlayerJoinFeedback;
import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindTextException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class MinecraftChessGame {
   public static final boolean GOOD_ACTION = true;
   public static final boolean BAD_ACTION = false;
   public static final int NOT_SET = -1;
   private static String gametext4;
   private static String gametext6;
   private static String gametext7;
   private static String gametext5;
   private static String timertext1;
   private static String timertext2;
   private static String timertext3;
   private static String gametext1;
   private static String gametext2;
   private static String gametext3;
   private static String gametext8;
   private static String gametext9;
   private static String surrendertext1;
   private ChessGame chessGame = null;
   private Output output = null;
   private String name;
   private int bet = -1;
   private boolean levitationEffect = false;
   private Material itemType = null;
   private ArrayList joinButtons = new ArrayList();
   private Button cancelButton = null;
   private Hologram hologram = null;
   private Player white = null;
   private Player black = null;
   private int x;
   private int y;
   private int z;
   private Location whiteIn = null;
   private Location blackIn = null;
   private Location out = null;
   private World world = null;
   private int time_limit = 0;
   private Timer timer = null;
   private int scheduler;
   private Scoreboard board = null;
   private Objective objective = null;
   private int whiteSeconds = -1;
   private int whiteMinutes = -1;
   private int blackSeconds = -1;
   private int blackMinutes = -1;
   private boolean isWhiteSurrendering = false;
   private boolean isBlackSurrendering = false;
   private MinecraftChessMain plugin;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$GameFeedback;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$ChessPlayerAction;

   public MinecraftChessGame(MinecraftChessMain plugin, int x, int y, int z, World w, String name) {
      this.plugin = plugin;
      this.world = w;
      this.x = x;
      this.y = y;
      this.z = z;
      this.name = name;
      this.output = new Output(x, y, z, w, this.plugin, this);
      this.board = Bukkit.getScoreboardManager().getNewScoreboard();
   }

   public void destroy() {
      if (this.hologram != null) {
         this.hologram.destroy();
      }

      if (this.output != null) {
         this.output.destroyItemFramesAndBarriers();
      }

   }

   public static void readTextsFromConfig(MinecraftChessMain plugin) throws CannotFindTextException {
      try {
         gametext4 = plugin.getConfig().getString("gametext4");
      } catch (Exception var14) {
         throw new CannotFindTextException("gametext4");
      }

      try {
         gametext6 = plugin.getConfig().getString("gametext6");
      } catch (Exception var13) {
         throw new CannotFindTextException("gametext6");
      }

      try {
         gametext5 = plugin.getConfig().getString("gametext5");
      } catch (Exception var12) {
         throw new CannotFindTextException("gametext5");
      }

      try {
         gametext7 = plugin.getConfig().getString("gametext7");
      } catch (Exception var11) {
         throw new CannotFindTextException("gametext7");
      }

      try {
         timertext1 = plugin.getConfig().getString("timertext1");
      } catch (Exception var10) {
         throw new CannotFindTextException("timertext1");
      }

      try {
         timertext2 = plugin.getConfig().getString("timertext2");
      } catch (Exception var9) {
         throw new CannotFindTextException("timertext2");
      }

      try {
         timertext3 = plugin.getConfig().getString("timertext3");
      } catch (Exception var8) {
         throw new CannotFindTextException("timertext3");
      }

      try {
         gametext1 = plugin.getConfig().getString("gametext1");
      } catch (Exception var7) {
         throw new CannotFindTextException("gametext1");
      }

      try {
         gametext3 = plugin.getConfig().getString("gametext3");
      } catch (Exception var6) {
         throw new CannotFindTextException("gametext3");
      }

      try {
         gametext2 = plugin.getConfig().getString("gametext2");
      } catch (Exception var5) {
         throw new CannotFindTextException("gametext2");
      }

      try {
         gametext8 = plugin.getConfig().getString("gametext8");
      } catch (Exception var4) {
         throw new CannotFindTextException("gametext8");
      }

      try {
         gametext9 = plugin.getConfig().getString("gametext9");
      } catch (Exception var3) {
         throw new CannotFindTextException("gametext9");
      }

      try {
         surrendertext1 = plugin.getConfig().getString("surrendertext1");
      } catch (Exception var2) {
         throw new CannotFindTextException("surrendertext1");
      }
   }

   public void reset() {
      this.isWhiteSurrendering = false;
      this.isBlackSurrendering = false;
      this.bet = -1;
      this.itemType = null;
      this.white = null;
      this.black = null;
      this.timer = null;
      if (this.hologram != null) {
         this.hologram.clear();
      }

   }

   public boolean buttonPush(String worldname, int x, int y, int z, Player player) {
      if (this.cancelButton != null && this.cancelButton.getX() == x && this.cancelButton.getY() == y && this.cancelButton.getZ() == z && worldname.equalsIgnoreCase(this.cancelButton.getWorld().getName())) {
         if (!this.isPlayable()) {
            player.sendMessage(Utils.chat("&cYou have to set the cancel button, the black starting location, the white starting location and game end location to make this board playable"));
            return true;
         }

         this.cancelButton.onPowered(player, this);
      }

      for(int i = 0; i < this.joinButtons.size(); ++i) {
         if (((JoinButton)this.joinButtons.get(i)).getX() == x && ((JoinButton)this.joinButtons.get(i)).getY() == y && ((JoinButton)this.joinButtons.get(i)).getZ() == z && worldname.equalsIgnoreCase(this.cancelButton.getWorld().getName())) {
            if (!this.isPlayable()) {
               player.sendMessage(Utils.chat("&cYou have to set the cancel button, the black starting location, the white starting location and game end location to make this board playable"));
               return true;
            }

            ((JoinButton)this.joinButtons.get(i)).onPowered(player, this);
            return true;
         }
      }

      return false;
   }

   public void refreshOutput() {
      try {
         this.output.refreshBoard(this.chessGame.getBoard());
      } catch (CloneNotSupportedException var2) {
         var2.printStackTrace();
      }

   }

   public void rotateItemFramesForBlack() {
      if (this.black != null) {
         this.output.rotateItemFramesForPlayer(this.black, 6);
         this.plugin.addPlayerToPacketListeningList(this.black, this.output.getItemFramesIdList());
      }

   }

   public boolean action(Player player, int x, int y, int z) {
      Color color = this.getColorFromPlayer(player);
      if (color == null) {
         return false;
      } else if (!this.isBothPlayersJoined()) {
         return true;
      } else {
         switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$ChessPlayerAction()[this.getAction(x, y, z, color).ordinal()]) {
         case 1:
            if (this.isSurrendering(color)) {
               this.endGame(this.getOpositeColor(color));
            } else {
               player.sendMessage(Utils.chat(surrendertext1));
               this.setIsSurrendering(color, true);
            }
            break;
         case 2:
            this.setIsSurrendering(color, false);
            switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$GameFeedback()[this.chessGame.doBoardAction(this.convertX(x), this.convertY(z), color).ordinal()]) {
            case 1:
               this.endGame(color);
               return true;
            case 2:
               this.white.playSound(this.white.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
               this.black.playSound(this.black.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
               if (this.chessGame.getBoard().isCheck(this.chessGame.getWhoseTurn())) {
                  this.white.sendMessage(Utils.chat(gametext3));
                  this.black.sendMessage(Utils.chat(gametext3));
               }

               if (this.timer != null) {
                  this.changeTimerTurn(this.getOpositeColor(color));
               }

               return true;
            case 3:
               player.sendMessage(Utils.chat(gametext9));
               return true;
            case 4:
               player.sendMessage(Utils.chat(gametext8));
               return true;
            case 5:
            case 6:
            case 7:
            case 9:
            default:
               return true;
            case 8:
               player.sendMessage(Utils.chat(gametext1));
               return true;
            case 10:
               player.sendMessage(Utils.chat(gametext2));
               return true;
            }
         case 3:
            this.setIsSurrendering(color, false);
            switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$GameFeedback()[this.chessGame.doPromotion(this.whatPromotion(x, color), color).ordinal()]) {
            case 1:
               this.endGame(color);
               return true;
            case 2:
               this.white.playSound(this.white.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
               this.black.playSound(this.black.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
               if (this.chessGame.getBoard().isCheck(this.chessGame.getWhoseTurn())) {
                  this.white.sendMessage(Utils.chat(gametext3));
                  this.black.sendMessage(Utils.chat(gametext3));
               }

               if (this.timer != null) {
                  this.changeTimerTurn(this.getOpositeColor(color));
               }

               return true;
            default:
               return true;
            }
         default:
            this.setIsSurrendering(color, false);
         }

         return true;
      }
   }

   public void endGame(Color whoWon) {
      this.plugin.removePlayerFromPacketListeningList(this.black);
      this.output.rotateItemFramesForPlayer(this.black, 0);
      Player player;
      Iterator var3;
      if (whoWon == Color.BLACK) {
         this.black.sendTitle(Utils.chat(gametext6), (String)null, -1, -1, -1);
         this.white.sendTitle(Utils.chat(gametext7), (String)null, -1, -1, -1);
         if (this.itemType != null) {
            this.black.getInventory().addItem(new ItemStack[]{new ItemStack(this.itemType, this.getBet() * 2)});
         }

         var3 = Bukkit.getOnlinePlayers().iterator();

         while(var3.hasNext()) {
            player = (Player)var3.next();
            player.sendMessage(Utils.chat(gametext4 + " " + this.black.getName() + " " + gametext5 + " " + this.white.getName()));
         }
      } else {
         this.black.playSound(this.black.getLocation().add(0.0, 0.1, 0.0), Sound.ENTITY_DONKEY_ANGRY, 1.0F, 1.0F);
         this.white.playSound(this.white.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 1.0F, 1.0F);
         this.white.sendTitle(Utils.chat(gametext6), (String)null, -1, -1, -1);
         this.black.sendTitle(Utils.chat(gametext7), (String)null, -1, -1, -1);
         if (this.itemType != null) {
            this.white.getInventory().addItem(new ItemStack[]{new ItemStack(this.itemType, this.getBet() * 2)});
         }

         var3 = Bukkit.getOnlinePlayers().iterator();

         while(var3.hasNext()) {
            player = (Player)var3.next();
            player.sendMessage(Utils.chat(gametext4 + " " + this.white.getName() + " " + gametext5 + " " + this.black.getName()));
         }
      }

      this.updateRatings(whoWon);
      if (this.timer != null) {
         Bukkit.getScheduler().cancelTask(this.scheduler);
         this.white.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
         this.black.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
         this.objective.unregister();
      }

      this.plugin.removePlayerFromList(this.white);
      this.plugin.removePlayerFromList(this.black);
      this.white.teleport(this.out);
      this.black.teleport(this.out);
      if (whoWon == Color.BLACK) {
         this.white.playSound(this.white.getLocation().add(0.0, 0.0, 0.0), Sound.ENTITY_DONKEY_ANGRY, 1.0F, 1.0F);
         this.black.playSound(this.black.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 1.0F, 1.0F);
      } else {
         this.black.playSound(this.black.getLocation().add(0.0, 0.0, 0.0), Sound.ENTITY_DONKEY_ANGRY, 1.0F, 1.0F);
         this.white.playSound(this.white.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 1.0F, 1.0F);
      }

      this.white.removePotionEffect(PotionEffectType.LEVITATION);
      this.black.removePotionEffect(PotionEffectType.LEVITATION);
      this.reset();
   }

   private void updateRatings(Color whoWon) {
      double blackRating = (double)this.plugin.getUsersConfig().getInt("Player." + this.black.getUniqueId().toString() + ".Rating");
      double whiteRating = (double)this.plugin.getUsersConfig().getInt("Player." + this.white.getUniqueId().toString() + ".Rating");
      double blackExpected = 1.0 / (1.0 + Math.pow(10.0, (whiteRating - blackRating) / 400.0));
      double whiteExpected = 1.0 / (1.0 + Math.pow(10.0, (blackRating - whiteRating) / 400.0));
      if (whoWon == Color.WHITE) {
         whiteRating += 32.0 * (1.0 - whiteExpected);
         blackRating += 32.0 * (0.0 - blackExpected);
      } else {
         blackRating += 32.0 * (1.0 - blackExpected);
         whiteRating += 32.0 * (0.0 - whiteExpected);
      }

      this.plugin.getUsersConfig().set("Player." + this.white.getUniqueId().toString() + ".Games", this.plugin.getUsersConfig().getInt("Player." + this.white.getUniqueId().toString() + ".Games") + 1);
      this.plugin.getUsersConfig().set("Player." + this.black.getUniqueId().toString() + ".Games", this.plugin.getUsersConfig().getInt("Player." + this.black.getUniqueId().toString() + ".Games") + 1);
      this.plugin.getUsersConfig().set("Player." + this.black.getUniqueId().toString() + ".Rating", Math.round(blackRating));
      this.plugin.getUsersConfig().set("Player." + this.white.getUniqueId().toString() + ".Rating", Math.round(whiteRating));
      this.plugin.saveUsersConfig();
   }

   public void startGame() {
      this.chessGame = new ChessGame();

      try {
         this.output.refreshBoard(this.chessGame.getBoard());
      } catch (CloneNotSupportedException var2) {
         var2.printStackTrace();
      }

      this.white.teleport(this.whiteIn);
      this.black.teleport(this.blackIn);
      if (this.levitationEffect) {
         this.white.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 9999999, 0, false, false, false));
         this.black.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 9999999, 0, false, false, false));
      }

      if (this.time_limit > 0) {
         this.setTimer();
      }

   }

   public void updateTimerScoreBoards() {
      try {
         long whiteTime = this.timer.getWhiteTimeBank();
         long blackTime = this.timer.getBlackTimeBank();
         if (whiteTime / 60000L != (long)this.whiteMinutes || whiteTime % 60000L / 1000L != (long)this.whiteSeconds || blackTime / 60000L != (long)this.blackMinutes || blackTime % 60000L / 1000L != (long)this.blackSeconds) {
            if (this.whiteSeconds != -1) {
               if (this.whiteSeconds < 10) {
                  if (this.whiteMinutes < 10) {
                     if (this.blackSeconds < 10) {
                        if (this.blackMinutes < 10) {
                           this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds);
                           this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds);
                        } else {
                           this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds);
                           this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds);
                        }
                     } else if (this.blackMinutes < 10) {
                        this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds);
                        this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds);
                     } else {
                        this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds);
                        this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds);
                     }
                  } else if (this.blackSeconds < 10) {
                     if (this.blackMinutes < 10) {
                        this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds);
                        this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds);
                     } else {
                        this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds);
                        this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds);
                     }
                  } else if (this.blackMinutes < 10) {
                     this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds);
                     this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds);
                  } else {
                     this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds);
                     this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds);
                  }
               } else if (this.whiteMinutes < 10) {
                  if (this.blackSeconds < 10) {
                     if (this.blackMinutes < 10) {
                        this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds);
                        this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds);
                     } else {
                        this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds);
                        this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds);
                     }
                  } else if (this.blackMinutes < 10) {
                     this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds);
                     this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds);
                  } else {
                     this.board.resetScores(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds);
                     this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds);
                  }
               } else if (this.blackSeconds < 10) {
                  if (this.blackMinutes < 10) {
                     this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds);
                     this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds);
                  } else {
                     this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds);
                     this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds);
                  }
               } else if (this.blackMinutes < 10) {
                  this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds);
                  this.board.resetScores(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds);
               } else {
                  this.board.resetScores(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds);
                  this.board.resetScores(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds);
               }
            }

            this.whiteSeconds = (int)(whiteTime % 60000L / 1000L);
            this.whiteMinutes = (int)(whiteTime / 60000L);
            this.blackSeconds = (int)(blackTime % 60000L / 1000L);
            this.blackMinutes = (int)(blackTime / 60000L);
            if (this.whiteSeconds < 10) {
               if (this.whiteMinutes < 10) {
                  if (this.blackSeconds < 10) {
                     if (this.blackMinutes < 10) {
                        this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                        this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
                     } else {
                        this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                        this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
                     }
                  } else if (this.blackMinutes < 10) {
                     this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                     this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
                  } else {
                     this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                     this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
                  }
               } else if (this.blackSeconds < 10) {
                  if (this.blackMinutes < 10) {
                     this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                     this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
                  } else {
                     this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                     this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
                  }
               } else if (this.blackMinutes < 10) {
                  this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                  this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
               } else {
                  this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":0" + this.whiteSeconds).setScore(2);
                  this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
               }
            } else if (this.whiteMinutes < 10) {
               if (this.blackSeconds < 10) {
                  if (this.blackMinutes < 10) {
                     this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
                     this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
                  } else {
                     this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
                     this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
                  }
               } else if (this.blackMinutes < 10) {
                  this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
                  this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
               } else {
                  this.objective.getScore(timertext2 + ": 0" + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
                  this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
               }
            } else if (this.blackSeconds < 10) {
               if (this.blackMinutes < 10) {
                  this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
                  this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
               } else {
                  this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
                  this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":0" + this.blackSeconds).setScore(1);
               }
            } else if (this.blackMinutes < 10) {
               this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
               this.objective.getScore(timertext3 + ": 0" + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
            } else {
               this.objective.getScore(timertext2 + ": " + this.whiteMinutes + ":" + this.whiteSeconds).setScore(2);
               this.objective.getScore(timertext3 + ": " + this.blackMinutes + ":" + this.blackSeconds).setScore(1);
            }
         }

         if (whiteTime == 0L) {
            this.endGame(Color.BLACK);
         } else if (blackTime == 0L) {
            this.endGame(Color.WHITE);
         }
      } catch (IllegalStateException var5) {
      }

   }

   public void changeTimerTurn(Color whoseTurn) {
      if (whoseTurn == Color.WHITE) {
         this.timer.finishBlack();
         this.timer.startWhite();
      } else {
         this.timer.finishWhite();
         this.timer.startBlack();
      }

   }

   public FigureType whatPromotion(int x, Color whoseTurn) {
      if (whoseTurn == Color.WHITE) {
         if (x == this.x - 1) {
            return FigureType.QUEEN;
         } else if (x == this.x) {
            return FigureType.ROOK;
         } else {
            return x == this.x + 1 ? FigureType.BISHOP : FigureType.KNIGHT;
         }
      } else if (x == this.x - 1) {
         return FigureType.KNIGHT;
      } else if (x == this.x) {
         return FigureType.BISHOP;
      } else {
         return x == this.x + 1 ? FigureType.ROOK : FigureType.QUEEN;
      }
   }

   public int convertX(int x) {
      return x + 3 - this.x;
   }

   public int convertY(int y) {
      return this.z - y + 3;
   }

   public ChessPlayerAction getAction(int x, int y, int z, Color whoseTurn) {
      if (y == this.y - 1) {
         if (x > this.x - 4 && x < this.x + 5 && z < this.z + 4 && z > this.z - 5) {
            return ChessPlayerAction.BOARD_CLICK;
         }

         if (whoseTurn == Color.WHITE) {
            if (z == this.z + 6 && x > this.x - 2 && x < this.x + 3) {
               return ChessPlayerAction.CHOICE_OF_PROMOTION;
            }

            if (x == this.x + 5 && z == this.z + 6) {
               return ChessPlayerAction.TRIES_TO_SURRENDER;
            }
         } else {
            if (z == this.z - 7 && x > this.x - 2 && x < this.x + 3) {
               return ChessPlayerAction.CHOICE_OF_PROMOTION;
            }

            if (x == this.x - 4 && z == this.z - 7) {
               return ChessPlayerAction.TRIES_TO_SURRENDER;
            }
         }
      }

      return ChessPlayerAction.INVALID_CLICK;
   }

   public boolean isPlayerOnBoard(Player player) {
      return this.white == player || this.black == player;
   }

   public boolean isCancelable() {
      return this.black == null;
   }

   public boolean isPlayable() {
      if (this.whiteIn == null) {
         return false;
      } else if (this.blackIn == null) {
         return false;
      } else if (this.out == null) {
         return false;
      } else {
         return this.cancelButton != null;
      }
   }

   public void removeJoinButton(String name) {
      for(int i = 0; i < this.joinButtons.size(); ++i) {
         if (((JoinButton)this.joinButtons.get(i)).getName().equals(name)) {
            this.joinButtons.remove(i);
         }
      }

   }

   public void removeTimeLimit() {
      this.time_limit = 0;
      if (this.hologram != null) {
         this.hologram.removeTimeLimit();
      }

   }

   public void removeText() {
      if (this.hologram != null) {
         this.hologram.destroy();
         this.hologram = null;
      }

   }

   public PlayerJoinFeedback addPlayer(Player player) {
      if (this.white == null) {
         this.white = player;
         return PlayerJoinFeedback.FIRST_PLAYER_ADDED;
      } else if (this.black == null) {
         this.black = player;
         this.startGame();
         return PlayerJoinFeedback.SECOND_PLAYER_ADDED;
      } else {
         return PlayerJoinFeedback.BOARD_IS_FULL;
      }
   }

   public void addJoinButton(JoinButton button) {
      this.joinButtons.add(button);
   }

   public void setCancelButton(Button button) {
      this.cancelButton = button;
   }

   public void setHologram(Hologram hologram) {
      if (this.hologram != null) {
         this.hologram.destroy();
      }

      this.hologram = hologram;
      if (this.time_limit != 0) {
         this.hologram.setTimeLimit((long)this.time_limit);
      }

      if (this.white != null) {
         this.hologram.setWhiteName(this.white.getName());
      }

      if (this.black != null) {
         this.hologram.setBlackName(this.black.getName());
      }

      if (this.itemType != null) {
         this.hologram.setBet(this.getBet() + " " + this.getItemType().name());
      }

   }

   public void setTimeLimit(int time_limit) {
      this.time_limit = time_limit;
      if (this.hologram != null) {
         this.hologram.setTimeLimit((long)time_limit);
      }

   }

   public void setLevitationEffect(boolean value) {
      this.levitationEffect = value;
   }

   public void setWhiteIn(Location l) {
      this.whiteIn = l;
      this.whiteIn.setYaw(180.0F);
      this.whiteIn.setPitch(90.0F);
   }

   public void setBlackIn(Location l) {
      this.blackIn = l;
      this.blackIn.setYaw(360.0F);
      this.blackIn.setPitch(90.0F);
   }

   public void setAllOut(Location l) {
      this.out = l;
   }

   public void setBet(int bet) {
      this.bet = bet;
   }

   public void setItemType(Material itemType) {
      this.itemType = itemType;
   }

   public void setTimer() {
      this.objective = this.board.registerNewObjective("timer", "dummy", ChatColor.GREEN + timertext1);
      this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      this.white.setScoreboard(this.board);
      this.black.setScoreboard(this.board);
      this.timer = new Timer(this.time_limit);
      this.timer.startWhite();
      this.scheduler = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
         public void run() {
            MinecraftChessGame.this.updateTimerScoreBoards();
         }
      }, 0L, 5L);
   }

   public World getWorld() {
      return this.world;
   }

   public Output getOutput() {
      return this.output;
   }

   public String getName() {
      return this.name;
   }

   private boolean isBothPlayersJoined() {
      return this.black != null && this.white != null;
   }

   public ArrayList getJoinButtons() {
      return this.joinButtons;
   }

   public JoinButton getJoinButtonFromName(String name) {
      for(int i = 0; i < this.joinButtons.size(); ++i) {
         if (((JoinButton)this.joinButtons.get(i)).getName().equalsIgnoreCase(name)) {
            return (JoinButton)this.joinButtons.get(i);
         }
      }

      return null;
   }

   public Color getPlayerColor(Player player) {
      if (player.equals(this.white)) {
         return Color.WHITE;
      } else {
         return player.equals(this.black) ? Color.BLACK : Color.NONE;
      }
   }

   private Color getColorFromPlayer(Player player) {
      if (player == this.white) {
         return Color.WHITE;
      } else {
         return player == this.black ? Color.BLACK : null;
      }
   }

   public Hologram getHologram() {
      return this.hologram;
   }

   public Material getItemType() {
      return this.itemType;
   }

   public int getBet() {
      return this.bet;
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

   public long getTimeLimit() {
      return (long)this.time_limit;
   }

   private boolean isSurrendering(Color color) {
      return color == Color.WHITE ? this.isWhiteSurrendering : this.isBlackSurrendering;
   }

   private void setIsSurrendering(Color color, boolean value) {
      if (color == Color.WHITE) {
         this.isWhiteSurrendering = value;
      } else {
         this.isBlackSurrendering = value;
      }

   }

   private Color getOpositeColor(Color color) {
      return color == Color.WHITE ? Color.BLACK : Color.WHITE;
   }

   public boolean getLevitationEffect() {
      return this.levitationEffect;
   }

   public Location getWhiteIn() {
      return this.whiteIn;
   }

   public Location getBlackIn() {
      return this.blackIn;
   }

   public Player getWhitePlayer() {
      return this.white;
   }

   public Player getBlackPlayer() {
      return this.black;
   }

   public Location getAllOut() {
      return this.out;
   }

   public Button getCancelButton() {
      return this.cancelButton;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$GameFeedback() {
      int[] var10000 = $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$GameFeedback;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[GameFeedback.values().length];

         try {
            var0[GameFeedback.BAD_FIGURES.ordinal()] = 9;
         } catch (NoSuchFieldError var10) {
         }

         try {
            var0[GameFeedback.BAD_FOR_KING.ordinal()] = 8;
         } catch (NoSuchFieldError var9) {
         }

         try {
            var0[GameFeedback.END_GAME.ordinal()] = 1;
         } catch (NoSuchFieldError var8) {
         }

         try {
            var0[GameFeedback.FIGURE_MARKED_AS_CLICKED.ordinal()] = 6;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[GameFeedback.ILLEGAL_MOVE.ordinal()] = 10;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[GameFeedback.INVALID_CLICK.ordinal()] = 7;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[GameFeedback.INVALID_COORDINATES.ordinal()] = 5;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[GameFeedback.MOVE_COMPLETED.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[GameFeedback.NEED_PAWN_TO_PROMOTE.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[GameFeedback.NOT_YOUR_TURN.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$GameFeedback = var0;
         return var0;
      }
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$ChessPlayerAction() {
      int[] var10000 = $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$ChessPlayerAction;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[ChessPlayerAction.values().length];

         try {
            var0[ChessPlayerAction.BOARD_CLICK.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[ChessPlayerAction.CHOICE_OF_PROMOTION.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[ChessPlayerAction.INVALID_CLICK.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[ChessPlayerAction.TRIES_TO_SURRENDER.ordinal()] = 1;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$minecraftpart$enums$ChessPlayerAction = var0;
         return var0;
      }
   }
}
