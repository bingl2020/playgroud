package oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();

        int n = products.length;
        int lo = 0, hi = n - 1;
        int len = searchWord.length();

        Arrays.sort(products);

        for (int i = 0; i < len; i++) {
            // find index for first matching
            while (lo <= hi
                    && (products[lo].length() <= i || products[lo].charAt(i) != searchWord.charAt(i)) ) {
                lo++;
            }

            while (lo <= hi
                    && (products[hi].length() <= i || products[hi].charAt(i) != searchWord.charAt(i)) ) {
                hi--;
            }
            int min = Math.min(lo + 2, hi);
            List<String> list = new ArrayList<>();
            for (int j = lo; j <= min; j++) {
                list.add(products[j]);
            }

            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {

        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};

        new SearchSuggestionsSystem().suggestedProducts(products, "mouse");

    }
}
