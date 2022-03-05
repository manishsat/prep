class CourseSchedule2 {
  
  /*
  There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
  You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
  For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
  Return the ordering of courses you should take to 
  finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
  */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //topological sort based on indegree of every node
        
        //first create adj list
        Map<Integer, List<Integer>> adjList = this.createAdjList(prerequisites);
        List<Integer> courseOrder = new ArrayList<Integer>();
        
        int[] inDegree = this.createInDegree(prerequisites, numCourses);
        Queue<Integer> processingQueue = new LinkedList<Integer>();
        
        for(int i=0 ; i< numCourses; i++) {
            if(inDegree[i] == 0) {
                //start BFS
                processingQueue.offer(i);
            }
        }
        
        
        while(!processingQueue.isEmpty()) {
            Integer course = processingQueue.poll();
            courseOrder.add(course);
            List<Integer> neighbors = adjList.get(course);
            
            if(neighbors != null) {
                for(Integer neighbor : neighbors) {                
                
                    inDegree[neighbor.intValue()] -= 1;
                
                    if(inDegree[neighbor.intValue()] == 0) {
                        processingQueue.offer(neighbor);
                    }                
                } 
            }   
        }
        
        return (courseOrder.size() == numCourses) ? courseOrder.stream().mapToInt(Integer::intValue).toArray() : new int[0];
    }
    
    private Map<Integer, List<Integer>> createAdjList(int[][] prerequisites) {
        
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        
        for(int i=0 ; i < prerequisites.length; i++) {
            
            int src = prerequisites[i][1];
            int dest = prerequisites[i][0];
            List<Integer> neighbors = adjList.getOrDefault(src, new ArrayList<Integer>());
            neighbors.add(dest);
            
            adjList.put(src, neighbors);
        }
        
        return adjList;
        
    }
    
    private int[] createInDegree(int[][] prerequisites, int numCourses) {
        int[] indegree = new int[numCourses];
        
        for(int i=0 ; i< prerequisites.length; i++) {
            int src = prerequisites[i][1];
            int dest = prerequisites[i][0];
            indegree[dest]++;
        }
        
        return indegree;
    }
  
  public static void main(Start[] arg) {
    int numCourses = 2;
    int prerequisites = {{1,0}};
    
    int numCourses1 = 4;
    int [][] prerequisites1 = {{1,0},{2,0},{3,1},{3,2}};
    CourseSchedule2 cs = new CourseSchedule2();
    System.out.println(cs.findOrder(numCourses1,prerequisites1 ));
  }
}
