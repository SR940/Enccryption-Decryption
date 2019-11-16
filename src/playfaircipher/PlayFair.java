/*

 */
package playfaircipher;

import java.util.Arrays;
import java.util.stream.Collectors;
import javafx.scene.control.TextField;

/**
* @author SS940

 */
public class PlayFair {
    public static String alphaBet ="\u0985\u0986\u0987\u0988\u0989\u098a\u098b\u098f\u0990\u0993\u0994\u0995"
            + "\u0996\u0997\u0998\u0999\u099a\u099b\u099c\u099d\u099e\u099f\u09a0\u09a1\u09a2"
            + "\u09a3\u09a4\u09a5\u09a6\u09a7\u09a8\u09aa\u09ab\u09ac\u09ad\u09ae\u09af\u09b0"
            + "\u09b2\u09b6\u09b7\u09b8\u09b9\u09dc\u09dd\u09df\u09ce\u0982\u0983\u0981\u09be"
            + "\u09bf\u09c0\u09c1\u09c2\u09c3\u09c7\u09c8\u09cb\u09cc\u09cd\u09f3\u09e6\u09e7\u09e8"
            + "\u09e9\u09ea\u09eb\u09ec\u09ed\u09ee\u09ef\u0020\u0021\u0023"
            +"\u0024\u0025\u0026\u0027\u0028\u0029\u002a\u002b\u002c\u002d\u002f"
            + "\u003A\u003b\u003c\u003d\u003e\u003f\u0040\u005b\u005d\u005e\u005f\u007b\u007C\u007d";
    
    
    
     public static String initalizedMatrixBased(String keyBased)
    {
        
        StringBuilder key = new StringBuilder(keyBased);
        key.append(alphaBet);
        return key.toString(); //return  key with all alphabet letter 
        
    }
    
     public static String removeDuplicates(String alphabetBased) {
        return Arrays.asList(alphabetBased.split("")).stream().distinct().collect(Collectors.joining());
        //return alphabet based without duplication
        
        
    }
    
  
    // Take white space.........
     public static String replaceMentChar(String finalAlphabetBased)
    {
        finalAlphabetBased=finalAlphabetBased.replaceAll(" ", " ");
        return finalAlphabetBased;
    }
    
