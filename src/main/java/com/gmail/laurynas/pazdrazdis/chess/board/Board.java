package com.gmail.laurynas.pazdrazdis.chess.board;

import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;
import com.gmail.laurynas.pazdrazdis.chess.figures.AbstractFigure;
import com.gmail.laurynas.pazdrazdis.chess.figures.Bishop;
import com.gmail.laurynas.pazdrazdis.chess.figures.Empty;
import com.gmail.laurynas.pazdrazdis.chess.figures.King;
import com.gmail.laurynas.pazdrazdis.chess.figures.Knight;
import com.gmail.laurynas.pazdrazdis.chess.figures.Pawn;
import com.gmail.laurynas.pazdrazdis.chess.figures.Queen;
import com.gmail.laurynas.pazdrazdis.chess.figures.Rook;

public class Board {
   private int whiteKingX = 4;
   private int whiteKingY = 0;
   private int blackKingX = 4;
   private int blackKingY = 7;
   private Tile tileWithPawnToPromote = null;
   private Tile[][] tiles = new Tile[8][8];
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$MoveType;

   public Board() {
      this.tiles[0][0] = new Tile(new Rook(Color.WHITE), 0, 0, Color.BLACK);
      this.tiles[1][0] = new Tile(new Knight(Color.WHITE), 1, 0, Color.WHITE);
      this.tiles[2][0] = new Tile(new Bishop(Color.WHITE), 2, 0, Color.BLACK);
      this.tiles[3][0] = new Tile(new Queen(Color.WHITE), 3, 0, Color.WHITE);
      this.tiles[4][0] = new Tile(new King(Color.WHITE), 4, 0, Color.BLACK);
      this.tiles[5][0] = new Tile(new Bishop(Color.WHITE), 5, 0, Color.WHITE);
      this.tiles[6][0] = new Tile(new Knight(Color.WHITE), 6, 0, Color.BLACK);
      this.tiles[7][0] = new Tile(new Rook(Color.WHITE), 7, 0, Color.WHITE);
      this.tiles[0][7] = new Tile(new Rook(Color.BLACK), 0, 7, Color.WHITE);
      this.tiles[1][7] = new Tile(new Knight(Color.BLACK), 1, 7, Color.BLACK);
      this.tiles[2][7] = new Tile(new Bishop(Color.BLACK), 2, 7, Color.WHITE);
      this.tiles[3][7] = new Tile(new Queen(Color.BLACK), 3, 7, Color.BLACK);
      this.tiles[4][7] = new Tile(new King(Color.BLACK), 4, 7, Color.WHITE);
      this.tiles[5][7] = new Tile(new Bishop(Color.BLACK), 5, 7, Color.BLACK);
      this.tiles[6][7] = new Tile(new Knight(Color.BLACK), 6, 7, Color.WHITE);
      this.tiles[7][7] = new Tile(new Rook(Color.BLACK), 7, 7, Color.BLACK);

      for(int i = 0; i < 8; ++i) {
         if (i % 2 == 0) {
            this.tiles[i][1] = new Tile(new Pawn(Color.WHITE), i, 1, Color.WHITE);
            this.tiles[i][6] = new Tile(new Pawn(Color.BLACK), i, 6, Color.BLACK);
            this.tiles[i][2] = new Tile(new Empty(), i, 2, Color.BLACK);
            this.tiles[i][3] = new Tile(new Empty(), i, 3, Color.WHITE);
            this.tiles[i][4] = new Tile(new Empty(), i, 4, Color.BLACK);
            this.tiles[i][5] = new Tile(new Empty(), i, 5, Color.WHITE);
         } else {
            this.tiles[i][1] = new Tile(new Pawn(Color.WHITE), i, 1, Color.BLACK);
            this.tiles[i][6] = new Tile(new Pawn(Color.BLACK), i, 6, Color.WHITE);
            this.tiles[i][2] = new Tile(new Empty(), i, 2, Color.WHITE);
            this.tiles[i][3] = new Tile(new Empty(), i, 3, Color.BLACK);
            this.tiles[i][4] = new Tile(new Empty(), i, 4, Color.WHITE);
            this.tiles[i][5] = new Tile(new Empty(), i, 5, Color.BLACK);
         }
      }

   }

