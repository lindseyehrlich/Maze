import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MazePanel extends JPanel implements KeyListener{
    private IMaze maze;
    private int size;
    
    //for navigator
    private int dotRow;
    private int dotCol;
    
    public MazePanel(IMaze maze, int size){
        this.maze = maze;
        setPreferredSize(new Dimension(size*maze.getMaze()[0].length, size*maze.getMaze().length));
        this.size = size;
        
        addKeyListener(this);
        setFocusable(true);
        
        dotRow = 0;
        dotCol = 0;
    }
    public void paintComponent(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        ICell[][] cells = maze.getMaze();
        for(int row = 0; row < cells.length; row++){
            for(int col = 0; col < cells[row].length; col++){
                Color c = new Color(0, 255-(maze.getCell(row, col).getDistance()), 0);
                g.setColor(c);
                g.fillRect(col*size, row*size, size, size);
                g.setColor(Color.BLACK);
                boolean[] open = cells[row][col].getOpenArray();
                if(!open[0]){
                    g.drawLine(col*size, row*size, (col+1)*size, row*size);
                }
                if(!open[1]){
                    g.drawLine((col+1)*size, row*size, (col+1)*size, (row+1)*size);
                }
                if(!open[2]){
                    g.drawLine(col*size, (row+1)*size, (col+1)*size, (row+1)*size);
                }
                if(!open[3]){
                    g.drawLine(col*size, row*size, col*size, (row+1)*size);
                }
            }
        }
        g.setColor(Color.BLUE);
        g.fillOval(dotCol*size, dotRow*size, size, size);
    }
    public void move(Direction d){
        ICell current = maze.getCell(dotRow, dotCol);
        if(current.canMove(d)){
            int dRow = d == Direction.NORTH ? -1 : d == Direction.SOUTH ? 1 : 0;
            int dCol = d == Direction.WEST ? -1 : d == Direction.EAST ? 1 : 0;
            dotRow += dRow;
            dotCol += dCol;
            repaint();
        }
    }
    //For KeyListener
    public void keyPressed(KeyEvent ke){
        if(ke.getKeyCode() == KeyEvent.VK_UP){
            move(Direction.NORTH);
        }
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            move(Direction.EAST);
        }
        if(ke.getKeyCode() == KeyEvent.VK_DOWN){
            move(Direction.SOUTH);
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT){
            move(Direction.WEST);
        }
    }
    public void keyReleased(KeyEvent ke){}
    public void keyTyped(KeyEvent ke){}
}