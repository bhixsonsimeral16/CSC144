package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;
import smallworld.SET;

/******************************************************************************
 *  Compilation:  javac MovieStats.java
 *  Execution:    java MovieStats data.txt 
 *  Dependencies: Graph.java In.java StdOut.java
 *
 *  Computes number of movies, actors, and edges in a given data file.
 *  
 ******************************************************************************/

public class MovieStats {

    public static void main(String[] args) {

        // set of actors
        SET<String> actors = new SET<String>();
        int movies = 0;
        int edges = 0;

        // read in data and initialize graph
        In data = new In(args[0]);
        while (data.hasNextLine()) {
            String line = data.readLine();
            String[] names = line.split("/");
            String movie = names[0];
            movies++;
            edges += names.length - 1;
            for (int i = 1; i < names.length; i++)
                actors.add(names[i]);
        }
        StdOut.println(movies + " movies");
        StdOut.println(actors.size() + " actors");
        StdOut.println(edges  + " edges");
    }
}
