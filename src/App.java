import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class App {
    public static void main(String[] args) throws Exception {
        //Read the text files
        String fileA = ReadFile("/Users/emir/Documents/Software Projects/SpecialTopics/src/FileA.txt");
        String fileB = ReadFile("/Users/emir/Documents/Software Projects/SpecialTopics/src/FileB.txt");
        //First method for LCE
        LongestCommanExtension(fileA, fileB, 4, 7);   
        //Initialize Suffix arrays for givent .txt files
        SuffixArray suffixA = new SuffixArray(fileA);
        SuffixArray suffixB = new SuffixArray(fileB);
        //Print Suffix array information
        PrintTable(fileA, suffixA);
        PrintTable(fileB, suffixB);
        //Second method for LCE
        


        
    }

    
    //Wirte a method that takes two suffix arrays and finds the longest common extension between two files
    public static void RangeMinimumQuery(SuffixArray suffixA, SuffixArray suffixB){
        
    }

    public static void PrintTable(String file, SuffixArray suffix){
        System.out.printf("\n%10s %10s %10s %s\n", "i", "SA[i]", "LCP[i]","Suffix");
        for (int i = 0; i < file.length(); i++) {
            int index = suffix.index(i);
            String ith = "\"" + file.substring(index, Math.min(index + 50, file.length())) + "\"";
            assert file.substring(index).equals(suffix.select(i));
            //int rank = suffix.rank(file.substring(index));
            if (i == 0) {
                System.out.printf("%10d %8d %8s %10s\n", i + 1, index + 1, "0", ith);
            }
            else {
                int lcp = suffix.lcp(i);
                System.out.printf("%10d %8d %8d %10s\n", i + 1, index + 1, lcp, ith);
            }
        }
    }

    public static int LongestCommanExtension(String fileA, String fileB, int i, int j){
        int t = 0;
        while(fileA.charAt(i - 1 + t) == fileB.charAt(j - 1 + t) && i + t < fileA.length() && j + t < fileB.length()){
            t++;
        }
        System.out.println(t);
        return t;
    }

    public static String ReadFile(String filePath){
        String file = "An error occured while reading the file";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              file = myReader.nextLine();
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return file;
    }

    
}