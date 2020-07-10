package bucketSort;

import java.util.*;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        // calculate frequency of each word
        Map<String, Integer> freqMap = new HashMap<>();
        for(String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        List<String> list = new ArrayList<>();

        // build buckets
        TrieNode[] buckets = new TrieNode[words.length + 1];

        for (String key : freqMap.keySet()) {
            addWordIntoBuckets(key, buckets);
        }

        for (int i = buckets.length - 1; i >=0; i--) {
            if (buckets[i] != null) {
               List<String> wordsInBucket = getWordsIntBuckets(buckets, i);
               for (String word : wordsInBucket) {
                   list.add(word);
                   if (list.size() == k) {
                       return list;
                   }
               }
            }
        }
        return list;
    }

    private List<String> getWordsIntBuckets(TrieNode[] tries, int index) {
return null;
    }
    private void addWordIntoBuckets(String word, TrieNode[] buckets) {
        char[] chars = word.toCharArray();
        if (buckets[chars[0] - 'a'] == null) {
            buckets[chars[0] - 'a'] = new TrieNode();
        }
        TrieNode curr = buckets[chars[0] - 'a'];
        for (char c : chars) {
            if (curr.next[c - 'a'] == null) {
                curr.next[c - 'a'] = new TrieNode();
            }
            curr = curr.next[c - 'a'];
        }
        // reach to the end
        curr.word = word;
    }

    private void getWords(TrieNode node, List<String> list, int k) {
        if(node == null) return;
        if(node.word != null) {
            list.add(node.word);
        }
        if(list.size() == k) return;
        for(int i = 0; i < 26; i++) {
            if(node.next[i] != null) {
                getWords(node.next[i], list, k);
            }
        }
    }

    private boolean addWord(TrieNode root, String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()) {
            if(curr.next[c - 'a'] == null) {
                curr.next[c - 'a'] = new TrieNode();
            }
            curr = curr.next[c - 'a'];
        }
        curr.word = word;
        return true;
    }

    public static void main(String[] args) {
        String[] input = {"i", "love", "leetcode", "i", "love", "coding"};
        TopKFrequentWords test = new TopKFrequentWords();
        test.topKFrequent(input, 2);
    }
}

class TrieNode {
    TrieNode[] next;
    String word;

    TrieNode() {
        this.next = new TrieNode[26];
    }

}