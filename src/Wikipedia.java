import java.io.*;
import java.util.*;
// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author mayakkrla
 *  @version 10 Jun 2026
 */
public class Wikipedia
{
 // page ID -> title
    private HashMap<Integer, String> titles = new HashMap<>();

    // page ID -> list of linked page IDs
    private HashMap<Integer, ArrayList<Integer>> links = new HashMap<>();

    // ----------------------------------------------------------
    /**
     * Create a new Wikipedia object.
     * @param pagesFile
     * @param linksFile
     * @throws IOException
     */
    public Wikipedia(String pagesFile, String linksFile) throws IOException
    {
        // Read pages.txt
        BufferedReader br = new BufferedReader(new FileReader(pagesFile));

        String line;

        while ((line = br.readLine()) != null)
        {
            String[] parts = line.split(" ", 2);

            int id = Integer.parseInt(parts[0]);
            String title = parts[1];

            titles.put(id, title);
            links.put(id, new ArrayList<>());
        }

        br.close();

        System.out.println("Finished reading " + pagesFile);

        // Read links.txt
        br = new BufferedReader(new FileReader(linksFile));

        while ((line = br.readLine()) != null)
        {
            String[] parts = line.split(" ");

            int src = Integer.parseInt(parts[0]);
            int dst = Integer.parseInt(parts[1]);

            links.get(src).add(dst);
        }

        br.close();

        System.out.println("Finished reading " + linksFile);
        System.out.println();
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void findLongestTitles()
    {
        ArrayList<String> titleList =
                new ArrayList<>(titles.values());

        titleList.sort(
                (a, b) -> Integer.compare(b.length(), a.length()));

        System.out.println("The longest titles are:");

        int count = 0;

        for (String title : titleList)
        {
            if (!title.contains("_"))
            {
                System.out.println(title);
                count++;

                if (count == 15)
                {
                    break;
                }
            }
        }
        System.out.println();
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void findMostLinkedPages()
    {
        HashMap<Integer, Integer> linkCount = new HashMap<>();

        for (int id : titles.keySet())
        {
            linkCount.put(id, 0);
        }

        for (int id : titles.keySet())
        {
            for (int dst : links.get(id))
            {
                linkCount.put(dst,
                        linkCount.get(dst) + 1);
            }
        }

        int max = Collections.max(linkCount.values());

        System.out.println("The most linked pages are:");

        for (int id : linkCount.keySet())
        {
            if (linkCount.get(id) == max)
            {
                System.out.println(
                        titles.get(id) + " " + max);
            }
        }

        System.out.println();
    }

    // Homework #1
    // ----------------------------------------------------------
    /**
     * Finds ths shortest path between two pages and stores it.
     * @param start Title of starting page
     * @param goal Title of ending page
     */
    public void findShortestPath(String start, String goal)
    {
        int startId = -1;
        int goalId = -1;
        for (int id : titles.keySet()){
            if (titles.get(id).equals(start)){
                startId = id;
            }

            if (titles.get(id).equals(goal)){
                goalId = id;
            }
        }
        if (startId == -1 || goalId == -1){
            System.out.println("Page not found.");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> parent = new HashMap<>();
        queue.offer(startId);
        visited.add(startId);
        while (!queue.isEmpty()){
            int current = queue.poll();
            if (current == goalId){
                break;
            }
            for (int next : links.get(current)){
                if (!visited.contains(next)){
                    visited.add(next);
                    parent.put(next, current);
                    queue.offer(next);
                }
            }
        }
        if (!visited.contains(goalId)){
            System.out.println("No path found.");
            return;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int current = goalId;
        while (current != startId){
            path.addFirst(current);
            current = parent.get(current);
        }
        path.addFirst(startId);
        System.out.println("Shortest path:");
        for (int id : path){
            System.out.println(titles.get(id));
        }
    }

    // Homework #2
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void findMostPopularPages()
    {
        HashMap<Integer, Double> rank = new HashMap<>();
        for (int id : titles.keySet()) {
            rank.put(id, 1.0);
        }
        while (true) {
            HashMap<Integer, Double> newRank = new HashMap<>();
            for (int id : titles.keySet()) {
                newRank.put(id, 0.0);
            }
            for (int id : titles.keySet()) {
                ArrayList<Integer> neighbors = links.get(id);
                if (neighbors.size() == 0) {
                    double share = rank.get(id) / titles.size();
                    for (int page : titles.keySet()) {
                        newRank.put(page, newRank.get(page) + share);
                    }
                } else {
                    double share = rank.get(id) / neighbors.size();
                    for (int dst : neighbors) {
                        newRank.put(dst, newRank.get(dst) + share);
                    }
                }
            }
            double diff = 0.0;
            for (int id : titles.keySet()) {
                double d = newRank.get(id) - rank.get(id);
                diff += d * d;
            }
            rank = newRank;
            if (diff < 0.01) {
                break;
            }
        }
        double total = 0.0;
        for (double value : rank.values()) {
            total += value;
        }
        System.out.println("Total PageRank = " + total);
        ArrayList<Integer> ids = new ArrayList<>(titles.keySet());
        final HashMap<Integer, Double> finalRank = rank;
        ids.sort((a, b) -> Double.compare(finalRank.get(b), finalRank.get(a)));
        System.out.println("Top 10 pages:");
        for (int i = 0; i < 10 && i < ids.size(); i++) {
            int id = ids.get(i);
            System.out.println((i + 1) + ". " + titles.get(id) + " : " 
            + finalRank.get(id));
        }
    }

    // Homework #3
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param start
     * @param goal
     */
    public void findLongestPath(String start, String goal)
    {
        // TODO
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        if (args.length != 2)
        {
            System.out.println(
                    "Usage: java Wikipedia pages.txt links.txt");
            return;
        }

        Wikipedia wikipedia =
                new Wikipedia(args[0], args[1]);

        wikipedia.findLongestTitles();
        wikipedia.findMostLinkedPages();

        wikipedia.findShortestPath(
                "渋谷",
                "パレートの法則");
    }
}
