/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stratified;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Stratified {

    /**
     * @param args the command line arguments
     */
    public static int N,n;
    public static double percentage;
    public static void main(String[] args) {
        
         System.out.println(" STRATIFIED SAMPLING");
                ArrayList<Integer> al = new ArrayList<>();
                ArrayList<ArrayList> all;
                al = GetData3();
                all = Stratified(al);
                
                for(ArrayList a:all){
                    System.out.println(a);
                    SRSforStratum(a);
                }    
    }
    
     public static ArrayList GetData3(){
        N = 0;
        n = 0;
        ArrayList list = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("ENTER THE POPULATION SIZE: ");
            N = sc.nextInt();
        }while(N <= 1);
        
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
            percentage = .2;
        } else {
            do{ 
                System.out.println("ENTER THE SAMPLE SIZE PERCENTAGE: ");
                percentage = sc.nextDouble();
            } while(percentage < 1 || percentage >= 99);
            percentage *= (.01);
        }
        System.out.println("Percentage = "+percentage); 
               
        return list;
    }
    
    
    public static void SRSforStratum(ArrayList<Integer> al){
       int stratumSize = al.size();
       int sampleSize =  (int) Math.ceil(stratumSize * (percentage)); 

       ArrayList<Integer> sampleframe = al;
       ArrayList<Integer> samples = new ArrayList<>();
       ArrayList<Integer> chosenIndex = new ArrayList<>(sampleSize);
       System.out.println("size of stratum "+stratumSize);
       System.out.println("size of sample "+sampleSize);
       System.out.println("size of sampleLIst "+samples.size());
       System.out.println("This is the stratum"+sampleframe);
         
       Random rand = new Random();
       
       
       int chosen = 0;
       for (int i = 0; i < sampleSize; i++){  
           int r = rand.nextInt(stratumSize);
            if(!chosenIndex.contains(r)){   
                    System.out.println("SUCCESSFULLY ENTERED THE LOOP");
                    chosenIndex.add(r);
                } else {
                    i--;
                }
            
                
            
       } 
       System.out.println("These are the indices"+chosenIndex);
            
    }     
    
    public static ArrayList<ArrayList> Stratified (ArrayList al){ 
    
        Collections.sort(al);
        ArrayList< ArrayList > alal = new ArrayList<>();
        boolean[] visited = new boolean[al.size()];

        for(int i = 0; i < al.size(); i++){
            Boolean el = visited[i];
            if( !el ){
                Object obj1 = al.get(i);
                alal.add( new ArrayList() );
                ArrayList curr = alal.get(alal.size() - 1);
                curr.add( obj1 );

                for(int j = i+1; j < al.size(); j++){
                    Object obj2 = al.get(j);
                    if(obj2.equals(obj1)){
                        visited[j] = true;
                        curr.add( obj2 );
                    }
                }
            }
        }
        
      return alal;  
      
    }
    
    
}
