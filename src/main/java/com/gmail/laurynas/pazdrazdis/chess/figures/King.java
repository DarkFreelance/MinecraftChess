package com.gmail.laurynas.pazdrazdis.chess.figures;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;

public class King extends AbstractFigure implements FirstMoveMethods {
   private boolean didFirstMove;

   public King(Color color) {
      super.setColor(color);
      super.setType(FigureType.KING);
      this.didFirstMove = false;
   }

   public boolean isBadForKing(Board board, int x1, int y1, int x2, int y2) {
      if (x2 - x1 == 2) {
         return super.isBadForKing(board, x1, y1, x2 - 1, y2) || super.isBadForKing(board, x1, y1, x2, y2);
      } else if (x1 - x2 == 2) {
         return super.isBadForKing(board, x1, y1, x2 + 1, y2) || super.isBadForKing(board, x1, y1, x2, y2);
      } else {
         return super.isBadForKing(board, x1, y1, x2, y2);
      }
   }

   public MoveType getMoveType(Board board, int x1, int y1, int x2, int y2) {
      if (board.getTile(x1, y1).getFigure().getColor() == board.getTile(x2, y2).getFigure().getColor()) {
         return MoveType.ILLEGAL_MOVE;
      } else if ((x1 - x2 != 1 || y2 - y1 != 1 && y2 - y1 != 0 && y2 - y1 != -1) && (x1 - x2 != -1 || y2 - y1 != 1 && y2 - y1 != 0 && y2 - y1 != -1) && (x1 != x2 || y1 - y2 != 1 && y1 - y2 != -1)) {
         if (!this.getDidFirstMove() && y1 == y2) {
            if (x2 == 2) {
               if (board.getTile(0, y2).getFigure().getType().equals(FigureType.ROOK) && !((Rook)board.getTile(0, y2).getFigure()).getDidFirstMove() && board.getTile(1, y2).getFigure().getType() == FigureType.EMPTY && board.getTile(2, y2).getFigure().getType() == FigureType.EMPTY && board.getTile(3, y2).getFigure().getType() == FigureType.EMPTY) {
                  if (this.isBadForKing(board, x1, y1, x2, y2)) {
                     return MoveType.BAD_FOR_KING;
                  }

                  return MoveType.LONG_CASTLING;
               }
            } else if (x2 == 6 && board.getTile(7, y2).getFigure().getType().equals(FigureType.ROOK) && !((Rook)board.getTile(7, y2).getFigure()).getDidFirstMove() && board.getTile(5, y2).getFigure().getType() == FigureType.EMPTY && board.getTile(6, y2).getFigure().getType() == FigureType.EMPTY) {
               if (this.isBadForKing(board, x1, y1, x2, y2)) {
                  return MoveType.BAD_FOR_KING;
               }

               return MoveType.SHORT_CASTLING;
            }
         }

         return MoveType.ILLEGAL_MOVE;
      } else {
         return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
      }
   }

   public void setDidFirstMove() {
      this.didFirstMove = true;
   }

   public boolean getDidFirstMove() {
      return this.didFirstMove;
   }
}
