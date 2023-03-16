public class Dijkstra {
    public static void dijkstra(IMaze maze){
        dijkstra(maze, maze.getMaze()[0][0]);
    }
    public static void dijkstra(IMaze maze, ICell start){
        ((Cell) start).setDistance(0);
        ICell[] adj = start.getAdj();
        for(int i = 0; i < 4; i++){
            dijkstra(adj[i], 1, start.getOpenArray()[i]);
        }
    }
    public static void dijkstra(ICell current, int level, boolean open){
        if(current == null || !open || current.getDistance() > -1){
            return;
        } else {
            ICell[] adj = current.getAdj();
            for(int i = 0; i < 4; i++){
                if(open){
                    current.setDistance(level);
                    dijkstra(adj[i], level+1, current.getOpenArray()[i]);
                }
            }
        }
    }
}