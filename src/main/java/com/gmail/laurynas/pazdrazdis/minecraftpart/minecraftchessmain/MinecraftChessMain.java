package com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain;

import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.minecraftpart.buttons.CancelButton;
import com.gmail.laurynas.pazdrazdis.minecraftpart.buttons.JoinButton;
import com.gmail.laurynas.pazdrazdis.minecraftpart.commands.ConfigCommandHandler;
import com.gmail.laurynas.pazdrazdis.minecraftpart.commands.ConfigTabCompleteHandler;
import com.gmail.laurynas.pazdrazdis.minecraftpart.commands.RatingCommandHandler;
import com.gmail.laurynas.pazdrazdis.minecraftpart.commands.RatingTabCompleteHandler;
import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindTextException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindValueException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.Hologram;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.MinecraftChessGame;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.Output;
import com.gmail.laurynas.pazdrazdis.minecraftpart.general.PacketListeningManager;
import com.gmail.laurynas.pazdrazdis.minecraftpart.listeners.Listeners;
import com.gmail.laurynas.pazdrazdis.minecraftpart.utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftChessMain extends JavaPlugin {
   public static final int MAP_LIMIT = 1000000000;
   private ArrayList chessGames = new ArrayList();
   private ArrayList playersIDWhoPlaingChess = new ArrayList();
   private HashMap playersKickOffTheBoardTaskId = new HashMap();
   private FileConfiguration usersConfig;
   private File usersFile;
   private PacketListeningManager plm = PacketListeningManager.getPacketManager();

   public void onEnable() {
      this.saveDefaultConfig();
      this.usersFile = new File("plugins" + File.separator + "MinecraftChess" + File.separator + "users.yml");
      if (!this.usersFile.exists()) {
         try {
            this.usersFile.createNewFile();
            this.usersConfig = YamlConfiguration.loadConfiguration(this.usersFile);
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      } else {
         this.usersConfig = YamlConfiguration.loadConfiguration(this.usersFile);
      }

      this.loadImagesFromResources();

      try {
         this.readConfig();
      } catch (CannotFindTextException var2) {
         Bukkit.getServer().getConsoleSender().sendMessage("[MinecraftChess] Exception occurred: cannot read " + var2.getMessage() + "from config file.");
         return;
      } catch (CannotFindValueException var3) {
         Bukkit.getServer().getConsoleSender().sendMessage("[MinecraftChess] Exception occurred: cannot find " + var3.getMessage() + " value from config file.");
         return;
      }

      new Listeners(this);
      new ConfigTabCompleteHandler(this);
      new ConfigCommandHandler(this);
      new RatingCommandHandler(this);
      new RatingTabCompleteHandler(this);
   }

   private void loadImagesFromResources() {
      File dir = new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images");
      if (!dir.exists()) {
         dir.mkdir();
      }

      this.loadImage("black_bishop_black_box_marked.png");
      this.loadImage("black_bishop_black_box.png");
      this.loadImage("black_bishop_green.png");
      this.loadImage("black_bishop_white_box_marked.png");
      this.loadImage("black_bishop_white_box.png");
      this.loadImage("black_king_black_box_marked.png");
      this.loadImage("black_king_black_box.png");
      this.loadImage("black_king_green.png");
      this.loadImage("black_king_white_box_marked.png");
      this.loadImage("black_king_white_box.png");
      this.loadImage("black_knight_black_box_marked.png");
      this.loadImage("black_knight_black_box.png");
      this.loadImage("black_knight_green.png");
      this.loadImage("black_knight_white_box_marked.png");
      this.loadImage("black_knight_white_box.png");
      this.loadImage("black_pawn_black_box_marked.png");
      this.loadImage("black_pawn_black_box.png");
      this.loadImage("black_pawn_green.png");
      this.loadImage("black_pawn_white_box_marked.png");
      this.loadImage("black_pawn_white_box.png");
      this.loadImage("black_queen_black_box_marked.png");
      this.loadImage("black_queen_black_box.png");
      this.loadImage("black_queen_green.png");
      this.loadImage("black_queen_white_box_marked.png");
      this.loadImage("black_queen_white_box.png");
      this.loadImage("black_rook_black_box_marked.png");
      this.loadImage("black_rook_black_box.png");
      this.loadImage("black_rook_green.png");
      this.loadImage("black_rook_white_box_marked.png");
      this.loadImage("black_rook_white_box.png");
      this.loadImage("white_bishop_black_box_marked.png");
      this.loadImage("white_bishop_black_box.png");
      this.loadImage("white_bishop_green.png");
      this.loadImage("white_bishop_white_box_marked.png");
      this.loadImage("white_bishop_white_box.png");
      this.loadImage("white_king_black_box_marked.png");
      this.loadImage("white_king_black_box.png");
      this.loadImage("white_king_green.png");
      this.loadImage("white_king_white_box_marked.png");
      this.loadImage("white_king_white_box.png");
      this.loadImage("white_knight_black_box_marked.png");
      this.loadImage("white_knight_black_box.png");
      this.loadImage("white_knight_green.png");
      this.loadImage("white_knight_white_box_marked.png");
      this.loadImage("white_knight_white_box.png");
      this.loadImage("white_pawn_black_box_marked.png");
      this.loadImage("white_pawn_black_box.png");
      this.loadImage("white_pawn_green.png");
      this.loadImage("white_pawn_white_box_marked.png");
      this.loadImage("white_pawn_white_box.png");
      this.loadImage("white_queen_black_box_marked.png");
      this.loadImage("white_queen_black_box.png");
      this.loadImage("white_queen_green.png");
      this.loadImage("white_queen_white_box_marked.png");
      this.loadImage("white_queen_white_box.png");
      this.loadImage("white_rook_black_box_marked.png");
      this.loadImage("white_rook_black_box.png");
      this.loadImage("white_rook_green.png");
      this.loadImage("white_rook_white_box_marked.png");
      this.loadImage("white_rook_white_box.png");
      this.loadImage("black_box_marked.png");
      this.loadImage("black_box.png");
      this.loadImage("white_box_marked.png");
      this.loadImage("white_box.png");
      this.loadImage("1.png");
      this.loadImage("2.png");
      this.loadImage("3.png");
      this.loadImage("4.png");
      this.loadImage("5.png");
      this.loadImage("6.png");
      this.loadImage("7.png");
      this.loadImage("8.png");
      this.loadImage("A.png");
      this.loadImage("B.png");
      this.loadImage("C.png");
      this.loadImage("D.png");
      this.loadImage("E.png");
      this.loadImage("F.png");
      this.loadImage("G.png");
      this.loadImage("H.png");
      this.loadImage("flag.png");
   }

   private void loadImage(String name) {
      File f = new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + name);
      if (!f.exists()) {
         InputStream is = this.getResource(name);

         try {
            f.createNewFile();
            FileOutputStream os = new FileOutputStream(f);
            byte[] bytes = new byte[1024];

            int read;
            while((read = is.read(bytes)) != -1) {
               os.write(bytes, 0, read);
            }

            os.close();
            is.close();
         } catch (IOException var7) {
            var7.printStackTrace();
         }
      }

   }

   public void addPlayerToPacketListeningList(Player player, ArrayList idList) {
      this.plm.injectPlayer(player, idList);
   }

   public void removePlayerFromPacketListeningList(Player player) {
      this.plm.removePlayer(player);
   }

   public ArrayList getMinecraftChessList() {
      return this.chessGames;
   }

   private void importMaps(World world) {
      for(int i = 0; i < 81; ++i) {
         int num = 999999920 + i;
         File file = new File(world.getWorldFolder().getAbsolutePath() + "/data/map_" + num + ".dat");
         if (!file.exists()) {
            InputStream is = this.getResource("map_" + i + ".dat");

            try {
               file.createNewFile();
               FileOutputStream os = new FileOutputStream(file);
               byte[] bytes = new byte[1024];

               int read;
               while((read = is.read(bytes)) != -1) {
                  os.write(bytes, 0, read);
               }

               os.close();
               is.close();
            } catch (IOException var9) {
               var9.printStackTrace();
            }
         }
      }

   }

   public MinecraftChessGame getMinecraftChessFromName(String name) {
      for(int i = 0; i < this.chessGames.size(); ++i) {
         if (((MinecraftChessGame)this.chessGames.get(i)).getName().equals(name)) {
            return (MinecraftChessGame)this.chessGames.get(i);
         }
      }

      return null;
   }

   private boolean isWorldName(String key) {
      return this.getConfig().isString("boards." + key + ".worldname");
   }

   public MinecraftChessGame createChessBoard(Player player, String boardName, World w) {
      if (this.getConfig().isInt("boards." + boardName + ".X") && this.getConfig().isInt("boards." + boardName + ".Y") && this.getConfig().isInt("boards." + boardName + ".Z")) {
         this.importMaps(w);
         MinecraftChessGame mc = new MinecraftChessGame(this, this.getConfig().getInt("boards." + boardName + ".X"), this.getConfig().getInt("boards." + boardName + ".Y"), this.getConfig().getInt("boards." + boardName + ".Z"), w, boardName);
         this.chessGames.add(mc);
         mc.getOutput().renderImages();
         return mc;
      } else {
         return null;
      }
   }

   public void setHologram(Player player, MinecraftChessGame mc) {
      if (this.getConfig().isDouble("boards." + mc.getName() + ".text.X") || this.getConfig().isInt("boards." + mc.getName() + ".text.X")) {
         if (this.getConfig().isDouble("boards." + mc.getName() + ".text.Y") || this.getConfig().isInt("boards." + mc.getName() + ".text.Y")) {
            if (this.getConfig().isDouble("boards." + mc.getName() + ".text.Z") || this.getConfig().isInt("boards." + mc.getName() + ".text.Z")) {
               if (this.getConfig().isString("boards." + mc.getName() + ".text.worldname")) {
                  if (Bukkit.getServer().getWorld(this.getConfig().getString("boards." + mc.getName() + ".text.worldname")) != null) {
                     mc.setHologram(new Hologram(this.getConfig().getDouble("boards." + mc.getName() + ".text.X"), this.getConfig().getDouble("boards." + mc.getName() + ".text.Y"), this.getConfig().getDouble("boards." + mc.getName() + ".text.Z"), Bukkit.getWorld(this.getConfig().getString("boards." + mc.getName() + ".text.worldname"))));
                     if (player != null) {
                        player.sendMessage(Utils.chat("&6[MinecraftChess] &aHologram text was successfully set to the board"));
                     }

                  }
               }
            }
         }
      }
   }

   public void setWhiteIn(Player player, MinecraftChessGame mc) {
      if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_white_in.X") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_white_in.X")) {
         if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_white_in.Y") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_white_in.Y")) {
            if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_white_in.Z") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_white_in.Z")) {
               if (this.getConfig().isString("boards." + mc.getName() + ".teleport_white_in.worldname")) {
                  if (Bukkit.getServer().getWorld(this.getConfig().getString("boards." + mc.getName() + ".teleport_white_in.worldname")) != null) {
                     mc.setWhiteIn(new Location(Bukkit.getWorld(this.getConfig().getString("boards." + mc.getName() + ".teleport_white_in.worldname")), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_white_in.X"), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_white_in.Y"), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_white_in.Z")));
                     if (player != null) {
                        player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe white starting location was successfully set to the board"));
                     }

                  }
               }
            }
         }
      }
   }

   public void setBlackIn(Player player, MinecraftChessGame mc) {
      if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_black_in.X") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_black_in.X")) {
         if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_black_in.Y") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_black_in.Y")) {
            if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_black_in.Z") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_black_in.Z")) {
               if (this.getConfig().isString("boards." + mc.getName() + ".teleport_black_in.worldname")) {
                  if (Bukkit.getServer().getWorld(this.getConfig().getString("boards." + mc.getName() + ".teleport_black_in.worldname")) != null) {
                     mc.setBlackIn(new Location(Bukkit.getWorld(this.getConfig().getString("boards." + mc.getName() + ".teleport_black_in.worldname")), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_black_in.X"), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_black_in.Y"), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_black_in.Z")));
                     if (player != null) {
                        player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe black starting location was successfully set to the board"));
                     }

                  }
               }
            }
         }
      }
   }

   public void setAllOut(Player player, MinecraftChessGame mc) {
      if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_out.X") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_out.X")) {
         if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_out.Y") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_out.Y")) {
            if (this.getConfig().isDouble("boards." + mc.getName() + ".teleport_out.Z") || this.getConfig().isInt("boards." + mc.getName() + ".teleport_out.Z")) {
               if (this.getConfig().isString("boards." + mc.getName() + ".teleport_out.worldname")) {
                  if (Bukkit.getServer().getWorld(this.getConfig().getString("boards." + mc.getName() + ".teleport_out.worldname")) != null) {
                     mc.setAllOut(new Location(Bukkit.getWorld(this.getConfig().getString("boards." + mc.getName() + ".teleport_out.worldname")), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_out.X"), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_out.Y"), this.getConfig().getDouble("boards." + mc.getName() + ".teleport_out.Z")));
                     if (player != null) {
                        player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe game end location was successfully set to the board"));
                     }

                  }
               }
            }
         }
      }
   }

   public void setCancelButton(Player player, MinecraftChessGame mc) {
      if (this.getConfig().isInt("boards." + mc.getName() + ".cancel_button.X")) {
         if (this.getConfig().isInt("boards." + mc.getName() + ".cancel_button.Y")) {
            if (this.getConfig().isInt("boards." + mc.getName() + ".cancel_button.Z")) {
               if (this.getConfig().isString("boards." + mc.getName() + ".cancel_button.worldname")) {
                  if (Bukkit.getServer().getWorld(this.getConfig().getString("boards." + mc.getName() + ".cancel_button.worldname")) != null) {
                     mc.setCancelButton(new CancelButton(Bukkit.getWorld(this.getConfig().getString("boards." + mc.getName() + ".cancel_button.worldname")), this.getConfig().getInt("boards." + mc.getName() + ".cancel_button.X"), this.getConfig().getInt("boards." + mc.getName() + ".cancel_button.Y"), this.getConfig().getInt("boards." + mc.getName() + ".cancel_button.Z"), this));
                     if (player != null) {
                        player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe cancel button was successfully set to the board"));
                     }

                  }
               }
            }
         }
      }
   }

   public void addJoinButton(Player player, MinecraftChessGame mc, String buttonName) {
      if (this.getConfig().isInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".X")) {
         if (this.getConfig().isInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".Y")) {
            if (this.getConfig().isInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".Z")) {
               if (this.getConfig().isString("boards." + mc.getName() + ".join_buttons." + buttonName + ".worldname")) {
                  if (Bukkit.getServer().getWorld(this.getConfig().getString("boards." + mc.getName() + ".join_buttons." + buttonName + ".worldname")) != null) {
                     if (this.getConfig().isString("boards." + mc.getName() + ".join_buttons." + buttonName + ".item")) {
                        if (this.getConfig().isInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".bet")) {
                           mc.addJoinButton(new JoinButton(buttonName, Bukkit.getWorld(this.getConfig().getString("boards." + mc.getName() + ".join_buttons." + buttonName + ".worldname")), this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".X"), this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".Y"), this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".Z"), this, this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".bet"), this.getConfig().getString("boards." + mc.getName() + ".join_buttons." + buttonName + ".item")));
                           if (player != null) {
                              player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe join button was successfully added to the board"));
                           }

                        }
                     } else {
                        mc.addJoinButton(new JoinButton(buttonName, Bukkit.getWorld(this.getConfig().getString("boards." + mc.getName() + ".join_buttons." + buttonName + ".worldname")), this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".X"), this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".Y"), this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".Z"), this));
                        if (player != null) {
                           player.sendMessage(Utils.chat("&6[MinecraftCHess] &aThe join button was successfully added to the board"));
                        }

                     }
                  }
               }
            }
         }
      }
   }

   public void setEnableBoardEdges(boolean value) {
      this.getConfig().set("enableboardedges", value);
   }

   public void setBetToJoinButton(Player player, MinecraftChessGame mc, String buttonName) {
      mc.getJoinButtonFromName(buttonName).setItemType(Material.getMaterial(this.getConfig().getString("boards." + mc.getName() + ".join_buttons." + buttonName + ".item")));
      mc.getJoinButtonFromName(buttonName).setBet(this.getConfig().getInt("boards." + mc.getName() + ".join_buttons." + buttonName + ".bet"));
      if (player != null) {
         player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe bet was successfully added to the button"));
      }

   }

   public void removeJoinButton(Player player, MinecraftChessGame mc, String buttonName) {
      mc.removeJoinButton(buttonName);
      if (player != null) {
         player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe join button was sucessfully removed from the board"));
      }

   }

   public void removePlayerKickOffTheBoardTaskId(Player p) {
      if (this.playersKickOffTheBoardTaskId.containsKey(p.getName())) {
         Bukkit.getScheduler().cancelTask((Integer)this.playersKickOffTheBoardTaskId.get(p.getName()));
         this.playersKickOffTheBoardTaskId.remove(p.getName());
      }

   }

   public void addPlayerKickOffTheBoardTaskId(Player p, int id) {
      this.removePlayerKickOffTheBoardTaskId(p);
      this.playersKickOffTheBoardTaskId.put(p.getName(), id);
   }

   public void removeBetFromJoinButton(Player player, MinecraftChessGame mc, String buttonName) {
      mc.getJoinButtonFromName(buttonName).removeBet();
      if (player != null) {
         player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe bet was sucessfully removed from the button"));
      }

   }

   public void setTimeLimit(Player player, MinecraftChessGame mc) {
      if (this.getConfig().isInt("boards." + mc.getName() + ".time_limit") && this.getConfig().getInt("boards." + mc.getName() + ".time_limit") > 0) {
         mc.setTimeLimit(this.getConfig().getInt("boards." + mc.getName() + ".time_limit"));
         if (mc.getHologram() != null) {
            mc.getHologram().setTimeLimit((long)this.getConfig().getInt("boards." + mc.getName() + ".time_limit"));
         }

         if (player != null) {
            player.sendMessage(Utils.chat("&6[MinecraftChessS] &aThe time limit was sucessfully set to the board"));
         }
      }

   }

   public void removeText(Player player, MinecraftChessGame mc) {
      mc.removeText();
      if (player != null) {
         player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe hologram text was successfully removed from the board"));
      }

   }

   public void removeTimeLimit(Player player, MinecraftChessGame mc) {
      mc.removeTimeLimit();
      if (player != null) {
         player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe time limit was successfully removed from the board"));
      }

   }

   public void setLevitationEffect(Player player, MinecraftChessGame mc) {
      if (this.getConfig().isBoolean("boards." + mc.getName() + ".levitation_effect")) {
         mc.setLevitationEffect(this.getConfig().getBoolean("boards." + mc.getName() + ".levitation_effect"));
         if (player != null) {
            player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe levitation effect was successfully set to the board"));
         }
      }

   }

   public void loadBoard(String boardName) {
      if (!this.isWorldName(boardName)) {
         Bukkit.getServer().getConsoleSender().sendMessage("[MinecraftChess] Exception occurred: cannot read world name of " + boardName + " board.");
      } else {
         World w = Bukkit.getWorld(this.getConfig().getString("boards." + boardName + ".worldname"));
         if (w == null) {
            Bukkit.getServer().getConsoleSender().sendMessage("[MinecraftChess] Exception occurred: world with name: " + this.getConfig().getString("boards." + boardName + ".worldname") + " doesn't exist.");
         } else {
            MinecraftChessGame mc = this.createChessBoard((Player)null, boardName, w);
            if (mc != null) {
               this.setHologram((Player)null, mc);
               this.setWhiteIn((Player)null, mc);
               this.setBlackIn((Player)null, mc);
               this.setCancelButton((Player)null, mc);
               this.setAllOut((Player)null, mc);
               this.setLevitationEffect((Player)null, mc);
               this.setTimeLimit((Player)null, mc);
               if (this.getConfig().isConfigurationSection("boards." + boardName + ".join_buttons")) {
                  Iterator var5 = this.getConfig().getConfigurationSection("boards." + boardName + ".join_buttons").getKeys(false).iterator();

                  while(var5.hasNext()) {
                     String buttonName = (String)var5.next();
                     this.addJoinButton((Player)null, mc, buttonName);
                  }
               }

            }
         }
      }
   }

   public void removeChessBoard(Player player, MinecraftChessGame mc) {
      mc.destroy();
      this.chessGames.remove(mc);
      if (player != null) {
         player.sendMessage(Utils.chat("&6[MinecraftChess] &aThe chess board was successfully removed"));
      }

   }

   public void saveUsersConfig() {
      try {
         this.usersConfig.save(this.usersFile);
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void readConfig() throws CannotFindTextException, CannotFindValueException {
      Hologram.readTextsFromConfig(this);
      JoinButton.readTextsFromConfig(this);
      JoinButton.readValuesFromConfig(this);
      CancelButton.readTextsFromConfig(this);
      MinecraftChessGame.readTextsFromConfig(this);
      RatingCommandHandler.readTextsFromConfig(this);
      Output.readEnableBoardEdges(this);
      if (this.getConfig().isConfigurationSection("boards")) {
         Iterator var2 = this.getConfig().getConfigurationSection("boards").getKeys(false).iterator();

         while(var2.hasNext()) {
            String boardName = (String)var2.next();
            this.loadBoard(boardName);
         }
      }

   }

   public void addPlayerToList(Player player) {
      this.playersIDWhoPlaingChess.add(player.getUniqueId().toString());
   }

   public void removePlayerFromList(Player player) {
      this.playersIDWhoPlaingChess.remove(player.getUniqueId().toString());
   }

   public Color getPlayerColor(Player player) {
      for(int i = 0; i < this.chessGames.size(); ++i) {
         if (((MinecraftChessGame)this.chessGames.get(i)).isPlayerOnBoard(player)) {
            return ((MinecraftChessGame)this.chessGames.get(i)).getPlayerColor(player);
         }
      }

      return Color.NONE;
   }

   public FileConfiguration getUsersConfig() {
      return this.usersConfig;
   }

   public MinecraftChessGame getPlayerBoard(Player player) {
      for(int i = 0; i < this.chessGames.size(); ++i) {
         if (((MinecraftChessGame)this.chessGames.get(i)).isPlayerOnBoard(player)) {
            return (MinecraftChessGame)this.chessGames.get(i);
         }
      }

      return null;
   }

   public boolean isPlayerInList(Player player) {
      return this.playersIDWhoPlaingChess.contains(player.getUniqueId().toString());
   }

   public boolean checkButtons(String worldname, int x, int y, int z, Player player) {
      for(int i = 0; i < this.chessGames.size(); ++i) {
         if (((MinecraftChessGame)this.chessGames.get(i)).buttonPush(worldname, x, y, z, player)) {
            return true;
         }
      }

      return false;
   }

   public void doAction(Player player, int x, int y, int z) {
      for(int i = 0; i < this.chessGames.size(); ++i) {
         if (((MinecraftChessGame)this.chessGames.get(i)).action(player, x, y, z)) {
            ((MinecraftChessGame)this.chessGames.get(i)).refreshOutput();
            return;
         }
      }

   }
}
