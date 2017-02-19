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
                System.out.println("[1] SIMPLE RANDOM SAMPLING");
                GetData();
            }
            
            else if (choice == 2){
                System.out.println("[2] SYSTEMATIC SAMPLING");                
            }
            
            else if(choice == 3){
                System.out.println("[3] STRATIFIED SAMPLING");
            }
            else if (choice > 4 || choice < 4){
                System.out.println("INVALID INPUT");
            }
        
           
        }while(choice != 4);
        
        System.out.println("[4] EXIT");               
        System.exit(0);
               
    }
    
    public static ArrayList<String> GetData(){
        int N = 0, n = 0;
        ArrayList<String> list = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER THE POPULATION: ");
        N = sc.nextInt();
        
        System.out.println("ENTER THE SAMPLING FRAME");
        sc.nextLine();
        for(int i = 1; i <= N; i++){
            System.out.print("["+i+"]" +" ");
            String member = sc.nextLine();
            list.add(member);    
            
        }
        //displayed to check if inputs match
        System.out.println("This is the sampling frame:");
        System.out.println(list);
        return list;
    }
        
        
       
   
    
    public void SRS(){
        
    }
    
}