    // Put all alphabet including key in matrix form.......
     public static char[][] alphabetInitialized (String based)
   {
        int count=0;
        char[][] alphabetMatrix=new char[10][10];
        for (int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                alphabetMatrix[i][j]=based.charAt(count);
                count++;
            }
        }
        return alphabetMatrix;
     }
     //Print matrix table......
     public static void printAlphabetMatrix(char[][] alphabetMatrix)
     {
          for (int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                System.out.print("\t"+alphabetMatrix[i][j]);
                
            }
            System.out.println("\n");
        }
     }
     
     // remove dupplicate letters from key in matrix...
     public static String keyFormation(String key)
     {
         String alphabetBased = initalizedMatrixBased(key);
         String finalalphabebesd = removeDuplicates(alphabetBased);
       
         return removeDuplicates(replaceMentChar(finalalphabebesd));
     }
     
     //  Check for the length of message and  if any letter is repeated........
     public static String plainTextFormation(String plainText)
     {
         StringBuilder plainTextTwo = new StringBuilder(plainText);
         
         for(int i=0;i<plainTextTwo.length()-1;i++)
         {
             char temp = plainTextTwo.charAt(i);
             if(temp == plainTextTwo.charAt(i+1))
             {
                    plainTextTwo.insert(i+1,"$");
                    i+=3;
             }
         }
         if (plainTextTwo.length() % 2 !=0 )
         {
             plainTextTwo.append("#");
         }
         
         return plainTextTwo.toString();
     }
    
     
     public static int[] indexOfcharr (char letter , char[][] alphabetbased)
     {
         int rowIndex=0;
         int coloumsIndex=0;
         int[] charIndex= new int[2];
         for(int i=0;i<10;i++)
         {
             for (int j=0;j<10;j++)
             {
            if (alphabetbased[i][j]==letter)
                 {
                     rowIndex=i;
                     coloumsIndex=j;
                 }
             }
         }
         charIndex[0]=rowIndex;
         charIndex[1]=coloumsIndex;
         return charIndex;
     }
     
    // Encryption process begin here..........
    public static String encyptedAlogirthm(String plainText, char[][] alphaMatrix )
    {
       String encyptedCipher="";
        char firstChar;
        char secondChar;
        int[] indexOF1stChar = new int[2] ;
        int[] indexOF2ndChar = new int[2] ;
        for (int i=0;i<plainText.length();i+=2)
        {
            
            firstChar = plainText.charAt(i);
            indexOF1stChar = indexOfcharr(firstChar, alphaMatrix);
            secondChar = plainText.charAt(i+1);
            indexOF2ndChar = indexOfcharr(secondChar, alphaMatrix);
           //same row......
            if (indexOF1stChar[0] == indexOF2ndChar[0])
            {
                if (indexOF1stChar[1] < 9)
                    indexOF1stChar[1]++;
                else
                    indexOF1stChar[1] = 0;
                if (indexOF2ndChar[1] < 9)
                    indexOF2ndChar[1]++;
                else
                    indexOF2ndChar[1] = 0;
            }
            //Same column.........
             else if (indexOF1stChar[1] == indexOF2ndChar[1])
            {
                if (indexOF1stChar[0] < 9)
                    indexOF1stChar[0]++;
                else
                    indexOF1stChar[0] = 0;
                if (indexOF2ndChar[0] < 9)
                    indexOF2ndChar[0]++;
                else
                    indexOF2ndChar[0] = 0;
            }
             // for rectangel..........
            else
            {
                int temp = indexOF1stChar[1];
                indexOF1stChar[1] = indexOF2ndChar[1];
                indexOF2ndChar[1] = temp;
            }
            encyptedCipher = encyptedCipher + alphaMatrix[indexOF1stChar[0]][indexOF1stChar[1]]
                    + alphaMatrix[indexOF2ndChar[0]][indexOF2ndChar[1]];
        }
        return encyptedCipher;
        }
    
    /////////
    
    
    
    // Decryption process begin from here........
    public static String decyptedAlogirthm(String cipherText, char[][] alphaMatrix )
    {
       String plaintext="";
        char firstChar;
        char secondChar;
        int[] indexOF1stChar = new int[2] ;
        int[] indexOF2ndChar = new int[2] ;
        for (int i=0;i<cipherText.length();i+=2)
        {
            
            firstChar = cipherText.charAt(i);
            indexOF1stChar = indexOfcharr(firstChar, alphaMatrix);
            secondChar = cipherText.charAt(i+1);
            indexOF2ndChar = indexOfcharr(secondChar, alphaMatrix);
           //For same row 
            if (indexOF1stChar[0] == indexOF2ndChar[0])
            {
                if (indexOF1stChar[1] > 0)
                    indexOF1stChar[1]--;
                else
                    indexOF1stChar[1] = 9;
                if (indexOF2ndChar[1] > 0)
                    indexOF2ndChar[1]--;
                else
                    indexOF2ndChar[1] = 9;
            }
            // for same column
             else if (indexOF1stChar[1] == indexOF2ndChar[1])
            {
                if (indexOF1stChar[0] > 0)
                    indexOF1stChar[0]--;
                else
                    indexOF1stChar[0] = 9;
                if (indexOF2ndChar[0] > 0)
                    indexOF2ndChar[0]--;
                else
                    indexOF2ndChar[0] = 9;
            }
             //For  ractangle........
            else
            {
                int temp = indexOF1stChar[1];
                indexOF1stChar[1] = indexOF2ndChar[1];
                indexOF2ndChar[1] = temp;
            }
            plaintext = plaintext + alphaMatrix[indexOF1stChar[0]][indexOF1stChar[1]]
                    + alphaMatrix[indexOF2ndChar[0]][indexOF2ndChar[1]];
        }
        return plaintext;
        }

    
}
