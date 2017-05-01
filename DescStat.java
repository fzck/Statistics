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
import java.util.Random;
import java.util.Scanner;

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
                
                
                                
            } else if (choice == 2){
                System.out.println("*** GROUPED DATA ***");
                method = 2;
                ArrayList b = new ArrayList<>();
                b = GetData();
                
                
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
    
    
    public static ArrayList<ArrayList> GetData2(){
        N = 0;
        n = 0;
        ArrayList<ArrayList> al;
        ArrayList list = new ArrayList<>();        
        Scanner sc = new Scanner(System.in);
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
    
    
    
    
    
    public static void Systematic(ArrayList sampleframe){               
        ArrayList samples = new ArrayList<>();
        ArrayList indices = new ArrayList<>();
        Random rand = new Random();
        int k = N/n;
        int j = rand.nextInt(k);

        for(int i=1,c=j; i<=n; i++,c+=k ){
            samples.add(sampleframe.get(c));
            
            indices.add(c);
        }
        System.out.println();
        System.out.println("These are the samples:");
        for (int i = 0; i < n; i++){
               j = (int) indices.get(i) + 1;
               System.out.print("["+j+"] ");
               System.out.println(samples.get(i));
               
        }        
     
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
        return mid;              
        
    }
}
