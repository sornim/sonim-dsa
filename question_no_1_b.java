import java.util.*;

public class NetworkDeviceFailure {
    
    public static List<Integer> getImpactedDevices(int[][] edges, int targetDevice) {
        // Create an adjacency list to represent the graph
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList.putIfAbsent(from, new ArrayList<>());
            adjList.putIfAbsent(to, new ArrayList<>());
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        // Perform a depth-first search to find the impacted devices
        List<Integer> impactedDevices = new ArrayList<>();
        boolean[] visited = new boolean[adjList.size()];
        dfs(adjList, targetDevice, visited, impactedDevices);
        
        return impactedDevices;
    }
    
    private static void dfs(Map<Integer, List<Integer>> adjList, int current, boolean[] visited, List<Integer> impactedDevices) {
        visited[current] = true;
        for (int neighbor : adjList.get(current)) {
            if (!visited[neighbor] && neighbor != targetDevice) {
                dfs(adjList, neighbor, visited, impactedDevices);
            }
            else if (visited[neighbor] && neighbor != targetDevice) {
                impactedDevices.add(neighbor);
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        int targetDevice = 4;
        List<Integer> impactedDevices = getImpactedDevices(edges, targetDevice);
        System.out.println(impactedDevices);
    }
}
