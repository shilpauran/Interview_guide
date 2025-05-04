package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class ReverseWords {
    public static void dummy() {
        char[] chars = reverseWords("Welcome to my home");
        for(char i: chars)
        {
            System.out.print(i);
        }
    }
    public static char[] reverseWords(String str)
    {
        char[] s = str.toCharArray();
        str.trim();
        str.strip();
        int start = 0 ; int end = 0 ;
        while(end < s.length)
        {
            if(s[end] == ' ')
            {
                reverse(s,start,end-1);
                start = end + 1 ;
                end++;
            }
            else
            {
                end++;
            }
        }
        reverse(s,start,s.length-1);
        reverse(s,0,s.length-1);
        return s;
    }

    static void reverse(char[] s , int low, int high)
    {
        while(low<=high)
        {
            char temp = s[low];
            s[low] = s[high];
            s[high] = temp;
            low++; high--;
        }
    }




    //leetcode another version. where
    /*
    Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
     */
    public String reverseWords1(String s) {

        char[] chars = s.strip().toCharArray();

        int start = 0, end = 0;

        while(end<chars.length)
        {
            if(chars[end] == ' ')
            {
                reverse(chars,start,end-1);
                start = end + 1;
                end++;
            }
            else
            {
                end++;
            }
        }

        reverse(chars,start,chars.length-1);
        reverse(chars,0,chars.length-1);

        String[] parts = new String(chars).split("#");
        return String.join("",parts);

    }


    void reverse1(char[] a, int low, int high)
    {

        if(a[low] == ' ')
        {
            a[low] = '#';
        }
        while(low<=high)
        {

            char temp = a[low];
            a[low] = a[high];
            a[high] = temp;
            low++;
            high--;
        }

    }
}
