package oa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDays {

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> seen = new HashMap<>();
        while (N > 0) {
            int[] cells2 = new int[8];
            seen.put(Arrays.hashCode(cells), N--);
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
            if (seen.containsKey(Arrays.hashCode(cells))) {
                N %= seen.get(Arrays.hashCode(cells)) - N;
            }
        }
        return cells;
    }

    public static void main(String[] args) {
        int[] test = new PrisonCellsAfterNDays().prisonAfterNDays(new int[]{0,1,0,1,1,0,0,1}, 100);
        //[0, 0, 0, 0, 1, 1, 1, 0]
        System.out.println(Arrays.toString(test));
    }
}
