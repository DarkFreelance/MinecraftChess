package com.gmail.laurynas.pazdrazdis.chess.game;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.board.Tile;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.GameFeedback;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;
import com.gmail.laurynas.pazdrazdis.chess.figures.Bishop;
import com.gmail.laurynas.pazdrazdis.chess.figures.DoubleMoveMethods;
import com.gmail.laurynas.pazdrazdis.chess.figures.FirstMoveMethods;
import com.gmail.laurynas.pazdrazdis.chess.figures.Knight;
import com.gmail.laurynas.pazdrazdis.chess.figures.Queen;
import com.gmail.laurynas.pazdrazdis.chess.figures.Rook;

public class ChessGame {
   public static final int NOT_SET = -100;
   private Board board;
   private Color whoseTurn;
   private boolean needPawnToPromote;
   private boolean isEndGame;
   private int x1;
   private int y1;
   private int x2;
   private int y2;
   private int oldX1;
   private int oldY1;
   private int oldX2;
   private int oldY2;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$MoveType;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType;

   public ChessGame() {
      this.whoseTurn = Color.WHITE;
      this.needPawnToPromote = false;
      this.isEndGame = false;
      this.x1 = -100;
      this.y1 = -100;
      this.x2 = -100;
      this.y2 = -100;
      this.oldX1 = -100;
      this.oldY1 = -100;
      this.oldX2 = -100;
      this.oldY2 = -100;
      this.board = new Board();
   }

