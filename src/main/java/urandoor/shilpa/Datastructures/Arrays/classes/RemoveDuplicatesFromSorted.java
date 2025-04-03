package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class RemoveDuplicatesFromSorted {

    int removeDuplicates(int[] a, int n)
    {
        //naive methods :
        //adding to set
        //using hashing

        int i = 0;
        for (int j = 1; j < n; j++) { // Fast pointer
            if (a[j] != a[i]) { // Found a new unique element
                i++;
                a[i] = a[j]; // Move the unique element forward
            }
        }

        //when the loop comes out i+1 will be new size
        // Fill remaining elements with 0 (or any placeholder) - optional
        for (int j = i + 1; j < n; j++) {
            a[j] = -1; // Set unused elements to 0
        }

        return i+1;

    }
}
