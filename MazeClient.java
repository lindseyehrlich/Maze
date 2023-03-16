public class MazeClient extends Dijkstra {
    public static final int ROWS = 5;
    public static final int COLS = 5;
    
    public static void main(String[] args){
        BinaryTree b = new BinaryTree(ROWS, COLS);
        dijkstra((IMaze)(b));
        System.out.println("Binary Tree Maze: ");
        System.out.println(b);
        Sidewinder s = new Sidewinder(ROWS, COLS);
        dijkstra((IMaze)(s));
        System.out.println("Sidewinder Maze: ");
        System.out.println(s);
        AldousBroder a = new AldousBroder(ROWS, COLS);
        dijkstra((IMaze)(a));
        System.out.println("AldousBroder Maze: ");
        System.out.println(a);
        //a.findLongestPath();
        Wilson w = new Wilson(ROWS, COLS);
        System.out.println("Wilson Maze: ");
        System.out.println(w);
    }
}