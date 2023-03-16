import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraphicsClient extends Dijkstra {
    public static final int ROWS = 30;
    public static final int COLS = 40;
    public static final int SIZE = 20;
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Maze");
        Scanner console = new Scanner(System.in);
        System.out.println("Enter algorithm (b, a, or w): ");
        String input = console.next();
        if(input.equals("b")){
            BinaryTree b = new BinaryTree(ROWS, COLS);
            dijkstra((IMaze)b);
            JPanel p3 = new MazePanel(b, SIZE);
            frame.setResizable(false);
            frame.add(p3);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } else if(input.equals("a")){
            AldousBroder a = new AldousBroder(ROWS, COLS);
            dijkstra((IMaze)a);
            JPanel p3 = new MazePanel(a, SIZE);
            frame.setResizable(false);
            frame.add(p3);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } else if(input.equals("w")){
            Wilson w = new Wilson(ROWS, COLS);
            dijkstra((IMaze)w);
            JPanel p3 = new MazePanel(w, SIZE);
            frame.setResizable(false);
            frame.add(p3);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}