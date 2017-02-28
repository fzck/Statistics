/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az105lab1;

import java.util.*;



/**
 *
 * @author localuser
 */
public class AZ105Lab1 {

    public static int N, n;
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("MENU");
            System.out.println();
            System.out.println("[1] SIMPLE RANDOM SAMPLING");
            System.out.println("[2] SYSTEMATIC SAMPLING");
            System.out.println("[3] STRATIFIED SAMPLING");
            System.out.println("[4] EXIT");
            System.out.println();
            System.out.println("Pick a number and press enter");
            choice = sc.nextInt();  
            
            if(choice == 1){            
                System.out.println(" SIMPLE RANDOM SAMPLING");
                SRS();
                
            }
            
            else if (choice == 2){
                System.out.println(" SYSTEMATIC SAMPLING");  
                Systematic();
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
        
        int sampleOption = 0;
        do{
            System.out.println("SAMPLE SIZE OPTIONS:[1]DEFAULT [2]USER INPUT");
            sampleOption = sc.nextInt();
        } while (sampleOption < 1 || sampleOption > 2);
        
        
        if (sampleOption == 1){
            double sampleSize = (double) N * .2;
            Math.ceil(sampleSize);
            n = (int) sampleSize;
            System.out.println("samplesize == "+n);
        } else {
            do{ 
                System.out.println("ENTER THE SAMPLE SIZE: ");
                n = sc.nextInt();            
            } while(n < 1 || n >= N);
        }
        
        
        
        return list;
    }
        
        
       
   
    
    public static void SRS(){
        
       /*
        TODO: character input 
        */
       ArrayList<Integer> sampleframe = GetData();
       ArrayList<Integer> samples = new ArrayList<>();
       ArrayList<Integer> chosenIndex = new ArrayList<>(n);
       System.out.println(sampleframe);
       
       //System.out.println(n);        
       Random rand = new Random();
       Integer r;
       
       //System.out.println(r);
       
       int chosen = 0;
       for (int i = 0; i < n; i++){  
            r = rand.nextInt(N);
            
            chosenIndex.add(r);
            
            while (samples.size() != n){
                if(!chosenIndex.contains(r) && !samples.contains(sampleframe.get(r))){
                    System.out.println("r "+r);
                    chosen = sampleframe.get(r);
                    samples.add(chosen);
                } else {
                    r = rand.nextInt(N);
                }
            }
               //System.out.println(chosen);
       }
       
       System.out.println(sampleframe);  
       System.out.println(samples); 
    }
    
    
    public static void Systematic(){
        
    ArrayList<Integer> sampleframe = GetData();
    ArrayList<Integer> samples = new ArrayList<>();
    
    Random rand = new Random();

    double k = (double) N/n;
    
    
    
    System.out.println("k " +k); 
    
    int k2 = (int) Math.ceil(k); ;
    System.out.println("k2 " +k2); 
    int indexAtN = rand.nextInt(k2);
    //unsaon nato pag-check if nilapas na sa index
    for(int i=1,j=indexAtN; i<=n; i++,j+=k2 ){
        while(k2<=N){
        samples.add(sampleframe.get(j));
        }
    }
    
    System.out.println(samples); 
        
    
    }
    
    public static void Stratified(){
        
        
        
        /*
            list of lists
            store each integer in 2d
            if int or character are the same is the same. group them together
        */
        
        
    }
    
}
