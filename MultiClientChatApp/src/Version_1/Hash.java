/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Version_1;

import java.io.PrintWriter;
import java.util.HashSet;

/**
 *
 * @author mickeyMice
 */
public class Hash extends Thread {

    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public boolean checkHash(String input) {
        boolean temp;
        while (true) {
            if (input != null) {
                synchronized (names) {
                    if (!names.contains(input)) {
                        names.add(input);
                        temp = true;
                    } else {
                        temp = false;
                    }
                }
            } else {
                temp = false;
            }
            return temp;
        }

    }
}
