package com.gmail.laurynas.pazdrazdis.chess.figures;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;

public class Pawn extends AbstractFigure implements FirstMoveMethods, DoubleMoveMethods {
   private boolean didFirstMove;
   private boolean lastMoveWasDoubleMoveWithThisFigure;

   public Pawn(Color color) {
      super.setColor(color);
      super.setType(FigureType.PAWN);
      this.didFirstMove = false;
      this.lastMoveWasDoubleMoveWithThisFigure = false;
   }

   public boolean isBadForKing(Board board, int x1, int y1, int x2, int y2) {
      if (board.getTile(x2, y2).getFigure().getType() == FigureType.EMPTY && x1 != x2) {
         AbstractFigure figure;
         if (board.getTile(x1, y1).getFigure().getColor() == Color.WHITE) {
            figure = board.getTile(x2, y2 - 1).getFigure();
            board.getTile(x2, y2 - 1).setFigure(board.getTile(x2, y2).getFigure());
         } else {
            figure = board.getTile(x2, y2 + 1).getFigure();
            board.getTile(x2, y2 + 1).setFigure(board.getTile(x2, y2).getFigure());
         }

         board.getTile(x2, y2).setFigure(board.getTile(x1, y1).getFigure());
         board.getTile(x1, y1).setFigure(new Empty());

         for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
               if (board.getTile(i, j).getFigure().getType() == FigureType.KING && board.getTile(i, j).getFigure().getColor() == board.getTile(x2, y2).getFigure().getColor()) {
                  for(int h = 0; h < 8; ++h) {
                     for(int k = 0; k < 8; ++k) {
                        if (board.getTile(h, k).getFigure().getColor() != board.getTile(i, j).getFigure().getColor() && this.isEnoughToTakeOffTheKing(board.getTile(h, k).getFigure().getMoveType(board, h, k, i, j))) {
                           if (board.getTile(x2, y2).getFigure().getColor() == Color.WHITE) {
                              board.getTile(x1, y1).setFigure(board.getTile(x2, y2).getFigure());
                              board.getTile(x2, y2).setFigure(board.getTile(x2, y2 - 1).getFigure());
                              board.getTile(x2, y2 - 1).setFigure(figure);
                           } else {
                              board.getTile(x1, y1).setFigure(board.getTile(x2, y2).getFigure());
                              board.getTile(x2, y2).setFigure(board.getTile(x2, y2 + 1).getFigure());
                              board.getTile(x2, y2 - 1).setFigure(figure);
                           }

                           return true;
                        }
                     }
                  }
               }
            }
         }

         if (board.getTile(x2, y2).getFigure().getColor() == Color.WHITE) {
            board.getTile(x1, y1).setFigure(board.getTile(x2, y2).getFigure());
            board.getTile(x2, y2).setFigure(board.getTile(x2, y2 - 1).getFigure());
            board.getTile(x2, y2 - 1).setFigure(figure);
         } else {
            board.getTile(x1, y1).setFigure(board.getTile(x2, y2).getFigure());
            board.getTile(x2, y2).setFigure(board.getTile(x2, y2 + 1).getFigure());
            board.getTile(x2, y2 + 1).setFigure(figure);
         }

