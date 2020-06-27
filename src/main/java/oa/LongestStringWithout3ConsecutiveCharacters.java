package oa;

import java.util.PriorityQueue;

//1405. Longest Happy String
public class LongestStringWithout3ConsecutiveCharacters {

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int size = a + b + c;
        int A = 0, B = 0, C = 0;
        for(int i = 0; i < size; i++) {
            if ((a >= b && a >= c && A != 2) || (B == 2 && a > 0) || (C == 2 && a > 0))  {
                sb.append("a");
                a--;
                A++;
                B = 0;
                C = 0;
            } else if ((b >= a && b >= c && B != 2) || (A == 2 && b > 0) || (C == 2 && b > 0)) {
                sb.append("b");
                b--;
                B++;
                A = 0;
                C = 0;
            } else if ((c >= a && c >= b && C != 2) || (B == 2 && c > 0) || (A == 2 && c > 0)) {
                sb.append("c");
                c--;
                C++;
                A = 0;
                B = 0;
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[0] - a[0]   : a[1] - b[1]);
        queue.offer(new int[]{0, 4});
        queue.offer(new int[]{1, 1});
        queue.offer(new int[]{2, 2});

      //  queue.peek()[1] = 4;

        queue.offer(new int[]{3, 3});

        new LongestStringWithout3ConsecutiveCharacters().longestDiverseString(10, 1, 2);
    }


}
