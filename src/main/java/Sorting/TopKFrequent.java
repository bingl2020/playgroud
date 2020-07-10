package Sorting;

import java.util.*;

/*
        Given an array of integers, write a method to return the k most frequent elements.
Solution:
bucketSort o(n)
heap  nlogk

*/
public class TopKFrequent {

    public List<Integer> topKFrequent_Heap(int[] nums, int k) {
        Map<Integer, Integer> freqMap = getFrequencyMap(nums);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

        for (Integer num : freqMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }

        Collections.reverse(res);
        return res;
    }

    public List<Integer> topKFrequent_BucketSort(int[] nums, int k) {

        Map<Integer, Integer> freqMap = getFrequencyMap(nums);
        List<Integer>[] buckets = new List[nums.length + 1];

        for (Integer num : freqMap.keySet()) {
            int frequency = freqMap.get(num);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(num);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = buckets.length - 1; i >= 0;  i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    if (k-- > 0) {
                        result.add(num);
                    }
                }
            }
        }
        return result;
    }

    private Map<Integer, Integer> getFrequencyMap(int[] nums) {

        Map<Integer, Integer> freqMap = new HashMap<>();

        if (nums == null) {
            return freqMap;
        }

        for (int i : nums) {
            freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
        }

        return freqMap;
    }

    public static void main(String[] args) {
        TopKFrequent test = new TopKFrequent();
        int a[] = {1, 2, 4, 2, 2, 5, 1, 4, 2, 1, 4, 2, 7, 7};

        System.out.println(test.topKFrequent_BucketSort(a, 3));
        System.out.println(test.topKFrequent_Heap(a, 3));
    }

}