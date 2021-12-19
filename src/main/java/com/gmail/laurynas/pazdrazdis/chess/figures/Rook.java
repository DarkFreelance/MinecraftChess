package com.gmail.laurynas.pazdrazdis.chess.figures;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;

public class Rook extends AbstractFigure implements FirstMoveMethods {
   private boolean didFirstMove;

   public Rook(Color color) {
      super.setColor(color);
      super.setType(FigureType.ROOK);
      this.didFirstMove = false;
   }

   public MoveType getMoveType(Board board, int x1, int y1, int x2, int y2) {
      if (board.getTile(x1, y1).getFigure().getColor() == board.getTile(x2, y2).getFigure().getColor()) {
         return MoveType.ILLEGAL_MOVE;
      } else {
         int i;
         if (x1 == x2) {
            if (y2 > y1) {
               for(i = y1 + 1; i < y2; ++i) {
                  if (board.getTile(x1, i).getFigure().getType() != FigureType.EMPTY) {
                     return MoveType.SOMETHING_IS_BLOCKING;
                  }
               }

               return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
            } else if (y2 >= y1) {
               return MoveType.ILLEGAL_MOVE;
            } else {
               for(i = y2 + 1; i < y1; ++i) {
                  if (board.getTile(x1, i).getFigure().getType() != FigureType.EMPTY) {
                     return MoveType.SOMETHING_IS_BLOCKING;
                  }
               }

               return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
            }
         } else if (y1 != y2) {
            return MoveType.ILLEGAL_MOVE;
         } else if (x2 > x1) {
            for(i = x1 + 1; i < x2; ++i) {
               if (board.getTile(i, y1).getFigure().getType() != FigureType.EMPTY) {
                  return MoveType.SOMETHING_IS_BLOCKING;
               }
            }

            return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
         } else if (x2 >= x1) {
            return MoveType.ILLEGAL_MOVE;
         } else {
            for(i = x2 + 1; i < x1; ++i) {
               if (board.getTile(i, y1).getFigure().getType() != FigureType.EMPTY) {
                  return MoveType.SOMETHING_IS_BLOCKING;
               }
            }

            return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
         }
      }
   }

   public boolean getDidFirstMove() {
      return this.didFirstMove;
   }

   public void setDidFirstMove() {
      this.didFirstMove = true;
   }
}
