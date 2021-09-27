package oca.test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LeetcodeMergeOverlappingIntervals {


        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> {return a[0] - b[0];});
            // Arrays.stream(intervals).forEach(arr -> System.out.println(Arrays.toString(arr)));
            Deque<int[]> queue = new LinkedList();
            queue.push(intervals[0]);
            for(int i=1 ; i<intervals.length ; i++){
                int[] curr = intervals[i];
                //already sorted based on start
                int end = curr[1];
                int start = curr[0];
                int[] top = queue.peek();
                if(start > top[1]){
                    queue.push(curr);
                }else if(end > top[1]){
                    top[1]=end;
                    queue.poll();
                    queue.push(top);
                }
            }
            int[][] res = new int[queue.size()][];
            int i =0;
            while(!queue.isEmpty()){
                int[] arr = queue.poll();
                res[i]=arr;
                i++;
            }
            return res;
        }

}
