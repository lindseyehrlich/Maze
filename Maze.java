import java.util.*;

public abstract class Maze implements IMaze{
    private ICell[][] maze;
    private int width;
    private int height;
    
    //all code methods from IMaze go here
    //Returns the maze as an Array of Cells
    public Maze(){
        this(5, 5);
    }
    
    public Maze(int rows, int cols){
        width = cols;
        height = rows;
        maze = new ICell[rows][cols];
        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[row].length; col++){
                maze[row][col] = new Cell();
            }
        }
        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[row].length; col++){
                ICell[] cells = new ICell[4];
                if(row > 0){
                    cells[0] = maze[row-1][col];
                }
                if(col < maze[row].length-1){
                    cells[1] = maze[row][col+1];
                }
                if(row < maze.length-1){
                    cells[2] = maze[row+1][col];
                }
                if(col > 0){
                    cells[3] = maze[row][col-1];
                }
                maze[row][col].setAdj(cells);
            }
        }
        generateMaze();
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public ICell[][] getMaze(){
        return maze;
    }
    //Code for the maze generating algorithm 
    public void generateMaze(int row, int col){
        maze = new ICell[row][col];
    }
    //Returns a specific cell in the maze
    public ICell getCell(int row, int col){
        return maze[row][col];
    }
    public ICell getCell(int[] coords){
        return getCell(coords[0], coords[1]);
    }
    //Creates a link between two cells in the maze| Must be two adjacent cells
    public void createLink(ICell from, ICell to) throws IllegalArgumentException{
        ICell[] fromAdj = from.getAdj();
        for(int i = 0; i < fromAdj.length; i++){
            if(fromAdj[i] == to){
                from.setOpen(intToDir(i));
                to.setOpen(intToDir((i+2)%4));
            }
        }
    }
    //Returns all cells adjacent (intention is NESW)
    public ICell[] getAdjacent(ICell cell){
        ICell[] adj = new ICell[4];
        if(getRow(cell) > 0){
            adj[0] = maze[getRow(cell)-1][getCol(cell)];
        }
        if(getCol(cell) < maze[0].length-1){
            adj[1] = maze[getRow(cell)][getCol(cell)+1];
        }
        if(getCol(cell) < maze.length-1){
            adj[2] = maze[getRow(cell)+1][getCol(cell)];
        }
        if(getCol(cell) > 0){
            adj[3] = maze[getRow(cell)][getCol(cell)-1];
        }
        return adj;
    }
    //Helper method for the generate maze to ensure all ICells are linked
    //NESW is what I used to keep everything organized
    public boolean allVisited(){
        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[row].length; col++){
                if(!maze[row][col].getVisited()){
                    return false;
                }
            }
        }
        return true;
    }
    //returns a random ICell 
    public ICell getRandomCell(){
        int row = (int) (Math.random() * maze.length);
        int col = (int) (Math.random() * maze[row].length);
        return maze[row][col];
    }
    //returns coordinates of the cell passed in {row, col} format
    public int[] getCoords(ICell cell){
        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[row].length; col++){
                if(cell == maze[row][col]){
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }
    //returns the row of the passed cell
    public int getRow(ICell cell){
        return getCoords(cell)[0];
    }
    //returns the column of the passed cell
    public int getCol(ICell cell){
        return getCoords(cell)[1];
    }
    
    private Direction intToDir(int n){
        return n == 0 ? Direction.NORTH : n == 1 ? Direction.EAST : n == 2 ? Direction.SOUTH : Direction.WEST;
    }
    /*public void findLongestPath(){
        ICell start = maze[0][0];
        ICell end = maze[getHeight()][getWidth()];
        List<ICell> path = findLongestPath(maze, start, end);
    }
    private List<ICell> findLongestPath(ICell[][] maze, ICell start, ICell end){
        List<ICell> result = null;
        boolean[] open = maze[getRow(start)][getCol(start)].getOpenArray();
        ICell[] adj = maze[getRow(start)][getCol(start)].getAdj();
        int rows = maze.length;
        int cols = maze[0].length;
        if(getRow(start) < 0 || getCol(start) < 0){
            return null;
        }
        if(getRow(start) == rows || getCol(start) == cols){
            return null;
        }
        for(int i = 0; i < 4; i++){
            if(!open[i]){
                return null;
            } else {
                path.add(start);
                
            }
            if(start.equals(end)){
                List<ICell> path = new ArrayList<ICell>();
                path.add(start);
                return path;
            }
        }
        
        return result;
    }*/
    public String toString(){
        String toReturn = "+";
        //ICell[][] maze = getMaze();
        for(int i = 0; i < maze[0].length; i++){
            toReturn += "---+";
        }
        toReturn += "\n";
        for(int row = 0; row < maze.length; row++){
            toReturn += "|";
            for(int col = 0; col < maze[row].length; col++){
                int num = maze[row][col].getDistance();
                if(!maze[row][col].getOpenArray()[1]){
                    if(num >= 10){
                        toReturn += maze[row][col].getDistance() + " |";                        
                    } else {
                        toReturn += " " + maze[row][col].getDistance() + " |";
                    }
                } else {
                    if(num >= 10){
                        toReturn += maze[row][col].getDistance() + "  ";                       
                    } else {
                        toReturn += " " + maze[row][col].getDistance() + "  ";
                    }
                }
            }
            toReturn += "\n+";
            for(int col = 0; col < maze[row].length; col++){
                if(row == maze.length-1){
                    for(int i = 0; i < maze[row].length; i++){
                        toReturn += "---+";
                    }
                    break;
                }
                if(maze[row][col].getOpenArray()[2]){
                    toReturn += "   +";
                } else {
                    toReturn += "---+";
                }
            }
            toReturn += "\n";
        }
        return toReturn;
    }
    //All Children of Maze must implement the generateMaze() method.      
    public abstract void generateMaze();
}