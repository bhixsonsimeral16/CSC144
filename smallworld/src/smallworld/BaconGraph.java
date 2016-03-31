/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld;

import edu.princeton.cs.In;

/**
 *
 * @author Brian
 */
public class BaconGraph extends Graph {
    
    // symbol table: key = string vertex, value = set of neighboring vertices
    private ST<String, SET<String>> st;
    
    // number of edges
    private int E;
    
    public BaconGraph(In in, String delimiter) {
        st = new ST<String, SET<String>>();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] names = line.split(delimiter);
            for (int i = 1; i < names.length; i++) {
                for (int j = i + 1; j < names.length; j++){
                    addEdge(names[i], names[j]);    
                }
            }
        }
    }
}
