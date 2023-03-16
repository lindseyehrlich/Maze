import java.util.*;

public class Wilson extends Maze{
    public Wilson(){
        super();
    }
    public Wilson(int rows, int cols){
        super(rows, cols);
    }
    public void generateMaze(){
        getMaze()[0][0].setVisited();
        Stack<ICell> s = new Stack<ICell>();
        ICell random = getRandomCell();
        ICell next = null;
        while(!allVisited()){
            random.setGhost(true);
            s.push(random);
            int randomDir = (int)(Math.random()*4);
            while(random.getAdj()[randomDir] == null){
                randomDir = (int)(Math.random()*4);
            }
            next = random.getAdj()[randomDir];
            if(next.getGhost()){ //if next cell has been ghost visited
                ICell removed = random;
                while(!removed.equals(next)){
                    removed.setGhost(false);
                    removed = s.pop();
                }
                s.push(removed);
                random = removed;
            } else if(next.getVisited()){ //if next cell has been visited
                createLink(next, random);
                next.setVisited();
                next.setGhost(false);
                random.setGhost(false);
                ICell temp = null;
                while(!s.isEmpty()){
                    temp = s.pop();
                    temp.setGhost(false);
                    createLink(random, temp);
                    random.setVisited();
                    random = temp;
                }
                temp.setVisited();
                random = getRandomCell();
                if(!allVisited()){
                    while(random.getVisited()){
                        random = getRandomCell();
                    }
                }
            } else { //if next cell hasn't been touched
                random = next;
            }
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