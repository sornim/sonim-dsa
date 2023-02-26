import java.util.*;

public class ServiceCenters {

    public static int countServiceCenters(int[] parent) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            if (!graph.containsKey(parent[i])) {
                graph.put(parent[i], new ArrayList<>());
            }
            graph.get(parent[i]).add(i);
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                boolean hasServiceCenter = false;
                for (int child : graph.getOrDefault(node, new ArrayList<>())) {
                    if (hasServiceCenter) {
                        count++;
                        break;
                    } else {
                        if (graph.containsKey(child)) {
                            queue.offer(child);
                        }
                        hasServiceCenter = true;
                    }
                }
                if (!hasServiceCenter) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        int count = countServiceCenters(parent);
        System.out.println(count); // 2
    }
}
