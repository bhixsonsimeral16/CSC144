package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdIn;
import edu.princeton.cs.StdOut;
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
    public static void main(String[] args) {

        // read in data and initialize graph
        System.out.println(args[0]);
        In in = new In(args[0]);
        Graph G = new Graph(in, "/");
        StdOut.println("Done reading movies and building graph");

        // compute shortest path from s to every other vertex
        String s = "Bacon, Kevin";
        PathFinder finder = new PathFinder(G, s);
        StdOut.println("Done BFS");

        // process queries
        StdOut.println("Enter the name of an actor");
        while (!StdIn.isEmpty()) {
            String actor = StdIn.readLine();
            for (String v : finder.pathTo(actor))
                StdOut.println(v);
            StdOut.println();
        }
    }
}
