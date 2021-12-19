package com.gmail.laurynas.pazdrazdis.chess.figures;

import com.gmail.laurynas.pazdrazdis.chess.board.Board;
import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.enums.FigureType;
import com.gmail.laurynas.pazdrazdis.chess.enums.MoveType;

public abstract class AbstractFigure implements Cloneable {
   private Color color;
   private FigureType type;

   public boolean isEnoughToTakeOffTheKing(MoveType moveType) {
      return moveType.equals(MoveType.COMMON_MOVE) || moveType.equals(MoveType.BAD_FOR_KING) || moveType.equals(MoveType.NEED_PAWN_TO_PROMOTE);
   }

   public boolean isBadForKing(Board board, int x1, int y1, int x2, int y2) {
      AbstractFigure figure = board.getTile(x2, y2).getFigure();
      board.getTile(x2, y2).setFigure(board.getTile(x1, y1).getFigure());
      board.getTile(x1, y1).setFigure(new Empty());

      for(int i = 0; i < 8; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (board.getTile(i, j).getFigure().getType().equals(FigureType.KING) && board.getTile(i, j).getFigure().getColor().equals(board.getTile(x2, y2).getFigure().getColor())) {
               for(int h = 0; h < 8; ++h) {
                  for(int k = 0; k < 8; ++k) {
                     if (!board.getTile(h, k).getFigure().getColor().equals(board.getTile(i, j).getFigure().getColor()) && this.isEnoughToTakeOffTheKing(board.getTile(h, k).getFigure().getMoveType(board, h, k, i, j))) {
                        board.getTile(x1, y1).setFigure(board.getTile(x2, y2).getFigure());
                        board.getTile(x2, y2).setFigure(figure);
                        return true;
                     }
                  }
               }
            }
         }
      }

      board.getTile(x1, y1).setFigure(board.getTile(x2, y2).getFigure());
      board.getTile(x2, y2).setFigure(figure);
      return false;
   }

   public abstract MoveType getMoveType(Board var1, int var2, int var3, int var4, int var5);

   public void setColor(Color color) {
      this.color = color;
   }

   public Color getColor() {
      return this.color;
   }

   public FigureType getType() {
      return this.type;
   }

   public void setType(FigureType type) {
      this.type = type;
   }

   public Object clone() throws CloneNotSupportedException {
      return super.clone();
   }
}
