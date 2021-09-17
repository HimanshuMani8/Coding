////https://leetcode.com/problems/minimum-window-substring/
/*
Given two strings s and t of lengths m and n respectively,
 return the minimum window substring of s such that 
 every character in t (including duplicates) is included in the window.
  If there is no such substring, return the empty string "".
*/
import java.util.*;
import java.lang.*;
import java.io.*;
class Solution {
    public static void main(String[] args) throws java.lang.Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s= bf.readLine();
        String t= bf.readLine();
        Solution sol = new Solution();
        System.out.println(sol.minWindow(s,t));
    }
    public String minWindow(String s, String t) {
        //Input: s = "ADOBECODEBANC", t = "ABC"
        HashMap<Character, Integer> hs = new HashMap<>();
        for(char c: t.toCharArray())
            hs.put(c,hs.getOrDefault(c,0)+1); 
        /*
           hs = {'A'=1
                 'B'=1
                 'C'=1} // In hash we get the count of all chars in t.
        */
        int count = hs.size(); /*count keep measure of all elements appraring not 0 times
        mtlb agr 0 hai that means ki in s abhi tk from 'i to j', I have seen the exact all the ements same time as in t.
        */
        int start=0;
        boolean changed=false; //True if ans is calculated false if not calculated.
        int end=s.length()-1;
        int i=0;
        for(int j=0;j<s.length();j++){
            hs.put(s.charAt(j),hs.getOrDefault(s.charAt(j),0)-1);
            /*As we move forward i from j, we decrease frequency of s.charAt(j) in hash, if it is  -ve it means from i to j, we have covered charAt(j) more than it appeared in hash*/
            if(hs.get(s.charAt(j))==0) count--; //One element is covered and decrease count.
            
            while(count==0 && i<=j){
               /*Calculate the start and end "Temp ans"*/
                if(end-start >= j-i){
                    changed=true;
                    end=j;
                    start=i;
                }
                /*Now increase i to j, and add that element to hash, and if frequency of that is   0 to 1 increase the count*/
                hs.put(s.charAt(i),hs.getOrDefault(s.charAt(i),0)+1);
                if(hs.getOrDefault(s.charAt(i),0)==1) count++;
                
                i++;
            }
        }
        if(changed==true){
        return s.substring(start,end+1); //If ans is calculated return substring(i to j).
        }else{
            return ""; //If ans is never calculated than return "";
        }
    }
}