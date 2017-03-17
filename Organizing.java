/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizing2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author localuser
 */
public class Organizing2 {

    /**
     * @param args the command line arguments
     */
    public static int N;
    public static void main(String[] args) {
        // TODO code application logic here
    Scanner sc = new Scanner(System.in);
        String choice_s = "";
        int choice = 0;
        
        do{
            DisplayMenu();            
            choice_s = sc.next();
            String title="";
            Scanner s = new Scanner(System.in);
            
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
                System.out.println("*** CATEGORICAL ***");
                System.out.println();
                System.out.println("TITLE(title of data set)");
                //sc = new Scanner(System.in);
                
                
                title = s.nextLine();
                
                
                System.out.println("this is the title  "+title);
                
                GetData();
                                
            } else if (choice == 2){
                System.out.println("*** NUMERICAL ***");
                System.out.println();
                System.out.println("TITLE(title of data set)");
                title = s.nextLine();
                
                System.out.println("this is the title  "+title);
                        
                
            } else if(choice == 3){
                System.out.println("*** QUIT ***");
            }
        }while(choice != 3);
        
        System.out.println("Thank you for your time.");               
        System.exit(0);              
        
     }
    
    
     
    public static void DisplayMenu(){
        System.out.println();
        System.out.println("*** SUMMARIZING AND PRESENTING DATA ***");
        System.out.println();
        System.out.println("[1] CATEGORICAL");
        System.out.println("[2] NUMERICAL");
        System.out.println("[3] QUIT");
        System.out.println();
        System.out.println("Please pick a number from the choices and press Enter");
    }
       
    public static boolean IsNumber(String a){
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) >= '0' && a.charAt(i) <= '9'){
                return true;
            }
        }
        return false;
    }  
    
    public static int Convert(String s){       
        int number = Integer.parseInt(s);
        
        return number;
    }
    
    public static ArrayList GetData(){
        N = 0;
        
        ArrayList list = new ArrayList<>();        
        Scanner sc = new Scanner(System.in);
        String testN = "";
        int population = 0;
        do{
            System.out.println();
            System.out.println("Enter the maximun number of inputs: ");
            
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
        
        
        for(int i = 1; i <= N; i++){
                String test = "";  
                Scanner j = new Scanner(System.in);
                 System.out.print("["+i+"]" +" ");                    
                    Object member;
                    member = j.nextLine();
                    test = member.toString();  
                    
               /* do{                     
                    System.out.print("["+i+"]" +" ");                    
                    Object member = sc.next();
                    test = member.toString();                    
                    if(!IsNumber(test)){
                        System.out.println("Oops. Numbers only");
                    }
                }while (!IsNumber(test));
                       */
                list.add(test);         
            }
        System.out.println("this is the list"+list);
        return list;
    }
    
}
