/*----------------------------------------------------------------
 *  Author:  Sean O'Sullivan
 *  Email:   sposul23@holycross.edu
 *  Written:  11/28/19
 *  
 *  Each Cell object manages information about and draws a
 *  single "cell" of the game grid. 
 *----------------------------------------------------------------*/

import GUI.*;
import java.awt.Color;

/**
 * A <i>Cell</i> object holds all information about the state of a single cell
 * of the minesweeper game board. This includes:
 *   - whether a mine is hidden in this cell or not
 *   - how many of its neighboring cells contain mines
 *   - whether it has been revealed yet or is still hidden
 * Each Cell object knows how to draw itself in a graphical window, and it will
 * draw itself in different styles depending on all the above state information.
 */
public class Cell extends Widget {
    /**
     * Size of one cell when it is drawn on the screen, in pixels.
     */
    public static final int SIZE = 20;

    /**
     * Whether a mine is hidden in this cell or not.
     */
    protected boolean isMine;

    /**
     * Whether this cell is "revealed" or not.
     */
    protected boolean isRevealed;

    /**
     * Count of how many neighboring cells have mines.
     */


    protected boolean firstPressed;

     /**
     *Whether this mine is the one the user pressed.
     */
    
    protected int neighborMineCount;

    /**
     * Constructor: Initialize a cell to be drawn at the given x, y coordinates
     * on the screen. The cell will be blank. That is, it will not be a mine,
     * and it will have no neighboring mines so a neighbor mine count of zero.
     */

    public boolean flagged;

    
    public Cell(int x, int y) {
        super(x, y, SIZE, SIZE);
        this.isMine = false;
        this.isRevealed = false;
        this.neighborMineCount = 0;
    }

    /**
     * Hide a mine in this cell by changing the isMine variable to true.
     */
    public void plantMine() {
        isMine = true;
    }

    /**
     * Returns true if a mine is hidden in this cell, otherwise returns false.
     */
    public boolean isMine() {
        return isMine;
    }


     public boolean isFirstMine() {
        firstPressed = true;
        return firstPressed;
    }

    /**
     * Increment the neighbor mine count variable by one. 
     */
    public void incrementNeighborMineCount() {
        neighborMineCount++;
    }

    /**
     * Set the neighbor mine count variable to a given value.
     */
    public void setNeighborMineCount(int count) {
        neighborMineCount = count;
    }

    /**
     * Returns the value of the neighbor mine count variable.
     */
    public int getNeighborMineCount() {
        return neighborMineCount;
    }

    /**
     * Change this cell so that it is "revealed" by setting isRevealed to true.
     */
    public void reveal() {
        isRevealed = true;
    }

    /**
     * Returns true if this cell is "revealed", otherwise returns false.
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Hide a mine in this cell by changing the isMine variable to true.
     */
    public void makeMine() {
        isMine = true;
    }

    /**
     * Change this cell so that it shows the mine that is hiding in it.
     */
    public void showMine() {
        if (isMine)
            isRevealed = true;
	    }

	    /**
	     * Check whether there are neighboring mines.
	     */
	    public boolean coastIsClear() {
		return (neighborMineCount == 0);
	    }

    //check whether cell is flagged.
     public boolean isFlagged() {
		return flagged;
	    }

    //flag cell.
     public boolean flag() {
		return flagged = true;
	    }

    //remove flag from cell.
     public boolean unFlag() {
		return flagged = false;
	    }

	    /**
	     * Paint this cell on the canvas. Don't call this directly, it is called by
	     * the GUI system automatically. This function should draw something on the
	     * canvas. Usually the drawing should stay within the bounds (x, y, width,
	     * height) which are protected member variables of GUI.Widget, which this
	     * class extends.
	     * @param canvas the canvas on which to draw.
	     */
    public void repaint(GUI.Canvas canvas) {
		// TODO: Add code here to draw this cell. The look of the cell should
		// depend on its current state, e.g. if it has been revealed or not, how
		// many neighbors it has, and so on. 

        
        canvas.setPenColor(Canvas.YELLOW.darker());
        canvas.raisedBevelRectangle(x, y, width, height);
        //if cell is pressed and not mine, show neighboring mine count and turn light gray.
        if(isRevealed == true) {
            canvas.setPenColor(Canvas.BOOK_BLUE);
            canvas.setFont( 20);
            canvas.sunkenBevelRectangle(x, y, width, height);
            //Different colors depending on neighborMinecount.
            if(neighborMineCount == 0)
                canvas.setPenColor(Canvas.BOOK_BLUE);
            if(neighborMineCount == 1)
                canvas.setPenColor(Canvas.GREEN.darker());
            if(neighborMineCount == 2)
                canvas.setPenColor(Canvas.YELLOW);
            if(neighborMineCount == 3)
                canvas.setPenColor(Canvas.ORANGE.darker());
            if(neighborMineCount >= 4)
                canvas.setPenColor(Canvas.RED);
           canvas.text (x + 10, y + 10, Integer.toString(getNeighborMineCount()));
        }
         if(flagged) {
            canvas.setPenColor(Canvas.PINK);
            canvas.filledRectangle(x, y, width, height);
        }

        //if cell is pressed and is a mine show mine and turn cell red.
        if(isRevealed == true && isMine == true) {
            if(firstPressed == true)
                canvas.setPenColor(Canvas.RED);
             else //reveal all the other mines.
            canvas.setPenColor(Canvas.YELLOW);
            canvas.sunkenBevelRectangle(x, y, width, height);
            canvas.picture(this.x, this.y, "sid.png", 20, 20);
             canvas.picture(550, 450, "found.png", 200,200);
        }
        
        
        return;
    }
}
