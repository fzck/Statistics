/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descriptive;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author ADMIN
 */
public class Descriptive {

    /**
     * @param args the command line arguments
     */
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
                System.out.println();
                System.out.println("*** UNGROUPED DATA ***");
                ArrayList a = new ArrayList<>();
                a = GetData();
                
                System.out.println(getMean(a));
                System.out.println(getMedian(a));
                System.out.println(getMode(a));
                
                
                                
            } else if (choice == 2){
                System.out.println("*** GROUPED DATA ***");
                method = 2;
                N = 0;
                n = 0;
                ArrayList<ArrayList> al;
                ArrayList list = new ArrayList<>();  
                ArrayList upper = new ArrayList<>();  
                ArrayList lower = new ArrayList<>();  
                ArrayList freq = new ArrayList<>();  

                String testN = "";
                int population = 0;
                do{
                    System.out.println();
                    System.out.println("Enter total number of class intervals: ");

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




                int type2 = 0, testT2 = 0;
                String typeTest2 = "";                
                do{ 
                    System.out.println();
                    System.out.println("** Are there open ended intervals? **");           
                    System.out.println("[1] Yes ");
                    System.out.println("[2] No ");
                    System.out.println();
                    System.out.println("Please pick a number from the choices above.");          
                    typeTest2 = sc.next();            
                    if(IsNumber(typeTest2)){
                        testT2 = Convert(typeTest2);
                    } else {                
                        do{
                            System.out.println("Please enter a number only.");
                            typeTest2 = sc.next();
                        } while (!IsNumber(typeTest2));

                        testT2 = Convert(typeTest2);
                    }      
                    type2 = testT2;
                }while(type2 < 1 || type2 > 2);

                if(type2 == 1){

                } else {

                }

                int type = 0, testT = 0;
                String typeTest = "";

                do{ 
                    System.out.println();
                    System.out.println("** INPUT OPTIONS **");           
                    System.out.println("[1] Integer ");
                    System.out.println("[2] Float ");
                    System.out.println();
                    System.out.println("Please pick a number from the choices above.");

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
                    System.out.println();
                    System.out.println("ENTER class limits and frequecies: ");
                    sc.nextLine();
                    System.out.println("Integers only");

                    for(int i = 1; i <= N; i++){
                        String test1 = "";  
                            boolean flag1 = true;
                            while(flag1 == true){                
                                System.out.print("["+i+"]" +" ");                    
                                Object member = sc.next();
                                test1 = member.toString();
                                int in;

                                try {
                                    member = sc.nextLine();   
                                    in = Integer.valueOf(test1);
                                if (in < 0) {
                                  System.out.println("oops, no negatives");
                                } else {
                                    System.out.println("Input accepted ");
                                    flag1 = false;
                                }
                              } catch(NumberFormatException e) {
                                  System.out.println("Please enter an integer only");
                              }

                               if(flag1 == false){ 
                                    upper.add(test1);
                               }
                            }  
                            String test2 = "";  
                            boolean flag2 = true;
                            while(flag2 == true){                
                                System.out.print("["+i+"]" +" ");                    
                                Object member2 = sc.next();
                                test2 = member2.toString();                   
                                int in2;                                       
                                try {
                                    member2 = sc.nextLine();                        
                                    in2 = Integer.valueOf(test2);
                                if (in2 < 0) {
                                  System.out.println("oops, no negatives");
                                } else {
                                    System.out.println("Input accepted ");
                                    flag2 = false;
                                }
                              } catch(NumberFormatException e) {
                                  System.out.println("Please enter an integer only");                     
                              }                   
                               if(flag2 == false){ 
                                    lower.add(test2);
                               }
                            }            
                            String test3 = "";  
                            boolean flag3 = true;
                                while(flag3 == true){                
                                System.out.print("["+i+"]" +" ");                    
                                Object member3 = sc.next();
                                test3 = member3.toString();

                                int in3;

                                try {
                                    member3 = sc.nextLine();   

                                    in3 = Integer.valueOf(test3);
                                if (in3 < 0) {
                                  System.out.println("oops, no negatives");
                                } else {
                                    System.out.println("Input accepted ");
                                    flag3 = false;
                                }
                              } catch(NumberFormatException e) {
                                  System.out.println("Please enter an integer only");

                              }

                               if(flag3 == false){ 
                                    freq.add(test3);
                               }
                            }            
                        }
                } else {
                    System.out.println();
                    
                    sc.nextLine();
                    System.out.println("Floats only");
                    for(int i = 1; i <= N; i++){
                      Double test = 0.0;
                      System.out.print("["+i+"]" +" ");
                      while (!sc.hasNextFloat()) {                       
                                System.out.println("Invalid input. Try again");
                                sc.next();
                            }                    
                            test = sc.nextDouble();                                               
                            upper.add(test);  
                      Double test2 = 0.0;
                      System.out.print("["+i+"]" +" ");
                      while (!sc.hasNextFloat()) {                       
                                System.out.println("Invalid input. Try again");
                                sc.next();
                            }                    
                            test2 = sc.nextDouble();                                               
                            lower.add(test2);
                      Double test3 = 0.0;
                      System.out.print("["+i+"]" +" ");
                      while (!sc.hasNextFloat()) {                       
                                System.out.println("Invalid input. Try again");
                                sc.next();
                            }                    
                            test3 = sc.nextDouble();                                               
                            freq.add(test3);

                    }


                }

                
                System.out.println();
                System.out.println(upper);
                System.out.println(lower);
                System.out.println(freq);
                System.out.println();
                
                ArrayList <Double> up = new ArrayList<>();
                ArrayList <Double> low = new ArrayList<>();
                ArrayList <Double> f = new ArrayList<>();
                ArrayList <Double> classmarks = new ArrayList<>();
                ArrayList <Double> fx = new ArrayList<>();
                ArrayList <Double> fx2 = new ArrayList<>();
                
                for(int i = 0; i < N; i++){
                    up.add(Double.parseDouble(upper.get(i).toString()));
                    low.add(Double.parseDouble(lower.get(i).toString()));
                    f.add(Double.parseDouble(freq.get(i).toString()));
                    
                }

                
                double mid = (up.get(0) + low.get(0)) / 2;
                classmarks.add(mid);
                for(int i = 1; i <N; i++){
                    classmarks.add((up.get(i)+low.get(i))/2);
                }      
                
                double fxs = (f.get(0) * classmarks.get(0));
                fx.add(fxs);
                for(int i = 1; i <N; i++){
                    fx.add((f.get(i) * classmarks.get(i)));
                }   
                
                double fxs2 = (fx.get(0) * classmarks.get(0));
                fx2.add(fxs2);
                for(int i = 1; i <N; i++){
                    fx2.add((fx.get(i) * classmarks.get(i)));
                }   
       
                System.out.println(up);
                System.out.println(low);
                System.out.println(f);
                System.out.println(classmarks);
                System.out.println(fx);
                System.out.println(fx2);
                
               
                double totalf = 0.0, totalfx = 0.0, totalfx2= 0.0;
                
                for(int i = 0; i < N; i++){
                    totalf += f.get(i);
                    totalfx += fx.get(i);
                    totalfx2 += fx2.get(i);
                }
                
                System.out.println(totalf);
                System.out.println(totalfx);
                System.out.println(totalfx2);
                
                
                double mean = totalfx/totalf;                
                System.out.println(mean);
                
                System.out.println("Median not included");
                
                
                
            } else if (choice > 3 || choice < 3){
                System.out.println("INVALID INPUT");
            }
                  
        }while(choice != 3);
        
        System.out.println("Thank you for your time.");               
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
            System.out.println();
            System.out.println("ENTER THE SAMPLE SIZE: ");
            
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
            System.out.println();
            System.out.println("** INPUT OPTIONS **");           
            System.out.println("[1] Integer ");
            System.out.println("[2] Float ");
            System.out.println();
            System.out.println("Please pick a number from the choices above.");
            
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
            System.out.println();
            System.out.println("ENTER MEMBERS OF THE SAMPLING FRAME: ");
            sc.nextLine();
            System.out.println("Integers only");
            
            for(int i = 1; i <= N; i++){
                String test = "";  
                boolean flag = true;
                    while(flag == true){                
                    System.out.print("["+i+"]" +" ");                    
                    Object member = sc.next();
                    test = member.toString();
                   
                    int in;
                                       
                    try {
                        member = sc.nextLine();   
                     
                        in = Integer.valueOf(test);
                    if (in < 0) {
                      System.out.println("oops, no negatives");
                    } else {
                        System.out.println("Input accepted ");
                        flag = false;
                    }
                  } catch(NumberFormatException e) {
                      System.out.println("Please enter an integer only");
                      
                  }
                    
                   if(flag == false){ 
                        list.add(test);
                   }
                }                      
            }
        } else {
            System.out.println();
            System.out.println("ENTER MEMBERS OF THE SAMPLING FRAME: ");
            sc.nextLine();
            System.out.println("Floats only");
            for(int i = 1; i <= N; i++){
              Double test = 0.0;
              System.out.print("["+i+"]" +" ");
              while (!sc.hasNextFloat()) {
                        
                        System.out.println("Invalid input. Try again");
                        sc.next();
                    }
                    
                    test = sc.nextDouble();                                               

                list.add(test);         
            }

                    
        }
         
        //displayed to verify if inputs match
        System.out.println();
        System.out.println("THIS IS THE SAMPLING FRAME: ");
        
        for (int i = 0,j = 1; i < list.size(); i++){          
           System.out.print("["+j+"]   ");
           System.out.println(list.get(i)); 
           j++;
       }
        
        System.out.println();
        System.out.println(list);
        System.out.println();
        
       
       
       return list;
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
        
  public static boolean IsFloat(String n){
        //Double.parseDouble(n);
        
        return (IsNumber(n) && (Convert(n)%1!=0));
        //return (n == Math.ceil(n));
    }
    
    
    
    public static ArrayList Reuse(ArrayList a){
        
        ArrayList b = new ArrayList<>(); 
        Collections.copy(b,a);
        return b;
    }
    
     public static void DisplayMenu(){
        System.out.println();
        System.out.println("*** DESCRIPTIVE STATISTICS ***");
        System.out.println();
        System.out.println("[1] UNGROUPED DATA");
        System.out.println("[2] GROUPED DATA");
        System.out.println("[3] QUIT");
        System.out.println();
        System.out.println("Please pick a number from the choices and press Enter");
    }
     
      
        
     public static double getMean(ArrayList al){
        double sum = 0.0;
        for(int i = 0; i < al.size(); i++){
            sum += Double.parseDouble(al.get(i).toString());
        }
        System.out.println(sum/al.size());
        
        double temp = 0;
        double mean = sum/al.size();
        ArrayList <Double> v = new ArrayList<>();
         for(int i = 0; i < N; i++){
                    v.add(Double.parseDouble(al.get(i).toString()));
         }          
        for (double a: v){
            temp += (mean-a)*(mean-a);
        }
        double variance = temp/v.size();
        System.out.println("Variance = "+variance);
        System.out.println("SD = "+Math.sqrt(variance));
        
        
        
        return sum/al.size();
    }
     
     private static double getMedian(ArrayList arr){
        
        Collections.sort(arr);
        System.out.println("SORTED"+arr);
        ArrayList<Double> d = new ArrayList<>();
        
        for (int i = 0; i < arr.size(); i++){
            d.add(Double.parseDouble(arr.get(i).toString()));
        }
        
        int n = d.size();
        double mid = 0.0;

        if (n % 2 == 0){
            double a = d.get(n/2);
            double b = d.get((n+2)/2);
            mid = (a + b)/2;
        } 
        else{
            mid = d.get((n)/2);
        }
        
        System.out.println("Median = "+mid);
        
        System.out.println("Range = "+(d.get(arr.size()-1) - d.get(0)));
        
        return mid;   
        
        
    }
      public static ArrayList<Double> getMode(ArrayList al){
        Collections.sort(al);
        ArrayList<Double> d = new ArrayList<>();
        for (int i = 0; i < al.size(); i++){
            d.add(Double.parseDouble(al.get(i).toString()));
        }
        
        
        ArrayList<Double> modes = new ArrayList<>();
        TreeMap<Double, Integer> map = new TreeMap<>();
        
        int changelog=0;
        double preval=0;
        
        for(int i=0; i<al.size(); i++){
            
            if(i==0){
                preval = d.get(0);
                changelog++;
            }
            else{
                if(preval!=d.get(i))
                    changelog++;
                preval = d.get(i);

            }
            map.put(d.get(i),(map.get(d.get(i)) == null) ? 1 :
                        map.get(d.get(i)) + 1);
        }
        
        if(changelog==d.size())
            return null;
    
        else{
           
            ArrayList<Integer> frequencies = new ArrayList<>(map.values());
            Collections.sort(frequencies);
            int max = frequencies.get(frequencies.size()-1);
            
            if(max>1){
                for(Map.Entry<Double, Integer> entry: map.entrySet()){
                    if(entry.getValue().equals(max))
                        modes.add(entry.getKey());
                }
             
            }
            
            if (modes == null){
                System.out.println("no mode");
            } else if (modes.size() == 1){
                System.out.println("unimodal");
            } else if (modes.size() == 2){
                System.out.println("bimodal");
            } else {
                System.out.println("multimodal");
            }
            
            return modes;
        }
    }
    
     
}
