package com.gmail.laurynas.pazdrazdis.chess.figures;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;

public class Bishop extends AbstractFigure {
   public Bishop(Color color) {
      super.setColor(color);
      super.setType(FigureType.BISHOP);
   }

   public MoveType getMoveType(Board board, int x1, int y1, int x2, int y2) {
      if (board.getTile(x1, y1).getFigure().getColor() == board.getTile(x2, y2).getFigure().getColor()) {
         return MoveType.ILLEGAL_MOVE;
      } else {
         int i;
         if (y2 - y1 == x2 - x1 && y2 > y1) {
            for(i = 1; i < y2 - y1; ++i) {
               if (board.getTile(x1 + i, y1 + i).getFigure().getType() != FigureType.EMPTY) {
                  return MoveType.SOMETHING_IS_BLOCKING;
               }
            }

            return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
         } else if (y2 - y1 == x2 - x1 && y2 < y1) {
            for(i = 1; i < y1 - y2; ++i) {
               if (board.getTile(x2 + i, y2 + i).getFigure().getType() != FigureType.EMPTY) {
                  return MoveType.SOMETHING_IS_BLOCKING;
               }
            }

            return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
         } else if (y2 - y1 == x1 - x2 && y2 > y1) {
            for(i = 1; i < y2 - y1; ++i) {
               if (board.getTile(x1 - i, y1 + i).getFigure().getType() != FigureType.EMPTY) {
                  return MoveType.SOMETHING_IS_BLOCKING;
               }
            }

            return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
         } else if (y2 - y1 == x1 - x2 && y2 < y1) {
            for(i = 1; i < y1 - y2; ++i) {
               if (board.getTile(x1 + i, y1 - i).getFigure().getType() != FigureType.EMPTY) {
                  return MoveType.SOMETHING_IS_BLOCKING;
               }
            }

            return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
         } else {
            return MoveType.ILLEGAL_MOVE;
         }
      }
   }
}
