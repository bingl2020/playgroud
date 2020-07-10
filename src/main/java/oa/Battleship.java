package oa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Battleship {
    public static String solution(int N, String S, String T) {
        int sink = 0;
        int hit = 0;
        Set<String> hits = new HashSet<>(Arrays.asList(T.split(" ")));
        String[] ships = S.split(",");
        for (String ship : ships) {
            Set<String> shipComponents = new HashSet<>();
            String[] splitTopAndBottom = ship.split(" ");
            // rows can be from 1 to 26 , logic added to read both the digits if row is double digit
            int[] head = getCoordinates(splitTopAndBottom[0]);
            int[] tail = getCoordinates(splitTopAndBottom[1]);

            for (int i = head[0]; i <= tail[0]; i++) {
                for (int j = head[1]; j <= tail[1]; j++) {
                    shipComponents.add("" + i + (char) j);
                }
            }
            if (hits.containsAll(shipComponents)) {
                sink++;
            } else {
                for (String com : shipComponents) {
                    if (hits.contains(com)) {
                        hit++;
                        break;
                    }
                }
            }

        }

        return "" + sink + "," + hit;

    }

    private static int[] getCoordinates(String mark) {
        int row = (Character.isLetter(mark.charAt(1) ))
                ? Integer.parseInt(mark.charAt(0)+"")
                : Integer.parseInt(mark.charAt(0)+""+mark.charAt(1)+"");

        int col = (row > 9 ) ? mark.charAt(2) :  mark.charAt(1);

        return new int[]{row, col};
    }
    
    public static void main(String[] args) {
        //  String ans = solution(4,"1B 2C,2D 4D","2B 2D 3D 4D 4A");
        String ans = solution(12, "1A 2A,12A 12A", "12A");
        System.out.println(ans);

    }
}
