/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplingtechniques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author AUREO, ZAULECK
 */
public class SamplingTechniques {

    public static int N, n, method = 0;
    public static double percentage = 0;
    
  public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        String choice_s = "";
        int choice = 0;
        do{
            DisplayMenu();            
            choice_s = sc.next();
            
            //test input
            if(IsNumber(choice_s)){
                choice = Convert(choice_s);
            } else {                
                do{
                    System.out.println("Please enter a number only.");
                    choice_s = sc.next();
                } while (!IsNumber(choice_s));
                choice = Convert(choice_s);
            } 
            
            if(choice == 1){  
                method = 1;
                System.out.println(" SIMPLE RANDOM SAMPLING");
                ArrayList a = new ArrayList<>();
                a = GetData();
                System.out.println(" RANDOM SAMPLE");
                SRS(a);
                System.out.println();
                                
            } else if (choice == 2){
                System.out.println(" SYSTEMATIC SAMPLING");
                method = 2;
                ArrayList b = new ArrayList<>();
                b = GetData();
                Systematic(b);
                System.out.println();
                
            } else if(choice == 3){
                System.out.println(" STRATIFIED SAMPLING");
                method = 3;                
                ArrayList al = new ArrayList<>();                
                ArrayList<ArrayList> all;                
                
                al = GetData();
                Collections.sort(al);
                all = Stratified(al);
                //System.out.println("Grouped data:  "+all);
                
                //re-using the SRS method to gather samples from each inner list
                for(int i = 0,j=1; i < all.size(); i++){
                    System.out.println("Stratum "+" "+j+++":"+all.get(i));
                    System.out.println("n = "+all.get(i).size());
                    SRS(all.get(i));  
                    System.out.println();
                }    
                System.out.println();
            } else if (choice > 4 || choice < 4){
                System.out.println("INVALID INPUT");
            }
                  
        }while(choice != 4);
        
