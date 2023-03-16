public class Cell implements ICell {
    private boolean visited;
    private boolean ghostVisited;
    private boolean[] open;
    private ICell[] adj;
    
    public Cell(){
        visited = false;
        ghostVisited = false;
        open = new boolean[4];
        distance_from_start = -1;
    }
    //Letting the generated know if a cell had been visited or not
    public void setVisited(){
        visited = true;
    }
    public boolean getVisited(){
        return visited;
    }
    //temp visited getter/setter for Wilson algorithm
    public void setGhost(boolean val){
        ghostVisited = val;
    }
    public boolean getGhost(){
        return ghostVisited;
    }
    //changes a direction from a barrier (default) to a non barrier
    public void setOpen(Direction d){
        if(d==d.NORTH){
            open[0] = true;
        } else if(d == d.EAST){
            open[1] = true;
        } else if(d == d.SOUTH){
            open[2] = true;
        } else if(d == d.WEST){
            open[3] = true;
        }
    }
    //checking if a direction is a barrier or not
    public boolean canMove(Direction d){
        int dir;
        if(d == d.NORTH){
            dir = 0;
        } else if(d == d.EAST){
            dir = 1;
        } else if(d == d.SOUTH){
            dir = 2;
        } else {
            dir = 3;
        }
        if(this.getOpenArray()[dir]){
            return true;
        }
        return false;
    }
    //get an array of booleans indicating if the ordinal directions are open
    //NESW is what is intended
    public boolean[] getOpenArray(){
        return open;
    }
    //Sets the adjacent cells.  
    //NESW is what is intended
    public void setAdj(ICell[] adj){
        this.adj = adj;
    }
    public ICell[] getAdj(){
        return adj;
    }
    //For Dijkstra's Algorithm
    private int distance_from_start;
    public void setDistance(int d){
        distance_from_start = d;
    }
    public int getDistance(){
        return distance_from_start;
    }
}