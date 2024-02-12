import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Merge {
    public static void main(String[] args){
        System.out.println("1: create a random array store it into a file, 2: take array from a file and sort");
        System.out.println("3: for a merge sort from an array from a file");
        Scanner number = new Scanner(System.in);
        int lmao = number.nextInt();
        switch(lmao) {
            case 1: 
            System.out.println("Enter the length of the array");
        
            Scanner myObj = new Scanner(System.in);
            int arrayLength = myObj.nextInt();

            createRandomArray(arrayLength); 
            

            break;
            case 2:
            System.out.println("Please write out the name of the file");
            Scanner myName1 = new Scanner(System.in);
            String name1 = myName1.nextLine();
            
            int[] numbers = readFileToArray(name1);
            bubbleSort(numbers);
            System.out.println("Please write out the name of the file");
            Scanner myName2 = new Scanner(System.in);
            String name2 = myName2.nextLine();
            writeArrayToFile(numbers, name2);


            break;
            
            case 3:
            System.out.println("Please write out the name of the file");
            Scanner myName3 = new Scanner(System.in);
            String name3 = myName3.nextLine();
            
            int[] numbers2 = readFileToArray(name3);
            mergeSort(numbers2, 0, numbers2.length - 1);
            System.out.println("Please write out the name of the file");
            Scanner myName4 = new Scanner(System.in);
            String name4 = myName4.nextLine();
            writeArrayToFile(numbers2, name4);
            


            break;
            default:
            System.out.println("Do you want to array to file?");
            Scanner scanner = new Scanner(System.in);
    
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();
    
            int[] arr = new int[size];
    
            for (int i = 0; i < size; i++) {
                System.out.print("Enter element " + (i + 1) + ": ");
                arr[i] = scanner.nextInt();
            }
    
            System.out.println("The array elements are:");
            for (int element : arr) {
                System.out.print(element + " ");
            }
            System.out.println("Please write out the name of the file");
            Scanner myName = new Scanner(System.in);
            String name = myName.nextLine();
            writeArrayToFile(arr, name);
            readFileToArray("filename.txt");
            break; 
        }


        




    }
//Ends of the main program
public static void createRandomArray(int arrayLength) {
    Random random = new Random();
    int[] randomArray = new int[arrayLength]; 

    for (int i = 0; i < arrayLength; i++) {  
     
        randomArray[i] = random.nextInt(100); 
        System.out.print(randomArray[i] + " ");
    }
    Scanner myObj1 = new Scanner(System.in);
    System.out.println("");
    System.out.println("What is the name of the file");
    String nameString = myObj1.nextLine();
    writeArrayToFile(randomArray, nameString);
}

//End of the create random array function (finished)
    public static void writeArrayToFile(int[] array, String filename){
        

        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
        
            for (int num : array) {
              bufferedWriter.write(Integer.toString(num));
              bufferedWriter.newLine(); // Add a newline character after each integer
            }
        
            bufferedWriter.close();
            System.out.println("Array written to file: " + filename);
          } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
          }
    }


    
//End of the write array to file function


    public static int[] readFileToArray(String filename) {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    numbers.add(number);
                    System.out.print(number + " ");
                    System.out.println("");
                } catch (NumberFormatException e) {
                    System.err.println("Invalid integer format in line: " + line); // Log or handle invalid lines
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            return new int[0]; // Return an empty array on error
        }
        
        return numbers.stream().mapToInt(Integer::intValue).toArray(); // Efficient conversion to int array
    }


public static void bubbleSort(int[] array){
    int n = array.length;
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < n - 1; i++) {
            if (array[i] > array[i + 1]) {
                // Swap elements
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                swapped = true;
            }
        }
        // Optimization: Largest element is in place, so reduce the array size for next pass
        n--;
    } while (swapped);

}
//End of the bubble function

public static void merge(int arr[], int l, int m, int r) {
    // Find sizes of two subarrays to be merged
    int n1 = m - l + 1;
    int n2 = r - m;
    
    /* Create temporary arrays */
    int L[] = new int[n1];
    int R[] = new int[n2];
    
    /*Copy data to temporary arrays*/
    for (int i = 0; i < n1; ++i)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; ++j)
        R[j] = arr[m + 1 + j];
    
    /* Merge the temporary arrays */
    
    // Initial indexes of first and second subarrays
    int i = 0, j = 0;
    
    // Initial index of merged subarray array
    int k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
    
    /* Copy remaining elements of L[] if any */
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }
    
    /* Copy remaining elements of R[] if any */
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

// Main function that sorts arr[l..r] using merge()
public static void mergeSort(int arr[], int l, int r) {
    if (l < r) {
        // Find the middle point
        int m = (l + r) / 2;
        
        // Sort first and second halves
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        
        // Merge the sorted halves
        merge(arr, l, m, r);
    }
}

// Utility function to print array of size n
public static void printArray(int arr[]) {
    int n = arr.length;
    for (int i = 0; i < n; ++i)
        System.out.print(arr[i] + " ");
    System.out.println();
}


}