        System.out.println("[4] Quit");               
        System.exit(0);              
    }
    
    public static ArrayList GetData(){
        N = 0;
        n = 0;
        ArrayList list = new ArrayList<>();        
        Scanner sc = new Scanner(System.in);
        String testN = "";
        int population = 0;
        do{
            System.out.println("ENTER THE POPULATION SIZE: ");
            testN = sc.next();                        
            if(IsNumber(testN)){
                population = Convert(testN);
            } else {                
                do{
                    System.out.println("Please enter a number only.");
                    testN = sc.next();
                } while (!IsNumber(testN));
                
                population = Convert(testN);
            }                         
            N = population;
        }while(N <= 1);
        
        // the user is given the option to choose the desired input type
        int type = 0, testT = 0;
        String typeTest = "";
                
        do{
            System.out.println("Choose your type of input:[1]Integer  [2]Character ");
            System.out.println("Please enter a NUMBER from the choices only.");
            typeTest = sc.next();
            
            if(IsNumber(typeTest)){
                testT = Convert(typeTest);
            } else {                
                do{
                    System.out.println("Please enter a number only.");
                    typeTest = sc.next();
                } while (!IsNumber(typeTest));
                
                testT = Convert(typeTest);
            }      
            type = testT;
        }while(type < 1 || type > 2);
        
        if(type == 1){
            System.out.println("ENTER MEMBERS OF THE SAMPLING FRAME: ");
            sc.nextLine();
            System.out.println("Numbers only");
            
            for(int i = 1; i <= N; i++){
                String test;               
                do{                     
                    System.out.print("["+i+"]" +" ");                    
                    Object member = sc.next();
                    test = member.toString();                    
                    if(!IsNumber(test)){
                        System.out.println("Oops. Numbers only");
                    }
                }while (!IsNumber(test));

                list.add(test);         
            }
        } else {
            System.out.println("ENTER MEMBERS OF THE SAMPLING FRAME: ");
            sc.nextLine();
            System.out.println("Characters only");
            for(int i = 1; i <= N; i++){
                String test;               
                do{                     
                    System.out.print("["+i+"]" +" ");                    
                    Object member = sc.next();
                    test = member.toString();                    
                    if(IsNumber(test)){
                        System.out.println("Oops. Characters only");
                    } else if (test.length() > 1){
                        System.out.println("Oops. We only accept one character");
                    } else if (test.charAt(0) >= 'A' && test.charAt(0) <= 'Z'){
                        System.out.println("Oops. We accept lowecase only");
                    }
                }while (IsNumber(test) || test.length() > 1 || (test.charAt(0) >= 'A' && test.charAt(0) <= 'Z'));

                list.add(test);         
            }            
        }
        
        //displayed to verify if inputs match
        System.out.println("THIS IS THE SAMPLING FRAME: ");
        
        for (int i = 0,j = 1; i < list.size(); i++){          
           System.out.print(" ["+j+"]   ");
           System.out.println(list.get(i)); 
           j++;
       }
        System.out.println();
        System.out.println(list);
        System.out.println();
        
        //sample size
        if(method == 3){ //for stratified sampling
            int sampleForStratified = 0, sampleStrat = 0;
            String testStrat = "";
            do{
                System.out.println("SAMPLE SIZE OPTIONS:[1]DEFAULT [2]USER INPUT");
                System.out.println("Please enter a NUMBER from the choices only.");
                testStrat = sc.next();                                           
                if(IsNumber(testStrat)){
                    sampleStrat = Convert(testStrat);
                } else {                
                    do{
                        System.out.println("Please enter a number only.");
                        testStrat = sc.next();
                    } while (!IsNumber(testStrat));
                    sampleStrat = Convert(testStrat);
                }             
                sampleForStratified = sampleStrat;                               
            } while (sampleForStratified < 1 || sampleForStratified > 2);

            if (sampleForStratified == 1){
                percentage = .2;
            } else {
                do{ 
                    System.out.println("ENTER THE SAMPLE SIZE PERCENTAGE: ");
                    percentage = sc.nextDouble();
                } while(percentage < 1 || percentage >= 99);
                percentage *= (.01);
            } 
            
        } else if (method == 1 || method == 2){ 
            int sampleOption = 0, sampleOpt = 0;
            String testOpt = "";
            
            do{
                System.out.println("SAMPLE SIZE OPTIONS:[1]DEFAULT [2]USER INPUT");
                System.out.println("Please enter a NUMBER from the choices only.");
                System.out.println();
                testOpt = sc.next();
                
                if(IsNumber(testOpt)){
                    sampleOpt  = Convert(testOpt);
                } else {                
                    do{
                        System.out.println("Please enter a number only.");
                        testOpt = sc.next();
                    } while (!IsNumber(testOpt));
                    sampleOpt = Convert(testOpt);
                }             
                sampleOption = sampleOpt;                                
            } while (sampleOption < 1 || sampleOption > 2);
            
            
            if (sampleOption == 1){
                double sampleSize = (double) N * .2;
                double size = Math.ceil(sampleSize);
                n = (int) size;
                //System.out.println("Sample size is == "+n);
                
            } else {
                String test_n = "";
                int sample_n;
                do{ 
                    System.out.println("ENTER THE SAMPLE SIZE: ");
                    test_n = sc.next();
                    
                    if(IsNumber(test_n)){
                        sample_n = Convert(test_n);
                    } else {                
                        do{
                            System.out.println("Please enter a number only.");
                            test_n = sc.next();
                        } while (!IsNumber(test_n));
                        sample_n = Convert(test_n);
                    }                         
                    n = sample_n;
                } while(n < 1 || n >= N);
            }       
        }               
        return list;
    }
    
    
    //Simple Random Sampling
    public static void SRS(ArrayList<Integer> al){
       int stratumSize = al.size();
       int sampleSize;
       if(method == 3){
           sampleSize =  (int) Math.ceil(stratumSize * (percentage)); 
       } else {
           sampleSize = n;
       }
       
       ArrayList<Integer> samplingframe = al;
       ArrayList<Integer> samples = new ArrayList<>();
       ArrayList<Integer> chosenIndex = new ArrayList<>(sampleSize);
      
       Random rand = new Random();
              
       for (int i = 0; i < sampleSize; i++){  
           int r = rand.nextInt(stratumSize);
            if(!chosenIndex.contains(r)){   
                    chosenIndex.add(r);
                } else {
                    i--;
                }                          
       } 
       
       for (int i = 0; i < sampleSize; i++){
           samples.add(samplingframe.get(chosenIndex.get(i)));           
       }
       
       
       for (int i = 0; i < samples.size(); i++){
           
           System.out.print(samples.get(i));
           int j = chosenIndex.get(i) + 1;
           System.out.println(" index = "+j);
       }
       
       System.out.println("CHOSEN SAMPLE/S:"+samples);
       System.out.println();
       
    }     
    
    public static void Systematic(ArrayList sampleframe){               
        ArrayList samples = new ArrayList<>();
        Random rand = new Random();
        int k = N/n;
        int j = rand.nextInt(k);

        for(int i=1,c=j; i<=n; i++,c+=k ){
            samples.add(sampleframe.get(c));
        }
        System.out.println(samples); 
     
    }
    //groups similar inputs together   
    public static ArrayList<ArrayList> Stratified (ArrayList ungrouped){        
        ArrayList<ArrayList> grouped = new ArrayList<>();        
        boolean[] isVisited = new boolean[ungrouped.size()];
        
        for(int i = 0; i < ungrouped.size(); i++){         
            if(!(isVisited[i])){
                Object current = ungrouped.get(i);
                grouped.add( new ArrayList() );
                ArrayList currList = grouped.get(grouped.size() - 1);
                currList.add(current);

                for(int j = i+1; j < ungrouped.size(); j++){
                    Object next = ungrouped.get(j);
                    if(next.equals(current)){
                        isVisited[j] = true;
                        currList.add(next);
                    }
                }
            }
        }
        return grouped;        
    }
    
    
    
    public static boolean IsNumber(String a){
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) >= '0' && a.charAt(i) <= '9'){
                return true;
            }
        }
        return false;
    }   
    
    public static boolean IsDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
     public static int Convert(String s){       
        int number = Integer.parseInt(s);
        
        return number;
    }
     
    
    
    public static void DisplayMenu(){
        System.out.println();
        System.out.println("Basic Sampling Methods");
        System.out.println();
        System.out.println("[1] SIMPLE RANDOM SAMPLING");
        System.out.println("[2] SYSTEMATIC SAMPLING");
        System.out.println("[3] STRATIFIED SAMPLING");
        System.out.println("[4] EXIT");
        System.out.println();
        System.out.println("Pick a number and press enter");
    }
    
}
    
    
