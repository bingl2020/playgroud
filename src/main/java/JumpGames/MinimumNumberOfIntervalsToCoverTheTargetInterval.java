package JumpGames;


import java.util.Collections;
import java.util.List;

/*

choose from a list of intervals, to make full coverage of target interval with minimum selection. If cannot cover, return -1.
for example: [[3,6],[4,5],[7,10],[6,9],[7,12],[12,17],[10,13],[18,22],[16,18]]; target is [7, 22]; should return 3;

 */
public class MinimumNumberOfIntervalsToCoverTheTargetInterval {

//[[3,6],[4,5],[7,10],[6,9],[7,12],[12,17],[10,13],[18,22],[16,18]]; target is [7, 22];

    public static int leastMerge(List<Interval> list, Interval interval) {
        Collections.sort(list, (o1, o2) -> o1.start - o2.start);

        int i = 0;
        int start = interval.start;
        int count = 0;
        while (i < list.size()) {
            //special case, no way to cover
            if (list.get(i).start > start) {
                break;
            }
            int furthest = Integer.MIN_VALUE;
            //1. find eligible intervals
            while (i < list.size() && list.get(i).start <= start) {
                if (list.get(i).end > start) { //eligible
                    //2. choose the one that has the furthest range
                    if (list.get(i).end > furthest) {
                        furthest = list.get(i).end;
                    }
                }
                i++;
            }
            count++;

            if (furthest >= interval.end) return count;
            //3. update start to furthest range
            start = furthest;
        }
        return 0;


    }


}