   public GameFeedback doBoardAction(int x, int y, Color who) {
      if (this.whoseTurn != who) {
         return GameFeedback.NOT_YOUR_TURN;
      } else if (x <= 7 && x >= 0 && y <= 7 && y >= 0) {
         if (this.isEndGame) {
            return GameFeedback.END_GAME;
         } else if (this.needPawnToPromote) {
            return GameFeedback.NEED_PAWN_TO_PROMOTE;
         } else if (this.board.getTile(x, y).getFigure().getColor() == this.whoseTurn) {
            if (this.x1 != -100) {
               this.board.getTile(this.x1, this.y1).setIsMarkedAsClicked(false);
            }

            this.x1 = x;
            this.y1 = y;
            this.board.getTile(this.x1, this.y1).setIsMarkedAsClicked(true);
            return GameFeedback.FIGURE_MARKED_AS_CLICKED;
         } else if (this.x1 == -100) {
            return GameFeedback.INVALID_CLICK;
         } else {
            this.x2 = x;
            this.y2 = y;
            switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$MoveType()[this.board.move(this.x1, this.y1, this.x2, this.y2).ordinal()]) {
            case 3:
               this.finishEverything();
               if (this.board.isCheckMate(this.whoseTurn)) {
                  this.isEndGame = true;
                  return GameFeedback.END_GAME;
               }

               this.changeWhoseTurn();
               return GameFeedback.MOVE_COMPLETED;
            case 4:
               return GameFeedback.BAD_FOR_KING;
            case 5:
               this.finishEverything();
               if (this.board.isCheckMate(this.whoseTurn)) {
                  this.isEndGame = true;
                  return GameFeedback.END_GAME;
               }

               this.changeWhoseTurn();
               return GameFeedback.MOVE_COMPLETED;
            case 6:
               this.finishEverything();
               if (this.board.isCheckMate(this.whoseTurn)) {
                  this.isEndGame = true;
                  return GameFeedback.END_GAME;
               }

               this.changeWhoseTurn();
               return GameFeedback.MOVE_COMPLETED;
            case 7:
               this.finishEverything();
               if (this.board.isCheckMate(this.whoseTurn)) {
                  this.isEndGame = true;
                  return GameFeedback.END_GAME;
               }

               this.changeWhoseTurn();
               return GameFeedback.MOVE_COMPLETED;
            case 8:
               if (this.board.getTile(this.x2, this.y2).getFigure() instanceof DoubleMoveMethods) {
                  ((DoubleMoveMethods)this.board.getTile(this.x2, this.y2).getFigure()).setLastMoveWasDoubleMoveWithThisFigure(true);
               }

               this.finishEverything();
               if (this.board.isCheckMate(this.whoseTurn)) {
                  this.isEndGame = true;
                  return GameFeedback.END_GAME;
               }

               this.changeWhoseTurn();
               return GameFeedback.MOVE_COMPLETED;
            case 9:
               this.needPawnToPromote = true;
               this.finishEverything();
               return GameFeedback.NEED_PAWN_TO_PROMOTE;
            default:
               this.x2 = -100;
               this.y2 = -100;
               return GameFeedback.ILLEGAL_MOVE;
            }
         }
      } else {
         return GameFeedback.INVALID_COORDINATES;
      }
   }

   public GameFeedback doPromotion(FigureType toWhat, Color who) {
      if (this.whoseTurn != who) {
         return GameFeedback.NOT_YOUR_TURN;
      } else if (!this.needPawnToPromote) {
         return GameFeedback.INVALID_CLICK;
      } else if (this.isEndGame) {
         return GameFeedback.END_GAME;
      } else {
         Tile tile;
         switch($SWITCH_TABLE$com$gmail$laurynas$pazdrazdis$chess$enums$FigureType()[toWhat.ordinal()]) {
         case 4:
            tile = this.board.getTileWithPawnToPromote();
            tile.setFigure(new Queen(this.whoseTurn));
            this.needPawnToPromote = false;
            if (this.board.isCheckMate(this.whoseTurn)) {
               this.isEndGame = true;
               return GameFeedback.END_GAME;
            }

            this.changeWhoseTurn();
            return GameFeedback.MOVE_COMPLETED;
         case 5:
            tile = this.board.getTileWithPawnToPromote();
            tile.setFigure(new Bishop(this.whoseTurn));
            this.needPawnToPromote = false;
            if (this.board.isCheckMate(this.whoseTurn)) {
               this.isEndGame = true;
               return GameFeedback.END_GAME;
            }

            this.changeWhoseTurn();
            return GameFeedback.MOVE_COMPLETED;
         case 6:
            tile = this.board.getTileWithPawnToPromote();
            tile.setFigure(new Rook(this.whoseTurn));
            ((FirstMoveMethods)tile.getFigure()).setDidFirstMove();
            this.needPawnToPromote = false;
            if (this.board.isCheckMate(this.whoseTurn)) {
               this.isEndGame = true;
               return GameFeedback.END_GAME;
            }

            this.changeWhoseTurn();
            return GameFeedback.MOVE_COMPLETED;
         case 7:
            tile = this.board.getTileWithPawnToPromote();
            tile.setFigure(new Knight(this.whoseTurn));
            this.needPawnToPromote = false;
            if (this.board.isCheckMate(this.whoseTurn)) {
               this.isEndGame = true;
               return GameFeedback.END_GAME;
            }

            this.changeWhoseTurn();
            return GameFeedback.MOVE_COMPLETED;
         default:
            return GameFeedback.BAD_FIGURES;
         }
      }
   }

   private void finishEverything() {
      if (this.board.getTile(this.x2, this.y2).getFigure() instanceof FirstMoveMethods) {
         ((FirstMoveMethods)this.board.getTile(this.x2, this.y2).getFigure()).setDidFirstMove();
      }

      this.board.getTile(this.x1, this.y1).setIsMarkedAsClicked(false);
      if (this.oldX1 != -100) {
         this.board.getTile(this.oldX1, this.oldY1).setIsMarkedAsChanged(false);
         this.board.getTile(this.oldX2, this.oldY2).setIsMarkedAsChanged(false);
         if (this.board.getTile(this.oldX2, this.oldY2).getFigure() instanceof DoubleMoveMethods) {
            ((DoubleMoveMethods)this.board.getTile(this.oldX2, this.oldY2).getFigure()).setLastMoveWasDoubleMoveWithThisFigure(false);
         }
      }

      this.board.getTile(this.x1, this.y1).setIsMarkedAsChanged(true);
      this.board.getTile(this.x2, this.y2).setIsMarkedAsChanged(true);
      this.oldX1 = this.x1;
      this.oldY1 = this.y1;
      this.oldX2 = this.x2;
      this.oldY2 = this.y2;
      this.x1 = -100;
      this.y1 = -100;
      this.x2 = -100;
      this.y2 = -100;
   }

   private void changeWhoseTurn() {
      if (this.whoseTurn == Color.WHITE) {
         this.whoseTurn = Color.BLACK;
      } else {
         this.whoseTurn = Color.WHITE;
      }

   }

   public Board getBoard() {
      return this.board;
   }

   public Color getWhoseTurn() {
      return this.whoseTurn;
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
