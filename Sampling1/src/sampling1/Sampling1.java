/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampling1;

import java.util.*;



/**
 *
 * @author ADMIN
 */
public class Sampling1 {

    /**
     * @param args the command line arguments
     */
    public static int N, n;
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("MENU");
        System.out.println();
        System.out.println("[1] SIMPLE RANDOM SAMPLING");
        System.out.println("[2] SYSTEMATIC SAMPLING");
        System.out.println("[3] STRATIFIED SAMPLING");
        System.out.println("[4] EXIT");
        
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Pick a number and press enter");
            choice = sc.nextInt();  
            
            if(choice == 1){            
                System.out.println(" SIMPLE RANDOM SAMPLING");
                SRS();
                
            }
            
            else if (choice == 2){
                System.out.println(" SYSTEMATIC SAMPLING");  
                
            }
            
            else if(choice == 3){
                System.out.println(" STRATIFIED SAMPLING");
            }
            else if (choice > 4 || choice < 4){
                System.out.println("INVALID INPUT");
            }
        
           
        }while(choice != 4);
        
        System.out.println("[4] EXIT");               
        System.exit(0);
               
    }
    
    public static ArrayList<Integer> GetData(){
        N = 0;
        n = 0;
        ArrayList<Integer> list = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER THE POPULATION: ");
        N = sc.nextInt();
        
        System.out.println("ENTER MEMBERS OF SAMPLING FRAME: ");
        sc.nextLine();
        for(int i = 1; i <= N; i++){
            System.out.print("["+i+"]" +" ");
            int member = sc.nextInt();
            list.add(member);    
            
        }
        //displayed to verify if inputs match
        System.out.println("THIS IS THE SAMPLING FRAME: ");
        System.out.println(list);
        
       
        System.out.println("ENTER THE SAMPLE SIZE: ");
        n = sc.nextInt();
        
        
        return list;
    }
        
        
       
   
    
    public static void SRS(){
       ArrayList<Integer> sampleframe = GetData();
       ArrayList<Integer> samples = new ArrayList<>();
       System.out.println(sampleframe);
             
       
       Random rand = new Random();
       Integer r;
       //add constraints
       int chosen = 0;
       for (int i = 0; i < n; i++){  
           
           r = rand.nextInt(N);
           System.out.println("r "+r);
              if(!samples.contains(r))
               chosen = sampleframe.get(r);
                samples.add(chosen);
               //System.out.println(chosen);
       }
       
       System.out.println(sampleframe);  
       System.out.println(samples); 
    }
               
    
}

