import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EarthquakeFinal {
    public static void main(String[] args) throws FileNotFoundException {
        int numOfYears = getNumOfYears();
        int[] data = generateQuakeData(numOfYears);
        ArrayList<Integer> readData = null;
        try {
            writeQuakedata(data);
            readData = readQuakeData();
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }

        printAnalysis(readData);
    }

    /**
     * Asks the user for a number 1-20
     *
     * @return
     */
    public static int getNumOfYears() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the number of years to evaluate seismic data: (1-20 inclusive) ");
        int years = scnr.nextInt();
        scnr.nextLine();//
            while (years < 1 || years > 20) {// loops until valid input is entered
                System.out.println("Invalid input");
                System.out.println("Please enter the number of years to evaluate seismic data: (1-20 inclusive) ");
                years = scnr.nextInt();
// Unable to valid that the input is a int
        }
        return years;
    }

    /**
     * Randomly gives the years a number 1-8
     *
     * @param years
     * @return
     */
    public static int[] generateQuakeData(int years) {
        int[] data = new int[years];
        Random randgen = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = randgen.nextInt(9);
        }
        return data;
    }

    /**
     * Will write the data to a file
     *
     * @param data
     * @throws FileNotFoundException
     */
    public static void writeQuakedata(int[] data) throws FileNotFoundException {
        FileOutputStream fs = new FileOutputStream("/Users/nicholasvolpe/Documents/IST 140/Ch07/src/quake.txt");
        PrintWriter outFile = new PrintWriter(fs);
        for (int i = 0; i < data.length; i++) {
            outFile.println(data[i]);
        }
        outFile.close();
    }

    /**
     * reads a file
     * stores data into an array list
     *
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<Integer> readQuakeData() throws FileNotFoundException {
        FileInputStream inFile = new FileInputStream("/Users/nicholasvolpe/Documents/IST 140/Ch07/src/quake.txt");
        Scanner in = new Scanner(inFile);
        ArrayList<Integer> data = new ArrayList<>();
        while (in.hasNextInt()) {// loops until all file data is in array list
            data.add(in.nextInt());
        }
        return data;
    }

    /**
     * Will print the data in the java class
     *
     * @param data
     */
    public static void printAnalysis(ArrayList<Integer> data) {
        System.out.println(data.toString());
        int curYear = data.get(0);
        String intensity = "Tempword";
        if (curYear <= 2) {// sets the intensity of the first year quake
            intensity = "MINOR";
        }
        if (curYear <= 5 && curYear >= 3) {// sets the intensity of the first year quake
            intensity = "MEDIUM";
        }
        if (curYear >= 6 && curYear <= 8) {// sets the intensity of the first year quake
            intensity = "MAJOR";
        }
        System.out.println("This year saw a " + intensity + " quake with a strength of " + curYear);
        int major = 0;

        for (int i = 0; i < data.size(); i++) {// adds 1 to every major quake that happened
            if (data.get(i) >= 6 && curYear <= 8) {
                major += 1;
            }
        }
        System.out.println("Over the past " + data.size() + " years there have been " + major + " MAJOR quakes on tge richter scale");
    }

}
