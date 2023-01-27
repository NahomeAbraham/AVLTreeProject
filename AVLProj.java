import java.io.*;
import java.util.Scanner;

public class AVLProj {

     public static void main(String []args) throws IOException {

         BufferedReader input = new BufferedReader(new FileReader("books.txt"));
         String line;
         BookObject books[] = new BookObject[10];
         int numBooks = 0;

         while((line = input.readLine()) != null)  //reads one line and checks if null
         {
            String data[] = new String[3];
            data[0] = line;
            data[1] = input.readLine();
            data[2] = input.readLine();

            //data[1] = isbn
            //data[2] = title
            //data[3] = author last name
            BookObject book = new BookObject(data[0], data[1], data[2]);
            books[numBooks] = book;
            numBooks++;

         }
         input.close();

         //Insert all the AVL nodes in the AVL Tree using the AVLTree class
         //Output all rotations as shown
         /*
         * Example Output: 
            Imbalance condition occurred at inserting ISBN 12345; fixed in LeftRight Rotation
            Imbalance condition occurred at inserting ISBN 87654; fixed in Left Rotation
            Imbalance condition occurred at inserting ISBN 974321; fixed in RightLeft Rotation
         */

         AVLTree tree = new AVLTree(books);
         //insert all the AVL nodes in the AVL Tree using the AVLTree class
         //Modify the AVLTree class file provided with the project to work with AVLNode 

     }


    
    
    
}
