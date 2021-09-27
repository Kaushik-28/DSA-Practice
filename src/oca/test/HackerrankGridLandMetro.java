package oca.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result1 {

    /*
     * Complete the 'gridlandMetro' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. INTEGER k
     *  4. 2D_INTEGER_ARRAY track
     */

    public static int gridlandMetro(int n, int m, int k, List<List<Integer>> track) {
        track.sort((o1,o2) -> {
            //System.out.println("COmparing:"+ o1 +" "+o2);
            int res = o1.get(0).compareTo(o2.get(0));
            if(res != 0){
               // System.out.println("1" +res);
                return res;
            }else{
                res = o1.get(1).compareTo(o2.get(1));
                if(res != 0){
                 //   System.out.println("2"+res);
                    return res;
                }else{
                    res = o1.get(2).compareTo(o2.get(2));
                   // System.out.println("3"+res);
                    return res;
                }
            }
        } );
        //track.forEach(list -> System.out.println(list.get(0)+" "+list.get(1)+" "+list.get(2)));

        //merge the overlapping tracks
        int lastRow = 0;

       Deque<Interval> intervals = new LinkedList<Interval>();
        for(List<Integer> line:track){
            int currRow = line.get(0);
            int start = line.get(1);
            int end = line.get(2);
            if(intervals.isEmpty()|| currRow != lastRow){
                intervals.push(new Interval(start,end));
            }else {
                Interval pInterval = intervals.peek();

                if (start > pInterval.end) {
                    intervals.push(new Interval(start, end));
                } else if (end > pInterval.end) {
                    pInterval = intervals.poll();
                    pInterval.end = end;
                    intervals.push(pInterval);
                }
            }
            lastRow=currRow;


        }
        int count =0;
        while(!intervals.isEmpty()){
            Interval interval = intervals.poll();
          //  System.out.println(interval.start+" "+interval.end);
            count = count + interval.end - interval.start +1;
        }


        int res = (m*n)- count;
        //System.out.println(res);
        return (m*n)- count;
    }


}

class Interval{
    int start;
    int end;
    public Interval(int start,int end){
        this.start=start;
        this.end=end;
    }
}



public class HackerrankGridLandMetro {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> track = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                track.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result1.gridlandMetro(n, m, k, track);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}
