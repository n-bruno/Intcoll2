//*********************************************************************
// FILE NAME    : Intcoll2.java
// DESCRIPTION  : This file contains the class Intcoll2.
//*********************************************************************

import java.util.*;

public class Intcoll2 {

////////////////////////////////////////////
//  Variables
////////////////////////////////////////////
    private int[] c;
    private int how_many = 0;

    /*this creates the array "c" that's private to this class.
    
    
    How_many stores the size so the array doesn't go out of bounds and crash the program
     */
////////////////////////////////////////////
//  Constructors
////////////////////////////////////////////
    public Intcoll2() {
        create(500);
    }

    public Intcoll2(int i) {
        create(i);
    }

    private void create(int i) {
        c = new int[i];
    }

////////////////////////////////////////////
//  copy
////////////////////////////////////////////
    public void copy(Intcoll2 obj) {
        /*
        First we check if the two objects have the same pointer,
        if they do, they are already equal. If we execute the code we
        will delete the information and copy over a blank array.
         */
        if (this != obj) {
            if (this.how_many != 0 && obj.how_many != 0) {
                c = new int[obj.c.length]; //overwrite array c
                for (int j = 0; obj.c[j] < how_many; j++) {
                    c[j] = obj.c[j];
                }
            }
        }
    }

////////////////////////////////////////////
//  Belongs
////////////////////////////////////////////
    public boolean belongs(int i) {
        /*
        The for loop will terminate when
            * j is more than how_many
            * c[j] s i
         */

        int j;
        for (j = 0; (j < how_many - 1) && (c[j] != i); j++) {
        }
        return ((i > 0) && (c[j] == i));
        /*
        Some interesting code here. It's very too the point and efficient.

        Firstly, the method will always return false when i is nagative.

        Also we will get false if c[j] is not equal to i.
        The only way for this statement to be false is if we exited the while
        loop because c[j] = 0, or in other words didn't find an instance
        of i in the array.
         */
    }
////////////////////////////////////////////
//  Insert
////////////////////////////////////////////

    public void insert(int integer) {
        if (integer > 0) { //check if the value is positive, if not, don't insert anything
            /*
            This while for will keep incrementing j if both are true:
                * j is more than how_many
                * c[j] is the value we want to insert
             */

            int j = 0;
            while((j < how_many) && (c[j] != integer))
                j++;
            
            if (j == how_many) {
                /*
                If the array c is full, we'll have to increase its size.
                Due to the nature of arrays, we cannot increase the size on
                the fly, instead we have to overwrite it with an array
                of a larger size.

                To prevent data loss, c will have all of its elements stored
                in newArray before being overwritten.

                After c is overwritten, elements from newArray will be put
                back into array c that is one index longer.
                 */

                int[] newArray = new int[how_many + 1]; //create new array for temporary storage
                for (int i = 0; i < how_many; i++) { //store everything in c in newArray
                    newArray[i] = c[i];
                }

                c = new int[how_many + 1];              //overwrite c with a larger size
                for (int aa = 0; aa <= how_many; aa++){ //store everything from newArray into c
                    c[aa] = newArray[aa];
                }
                //System.arraycopy(newArray, 0, c, 0, how_many + 1);
                how_many++;
            }

            c[j] = integer;       //overwrite index j with the value we want inserted

            /*System.out.println("$$$$$$$$$");      //for debug purposes
                for(int aa = 0; aa < c.length; aa++)
                    System.out.println(c[aa]);//*/
            /*System.out.println(how_many);*/
        }
    }
////////////////////////////////////////////
//  Omit
////////////////////////////////////////////

    /*
    In here we delete an element in c of the value i
     */
    public void omit(int i) {
        if (i > 0) { //if i is positive
            /*
            Finds the index where i is and stores it in j.
            If there isn't an instance of i, then j is the index where it's 0

            if j is how_many we skip over the the deletion process as i isn't in the array
            so we can't remove it
             */

            int j = 0;
            if (how_many > 0){ //this actually stops stuff from breaking from a
                               //bug that was introduced when your array size is zero
                               //and you enter a negative number and the program crashes
                while ((j < c.length - 1) & (c[j] != i)) {
                j++;
                }
            
                if (c[j] == i) { //check if c[j] is i (the value we want to remove)
                    int k = j + 1;
                    while (k < how_many) {
                        k++;
                    }

                    c[j] = c[k - 1]; //here we overwrite the element at c[j] with the last element
                    c[k - 1] = 0;    //we overwrite the element we copied over with zero to avoid duplicates
                    how_many--;

                }
            }
        }
    }

////////////////////////////////////////////
//  Howmany
////////////////////////////////////////////
    public int get_howmany() {
        return how_many;
    }

////////////////////////////////////////////
//  Print
////////////////////////////////////////////
    public void print() {
        /*
        This prints all the elements in the object's array with a for loop.
         */
        System.out.println();
        for (int j = 0; j < this.how_many; j++) {
            System.out.println(c[j]);
        }
    }

////////////////////////////////////////////
//  Equals
////////////////////////////////////////////
    public boolean equals(Intcoll2 obj) {
        boolean result = true;
        if (this.get_howmany() != obj.get_howmany() && (this != obj)) {
            for (int j = 0; (j < how_many) && result; j++) {
                result = obj.belongs(c[j]);
            }
        } return result;
    }
}
