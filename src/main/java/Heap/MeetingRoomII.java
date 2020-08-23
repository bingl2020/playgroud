package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomII {
/*
Given a list of intervals representing the start and end time of N meetings,
find the minimum number of rooms required to hold all the meetings.
 */

/*  Thought Process
We need to allocate a different meeting room, if a meeting is clashing with the previous meeting.
if current meeting's start time is equal or greater than the previours meeting's end time. reuse the

1. sort all the meeting on the basis of the start time
2. use minHeap to track the end timing
3.

 */

    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;
        for (int[] itv : intervals) {
            if (heap.isEmpty()) {
                count++;
                heap.offer(itv[1]);
            } else {
                if (itv[0] >= heap.peek()) {
                    heap.poll();
                } else {
                    count++;
                }

                heap.offer(itv[1]);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] test  = new int[][] {{4,5}, {2,3}, {2,4}, {3,5}, {1, 4}, {2, 4}};

        minMeetingRooms(test);

    }





}