   public MoveType move(int x1, int y1, int x2, int y2) {
      MoveType moveType = this.tiles[x1][y1].getFigure().getMoveType(this, x1, y1, x2, y2);
      switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$MoveType()[moveType.ordinal()]) {
      case 3:
         this.getTile(x2, y2).setFigure(this.getTile(x1, y1).getFigure());
         this.getTile(x1, y1).setFigure(new Empty());
         if (this.getTile(x2, y2).getFigure().getType() == FigureType.KING) {
            if (this.getTile(x2, y2).getFigure().getColor() == Color.WHITE) {
               this.whiteKingX = x2;
               this.whiteKingY = y2;
            } else {
               this.blackKingX = x2;
               this.blackKingY = y2;
            }
         }
         break;
      case 4:
      default:
         return moveType;
      case 5:
         this.switchFigures(this.getTile(x1, y1), this.getTile(x2, y2));
         this.switchFigures(this.getTile(x1 - 4, y1), this.getTile(x2 + 1, y2));
         if (this.getTile(x2, y2).getFigure().getColor() == Color.WHITE) {
            this.whiteKingX = x2;
            this.whiteKingY = y2;
         } else {
            this.blackKingX = x2;
            this.blackKingY = y2;
         }
         break;
      case 6:
         this.switchFigures(this.getTile(x1, y1), this.getTile(x2, y2));
         this.switchFigures(this.getTile(x1 + 3, y1), this.getTile(x2 - 1, y2));
         if (this.getTile(x2, y2).getFigure().getColor() == Color.WHITE) {
            this.whiteKingX = x2;
            this.whiteKingY = y2;
         } else {
            this.blackKingX = x2;
            this.blackKingY = y2;
         }
         break;
      case 7:
         this.switchFigures(this.getTile(x1, y1), this.getTile(x2, y2));
         this.getTile(x2, y1).setFigure(new Empty());
         break;
      case 8:
         this.getTile(x2, y2).setFigure(this.getTile(x1, y1).getFigure());
         this.getTile(x1, y1).setFigure(new Empty());
         break;
      case 9:
         this.getTile(x2, y2).setFigure(this.getTile(x1, y1).getFigure());
         this.getTile(x1, y1).setFigure(new Empty());
         this.tileWithPawnToPromote = this.getTile(x2, y2);
      }

      return moveType;
   }

   private void switchFigures(Tile tile1, Tile tile2) {
      AbstractFigure temp = tile1.getFigure();
      tile1.setFigure(tile2.getFigure());
      tile2.setFigure(temp);
   }

   public boolean isCheck(Color whoseTurn) {
      int k;
      int l;
      MoveType moveType;
      if (whoseTurn == Color.WHITE) {
         for(k = 0; k < 8; ++k) {
            for(l = 0; l < 8; ++l) {
               moveType = this.getTile(k, l).getFigure().getMoveType(this, k, l, this.blackKingX, this.blackKingY);
               if (moveType == MoveType.COMMON_MOVE || moveType == MoveType.NEED_PAWN_TO_PROMOTE) {
                  return true;
               }
            }
         }
      } else {
         for(k = 0; k < 8; ++k) {
            for(l = 0; l < 8; ++l) {
               moveType = this.getTile(k, l).getFigure().getMoveType(this, k, l, this.whiteKingX, this.whiteKingY);
               if (moveType == MoveType.COMMON_MOVE || moveType == MoveType.NEED_PAWN_TO_PROMOTE) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean isCheckMate(Color whoseTurn) {
      for(int i = 0; i < 8; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (this.getTile(i, j).getFigure().getColor() != whoseTurn) {
               for(int k = 0; k < 8; ++k) {
                  for(int l = 0; l < 8; ++l) {
                     MoveType moveType = this.getTile(i, j).getFigure().getMoveType(this, i, j, k, l);
                     if (moveType == MoveType.COMMON_MOVE || moveType == MoveType.LONG_CASTLING || moveType == MoveType.SHORT_CASTLING || moveType == MoveType.NEED_PAWN_TO_PROMOTE || moveType == MoveType.PAWN_DOUBLE_MOVE || moveType == MoveType.PAWN_RARE_MOVE) {
                        return false;
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   public Tile getTileWithPawnToPromote() {
      return this.tileWithPawnToPromote;
   }

   public Tile getTile(int x, int y) {
      return this.tiles[x][y];
   }

   public void setTile(Tile tile, int x, int y) {
      this.tiles[x][y] = tile;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$MoveType() {
      int[] var10000 = $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$MoveType;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[MoveType.values().length];

         try {
            var0[MoveType.BAD_FOR_KING.ordinal()] = 4;
         } catch (NoSuchFieldError var9) {
         }

         try {
            var0[MoveType.COMMON_MOVE.ordinal()] = 3;
         } catch (NoSuchFieldError var8) {
         }

         try {
            var0[MoveType.ILLEGAL_MOVE.ordinal()] = 2;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[MoveType.LONG_CASTLING.ordinal()] = 5;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[MoveType.NEED_PAWN_TO_PROMOTE.ordinal()] = 9;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[MoveType.PAWN_DOUBLE_MOVE.ordinal()] = 8;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[MoveType.PAWN_RARE_MOVE.ordinal()] = 7;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[MoveType.SHORT_CASTLING.ordinal()] = 6;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[MoveType.SOMETHING_IS_BLOCKING.ordinal()] = 1;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$MoveType = var0;
         return var0;
      }
   }
}
