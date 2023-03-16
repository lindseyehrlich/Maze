import java.util.*;

public class AldousBroder extends Maze {
    private int rows;
    private int cols;
    
    public AldousBroder(){
        super();
    }
    public AldousBroder(int rows, int cols){
        super(rows, cols);
    }
    public void generateMaze(){
        ArrayList<ICell> visited = new ArrayList<ICell>();
        ICell randCell = this.getRandomCell();
        randCell.setVisited();
        visited.add(randCell);
        while(visited.size() < this.getWidth()*this.getHeight()){
            int randDir = (int) (Math.random()*4);
            ICell to = randCell.getAdj()[randDir];
            while(to == null){
                randDir = (int) (Math.random()*4);
                to = randCell.getAdj()[randDir];
            }
            if(!to.getVisited()){
                super.createLink(randCell, to);
                to.setVisited();
                visited.add(to);
                randCell = to;
            } else {
                randCell = to;
            }
        }
    }
}