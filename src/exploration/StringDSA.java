package exploration;


import java.util.*;

public class StringDSA {

    /**
     * This method removes the outermost parentheses of a valid string of parentheses.
     *
     * @param inStr The input string of parentheses.
     * @return The resulting string after removing the outermost parentheses.
     * <p>
     * Example:
     * Input: "(()())(())"
     * Output: "()()()"
     */
    public String removeOuterParentheses(String inStr) {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < inStr.length(); x++) {
            char a = inStr.charAt(x);
            if (a == '(') {
                if (counter > 0) { // Exclude the first opening bracket
                    sb.append(a);
                }
                counter++;
            } else { // For closing bracket
                counter--; // Decrement the counter
                if (counter > 0) { // Exclude the last closing bracket
                    sb.append(a);
                }
            }
        }
        return sb.toString();
    }

    /**
     * This method reverses the words in a given string.
     *
     * @param inputStr The input string.
     * @return The resulting string after reversing the words.
     * <p>
     * Example:
     * Input: "the sky is blue"
     * Output: "blue is sky the"
     */
    public String reverseWords(String inputStr) {
        inputStr = inputStr.trim(); // Remove leading and trailing spaces.
        char[] chArray = inputStr.toCharArray(); // Convert to character array

        // Reverse the whole sentence.
        int s = 0, e = inputStr.length() - 1;
        while (s < e) {
            // Swap characters
            var t = chArray[e];
            chArray[e] = chArray[s];
            chArray[s] = t;
            s++;
            e--;
        }

        // Reverse individual words.
        int st = 0, end = 0;
        for (int p = 0; p < chArray.length; p++) {
            if (chArray[p] == ' ' || p + 1 == chArray.length) {
                if (p == 0 && chArray[p] == ' ') {
                    st = p + 1;
                    continue; // Skip leading spaces
                }
                if (p > 0 && chArray[p - 1] == ' ') {
                    st = p + 1;
                    continue; // Skip multiple spaces
                }

                end = p + 1 == chArray.length ? p : p - 1;

                while (st < end) {
                    // Swap characters
                    var t = chArray[end];
                    chArray[end] = chArray[st];
                    chArray[st] = t;
                    st++;
                    end--;
                }
                st = p + 1;
            }
        }
        return new String(chArray); // Convert array back to string
    }

    /**
     * This method finds the largest odd number in a string.
     *
     * @param inStr The input string.
     * @return The largest odd number in the string. If no odd number is found, returns an empty string.
     * <p>
     * Example:
     * Input: "52"
     * Output: "5"
     */
    public String largestOddNumber(String inStr) {
        for (int x = inStr.length() - 1; x >= 0; x--) {
            int number = inStr.charAt(x) - '0'; // Convert character to integer

            if (number % 2 != 0) {
                return inStr.substring(0, x + 1); // Return the largest odd number
            }
        }
        return "";
    }

    /**
     * This method finds the longest common prefix among an array of strings.
     *
     * @param inpStrArray The input array of strings.
     * @return The longest common prefix. If no common prefix is found, returns an empty string.
     * <p>
     * Example:
     * Input: ["flower","flow","flight"]
     * Output: "fl"
     */
    public String longestCommonPrefix(String[] inpStrArray) {
        String prefix = inpStrArray[0];

        for (int i = 1; i < inpStrArray.length; i++) {
            String item = inpStrArray[i];
            int k = 0;
            String potentialPrefix = prefix.length() < item.length() ? prefix : item;

            while (k < potentialPrefix.length()) {
                if (item.charAt(k) != prefix.charAt(k)) {
                    break; // Stop if characters do not match
                }
                k++;
            }
            prefix = potentialPrefix.substring(0, k);
        }

        return prefix;
    }

    /**
     * This method checks if two strings are isomorphic.
     *
     * @param str1 The first input string.
     * @param str2 The second input string.
     * @return True if the two strings are isomorphic, false otherwise.
     * <p>
     * Example:
     * Input: str1 = "egg", str2 = "add"
     * Output: true
     */
    public boolean isIsomorphic(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        int[] map1 = new int[256];
        int[] map2 = new int[256];

        for (int x = 0; x < str1.length(); x++) {
            int ch1 = str1.charAt(x);
            int ch2 = str2.charAt(x);

            // Add to map for new entries.
            if (map1[ch1] == 0 && map2[ch2] == 0) {
                map1[ch1] = ch2;
                map2[ch2] = ch1;
            }
            // When entry found now match
            if (map1[ch1] != 0 || map2[ch2] != 0) {
                if (map1[ch1] != ch2 && map2[ch2] != ch1) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This method checks if a string can be rotated to match another string.
     *
     * @param input The original string.
     * @param goal  The string to match.
     * @return True if the original string can be rotated to match the goal string, false otherwise.
     * <p>
     * Example:
     * Input: input = "abcde", goal = "cdeab"
     * Output: true
     */
    public boolean rotateString(String input, String goal) {
        int inputLen = input.length();
        int goalLen = goal.length();

        // If lengths are not the same, return false
        if (inputLen != goalLen) {
            return false;
        }
        // If strings are identical, return true
        if (input.equals(goal)) {
            return true;
        }

        for (int i = 0; i < inputLen; i++) {
            // Rotate the input string
            input = input.substring(1, inputLen) + input.charAt(0);
            if (input.equals(goal)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method checks if two strings are anagrams of each other.
     * Anagrams are words or phrases formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     * @param str1 The first string
     * @param str2 The second string
     * @return true if the two strings are anagrams of each other, false otherwise.
     * <p>
     * Example:
     * Input: str1 = "anagram", str2 = "nagaram"
     * Output: true
     */
    public boolean checkAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        int[] freq = new int[26]; // only 26 Alphabets

        for (int x = 0; x < str1.length(); x++) {
            // for string 1
            char ch = str1.charAt(x);
            int chInt = ch - 'a';
            freq[chInt]++;

            // for string 2
            freq[str2.charAt(x) - 'a']--;
        }

        for (int x = 0; x < 26; x++) {
            if (freq[x] != 0) return false;
        }
        return true;
    }

    /**
     * This method sorts the characters in a string based on their frequency.
     *
     * @param inpStr The input string
     * @return The sorted string
     * <p>
     * Example:
     * Input: inpStr = "tree"
     * Output: "eert"
     */
    public String frequencySort(String inpStr) {
        HashMap<Character, Integer> countsMap = new HashMap<>();
        char[] inpStrArray = inpStr.toCharArray();

        //first prep map.
        for (char ch : inpStrArray) {
            countsMap.put(ch, countsMap.getOrDefault(ch, 0) + 1);
        }

        //sort keys by its value
        List<Character> chKeys = new ArrayList<>(countsMap.keySet());
        Collections.sort(chKeys, (p, q) -> countsMap.get(q) - countsMap.get(p));

        //build string
        StringBuilder sb = new StringBuilder();
        for (Character key : chKeys) {
            int freq = countsMap.get(key); // gets freq
            for (int z = 0; z < freq; z++) {
                sb.append(key); // build new string with builder.
            }
        }
        return sb.toString();
    }

    /**
     * This method calculates the maximum depth of valid parentheses in a string.
     *
     * @param inStr The input string
     * @return The maximum depth of valid parentheses
     * <p>
     * Example:
     * Input: inStr = "(1+(2*3)+((8)/4))+1"
     * Output: 3
     */
    public int maxDepth_Of_Parentheses(String inStr) {
        int counter = 0;
        int maxP = 0;

        for (int x = 0; x < inStr.length(); x++) {
            char ch = inStr.charAt(x);
            if (ch == '(') {
                counter++;
                maxP = Math.max(counter, maxP);
            } else if (ch == ')') {
                counter--;
            }
        }

        return maxP;
    }

    /**
     * This method converts a Roman numeral to an integer.
     *
     * @param inStr The input string
     * @return The integer value of the Roman numeral
     * <p>
     * Example:
     * Input: inStr = "LVIII"
     * Output: 58
     */
    public int roman_to_Integer(String inStr) {
        var riMap = new HashMap<String, Integer>();
        riMap.put("M", 1000);
        riMap.put("D", 500);
        riMap.put("C", 100);
        riMap.put("L", 50);
        riMap.put("X", 10);
        riMap.put("V", 5);
        riMap.put("I", 1);

        String lastSymbol = inStr.substring(inStr.length() - 1);
        int lastVal = riMap.get(lastSymbol);
        int totalVal = lastVal;

        for (int x = inStr.length() - 2; x >= 0; x--) {
            String symbol = inStr.substring(x, x + 1);
            int symbolValue = riMap.get(symbol);
            if (symbolValue >= lastVal) {
                totalVal = totalVal + symbolValue;
                lastVal = symbolValue;
            } else {
                totalVal = totalVal - symbolValue;
                lastVal = symbolValue;
            }
        }

        return totalVal;
    }

    /**
     * This method calculates the number of substrings in a string.
     *
     * @param inStr The input string
     * @return The number of substrings
     * <p>
     * Example:
     * Input: inStr = "abc"
     * Output: 6
     */
    public int no_of_subString(String inStr) {
        int len = inStr.length();
        int total = len * (len + 1) / 2;
        return total;
    }


    /**
     * This method counts the number of substrings with K distinct characters in a given string.
     * It uses a sliding window approach to generate all substrings and keeps track of distinct characters using a boolean array.
     *
     * @param inStr The input string.
     * @param K     The number of distinct characters.
     * @return The total number of substrings with K distinct characters.
     * <p>
     * Example:
     * Input: inStr = "abc", K = 2
     * Output: 2
     * Explanation: The substrings with 2 distinct characters are "ab" and "bc".
     */
    public int count_no_of_K_distinctChar_subString(String inStr, int K) {
        boolean[] seen = new boolean[26];
        int totalKdistinctSubStrings = 0;

        for (int outer = 0; outer < inStr.length(); outer++) {
            int distinctCharCount = 0;
            for (int inner = outer; inner < inStr.length(); inner++) {
                char c1 = inStr.charAt(inner);
                int c1Idx = c1 - 'a';

                if (!seen[c1Idx]) {
                    distinctCharCount++;
                    seen[c1Idx] = true;
                }

                if (distinctCharCount == K) {
                    totalKdistinctSubStrings++;
                }
            }
            Arrays.fill(seen, false);
        }
        return totalKdistinctSubStrings;
    }

    /**
     * This method counts the total number of homogenous substrings in a given string.
     * A homogenous substring is a substring with all the same characters.
     *
     * @param inStr The input string.
     * @return The total number of homogenous substrings modulo 1e9 + 7.
     * <p>
     * Example:
     * Input: inStr = "aa"
     * Output: 3
     * Explanation: The homogenous substrings are "a", "a", and "aa".
     */
    public int countHomogenous(String inStr) {
        int len = inStr.length();
        if (len <= 0) {
            return 0;
        }

        long totalHomogenousCount = 0;
        long lenCounter = 0;
        int MOD = (int) 1e9 + 7;

        int start = 0;

        while (start < len) {
            lenCounter++;
            if (start + 1 == len || inStr.charAt(start) != inStr.charAt(start + 1)) {
                long totalSubStrings = lenCounter * (lenCounter + 1) / 2;
                totalHomogenousCount = totalHomogenousCount + totalSubStrings;
                lenCounter = 0;
            }
            start++;
        }
        int resTMod = (int) (totalHomogenousCount % MOD);
        return resTMod;
    }

    /**
     * This method finds the longest palindromic substring in a given string.
     * It uses an expand from center approach to check for palindromes.
     *
     * @param inStr The input string.
     * @return The longest palindromic substring.
     * <p>
     * Example:
     * Input: inStr = "babad"
     * Output: "bab"
     * Explanation: "bab" is the longest palindromic substring.
     */
    public String longestPalindrome(String inStr) {
        int len = inStr.length();
        int center = len / 2;
        String maxPalindrom = inStr.substring(0, 1);

        while (center >= 0) {
            String res = expandFromCenter(inStr, center - 1, center + 1);
            if (res.length() > maxPalindrom.length()) {
                maxPalindrom = res;
            }
            String resE = expandFromCenter(inStr, center - 1, center);
            if (resE.length() > maxPalindrom.length()) {
                maxPalindrom = resE;
            }
            if (maxPalindrom.length() == len) {
                break;
            }
            center--;
        }

        if (maxPalindrom.length() == len) {
            return maxPalindrom;
        }

        center = len / 2;
        while (center < len) {
            String res = expandFromCenter(inStr, center - 1, center + 1);
            if (res.length() > maxPalindrom.length()) {
                maxPalindrom = res;
            }
            String resE = expandFromCenter(inStr, center - 1, center);
            if (resE.length() > maxPalindrom.length()) {
                maxPalindrom = resE;
            }
            if (maxPalindrom.length() == len) {
                break;
            }
            center++;
        }

        return maxPalindrom;
    }

    /**
     * This helper method expands a substring from the center and checks if it's a palindrome.
     *
     * @param input The input string.
     * @param left  The left index.
     * @param right The right index.
     * @return The palindromic substring.
     */
    private String expandFromCenter(String input, int left, int right) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }
        return input.substring(left + 1, right);
    }

    /**
     * This method calculates the sum of the beauty of all substrings of a string.
     * The beauty of a substring is the difference between the maximum and minimum frequency of characters.
     *
     * @param inStr The input string.
     * @return The sum of the beauty of all substrings.
     * <p>
     * Example:
     * Input: inStr = "aab"
     * Output: 2
     * Explanation: The substrings are "a", "a", "b", "aa", "ab", "aab". The beauty of each substring is 0, 0, 0, 1, 0, 1. The sum is 2.
     */
    public int sum_of_all_beauty_substring(String inStr) {
        if (inStr.length() <= 0) return 0;
        int sum = 0;

        for (int outer = 0; outer < inStr.length(); outer++) {
            int[] charFreqArr = new int[26];

            for (int inner = outer; inner < inStr.length(); inner++) {
                var ch = inStr.charAt(inner);
                var chVal = ch - 'a';
                charFreqArr[chVal]++;

                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int p = 0;
                while (p < charFreqArr.length) {
                    if (charFreqArr[p] != 0) {
                        min = Math.min(min, charFreqArr[p]);
                        max = Math.max(max, charFreqArr[p]);
                    }
                    p++;
                }
                int diff = max - min;
                sum = sum + diff;
            }
        }
        return sum;
    }


    /**
     * This method calculates the minimum number of parentheses to be added to make the input string valid.
     *
     * @param inStr The input string of parentheses.
     * @return The minimum number of parentheses to be added.
     * <p>
     * Example:
     * Input: "())"
     * Output: 1
     */
    public int minParenthesesAddToMakeValid(String inStr) {
        int counter = 0;
        int open = 0;
        for (int x = 0; x < inStr.length(); x++) {
            char ch = inStr.charAt(x);
            if (ch == '(') {
                open++;
            } else { // If the character is ')'
                open--;
            }
            if (open == -1) {
                counter++; // Increment the counter to balance the parentheses
                open++; // Reset the open counter
            }
        }
        return counter + open; // Add the remaining open parentheses to the counter
    }

    /**
     * This method generates the nth term of the count-and-say sequence.
     *
     * @param n The input number.
     * @return The nth term of the count-and-say sequence.
     * <p>
     * Example:
     * Input: 4
     * Output: "1211"
     */
    public String countAndSay(int n) {
        StringBuilder ans = new StringBuilder();
        ans.append("1"); // Start with the first term

        // Generate the nth term
        for (int i = 2; i <= n; i++) {
            ans = helper(ans);
        }
        return ans.toString();
    }

    private StringBuilder helper(StringBuilder prevcountNSayDigit) {
        StringBuilder ans = new StringBuilder();

        int freq = 1;

        for (int i = 0; i < prevcountNSayDigit.length(); i++) {
            if (i == prevcountNSayDigit.length() - 1 || prevcountNSayDigit.charAt(i) != prevcountNSayDigit.charAt(i + 1)) {
                ans.append(freq);
                ans.append(prevcountNSayDigit.charAt(i));
                freq = 1; // Reset the frequency for the next distinct digit
            } else {
                freq++; // Increase the frequency for repeating digits
            }
        }
        return ans;
    }

    /**
     * This method finds the longest prefix of a string.
     *
     * @param inpStr The input string.
     * @return The longest prefix of the string.
     * <p>
     * Example:
     * Input: "level"
     * Output: "le"
     */
    public String longest_Prefix(String inpStr) {
        char[] strAr = inpStr.toCharArray();
        int stringLen = strAr.length;

        int prefixLen = 0;
        int matchPointer = 1; // Start comparing from the second character

        int[] lps = new int[stringLen];
        lps[0] = 0; // The first character is always 0

        while (matchPointer < stringLen) {
            if (strAr[prefixLen] == strAr[matchPointer]) {
                int calculatedVal = prefixLen + 1;
                lps[matchPointer] = calculatedVal;

                prefixLen += 1; // Increase the length by 1
                matchPointer += 1; // Move the pointer to the next character
            } else {
                if (prefixLen != 0) {
                    prefixLen = lps[prefixLen - 1]; // Start from the calculated value, not from 0
                } else {
                    lps[matchPointer] = 0; // No matching character
                    matchPointer += 1; // Try the next character
                }
            }
        }
        return inpStr.substring(0, prefixLen);
    }

    /**
     * This method finds the first occurrence of a needle in a haystack using the brute force sliding window algorithm.
     *
     * @param haystack The string in which to search.
     * @param needle   The string to search for.
     * @return The index of the first occurrence of the needle in the haystack. If the needle is not found, returns -1.
     * <p>
     * Example:
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     */
    public int Needle_Haystack_BF_SlidingWindow(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;

        int firstIdx = 0;

        for (int i = 0; i < haystack.length(); i++) {
            int start = 0;
            firstIdx = i;

            while (start < needle.length() && firstIdx < haystack.length()) {
                if (needle.charAt(start) != haystack.charAt(firstIdx)) {
                    break; // Not matching
                }
                if (start == needle.length() - 1) {
                    return firstIdx - start; // Matched
                }
                firstIdx++;
                start++;
            }
        }
        return -1;
    }

    /**
     * This method finds the first occurrence of a needle in a haystack using the KMP (Knuth Morris Pratt) algorithm.
     *
     * @param haystack The string in which to search.
     * @param needle   The string to search for.
     * @return The index of the first occurrence of the needle in the haystack. If the needle is not found, returns -1.
     * <p>
     * Example:
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     */
    public int Needle_Haystack_KMP_Algo(String haystack, String needle) {
        int hayLen = haystack.length();
        int ndLen = needle.length();

        if (hayLen < ndLen) return -1;

        int[] lpsRes = calculateLPS(needle);
        int hayIdx = 0;
        int ndlIdx = 0;

        while (hayIdx < hayLen) {
            if (needle.charAt(ndlIdx) == haystack.charAt(hayIdx)) {
                hayIdx++;
                ndlIdx++;
            } else {
                if (ndlIdx != 0) {
                    int resumeFrombackIdx = lpsRes[ndlIdx - 1]; // Find how far back it can go and resume from the LPS stored number
                    ndlIdx = resumeFrombackIdx; // Resume from back based on LPS instead of going all the way back
                } else {
                    hayIdx++; // Just move the haystack index and the needle's first character itself is not matching
                }
            }
            if (ndlIdx == ndLen) {
                return hayIdx - ndlIdx; // Find the starting index of the haystack where it started matching
            }
        }

        return -1;
    }

    /**
     * This method calculates the Longest Proper Prefix which is also suffix (LPS) array for a string.
     * The LPS array is used to keep track of the longest suffix of the substring ending at position i that matches a prefix of the original string.
     *
     * @param inputStr The input string
     * @return The LPS array
     * <p>
     * Example:
     * Input: inputStr = "ababcabab"
     * Output: [0, 0, 1, 2, 0, 1, 2, 3, 4]
     */
    private int[] calculateLPS(String inputStr) {
        int matchedOrPrefixLen = 0;
        int runner = 1;
        int[] lps = new int[inputStr.length()];

        while (runner < inputStr.length()) {
            if (inputStr.charAt(matchedOrPrefixLen) == inputStr.charAt(runner)) {
                matchedOrPrefixLen++;
                lps[runner] = matchedOrPrefixLen;
                runner++;
            } else {
                if (matchedOrPrefixLen == 0) {
                    lps[runner] = 0;
                    runner++;
                } else {
                    int revisedIdx = matchedOrPrefixLen - 1;
                    matchedOrPrefixLen = lps[revisedIdx];
                }
            }
        }

        return lps;
    }

    /**
     * This method checks if the string 'needle' can be obtained by repeating the string 'haystack' one or more times.
     *
     * @param haystack The string to be repeated
     * @param needle   The string to be obtained
     * @return The minimum number of times 'haystack' needs to be repeated. If 'needle' cannot be obtained from 'haystack', return -1.
     * <p>
     * Example:
     * Input: haystack = "abcd", needle = "cdabcdab"
     * Output: 3
     */
    public int repeatedStringMatch(String haystack, String needle) {
        Set<Character> set = new HashSet<>();
        for (char c : haystack.toCharArray()) {
            set.add(c);
        }
        for (char c : needle.toCharArray()) {
            if (!set.contains(c)) {
                return -1;
            }
        }

        int counter = 0;
        String resHaystack = "";

        while (resHaystack.length() < needle.length()) {
            counter++;
            resHaystack += haystack;
        }
        int resC = Needle_Haystack_BF_SlidingWindow(resHaystack, needle);

        if (resC < 0) {
            resHaystack += haystack;
            counter++;
            resC = Needle_Haystack_BF_SlidingWindow(resHaystack, needle);
        }
        return resC != -1 ? counter : -1;
    }

    /**
     * This method finds the shortest palindrome by adding characters in front of the original string.
     *
     * @param inpStr The input string
     * @return The shortest palindrome
     * <p>
     * Example:
     * Input: inpStr = "aacecaaa"
     * Output: "aaacecaaa"
     */
    public String shortestPalindrome_KMP(String inpStr) {
        String revStr = new StringBuilder(inpStr).reverse().toString();
        String merged = inpStr + revStr;
        int alreadyMatchingIdx = calculateLPS_ShortPalindorm_KMP(merged, inpStr.length());

        if (alreadyMatchingIdx >= inpStr.length()) {
            return inpStr;
        }

        String restStr = inpStr.substring(alreadyMatchingIdx);
        String shortestPalindrom = new StringBuilder(restStr).reverse().append(inpStr).toString();
        return shortestPalindrom;
    }

    /**
     * This method calculates the Longest Proper Prefix which is also suffix (LPS) array for a string.
     * The LPS array is used to keep track of the longest suffix of the substring ending at position i that matches a prefix of the original string.
     *
     * @param inputStr     The input string
     * @param orgStringLen The length of the original string
     * @return The last element of the LPS array
     * <p>
     * Example:
     * Input: inputStr = "aacecaaaaaacecaa", orgStringLen = 8
     * Output: 7
     */
    int calculateLPS_ShortPalindorm_KMP(String inputStr, int orgStringLen) {
        int matchedOrPrefixLen = 0;
        int runner = 1;
        int[] lps = new int[inputStr.length()];

        while (runner < inputStr.length()) {
            if (runner == orgStringLen) {
                matchedOrPrefixLen = 0;
            }

            if (inputStr.charAt(matchedOrPrefixLen) == inputStr.charAt(runner)) {
                matchedOrPrefixLen++;
                lps[runner] = matchedOrPrefixLen;
                runner++;
            } else {
                if (matchedOrPrefixLen == 0) {
                    lps[runner] = 0;
                    runner++;
                } else {
                    int revisedIdx = matchedOrPrefixLen - 1;
                    matchedOrPrefixLen = lps[revisedIdx];
                }
            }
        }

        return lps[inputStr.length() - 1];
    }

    /**
     * This method converts a string to an integer (atoi).
     * It handles spaces before the digit or sign, stops when it hits a space in between, and handles overflow and underflow.
     *
     * @param inpStr The input string.
     * @return The converted integer.
     * <p>
     * Example:
     * Input: inpStr = "42"
     * Output: 42
     */
    public int stringTointeger_Atoi(String inpStr) {
        int res = 0;
        int idx = 0;
        int sign = 1;
        int len = inpStr.length();

        while (idx < len && inpStr.charAt(idx) == ' ') {
            idx++;
        }
        if (idx < len && inpStr.charAt(idx) == '-') {
            sign = -1;
            idx++;
        } else if (idx < len && inpStr.charAt(idx) == '+') {
            sign = 1;
            idx++;
        }

        while (idx < len && Character.isDigit(inpStr.charAt(idx))) {
            int digit = inpStr.charAt(idx) - '0';

            if (res > Integer.MAX_VALUE / 10) {
                if (sign == 1) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }

            if (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10) {
                if (sign == 1) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }

            res = 10 * res + digit;
            idx++;
        }

        return sign == 1 ? res : -1 * res;
    }

    /**
     * This method checks if a string can be rotated to match a goal string.
     * It finds the index of the first character of the goal in the input, then compares the rest of the input with the goal.
     * <p>
     * Note: This method does not handle all use cases. For complex cases like "bbbacddceeb" vs "ceebbbbacdd", use a different method.
     *
     * @param input The input string.
     * @param goal  The goal string.
     * @return True if the input can be rotated to match the goal, false otherwise.
     * <p>
     * Example:
     * Input: input = "abc", goal = "bca"
     * Output: true
     * Explanation: "abc" can be rotated to "bca".
     */
    public boolean rotateString_NOT_ALL_Usecases_NOTUSED(String input, String goal) {
        if (input.length() != goal.length()) return false;

        int pivotIdx = 0;
        char pivotVal = goal.charAt(0);

        for (int x = 0; x < input.length(); x++) {
            if (input.charAt(x) == pivotVal) {
                pivotIdx = x;
                break;
            }
        }

        boolean res = true;
        for (int x = pivotIdx; x < input.length(); x++) {
            if (input.charAt(x) != goal.charAt(x - pivotIdx)) {
                res = false;
                break;
            }
        }

        int goalIdx = input.length() - pivotIdx;
        int inputIdx = 0;
        while (goalIdx < goal.length() && inputIdx < pivotIdx) {
            if (goal.charAt(goalIdx) != input.charAt(inputIdx)) {
                res = false;
                break;
            }
            goalIdx++;
            inputIdx++;
        }

        return res;
    }


    /**
     * This method finds the first occurrence of a needle in a haystack using the Rabin-Karp rolling hash algorithm.
     *
     * @param haystack The string in which to search.
     * @param needle   The string to search for.
     * @return The index of the first occurrence of the needle in the haystack. If the needle is not found, returns -1.
     * <p>
     * Example:
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     */
    public int Needle_Haystack_RabinKarp_RollingHash_Algo(String haystack, String needle) {
        int needleLen = needle.length();
        int haystackLen = haystack.length();
        int MOD = 1000000033;

        if (needleLen > haystackLen) return -1;

        var needleHashVal = calculateHashValue(needle, needleLen);

        int totalAlphabet = 26;

        int rollingHayStackVal = 0;
        for (int windowSt = 0; windowSt < haystackLen; windowSt++) {

            if (windowSt == 0) {
                rollingHayStackVal = calculateHashValue(haystack, needleLen);
            } else {

                // Apply rolling hash formula to remove last element and add next in line.
                // Update Hash using Previous Hash Value in O(1)

                // Previous hash
                int prevHashNormalizedVal = ((rollingHayStackVal * totalAlphabet) % MOD);

                // Previous character
                int prevCharTobeRemoved = haystack.charAt(windowSt - 1) - 'a';
                // 26^3 * 2
                int prevCharNormalizedVal = (((int) (Math.pow(totalAlphabet, needleLen) * prevCharTobeRemoved)) % MOD);

                // New hash value
                int haystackVAlafterRemoval = (prevHashNormalizedVal - prevCharNormalizedVal);

                // Next character in line e.g. c cb to cb a
                // ->c<- c b <-a<- c removed and a entered.
                int nextCharVal = (haystack.charAt(windowSt + needleLen - 1) - 'a') + MOD;

                // 26^ 0 * 2 this can be optional as it always 26 ^ 0 = 1.
                // int nextCharNormalizedVal = (int) (nextCharVal * Math.pow(totalAlphabet, 0));

                rollingHayStackVal = (haystackVAlafterRemoval + nextCharVal) % MOD;

            }

            if (rollingHayStackVal == needleHashVal) {
                // Character comparison like BF method.
                for (int i = 0; i < needleLen; i++) {
                    if (needle.charAt(i) != haystack.charAt(i + windowSt)) {
                        break;
                    }
                    if (i == needleLen - 1) { // Last character was matched so
                        return windowSt; // Found and returned.
                    }
                }
            }
        }

        return -1;
    }

    /**
     * This method calculates the hash value for a given string.
     *
     * @param inputStr      The input string.
     * @param lenToConsider The length of the string to consider for the hash calculation.
     * @return The calculated hash value.
     */
    private int calculateHashValue(String inputStr, int lenToConsider) {
        int MOD = 1000000033;
        int ans = 0;
        for (int p = 0; p < lenToConsider; p++) {
            int charVal = inputStr.charAt(p) - 'a';
            int pw = lenToConsider - 1 - p;
            ans += ((int) (Math.pow(26, pw) * charVal)) % MOD;
        }
        return ans;
    }


    /**
     * This method finds the shortest palindrome by prepending characters to the given string.
     * It uses the Rabin-Karp algorithm to compare substrings in constant time.
     *
     * @param inpStr The input string.
     * @return The shortest palindrome.
     * <p>
     * Example:
     * Input: inpStr = "aacecaaa"
     * Output: "aaacecaaa"
     * Explanation: "a" is prepended to "aacecaaa" to form the palindrome "aaacecaaa".
     */
    public String shortestPalindrome_RobinKarp(String inpStr) {
        final int base = 131;
        final int mod = (int) 1e9 + 7;
        int multiplier = 1;

        int prefixHash = 0;
        int suffixHash = 0;

        int palindIdx = 0;
        int len = inpStr.length();

        for (int i = 0; i < len; i++) {
            int chVal = inpStr.charAt(i) - 'a' + 1;
            prefixHash = (int) (((long) prefixHash * base + chVal) % mod);
            suffixHash = (int) ((suffixHash + (long) multiplier * chVal) % mod);
            multiplier = (int) (((long) multiplier * base) % mod);

            if (prefixHash == suffixHash) {
                palindIdx = i + 1;
            }
        }

        if (palindIdx == len) {
            return inpStr;
        }

        String remaining = inpStr.substring(palindIdx);
        StringBuilder remainingBuilder = new StringBuilder(remaining);
        StringBuilder remainingReverse = remainingBuilder.reverse();
        String res = remainingReverse.toString();

        return res + inpStr;
    }

    /**
     * This method is used to understand the Rabin-Karp algorithm.
     * It calculates the prefix and suffix hash values for each character in the string.
     *
     * @param inpStr The input string.
     */
    private void Just_RAbin_Algo_calc_Understanding(String inpStr) {
        final int base = 3;
        int multiplier = 1;

        int prefixHash = 0;
        int suffixHash = 0;

        int palindIdx = 0;
        int len = inpStr.length();

        for (int i = 0; i < len; i++) {
            int chVal = inpStr.charAt(i) - 'a' + 1;
            prefixHash = prefixHash * base + chVal;
            suffixHash = suffixHash + multiplier * chVal;
            multiplier = multiplier * base;

            if (prefixHash == suffixHash) {
                palindIdx = i + 1;
            }
        }
    }
}
