package com.gmail.laurynas.pazdrazdis.chess.board;

import com.gmail.laurynas.pazdrazdis.chess.enums.Color;
import com.gmail.laurynas.pazdrazdis.chess.figures.AbstractFigure;

public class Tile implements Cloneable {
   private int x;
   private int y;
   private Color tileColor;
   private AbstractFigure figure;
   private boolean isMarkedAsClicked = false;
   private boolean isMarkedAsChanged = false;

   public Tile(AbstractFigure figure, int x, int y, Color tileColor) {
      this.figure = figure;
      this.x = x;
      this.y = y;
      this.tileColor = tileColor;
   }

   public Color getColor() {
      return this.tileColor;
   }

   public AbstractFigure getFigure() {
      return this.figure;
   }

   public void setFigure(AbstractFigure figure) {
      this.figure = figure;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public boolean equals(Object object) {
      return object instanceof Tile && ((Tile)object).getFigure().getType() == this.figure.getType() && ((Tile)object).getFigure().getColor() == this.figure.getColor() && ((Tile)object).getIsMarkedAsClicked() == this.isMarkedAsClicked && ((Tile)object).getIsMarkedAsChanged() == this.isMarkedAsChanged;
   }

   public boolean getIsMarkedAsChanged() {
      return this.isMarkedAsChanged;
   }

   public void setIsMarkedAsChanged(boolean value) {
      this.isMarkedAsChanged = value;
   }

   public boolean getIsMarkedAsClicked() {
      return this.isMarkedAsClicked;
   }

   public void setIsMarkedAsClicked(boolean value) {
      this.isMarkedAsClicked = value;
   }

   public Object clone() throws CloneNotSupportedException {
      Tile t = (Tile)super.clone();
      t.setFigure((AbstractFigure)this.figure.clone());
      return t;
   }
}
