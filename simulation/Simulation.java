/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.util.Scanner;
import static simulation.AureoZauleckAnsLab1.Convert;
import static simulation.AureoZauleckAnsLab1.IsNumber;

/**
 *
 * @author ADMIN
 */
public class Simulation {

    /**
     * @param args the command line arguments
     */
    private static AureoZauleckAnsLab1 anslab1;
    private static AureoZauleckAnsLab2 anslab2;
    private static AureoZauleckAnsLab3 anslab3;
    
    public Simulation(){
        main(null);
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        int start = DisplayCredits();
        if (start == 2){
            System.exit(0);
        } else {
            
        }
        
        int type = Menu();
        if(type == 1){
            anslab1 = new AureoZauleckAnsLab1();        
        } else if (type == 2){
            anslab2 = new AureoZauleckAnsLab2();
        } else if (type == 3){
            anslab3 = new AureoZauleckAnsLab3();
        }
        
        
               
        
    }
    
    public static int DisplayCredits(){
        System.out.println("*****************************");
        System.out.println();
        System.out.println("AZ MINI - STATISTICAL PACKAGE");
        
        System.out.println();
        System.out.println(" A compilation of laboratory\n  exercises for CMSC 105");
        System.out.println();
        System.out.println();
        System.out.println("\tDevelopers:");
        System.out.println("   Florence Athena Zauleck");
        System.out.println("      Julie Mae Aureo");
        System.out.println();
        System.out.println("*****************************");
        System.out.println();
        System.out.println("[1] Continue");
        System.out.println("[2] Quit");
        Scanner sc = new Scanner(System.in);
        
        int type = 0, testT = 0;
        String typeTest = "";   
        do{
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
        
        return type;        
    }
    
    public static int Menu(){
        Scanner sc = new Scanner(System.in); 
        int type = 0, testT = 0;
        String typeTest = "";               
        do{ 
            System.out.println();
            System.out.println("** MINI-STATISTICAL PACKAGE (MINI-SP) **");           
            System.out.println("[1] SIMULATING DIFFERENT TYPES OF SAMPLING TECHNIQUES ");
            System.out.println("[2] ORGANIZING, SUMMARIZING, AND PRESENTING DATA ");
            System.out.println("[3] MEASURES OF CENTRAL TENDENCY AND DISPERSION ");
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
        }while(type < 1 || type > 3);
        
        return type;
    }
    
}
