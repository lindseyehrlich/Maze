public class BinaryTree extends Maze {
    private int rows;
    private int cols;
    
    public BinaryTree(){
        super();
    }
    public BinaryTree(int rows, int cols){
        super(rows, cols);
    }
    public void generateMaze(){
        //String toReturn = "";
        ICell[][] maze = super.getMaze();
        int rowLength = maze.length;
        int colLength = maze[0].length;
        for(int row = 0; row < rowLength - 1; row++){
            for(int col = 0; col < colLength - 1; col++){
                if(Math.random() < 0.5){
                    super.createLink(maze[row][col], maze[row][col+1]);
                } else {
                    super.createLink(maze[row][col], maze[row+1][col]);
                }
            }
            super.createLink(maze[row][colLength-1], maze[row+1][colLength-1]);
        }
        for(int col = 0; col < maze[maze.length-1].length-1; col++){
            super.createLink(maze[maze.length-1][col], maze[maze.length-1][col+1]);
        }
    }
}