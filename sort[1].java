import java.util.*;
import java.sql.Timestamp;
public class sort {

	public static void main(String[] args) {
		System.out.print("How many numbers from 1-16 in the array? 2^");
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt();
		int val = 1;
        for(int i = 0; i < number; i++)
        	val = val * 2;
        int array[] = new int[val];// array for insertion sort
        int arrm[] = new int[val]; // array for merge sort
        int arrq1[] = new int[val]; // array for reg quick sort
        int arrq2[] = new int [val]; // array for quick and merge sort hybrid
        int arrq3[] = new int[val]; // array for random quick sort
        Random r = new Random();
        System.out.println("\nUnsorted List: ");
        for(int i = 0; i < array.length; i++) {
        	array[i] = r.nextInt(10000)+ 1; // placing random values into array
        	arrm[i] = array[i]; // copying each number from array to all other arrays
        	arrq1[i] = array[i];
        	arrq2[i] = array[i];
        	arrq3[i] = array[i];
        	System.out.print(array[i] + " ");
        	
        }
        Date datestart1 = new Date(); // timestamp to measure algorithms
        long timestart1 = datestart1.getTime();

        System.out.println("\n\nInsertion Sort: ");
        insertSort(array, 0, array.length-1);
        print(array);
        datestart1 = new Date();
        long timeend1 = datestart1.getTime();
        long inserttime = timeend1 - timestart1;

        datestart1 = new Date();
        timestart1 = datestart1.getTime();
        System.out.println("\nMerge Sort: ");
        mergeSort(arrm,0, arrm.length-1);
        print(arrm);
        datestart1 = new Date();
        timeend1 = datestart1.getTime();
        long mergetime = timeend1 - timestart1;
        
        datestart1 = new Date();
        timestart1 = datestart1.getTime();
        System.out.println("\nRegular Quick Sort: ");
        quickSort1(arrq1, 0, arrq1.length-1);
        print(arrq1);
        datestart1 = new Date();
        timeend1 = datestart1.getTime();
        long quicktime = timeend1 - timestart1;
        
        
        datestart1 = new Date();
        timestart1 = datestart1.getTime();
        System.out.println("\nQuicksort/ Insertion Sort Combo");
        quickSort2(arrq2, 0, arrq2.length-1);
        print(arrq2);
        datestart1 = new Date();
        timeend1 = datestart1.getTime();
        long quicktime2 = timeend1 - timestart1;
        
        datestart1 = new Date();
        timestart1 = datestart1.getTime();
        System.out.println("\nRandomized Quicksort");
        if(arrq3.length > 16)//only randomizes when arrq3 is higher than 16
        {
        	Random rand = new Random();
        	int pivotitem = rand.nextInt(arrq3.length); //finding random
        	int t = arrq3[pivotitem]; //swapping random with start
        	arrq3[pivotitem] = arrq3[0];
        	arrq3[0] = t;
        }
        quickSort3(arrq3,0, arrq3.length-1);
        print(arrq3);
        datestart1 = new Date();
        timeend1 = datestart1.getTime();
        long quicktime3 = timeend1 - timestart1;
        
        System.out.println("\nTime efficiency: ");
        System.out.println("Insertion Sort: " + inserttime + " milliseconds");
        System.out.println("Merge Sort: " + mergetime + " milliseconds");
        System.out.println("Quick Sort 1: " + quicktime + " milliseconds");
        System.out.println("Quick Sort 2: " + quicktime2 + " milliseconds");
        System.out.println("Quick Sort 3: " + quicktime3 + " milliseconds");
	}  
	public static void print(int arr[])
	{
		for(int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}
	public static void insertSort(int arr[], int start, int end)//Insertion Sort
	{
		for(int i = start+1; i <= end; i++)
		{
			int target = arr[i];
			int j = i-1;
			while(j>=0 && target < arr[j])
			{
				arr[j+1] = arr[j];
			    j--;
			}
			arr[j+1] = target;
		}
			
	}
	public static void merge(int arr[], int start, int mid, int end)//merge sort
	{
		int temp[] = new int[end - start + 1];
		int i = start;
		int j = mid + 1;
		int k = 0;
		while(i <= mid && j <= end)
		{
			if(arr[i] <= arr[j])
			{
				temp[k] = arr[i];
				k++;
				i++;
			}
			else
			{
				temp[k] = arr[j];
				k++;
				j++;
			}
		}
		while(i <= mid)
		{
			temp[k] = arr[i];
			k++;
			i++;
		}
		while(j <= end)
		{
			temp[k] = arr[j];
			k++;
			j++;
			
		}
		for(i = start; i <= end; i++)
		{
			arr[i] = temp[i - start];
		}
	}
	public static void mergeSort(int arr[], int start, int end) // merge sort
	{
		if(start < end)
		{
			int mid = (start + end) /2;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);
			merge(arr, start, mid, end);
		}
	}
	
    public static void quickSort1(int arr[], int start, int end)// regular quick sort
    {
    	if(start < end)
    	{
    		int pivotpoint = partition(arr, start, end);
    		quickSort1(arr, start, pivotpoint-1);
    		quickSort1(arr, pivotpoint + 1, end);
    	}
    }
    public static int partition(int arr[], int start, int end)
    {
    	int pivotitem = arr[start];
    	int j = start - 1;
    	for(int i = start; i <= end; i++)
    	{
    		if(arr[i] < pivotitem)
    		{
    			j++;
    			int temp = arr[j];
    			arr[j] = arr[i];
    			arr[i] = temp;
    		}
    	}
    	int temp = arr[j + 1];
    	arr[j + 1] = arr[end];
    	arr[end] = temp;
    	return j + 1;
    }
    public static void quickSort2(int arr[], int start, int end) //insertion and quick sort combo
    {
    	if(start < end)
    	{
    		if((end - start) <= 16)
    			insertSort(arr, start, end);
    		else
    		{
    			int pivotpoint = partition(arr, start, end);
    			quickSort2(arr, start, pivotpoint - 1);
    			quickSort2(arr, pivotpoint + 1, end);
    		}
    	}
    }

    public static void quickSort3(int arr[], int start, int end)//swapped rand and start before method
    {
    	if(start < end)
    	{    		
    		int pivotpoint = partition(arr, start,end);
    		quickSort3(arr, start, pivotpoint - 1);
    		quickSort3(arr, pivotpoint + 1, end);
    	}
    }

}
