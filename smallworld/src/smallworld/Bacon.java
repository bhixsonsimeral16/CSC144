package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdIn;
import edu.princeton.cs.StdOut;
import java.util.ArrayList;
import java.util.Objects;
import smallworld.Graph;
import smallworld.PathFinder;

/******************************************************************************
 *  Compilation:  javac Bacon.java
 *  Execution:    java Bacon input.txt
 *  Dependencies: Graph.java In.java PathFinder.java
 *  
 *  Reads in a data file containing movie records (a movie followed by a list 
 *  of actors appearing in that movie), and runs breadth first search to
 *  find the shortest distance from the source (Kevin Bacon) to each other
 *  actor and movie. After computing the Kevin Bacon numbers, the user is prompted
 *  to enter actors and the program prints out the shortest chain.
 * 
 *  % java Bacon ../data/movies-top-grossing.txt
 *
 ******************************************************************************/


public class Bacon {
    
    
//    public static ArrayList<String> sort(BaconGraph G, ArrayList<String> baconList, ArrayList<String> usedNames){
//        //List of people with a specific Bacon number (adjacent to a bacon number list)
//        ArrayList<String> baconNumber = new ArrayList<>();
//        
//        for(String v : baconList){
//            ArrayList<String> temp = makeArrayList(G.adjacentTo(v));   //stores the list of 
//            
//            for (String w : temp) {
//                //if the usedNames list contains the Name, then we do not add it to the list
//                if(!usedNames.contains(w)){
//                    usedNames.add(w);
//                    baconNumber.add(w);
//                }
//            }
//        }
//        return baconNumber;
//    }
    /**
     * Taken from http://stackoverflow.com/questions/6416706/easy-way-to-change-iterable-into-collection
     * @param iter take in an Iterable
     * @return an ArrayList
     */
    public static ArrayList<String> makeArrayList(Iterable<String> iter) {
        ArrayList<String> list = new ArrayList<String>();
        for (String item : iter) {
            list.add(item);
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        
        // read in data and initialize graph
        In data = new In(args[0]);
        Graph G = new Graph(data, "/");
        StdOut.println("Done reading movies and building graph");

        // run breadth first search
        String s = "Bacon, Kevin";
        PathFinder finder = new PathFinder(G, s);
        StdOut.println("Done BFS");

        // compile lists of Kevin Bacon numbers - 100 for infinity
        int MAX_BACON = 100;
        ArrayList<String> bacon0 = new ArrayList<>();
        ArrayList<String> bacon1 = new ArrayList<>();
        ArrayList<String> bacon2 = new ArrayList<>();
        ArrayList<String> bacon3 = new ArrayList<>();
        ArrayList<String> bacon4 = new ArrayList<>();
        ArrayList<String> bacon5 = new ArrayList<>();
        ArrayList<String> bacon6 = new ArrayList<>();
        ArrayList<String> bacon7 = new ArrayList<>();
        ArrayList<String> bacon8 = new ArrayList<>();
        
        for (String actor : G.vertices()) {
            if (finder.distanceTo(actor) % 2 != 0) continue;  // it's a movie vertex

            int bacon = Math.min(MAX_BACON, finder.distanceTo(actor) / 2);
            if(bacon == 0){
                bacon0.add(actor);
            }
            else if(bacon == 1){
                bacon1.add(actor);
            }
            else if(bacon == 2){
                bacon2.add(actor);
            }
            else if(bacon == 3){
                bacon3.add(actor);
            }
            else if(bacon == 4){
                bacon4.add(actor);
            }
            else if(bacon == 5){
                bacon5.add(actor);
            }
            else if(bacon == 6){
                bacon6.add(actor);
            }
            else if(bacon == 7){
                bacon7.add(actor);
            }
            else if(bacon == 8){
                bacon8.add(actor);
            }
        }
        System.out.println("Actors with a Bacon Number of 5\n");
        for(String v : bacon5){
            System.out.println(v);
        }
            
            
            
            
            

//        // read in data and initialize graph
//        System.out.println(args[0]);
//        In in = new In(args[0]);
//        BaconGraph G = new BaconGraph(in, "/");
//        StdOut.println("Done reading movies and building graph");
//
//        // compute shortest path from s to every other vertex
//        String s = "Bacon, Kevin";
//        PathFinder finder = new PathFinder(G, s);
//        StdOut.println("Done BFS");
//
//        // process queries
//        StdOut.println("Enter the name of an actor");
//        while (!StdIn.isEmpty()) {
//            String actor = StdIn.readLine();
//            for (String v : finder.pathTo(actor))
//                StdOut.println(v);
//            StdOut.println();
//        }
        
//        ArrayList<String> usedNames = new ArrayList<>();
//        usedNames.add("Bacon, Kevin");
//        
//        ArrayList<String> adjacent1 = makeArrayList(G.adjacentTo("Bacon, Kevin"));
        
//        ArrayList firstBacon = (ArrayList<String>) G.adjacentTo("Bacon, Kevin");
//        
//        ArrayList<String> bacon1 = sort(G, adjacent1, usedNames);
//        ArrayList<String> bacon2 = sort(G, bacon1, usedNames);
//        ArrayList<String> bacon3 = sort(G, bacon2, usedNames);
//        ArrayList<String> bacon4 = sort(G, bacon3, usedNames);
//        ArrayList<String> bacon5 = sort(G, bacon4, usedNames);
//        System.out.println("Actors with a Bacon Number of 5\n");
//        for(String s : bacon5){
//            System.out.println(s);
//        }
    }
}
