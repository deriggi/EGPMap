/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jDeRiggi
 */
public class TAMISUtil {

    private static final Logger log = Logger.getLogger(TAMISUtil.class.getName());

    public static boolean hasIt(HashMap<String, String> data, String parameter) {
        if (data.containsKey(parameter)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(TAMISUtil.cleanAndParseFloat("$4,000.0"));
        System.out.println(TAMISUtil.cleanAndParseFloat(" % 4,00%0.#0_#"));
    }

    public static int cleanAndParseInt(String inprop) {
        if (inprop == null || inprop.length() == 0) {
            return 0;
        }

        String clean = inprop.replaceAll("[^0-9\\-]", "");

        try {
            return Integer.parseInt(clean);
        } catch (NumberFormatException nfe) {
            log.log(Level.WARNING, nfe.getMessage());
        }
        return 0;

    }

    public static float cleanAndParseFloat(String inprop) {
        if (inprop == null || inprop.length() == 0) {
            return 0;
        }

        String clean = inprop.replaceAll("[^0-9\\.\\-]", "");

        try {
            return Float.parseFloat(clean);
        } catch (NumberFormatException nfe) {
            log.log(Level.WARNING, nfe.getMessage());
        }
        return 0;

    }

    public static String cleanProperty(String inprop) {
        if (inprop == null) {
            return "_";
        }
        String clean = inprop.replaceAll("[\\#]", "n_");
//        clean = clean.replaceAll("[\\,]", "");
        clean = clean.replaceAll("[\\%]", "p_");
        clean = clean.replaceAll("\\s|[^0-9a-zA-Z_]", "").toLowerCase();

        return clean;
    }

    public static void printMap(HashMap<String, String> map) {
        Set<String> keys = map.keySet();
        for (String s : keys) {
            System.out.println(s + ": \t" + map.get(s));
        }
        System.out.println("=================");
        System.out.println("");
    }
}
