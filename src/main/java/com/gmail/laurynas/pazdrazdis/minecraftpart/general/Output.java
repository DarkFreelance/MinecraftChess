package com.gmail.laurynas.pazdrazdis.minecraftpart.general;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.board.Tile;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.CannotFindValueException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.exceptions.ItemFrameSpawnException;
import com.gmail.laurynas.pazdrazdis.minecraftpart.minecraftchessmain.MinecraftChessMain;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import net.minecraft.server.v1_16_R3.DataWatcher;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import net.minecraft.server.v1_16_R3.DataWatcherRegistry;
import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.scheduler.BukkitScheduler;

public class Output {
   private static final int BLACK_BISHOP_BLACK_BOX_MARKED = 999999920;
   private static final int BLACK_BISHOP_BLACK_BOX = 999999921;
   private static final int BLACK_BISHOP_GREEN = 999999922;
   private static final int BLACK_BISHOP_WHITE_BOX_MARKED = 999999923;
   private static final int BLACK_BISHOP_WHITE_BOX = 999999924;
   private static final int BLACK_KING_BLACK_BOX_MARKED = 999999925;
   private static final int BLACK_KING_BLACK_BOX = 999999926;
   private static final int BLACK_KING_GREEN = 999999927;
   private static final int BLACK_KING_WHITE_BOX_MARKED = 999999928;
   private static final int BLACK_KING_WHITE_BOX = 999999929;
   private static final int BLACK_KNIGHT_BLACK_BOX_MARKED = 999999930;
   private static final int BLACK_KNIGHT_BLACK_BOX = 999999931;
   private static final int BLACK_KNIGHT_GREEN = 999999932;
   private static final int BLACK_KNIGHT_WHITE_BOX_MARKED = 999999933;
   private static final int BLACK_KNIGHT_WHITE_BOX = 999999934;
   private static final int BLACK_PAWN_BLACK_BOX_MARKED = 999999935;
   private static final int BLACK_PAWN_BLACK_BOX = 999999936;
   private static final int BLACK_PAWN_GREEN = 999999937;
   private static final int BLACK_PAWN_WHITE_BOX_MARKED = 999999938;
   private static final int BLACK_PAWN_WHITE_BOX = 999999939;
   private static final int BLACK_QUEEN_BLACK_BOX_MARKED = 999999940;
   private static final int BLACK_QUEEN_BLACK_BOX = 999999941;
   private static final int BLACK_QUEEN_GREEN = 999999942;
   private static final int BLACK_QUEEN_WHITE_BOX_MARKED = 999999943;
   private static final int BLACK_QUEEN_WHITE_BOX = 999999944;
   private static final int BLACK_ROOK_BLACK_BOX_MARKED = 999999945;
   private static final int BLACK_ROOK_BLACK_BOX = 999999946;
   private static final int BLACK_ROOK_GREEN = 999999947;
   private static final int BLACK_ROOK_WHITE_BOX_MARKED = 999999948;
   private static final int BLACK_ROOK_WHITE_BOX = 999999949;
   private static final int WHITE_BISHOP_BLACK_BOX_MARKED = 999999950;
   private static final int WHITE_BISHOP_BLACK_BOX = 999999951;
   private static final int WHITE_BISHOP_GREEN = 999999952;
   private static final int WHITE_BISHOP_WHITE_BOX_MARKED = 999999953;
   private static final int WHITE_BISHOP_WHITE_BOX = 999999954;
   private static final int WHITE_KING_BLACK_BOX_MARKED = 999999955;
   private static final int WHITE_KING_BLACK_BOX = 999999956;
   private static final int WHITE_KING_GREEN = 999999957;
   private static final int WHITE_KING_WHITE_BOX_MARKED = 999999958;
   private static final int WHITE_KING_WHITE_BOX = 999999959;
   private static final int WHITE_KNIGHT_BLACK_BOX_MARKED = 999999960;
   private static final int WHITE_KNIGHT_BLACK_BOX = 999999961;
   private static final int WHITE_KNIGHT_GREEN = 999999962;
   private static final int WHITE_KNIGHT_WHITE_BOX_MARKED = 999999963;
   private static final int WHITE_KNIGHT_WHITE_BOX = 999999964;
   private static final int WHITE_PAWN_BLACK_BOX_MARKED = 999999965;
   private static final int WHITE_PAWN_BLACK_BOX = 999999966;
   private static final int WHITE_PAWN_GREEN = 999999967;
   private static final int WHITE_PAWN_WHITE_BOX_MARKED = 999999968;
   private static final int WHITE_PAWN_WHITE_BOX = 999999969;
   private static final int WHITE_QUEEN_BLACK_BOX_MARKED = 999999970;
   private static final int WHITE_QUEEN_BLACK_BOX = 999999971;
   private static final int WHITE_QUEEN_GREEN = 999999972;
   private static final int WHITE_QUEEN_WHITE_BOX_MARKED = 999999973;
   private static final int WHITE_QUEEN_WHITE_BOX = 999999974;
   private static final int WHITE_ROOK_BLACK_BOX_MARKED = 999999975;
   private static final int WHITE_ROOK_BLACK_BOX = 999999976;
   private static final int WHITE_ROOK_GREEN = 999999977;
   private static final int WHITE_ROOK_WHITE_BOX_MARKED = 999999978;
   private static final int WHITE_ROOK_WHITE_BOX = 999999979;
   private static final int BLACK_BOX_MARKED = 999999980;
   private static final int BLACK_BOX = 999999981;
   private static final int WHITE_BOX_MARKED = 999999982;
   private static final int WHITE_BOX = 999999983;
   private static final int NUMBER_1 = 999999984;
   private static final int NUMBER_2 = 999999985;
   private static final int NUMBER_3 = 999999986;
   private static final int NUMBER_4 = 999999987;
   private static final int NUMBER_5 = 999999988;
   private static final int NUMBER_6 = 999999989;
   private static final int NUMBER_7 = 999999990;
   private static final int NUMBER_8 = 999999991;
   private static final int LETTER_A = 999999992;
   private static final int LETTER_B = 999999993;
   private static final int LETTER_C = 999999994;
   private static final int LETTER_D = 999999995;
   private static final int LETTER_E = 999999996;
   private static final int LETTER_F = 999999997;
   private static final int LETTER_G = 999999998;
   private static final int LETTER_H = 999999999;
   private static final int FLAG = 1000000000;
   private static boolean enableBoardEdges;
   private Board board = new Board();
   private ItemFrame[][] itemFrames = new ItemFrame[8][8];
   private ArrayList itemFramesIdList = new ArrayList();
   private ItemFrame blackSurrenderItemFrame;
   private ItemFrame whiteSurrenderItemFrame;
   private ArrayList boardEdgesItemFrames = new ArrayList();
   private ArrayList promotionItemFrames = new ArrayList();
   private int x;
   private int y;
   private int z;
   private World world;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType;

