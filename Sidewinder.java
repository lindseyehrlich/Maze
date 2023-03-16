import java.util.*;

public class Sidewinder extends Maze{
    private int rows;
    private int cols;
    
    public Sidewinder(){
        super();
    }
    public Sidewinder(int rows, int cols){
        super(rows, cols);
    }
    public void generateMaze(){
        ArrayList<ICell> remember = new ArrayList<ICell>();
        String toReturn = "";
        ICell[][] maze = super.getMaze();
        int rowLength = maze.length;
        int colLength = maze[0].length;
        for(int row = 0; row < rowLength - 1; row++){
            for(int col = 0; col < colLength - 1; col++){
                if(Math.random() < 0.5){
                    super.createLink(maze[row][col], maze[row][col+1]);
                    remember.add(maze[row][col]);
                } else {
                    int num = (int) (Math.random() * remember.size());
                    if(remember.size() == 0){
                        super.createLink(maze[row][col], maze[row+1][col]);
                    } else {
                        super.createLink(remember.get(num), maze[getRow(remember.get(num))+1][getCol(remember.get(num))]);
                    }
                    remember = new ArrayList<ICell>();
                }
            }
            int num = (int) (Math.random() * remember.size());
            if(remember.size() == 0){
                super.createLink(maze[row][maze[row].length-1], maze[row+1][maze[row].length-1]);
            } else {
                super.createLink(remember.get(num), maze[getRow(remember.get(num))+1][getCol(remember.get(num))]);
            }
        }
        for(int col = 0; col < maze[maze.length-1].length-1; col++){
            super.createLink(maze[maze.length-1][col], maze[maze.length-1][col+1]);
        }
    }
    public String toString(){
        String toReturn = "+";
        ICell[][] maze = super.getMaze();
        for(int i = 0; i < maze[0].length; i++){
            toReturn += "---+";
        }
        toReturn += "\n";
        for(int row = 0; row < maze.length; row++){
            toReturn += "|";
            for(int col = 0; col < maze[row].length; col++){
                if(!maze[row][col].getOpenArray()[1]){
                    toReturn += "   |";
                } else {
                    toReturn += "    ";
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
}