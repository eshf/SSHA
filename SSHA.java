package SSHA;
import java.util.*;
import java.math.*;

public class SSHA{

    public static void main(String [] args){
        int collide=0;
        int collideHash= -1;
        int count =0;

        long startTimeMillis= System.nanoTime();

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        boolean match= false;

        System.out.println("\nFind the Collisions of Hash Functions:");
        System.out.println();

        do{
            count++;
            String msgString= "Mr. Don Lee owes Fiona Eng\n" + collide + "dollars";

            String hexSSHAString= getSSHAString(msgString);

            if(hm.isEmpty()){
                hm.put(hexSSHAString, collide);
                collide++;
            }
            else{
                if(hm.containsKey(hexSSHAString)){
                    collideHash=hm.get(hexSSHAString);
                    System.out.println("Collisions:"+ collide);
                    match = true;
                }
                else{
                    hm.put(hexSSHAString, collide);
                    collide++;

                }
            }

        } while(!match);
        System.out.println("Number of Tries:"+ count);
        System.out.println("It collided at where x!=x'(prime):"+ "\nx:"+ collideHash + "\nx'(prime):" + collide);
        String line1 = "\nMr. Don Lee owes Fiona Eng:" + collideHash + "\ndollars";
        String line2 = "\nMr. Don Lee owes Fiona Eng:" + collide + "\ndollars";

        String valueHash1= getSSHAString(line1);
        String valueHash2= getSSHAString(line2);

        System.out.println("Here are the two messages with the same hash values:");
        System.out.println(line1 + "\nhash value:"+ valueHash1 + "\t" + line2 + "\nhash value:"+ valueHash2);
        System.out.println("\nProcessing Time:" + ((System.nanoTime()- startTimeMillis)) / 1000000 + "\nmilliseconds");

        }

        public static String getSSHAString(String msgString){
            String hashedMessage= SHA1.getHashM(msgString);
            //SHA-1 has output of 160 bytes, 1 character(4 bytes), 40 blocks of 4 bytes=160 bytes
            String hexSSHAString = hashedMessage.substring(30, 40);
            String HashedMessageX = convertHashHex(hashedMessage);
            String sshaHX= convertHashHex(hashedMessage);
            return hexSSHAString;
        }

        //Convert hex to binary string (hex=base 16, binary=base 2)
        public static String convertHashHex(String hh){
            String a = new BigInteger(hh, 16).toString(2);

        //Allow padding of zeroes to binary string
        //if the corresponding binary string is not in zeros (1's complement theory)
        //If the given output does not fit actual size
        if(a.length() % 4 !=0){
            //add zeroes to the given string 
            a= "0000".substring(a.length() % 4) + a;
        }
            return a;
        }
        public static String convertBinaryToHexString(String a){
            return new BigInteger(a, 2).toString(16);
        }
    }
