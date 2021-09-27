package oca.test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

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
        // Write your code here
        List<List<Boolean>> grid = new ArrayList<>();
        IntStream.range(0,n).forEach(row -> {
            grid.add(new ArrayList<Boolean>());
        });
        grid.stream().forEach( row ->{
            IntStream.range(0,m).forEach(i -> {
                row.add(Boolean.FALSE);
            });
        });
        for(List<Integer> line : track){
            List<Boolean> row = grid.get(line.get(0) -1);
            int start = line.get(1);
            int end = line.get(2);
            for(int i = start -1 ; i<=end-1;i++){
                row.set(i,true);
            }
        }
        List<Integer> countList = new ArrayList<>();
        countList.add(0);
        grid.stream().forEach(row -> {
            row.stream().forEach(flag ->{
                if(flag){
                    int count = countList.get(0);
                    countList.set(0,++count);
                }
            });
        });

        return countList.get(0);
    }
}

public class HackerrankGridLandMetroBruteForce {
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

        int result = Result.gridlandMetro(n, m, k, track);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}