   public Output(int x, int y, int z, World world, final MinecraftChessMain plugin, final MinecraftChessGame mc) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.world = world;
      this.loadBoardChunks();
      this.removeOldEntities();
      BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
      scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
         public void run() {
            try {
               Output.this.createStartingItemFramesWithImages();
               Output.this.createItemFramesIdList();
            } catch (ItemFrameSpawnException var2) {
               Bukkit.getServer().getConsoleSender().sendMessage("[MinecraftChess] Exception occurred: cannot print ItemFrame in location(world = \"" + mc.getWorld().getName() + "\", x = " + var2.getX() + ", y = " + var2.getY() + ", z = " + var2.getZ() + ").");
               plugin.getMinecraftChessList().remove(mc);
            }

         }
      }, 20L);
   }

   public void renderImages() {
      try {
         Bukkit.getMap(999999920).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_bishop_black_box_marked.png"))));
         Bukkit.getMap(999999921).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_bishop_black_box.png"))));
         Bukkit.getMap(999999922).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_bishop_green.png"))));
         Bukkit.getMap(999999923).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_bishop_white_box_marked.png"))));
         Bukkit.getMap(999999924).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_bishop_white_box.png"))));
         Bukkit.getMap(999999925).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_king_black_box_marked.png"))));
         Bukkit.getMap(999999926).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_king_black_box.png"))));
         Bukkit.getMap(999999927).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_king_green.png"))));
         Bukkit.getMap(999999928).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_king_white_box_marked.png"))));
         Bukkit.getMap(999999929).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_king_white_box.png"))));
         Bukkit.getMap(999999930).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_knight_black_box_marked.png"))));
         Bukkit.getMap(999999931).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_knight_black_box.png"))));
         Bukkit.getMap(999999932).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_knight_green.png"))));
         Bukkit.getMap(999999933).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_knight_white_box_marked.png"))));
         Bukkit.getMap(999999934).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_knight_white_box.png"))));
         Bukkit.getMap(999999935).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_pawn_black_box_marked.png"))));
         Bukkit.getMap(999999936).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_pawn_black_box.png"))));
         Bukkit.getMap(999999937).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_pawn_green.png"))));
         Bukkit.getMap(999999938).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_pawn_white_box_marked.png"))));
         Bukkit.getMap(999999939).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_pawn_white_box.png"))));
         Bukkit.getMap(999999940).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_queen_black_box_marked.png"))));
         Bukkit.getMap(999999941).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_queen_black_box.png"))));
         Bukkit.getMap(999999942).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_queen_green.png"))));
         Bukkit.getMap(999999943).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_queen_white_box_marked.png"))));
         Bukkit.getMap(999999944).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_queen_white_box.png"))));
         Bukkit.getMap(999999945).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_rook_black_box_marked.png"))));
         Bukkit.getMap(999999946).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_rook_black_box.png"))));
         Bukkit.getMap(999999947).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_rook_green.png"))));
         Bukkit.getMap(999999948).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_rook_white_box_marked.png"))));
         Bukkit.getMap(999999949).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_rook_white_box.png"))));
         Bukkit.getMap(999999950).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_bishop_black_box_marked.png"))));
         Bukkit.getMap(999999951).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_bishop_black_box.png"))));
         Bukkit.getMap(999999952).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_bishop_green.png"))));
         Bukkit.getMap(999999953).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_bishop_white_box_marked.png"))));
         Bukkit.getMap(999999954).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_bishop_white_box.png"))));
         Bukkit.getMap(999999955).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_king_black_box_marked.png"))));
         Bukkit.getMap(999999956).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_king_black_box.png"))));
         Bukkit.getMap(999999957).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_king_green.png"))));
         Bukkit.getMap(999999958).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_king_white_box_marked.png"))));
         Bukkit.getMap(999999959).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_king_white_box.png"))));
         Bukkit.getMap(999999960).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_knight_black_box_marked.png"))));
         Bukkit.getMap(999999961).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_knight_black_box.png"))));
         Bukkit.getMap(999999962).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_knight_green.png"))));
         Bukkit.getMap(999999963).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_knight_white_box_marked.png"))));
         Bukkit.getMap(999999964).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_knight_white_box.png"))));
         Bukkit.getMap(999999965).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_pawn_black_box_marked.png"))));
         Bukkit.getMap(999999966).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_pawn_black_box.png"))));
         Bukkit.getMap(999999967).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_pawn_green.png"))));
         Bukkit.getMap(999999968).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_pawn_white_box_marked.png"))));
         Bukkit.getMap(999999969).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_pawn_white_box.png"))));
         Bukkit.getMap(999999970).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_queen_black_box_marked.png"))));
         Bukkit.getMap(999999971).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_queen_black_box.png"))));
         Bukkit.getMap(999999972).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_queen_green.png"))));
         Bukkit.getMap(999999973).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_queen_white_box_marked.png"))));
         Bukkit.getMap(999999974).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_queen_white_box.png"))));
         Bukkit.getMap(999999975).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_rook_black_box_marked.png"))));
         Bukkit.getMap(999999976).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_rook_black_box.png"))));
         Bukkit.getMap(999999977).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_rook_green.png"))));
         Bukkit.getMap(999999978).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_rook_white_box_marked.png"))));
         Bukkit.getMap(999999979).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_rook_white_box.png"))));
         Bukkit.getMap(999999980).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_box_marked.png"))));
         Bukkit.getMap(999999981).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "black_box.png"))));
         Bukkit.getMap(999999982).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_box_marked.png"))));
         Bukkit.getMap(999999983).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "white_box.png"))));
         Bukkit.getMap(999999984).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "1.png"))));
         Bukkit.getMap(999999985).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "2.png"))));
         Bukkit.getMap(999999986).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "3.png"))));
         Bukkit.getMap(999999987).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "4.png"))));
         Bukkit.getMap(999999988).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "5.png"))));
         Bukkit.getMap(999999989).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "6.png"))));
         Bukkit.getMap(999999990).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "7.png"))));
         Bukkit.getMap(999999991).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "8.png"))));
         Bukkit.getMap(999999992).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "A.png"))));
         Bukkit.getMap(999999993).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "B.png"))));
         Bukkit.getMap(999999994).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "C.png"))));
         Bukkit.getMap(999999995).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "D.png"))));
         Bukkit.getMap(999999996).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "E.png"))));
         Bukkit.getMap(999999997).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "F.png"))));
         Bukkit.getMap(999999998).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "G.png"))));
         Bukkit.getMap(999999999).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "H.png"))));
         Bukkit.getMap(1000000000).addRenderer(new CustomMapRenderer(ImageIO.read(new File("plugins" + File.separator + "MinecraftChess" + File.separator + "images" + File.separator + "flag.png"))));
      } catch (IOException var2) {
         Bukkit.getServer().getConsoleSender().sendMessage("[MinecraftChess] Exception occurred: cannot find image");
      }

   }

   private void createItemFramesIdList() {
      for(int i = 0; i < 8; ++i) {
         for(int j = 0; j < 8; ++j) {
            this.itemFramesIdList.add(this.itemFrames[i][j].getEntityId());
         }
      }

   }

   public ArrayList getItemFramesIdList() {
      return this.itemFramesIdList;
   }

   public void rotateItemFramesForPlayer(Player player, int rotation) {
      for(int i = 0; i < 8; ++i) {
         for(int j = 0; j < 8; ++j) {
            DataWatcher dw = new DataWatcher((Entity)null);
            dw.register(new DataWatcherObject(8, DataWatcherRegistry.b), rotation);
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(this.itemFrames[i][j].getEntityId(), dw, true);
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
         }
      }

   }

   public static void readEnableBoardEdges(MinecraftChessMain plugin) throws CannotFindValueException {
      try {
         enableBoardEdges = plugin.getConfig().getBoolean("enableboardedges");
      } catch (Exception var2) {
         throw new CannotFindValueException("enableboardedges");
      }
   }

   public static boolean getEnableBoardEdges() {
      return enableBoardEdges;
   }

   public void destroyItemFramesAndBarriers() {
      this.world.getBlockAt(new Location(this.world, (double)this.whiteSurrenderItemFrame.getLocation().getBlockX(), (double)(this.whiteSurrenderItemFrame.getLocation().getBlockY() - 1), (double)this.whiteSurrenderItemFrame.getLocation().getBlockZ())).setType(Material.AIR);
      this.world.getBlockAt(new Location(this.world, (double)this.blackSurrenderItemFrame.getLocation().getBlockX(), (double)(this.blackSurrenderItemFrame.getLocation().getBlockY() - 1), (double)this.blackSurrenderItemFrame.getLocation().getBlockZ())).setType(Material.AIR);
      this.whiteSurrenderItemFrame.remove();
      this.blackSurrenderItemFrame.remove();

      int i;
      for(i = 0; i < 8; ++i) {
         for(int j = 0; j < 8; ++j) {
            this.world.getBlockAt(new Location(this.world, (double)this.itemFrames[i][j].getLocation().getBlockX(), (double)(this.itemFrames[i][j].getLocation().getBlockY() - 1), (double)this.itemFrames[i][j].getLocation().getBlockZ())).setType(Material.AIR);
            this.itemFrames[i][j].remove();
         }
      }

      for(i = 0; i < this.promotionItemFrames.size(); ++i) {
         this.world.getBlockAt(new Location(this.world, (double)((ItemFrame)this.promotionItemFrames.get(i)).getLocation().getBlockX(), (double)(((ItemFrame)this.promotionItemFrames.get(i)).getLocation().getBlockY() - 1), (double)((ItemFrame)this.promotionItemFrames.get(i)).getLocation().getBlockZ())).setType(Material.AIR);
         ((ItemFrame)this.promotionItemFrames.get(i)).remove();
      }

      this.destroyBoardEdgesAndBarriers();
   }

   public void destroyBoardEdgesAndBarriers() {
      for(int i = 0; i < this.boardEdgesItemFrames.size(); ++i) {
         this.world.getBlockAt(new Location(this.world, (double)((ItemFrame)this.boardEdgesItemFrames.get(i)).getLocation().getBlockX(), (double)(((ItemFrame)this.boardEdgesItemFrames.get(i)).getLocation().getBlockY() - 1), (double)((ItemFrame)this.boardEdgesItemFrames.get(i)).getLocation().getBlockZ())).setType(Material.AIR);
         ((ItemFrame)this.boardEdgesItemFrames.get(i)).remove();
      }

   }

   public void loadBoardChunks() {
      this.world.getBlockAt(this.x - 16, this.y, this.z).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x - 16, this.y, this.z - 16).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x - 16, this.y, this.z + 16).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x, this.y, this.z).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x, this.y, this.z - 16).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x - 16, this.y, this.z + 16).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x + 16, this.y, this.z).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x + 16, this.y, this.z - 16).getChunk().setForceLoaded(true);
      this.world.getBlockAt(this.x + 16, this.y, this.z + 16).getChunk().setForceLoaded(true);
   }

   public void createStartingItemFramesWithImages() throws ItemFrameSpawnException {
      this.whiteSurrenderItemFrame = this.createItemFrameWithImage(this.x + 5, this.z + 6, 1000000000, Rotation.NONE);
      this.blackSurrenderItemFrame = this.createItemFrameWithImage(this.x - 4, this.z - 7, 1000000000, Rotation.CLOCKWISE);
      if (enableBoardEdges) {
         this.createBoardEdges();
      }

      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x - 1, this.z + 6, 999999974, Rotation.NONE));
      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x, this.z + 6, 999999979, Rotation.NONE));
      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x + 1, this.z + 6, 999999954, Rotation.NONE));
      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x + 2, this.z + 6, 999999964, Rotation.NONE));
      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x + 2, this.z - 7, 999999941, Rotation.CLOCKWISE));
      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x + 1, this.z - 7, 999999946, Rotation.CLOCKWISE));
      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x, this.z - 7, 999999921, Rotation.CLOCKWISE));
      this.promotionItemFrames.add(this.createItemFrameWithImage(this.x - 1, this.z - 7, 999999931, Rotation.CLOCKWISE));
      this.itemFrames[0][0] = this.createItemFrameWithImage(this.x - 3, this.z + 3, 999999976, Rotation.NONE);
      this.itemFrames[1][0] = this.createItemFrameWithImage(this.x - 2, this.z + 3, 999999964, Rotation.NONE);
      this.itemFrames[2][0] = this.createItemFrameWithImage(this.x - 1, this.z + 3, 999999951, Rotation.NONE);
      this.itemFrames[3][0] = this.createItemFrameWithImage(this.x, this.z + 3, 999999974, Rotation.NONE);
      this.itemFrames[4][0] = this.createItemFrameWithImage(this.x + 1, this.z + 3, 999999956, Rotation.NONE);
      this.itemFrames[5][0] = this.createItemFrameWithImage(this.x + 2, this.z + 3, 999999954, Rotation.NONE);
      this.itemFrames[6][0] = this.createItemFrameWithImage(this.x + 3, this.z + 3, 999999961, Rotation.NONE);
      this.itemFrames[7][0] = this.createItemFrameWithImage(this.x + 4, this.z + 3, 999999979, Rotation.NONE);
      this.itemFrames[0][7] = this.createItemFrameWithImage(this.x - 3, this.z - 4, 999999949, Rotation.NONE);
      this.itemFrames[1][7] = this.createItemFrameWithImage(this.x - 2, this.z - 4, 999999931, Rotation.NONE);
      this.itemFrames[2][7] = this.createItemFrameWithImage(this.x - 1, this.z - 4, 999999924, Rotation.NONE);
      this.itemFrames[3][7] = this.createItemFrameWithImage(this.x, this.z - 4, 999999941, Rotation.NONE);
      this.itemFrames[4][7] = this.createItemFrameWithImage(this.x + 1, this.z - 4, 999999929, Rotation.NONE);
      this.itemFrames[5][7] = this.createItemFrameWithImage(this.x + 2, this.z - 4, 999999921, Rotation.NONE);
      this.itemFrames[6][7] = this.createItemFrameWithImage(this.x + 3, this.z - 4, 999999934, Rotation.NONE);
      this.itemFrames[7][7] = this.createItemFrameWithImage(this.x + 4, this.z - 4, 999999946, Rotation.NONE);

      int i;
      for(i = 0; i < 8; ++i) {
         if (i % 2 == 0) {
            this.itemFrames[i][1] = this.createItemFrameWithImage(this.x - 3 + i, this.z + 2, 999999969, Rotation.NONE);
            this.itemFrames[i][6] = this.createItemFrameWithImage(this.x - 3 + i, this.z - 3, 999999936, Rotation.NONE);
         } else {
            this.itemFrames[i][1] = this.createItemFrameWithImage(this.x - 3 + i, this.z + 2, 999999966, Rotation.NONE);
            this.itemFrames[i][6] = this.createItemFrameWithImage(this.x - 3 + i, this.z - 3, 999999939, Rotation.NONE);
         }
      }

      for(i = 0; i < 4; ++i) {
         for(int j = 0; j < 8; ++j) {
            if ((i + j) % 2 == 0) {
               this.itemFrames[j][2 + i] = this.createItemFrameWithImage(this.x - 3 + j, this.z + 1 - i, 999999981, Rotation.NONE);
            } else {
               this.itemFrames[j][2 + i] = this.createItemFrameWithImage(this.x - 3 + j, this.z + 1 - i, 999999983, Rotation.NONE);
            }
         }
      }

   }

   private void createBoardEdges() throws ItemFrameSpawnException {
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 3, this.z + 4, 999999992, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 2, this.z + 4, 999999993, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 1, this.z + 4, 999999994, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x, this.z + 4, 999999995, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 1, this.z + 4, 999999996, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 2, this.z + 4, 999999997, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 3, this.z + 4, 999999998, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 4, this.z + 4, 999999999, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 3, this.z - 5, 999999992, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 2, this.z - 5, 999999993, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 1, this.z - 5, 999999994, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x, this.z - 5, 999999995, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 1, this.z - 5, 999999996, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 2, this.z - 5, 999999997, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 3, this.z - 5, 999999998, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 4, this.z - 5, 999999999, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z + 3, 999999984, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z + 2, 999999985, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z + 1, 999999986, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z + 0, 999999987, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z - 1, 999999988, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z - 2, 999999989, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z - 3, 999999990, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x - 4, this.z - 4, 999999991, Rotation.NONE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z + 3, 999999984, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z + 2, 999999985, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z + 1, 999999986, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z + 0, 999999987, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z - 1, 999999988, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z - 2, 999999989, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z - 3, 999999990, Rotation.CLOCKWISE));
      this.boardEdgesItemFrames.add(this.createItemFrameWithImage(this.x + 5, this.z - 4, 999999991, Rotation.CLOCKWISE));
   }

   public void removeOldEntities() {
      Iterator var2 = this.world.getEntities().iterator();

      while(var2.hasNext()) {
         org.bukkit.entity.Entity entity = (org.bukkit.entity.Entity)var2.next();
         if (entity.isSilent() && entity.getLocation().getBlockX() - (this.x - 4) > -1 && entity.getLocation().getBlockX() - (this.x - 4) < 10 && entity.getLocation().getBlockZ() - (this.z - 7) > -1 && entity.getLocation().getBlockZ() - (this.z - 7) < 14 && entity.getLocation().getBlockY() - this.y > -1 && entity.getLocation().getBlockY() - this.y < 1) {
            entity.remove();
         }
      }

   }

   private ItemFrame createItemFrameWithImage(int x, int z, int mapid, Rotation rotation) throws ItemFrameSpawnException {
      try {
         this.clearPlaceForItemFrame(x, z);
         ItemFrame itemFrame = (ItemFrame)this.world.spawn(new Location(this.world, (double)x, (double)this.y, (double)z), ItemFrame.class);
         itemFrame.setFacingDirection(BlockFace.UP);
         itemFrame.setRotation(rotation);
         itemFrame.setCustomName("chessplugin9b14");
         itemFrame.setSilent(true);
         this.setImage(itemFrame, mapid);
         return itemFrame;
      } catch (IllegalArgumentException var6) {
         throw new ItemFrameSpawnException(x, this.y, z);
      }
   }

   private void clearPlaceForItemFrame(int x, int z) {
      this.world.getBlockAt(x, this.y - 1, z).setType(Material.BARRIER);
      this.world.getBlockAt(x, this.y, z).setType(Material.AIR);
   }

   private void setImage(ItemFrame itemFrame, int mapid) {
      ItemStack i = new ItemStack(Material.FILLED_MAP, 1);
      MapMeta mapMeta = (MapMeta)i.getItemMeta();
      mapMeta.setMapId(mapid);
      i.setItemMeta(mapMeta);
      itemFrame.setItem(i);
   }

   public void refreshBoard(Board board) throws CloneNotSupportedException {
      for(int i = 0; i < 8; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (!board.getTile(i, j).equals(this.board.getTile(i, j))) {
               this.updateTile(i, j, board);
               this.board.setTile((Tile)board.getTile(i, j).clone(), i, j);
            }
         }
      }

   }

   public void updateTile(int i, int j, Board board) {
      if (board.getTile(i, j).getIsMarkedAsChanged()) {
         if (board.getTile(i, j).getColor() == Color.WHITE) {
            if (board.getTile(i, j).getFigure().getColor() == Color.WHITE) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999968);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999958);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999973);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999953);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999978);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999963);
                  return;
               default:
                  return;
               }
            }

            if (board.getTile(i, j).getFigure().getColor() == Color.BLACK) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999938);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999928);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999943);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999923);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999948);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999933);
                  return;
               default:
                  return;
               }
            }

            this.setImage(this.itemFrames[i][j], 999999982);
         } else {
            if (board.getTile(i, j).getFigure().getColor() == Color.WHITE) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999965);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999955);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999970);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999950);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999975);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999960);
                  return;
               default:
                  return;
               }
            }

            if (board.getTile(i, j).getFigure().getColor() == Color.BLACK) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999935);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999925);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999940);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999920);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999945);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999930);
                  return;
               default:
                  return;
               }
            }

            this.setImage(this.itemFrames[i][j], 999999980);
         }
      } else {
         if (board.getTile(i, j).getIsMarkedAsClicked()) {
            if (board.getTile(i, j).getFigure().getColor() == Color.WHITE) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999967);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999957);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999972);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999952);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999977);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999962);
                  return;
               default:
                  return;
               }
            }

            switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
            case 2:
               this.setImage(this.itemFrames[i][j], 999999937);
               return;
            case 3:
               this.setImage(this.itemFrames[i][j], 999999927);
               return;
            case 4:
               this.setImage(this.itemFrames[i][j], 999999942);
               return;
            case 5:
               this.setImage(this.itemFrames[i][j], 999999922);
               return;
            case 6:
               this.setImage(this.itemFrames[i][j], 999999947);
               return;
            case 7:
               this.setImage(this.itemFrames[i][j], 999999932);
               return;
            default:
               return;
            }
         }

         if (board.getTile(i, j).getColor() == Color.WHITE) {
            if (board.getTile(i, j).getFigure().getColor() == Color.WHITE) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999969);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999959);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999974);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999954);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999979);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999964);
                  return;
               default:
                  return;
               }
            }

            if (board.getTile(i, j).getFigure().getColor() == Color.BLACK) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999939);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999929);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999944);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999924);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999949);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999934);
                  return;
               default:
                  return;
               }
            }

            this.setImage(this.itemFrames[i][j], 999999983);
         } else {
            if (board.getTile(i, j).getFigure().getColor() == Color.WHITE) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999966);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999956);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999971);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999951);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999976);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999961);
                  return;
               default:
                  return;
               }
            }

            if (board.getTile(i, j).getFigure().getColor() == Color.BLACK) {
               switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[board.getTile(i, j).getFigure().getType().ordinal()]) {
               case 2:
                  this.setImage(this.itemFrames[i][j], 999999936);
                  return;
               case 3:
                  this.setImage(this.itemFrames[i][j], 999999926);
                  return;
               case 4:
                  this.setImage(this.itemFrames[i][j], 999999941);
                  return;
               case 5:
                  this.setImage(this.itemFrames[i][j], 999999921);
                  return;
               case 6:
                  this.setImage(this.itemFrames[i][j], 999999946);
                  return;
               case 7:
                  this.setImage(this.itemFrames[i][j], 999999931);
                  return;
               default:
                  return;
               }
            }

            this.setImage(this.itemFrames[i][j], 999999981);
         }
      }

   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType() {
      int[] var10000 = $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[FigureType.values().length];

         try {
            var0[FigureType.BISHOP.ordinal()] = 5;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[FigureType.EMPTY.ordinal()] = 1;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[FigureType.KING.ordinal()] = 3;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[FigureType.KNIGHT.ordinal()] = 7;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[FigureType.PAWN.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[FigureType.QUEEN.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[FigureType.ROOK.ordinal()] = 6;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType = var0;
         return var0;
      }
   }
}
