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
     * Complete the 'andQueries' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY numbers
     *  2. 2D_INTEGER_ARRAY queries
     */
     public static int  findAnd(List<Integer> array){
         int ans=array.get(0);
         for(int i=1;i<array.size();i++){
             ans=(ans&array.get(i));
         }
         return ans;
     }


     public static int queryProcessing(List<Integer> numbers,int l,int r,int s,int t){
         int ans=0;
         List<Integer> subarray=new ArrayList<Integer>();
         for(int i=l;i<=r-s+1;i++){
             
             for(int j=i;j<l+s;j++){
                 subarray.set(j-i,numbers.get(j));

             }
             
             if(findAnd(subarray)>=t)
             ans++;
         }
         return ans;


     }


    public static List<Integer> andQueries(List<Integer> numbers, List<List<Integer>> queries) {
    // Write your code here
    int queriesLength=queries.size();
    ArrayList<Integer> result=new ArrayList<Integer>();
    for(int i=0;i<queriesLength;i++){
        int l=queries.get(i).get(0);
        int r=queries.get(i).get(1);
        int s=queries.get(i).get(2);
        int t=queries.get(i).get(3);
        result.add(queryProcessing(numbers,l-1,r-1,s,t));

    }
    return result;

    }

}

 class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int numbersCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> numbers = IntStream.range(0, numbersCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int queriesRows = Integer.parseInt(bufferedReader.readLine().trim());
        int queriesColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, queriesRows).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.andQueries(numbers, queries);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
