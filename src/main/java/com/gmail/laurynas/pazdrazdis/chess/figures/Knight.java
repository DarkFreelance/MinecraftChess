package com.gmail.laurynas.pazdrazdis.chess.figures;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;

public class Knight extends AbstractFigure {
   public Knight(Color color) {
      super.setColor(color);
      super.setType(FigureType.KNIGHT);
   }

   public MoveType getMoveType(Board board, int x1, int y1, int x2, int y2) {
      if (board.getTile(x1, y1).getFigure().getColor() == board.getTile(x2, y2).getFigure().getColor()) {
         return MoveType.ILLEGAL_MOVE;
      } else if ((x2 - x1 != 2 && x2 - x1 != -2 || y2 - y1 != 1 && y2 - y1 != -1) && (x2 - x1 != 1 && x2 - x1 != -1 || y2 - y1 != 2 && y2 - y1 != -2)) {
         return MoveType.ILLEGAL_MOVE;
      } else {
         return this.isBadForKing(board, x1, y1, x2, y2) ? MoveType.BAD_FOR_KING : MoveType.COMMON_MOVE;
      }
   }
}
