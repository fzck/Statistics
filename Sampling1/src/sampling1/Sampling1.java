/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampling1;

import java.util.Scanner;


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
        int choice;
        do{
            System.out.println("Pick a number and press enter");
            choice = sc.nextInt();  
            
            if(choice == 1){            
                System.out.println("[1] SIMPLE RANDOM SAMPLING");               
            }
            
            else if (choice == 2){
                System.out.println("[2] SYSTEMATIC SAMPLING");                
            }
            
            else if(choice == 3){
                System.out.println("[3] STRATIFIED SAMPLING");
            }
            else if (choice >4 || choice <4){
                System.out.println("INVALID INPUT");
            }
        
           
        }while(choice != 4);
        
        System.out.println("[4] EXIT");               
        System.exit(0);
        
        
        
    }
}