         return false;
      } else {
         return super.isBadForKing(board, x1, y1, x2, y2);
      }
   }

   public MoveType getMoveType(Board board, int x1, int y1, int x2, int y2) {
      if (board.getTile(x1, y1).getFigure().getColor() == board.getTile(x2, y2).getFigure().getColor()) {
         return MoveType.ILLEGAL_MOVE;
      } else if (this.getColor() == Color.WHITE) {
         if (x1 == x2) {
            if (!this.getDidFirstMove() && y2 - y1 == 2) {
               if (board.getTile(x1, y1 + 1).getFigure().getType() == FigureType.EMPTY && board.getTile(x1, y1 + 2).getFigure().getType() == FigureType.EMPTY) {
                  return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.PAWN_DOUBLE_MOVE;
               } else {
                  return MoveType.SOMETHING_IS_BLOCKING;
               }
            } else if (y2 - y1 == 1) {
               if (board.getTile(x1, y1 + 1).getFigure().getType() != FigureType.EMPTY) {
                  return MoveType.SOMETHING_IS_BLOCKING;
               } else if (this.isBadForKing(board, x1, y1, x2, y2)) {
                  return MoveType.BAD_FOR_KING;
               } else {
                  return y2 == 7 ? MoveType.NEED_PAWN_TO_PROMOTE : MoveType.COMMON_MOVE;
               }
            } else {
               return MoveType.ILLEGAL_MOVE;
            }
         } else if (y2 - y1 != 1 || x2 - x1 != 1 && x2 - x1 != -1) {
            return MoveType.ILLEGAL_MOVE;
         } else if (board.getTile(x2, y2).getFigure().getType() != FigureType.EMPTY) {
            if (this.isBadForKing(board, x1, y1, x2, y2)) {
               return MoveType.BAD_FOR_KING;
            } else {
               return y2 == 7 ? MoveType.NEED_PAWN_TO_PROMOTE : MoveType.COMMON_MOVE;
            }
         } else if (board.getTile(x2, y2 - 1).getFigure().getType().equals(FigureType.PAWN) && ((Pawn)board.getTile(x2, y2 - 1).getFigure()).getLastMoveWasDoubleMoveWithThisFigure()) {
            return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.PAWN_RARE_MOVE;
         } else {
            return MoveType.ILLEGAL_MOVE;
         }
      } else if (x1 == x2) {
         if (!this.getDidFirstMove() && y2 - y1 == -2) {
            if (board.getTile(x1, y1 - 1).getFigure().getType() == FigureType.EMPTY && board.getTile(x1, y1 - 2).getFigure().getType() == FigureType.EMPTY) {
               return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.PAWN_DOUBLE_MOVE;
            } else {
               return MoveType.SOMETHING_IS_BLOCKING;
            }
         } else if (y2 - y1 == -1) {
            if (board.getTile(x1, y1 - 1).getFigure().getType() != FigureType.EMPTY) {
               return MoveType.SOMETHING_IS_BLOCKING;
            } else if (this.isBadForKing(board, x1, y1, x2, y2)) {
               return MoveType.BAD_FOR_KING;
            } else {
               return y2 == 0 ? MoveType.NEED_PAWN_TO_PROMOTE : MoveType.COMMON_MOVE;
            }
         } else {
            return MoveType.ILLEGAL_MOVE;
         }
      } else if (y2 - y1 != -1 || x2 - x1 != 1 && x2 - x1 != -1) {
         return MoveType.ILLEGAL_MOVE;
      } else if (board.getTile(x2, y2).getFigure().getType() != FigureType.EMPTY) {
         if (this.isBadForKing(board, x1, y1, x2, y2)) {
            return MoveType.BAD_FOR_KING;
         } else {
            return y2 == 0 ? MoveType.NEED_PAWN_TO_PROMOTE : MoveType.COMMON_MOVE;
         }
      } else if (board.getTile(x2, y2 + 1).getFigure().getType().equals(FigureType.PAWN) && ((Pawn)board.getTile(x2, y2 + 1).getFigure()).getLastMoveWasDoubleMoveWithThisFigure()) {
         return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.PAWN_RARE_MOVE;
      } else {
         return MoveType.ILLEGAL_MOVE;
      }
   }

   public boolean getDidFirstMove() {
      return this.didFirstMove;
   }

   public void setDidFirstMove() {
      this.didFirstMove = true;
   }

   public boolean getLastMoveWasDoubleMoveWithThisFigure() {
      return this.lastMoveWasDoubleMoveWithThisFigure;
   }

   public void setLastMoveWasDoubleMoveWithThisFigure(boolean value) {
      this.lastMoveWasDoubleMoveWithThisFigure = value;
   }
}
