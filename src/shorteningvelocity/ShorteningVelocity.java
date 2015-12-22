package ShorteningVelocity;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import java.io.File;      
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class ShorteningVelocity {
    public static void main(String[] args){
        //Create a file chooser
        String filename = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("D:\\"));
        fileChooser.setSelectedFile(new File("README.html"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            filename = fileChooser.getSelectedFile().getPath();
            JOptionPane.showMessageDialog(null, "You selected " + filename);
        }
        else if (result == JFileChooser.CANCEL_OPTION)
        {   
            JOptionPane.showMessageDialog(null, "You selected nothing.");
        }
        else if (result == JFileChooser.ERROR_OPTION)
        {
            JOptionPane.showMessageDialog(null, "An error occurred.");  
        }                             
            float length1 = 0;
            float length2 = 0;
            String a = null;
            String b = null;
        List<String> tmpList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File(filename));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                //if temp contains 5015.0 in the beginning of the string
                if(temp.contains("5015.0 "))    {
                    a = temp.substring(7,14);
                }
                if(temp.contains("5115.0 "))    {
                    b = temp.substring(7,14);
                }
                tmpList.add(temp);        
            }
            length1 = Float.parseFloat(a);
            length2 = Float.parseFloat(b); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] myArray = new String[tmpList.size()];
        myArray = tmpList.toArray(myArray);
        String lo = myArray[7];
        float loValue = Float.parseFloat(lo.replaceAll(".*?([\\d.]+).*", "$1"));
            
        //shortening velocity formula
        float shorteningVelocity;
        //you have to multiply by 1000 for milisecond to sec conversion
            shorteningVelocity = ((((length1 - length2)/100)/loValue)*1000);
                DecimalFormat df = new DecimalFormat("#");
                df.setMaximumFractionDigits(8);
               System.out.println(filename);
               System.out.println("Shortening Velocity: " + df.format(shorteningVelocity) 
               + " muscle lengths/second");              
               System.out.println("5015 miliseconds length: " + length1 + "mm");
               System.out.println("5115 miliseconds length: " + length2 + "mm");
               System.out.println("Lo: " + loValue + "mm");
                }
}



