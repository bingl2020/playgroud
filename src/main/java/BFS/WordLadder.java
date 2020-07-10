package BFS;

import java.util.*;

/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() == 0 || endWord.length() == 0 || wordList == null) {
            return 0;
        }

        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Deque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        wordSet.remove(beginWord);

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String word = queue.poll();
                List<String> transformations = getTransformations(word, wordSet);
                for (String tran : transformations) {
                    if (tran.equals(endWord)) {
                        return level + 1;
                    }

                    queue.offer(tran);
                    wordSet.remove(tran);
                }
            }
            level++;
        }

        return 0;
    }

    private List<String> getTransformations(String word, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) {
                    continue;
                }
                chars[i] = c;

                String newWord = String.valueOf(chars);
                if (wordSet.contains(newWord)) {
                    res.add(newWord);
                }
                chars[i] = original;
            }
        }
        return res;
    }

}
