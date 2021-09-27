package oca.test;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class GraphShortestPath {

    static class Graph{
        List<List<Integer>> adjList = new ArrayList<>();//this is adjacency list
        int size=0;
        public Graph(int size){
            adjList = new ArrayList<>();
            this.size = size;
            IntStream.range(0,size+1).forEach(i ->{
                adjList.add(new ArrayList<>());
            });
        }

        public void addEdge(int start , int end){
            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }

        public int[] shortestReachDFS(int start){
            int[] shortestDist = new int[size];
            Arrays.fill(shortestDist,-1);
            HashSet<Integer> visited = new HashSet<>();
            shortestDist[start-1]=0;
            visited.add(start);

            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(start);
            while (!queue.isEmpty()){
                int currNode = queue.poll();
                visited.add(currNode);
                adjList.get(currNode).stream().forEach(i -> {
                    if(!visited.contains(i)) {
                        queue.offer(i);
                        shortestDist[i-1]=shortestDist[currNode-1]+6;
                    }
                });
            }
            return shortestDist;

        }
    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner s = new Scanner(System.in);
        int numberOfQueries = Integer.parseInt(s.nextLine());
        int noOfEdges=0;
        int noOfNodes=0;

       List<Graph> list = new ArrayList<>();
        while(numberOfQueries > 0){
            numberOfQueries --;
            String[] input = s.nextLine().split(" ");
            noOfNodes = Integer.parseInt(input[0]);
            noOfEdges = Integer.parseInt(input[1]);Graph graph=new Graph(noOfNodes);;
            List<List<Integer>> edges = new ArrayList();
            IntStream.range(0,noOfEdges).forEach(i ->{
                String[] input1 = s.nextLine().split(" ");
                int parentNode = Integer.parseInt(input1[0]);
                int childNode = Integer.parseInt(input1[1]);
                graph.addEdge(parentNode,childNode);
            });
            int startingNode = Integer.parseInt(s.nextLine());
            int[] res=        graph.shortestReachDFS(startingNode);
            Arrays.stream(res).forEach(i->{
                if(i != 0){
                    System.out.print(i+" ");
                }
            });
            System.out.print("\n");


        }



    }



}

