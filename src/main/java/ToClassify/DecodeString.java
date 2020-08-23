package ToClassify;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        Deque<String> strStack = new ArrayDeque<>();
        Deque<Integer> numStack = new ArrayDeque<>();

        StringBuilder builder = new StringBuilder();
        StringBuilder resBuilder = new StringBuilder();

        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                StringBuilder numBuilder = new StringBuilder();
                do {
                    numBuilder.append(c);
                } while (Character.isDigit(s.charAt(++index)));

                numStack.push(Integer.parseInt(numBuilder.toString()));
            } else if ('[' == c) {
                strStack.push(builder.toString());
                builder.setLength(0);
                index++;
            } else if (']' == c) {
                resBuilder = new StringBuilder(strStack.pop());
                int times = numStack.pop();

                while (times-- > 0) {
                    resBuilder.append(builder.toString());
                }
                builder.setLength(0);

                index++;
            } else {
                builder.append(c);
                index++;
            }
        }

        return resBuilder.toString();
    }

    public static void main(String[] args) {
        new DecodeString().decodeString("3[a2[c]]");

    }
}
