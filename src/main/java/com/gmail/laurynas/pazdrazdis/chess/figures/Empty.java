package com.gmail.laurynas.pazdrazdis.chess.figures;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;

public class Empty extends AbstractFigure {
   public Empty() {
      super.setColor(Color.NONE);
      super.setType(FigureType.EMPTY);
   }

   public MoveType getMoveType(Board board, int x1, int y1, int x2, int y2) {
      return MoveType.ILLEGAL_MOVE;
   }
}
