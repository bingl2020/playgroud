package Tree;

import java.util.*;

/*
You are given some lists of regions where the first region of each list includes all other regions in that list.

Naturally, if a region X contains another region Y then X is bigger than Y. Also by definition a region X contains itself.

Given two regions region1, region2, find out the smallest region that contains both of them.

If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.

It's guaranteed the smallest region exists.



Example 1:

Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"
 */
public class SmallestCommonRegion {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {

        // construct the map
        Map<String, String> map  = new HashMap<>();
        for (List<String> region : regions) {
            String parent = region.get(0);
            for (int i = 1; i < region.size(); i++) {
                map.put(region.get(i), parent);
            }
        }

        Set<String> set = new HashSet<>();

        while (region1 == null || region2 == null) {
            if (region1 != null) {
                if (set.contains(region1)) {
                    return region1;
                }

                set.add(region1);
                region1 = map.get(region1);
            }

            if (region2 != null) {
                if (set.contains(region2)) {
                    return region2;
                }

                set.add(region2);
                region2 = map.get(region2);
            }
        }

        return null;
    }
}
