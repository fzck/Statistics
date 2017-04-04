
package organizing;

//todo separate functions for table
//collapse
//add yes or no's
//add restrictions
//comebacks
//cf
//c%

//Aureo, Zauleck
//in this mp, we used the built-in APIs for the histogram and pie chart as well as the 

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Map;
import java.util.Random;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.statistics.*;
import org.jfree.ui.RefineryUtilities;


  
public class Organizing {
    

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
                
                ArrayList a = new ArrayList<>();
                ArrayList <Double> percentages = new ArrayList<>();
                ArrayList<ArrayList> all; 
                a = GetData();
                
                Collections.sort(a);
                all = Stratified(a);
                System.out.println("GROUPED DATA:  "+all);
                System.out.println("size  "+all.size());
                            
                double percent = 0.0, sum = 0.0;
                for(int i = 0; i < all.size(); i++){
                   
                    ArrayList inner = new ArrayList<>();
                    inner = all.get(i);
                    //System.out.println(inner);
                    
                    int inner_n = inner.size();
                   
                    percent = GetPercentage(N, inner_n);
                    percentages.add(percent);
                    sum += percent;
                    System.out.println("" + inner.get(0) + "\t"+"        " + percent);
                }
                 System.out.println("\t"+"total   "+ Math.ceil(sum));
               
                 System.out.println("all = "+all);
                 
            
            JFrame frame = new JFrame();
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        
            JTable table = new JTable();    
            table.setModel(new DefaultTableModel((int) (all.size() + 2),2));

            table.setValueAt("VALUE LABELS", 0, 0);
            table.setValueAt("PERCENTAGE", 0, 1);


            table.setValueAt("TOTAL = 100%", (int) (all.size() + 1), 1);

            for(int i = 0; i < all.size(); i++){
                table.setValueAt(all.get(i).get(0),i+1,0);

                table.setValueAt(new DecimalFormat("#.##").format(percentages.get(i)),i+1,1);

            }

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createTitledBorder (title));
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(300, 150);
            frame.setVisible(true);     

            
            int type = 0, testT = 0;
            String typeTest = "";
            do{ 
                System.out.println("GENERATE GRAPH?");
                System.out.println("[1] YES");
                System.out.println("[2] NO");     


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
            
            if (type == 1){   
               DefaultPieDataset dataset = new DefaultPieDataset( );
               for(int i = 0; i < all.size(); i++){
                   dataset.setValue(all.get(i).get(0).toString()+" = "+new DecimalFormat("#.##").format(percentages.get(i))+"%",percentages.get(i));
               }
               JFreeChart chart = ChartFactory.createPieChart(      
                title,  // chart title 
                dataset,        // data    
                true,           // include legend   
                true, 
                false);
                ChartPanel chartPanel = new ChartPanel( chart );        
                chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
                JFrame l = new JFrame();
                              
                l.setContentPane( chartPanel );
                l.setSize(400, 400);
                l.setVisible(true);
                
            }else{
                //do nothing?
            }
            
            
            int type2 = 0, testT2 = 0;
            String typeTest2 = "";
            do{ 
                System.out.println("REDISPLAY TABLE?");
                System.out.println("[1] YES");
                System.out.println("[2] NO");     


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
            
            if (type2 == 1){   
            
            DisplayTable(all,percentages,title);    
              
            }else{
                //do nothing?
            }
            
            
            
                                
            } else if (choice == 2){
             
               
                System.out.println("*** NUMERICAL ***");
                System.out.println();
                System.out.println("TITLE(title of data set)");
                title = s.nextLine();
                
                System.out.println("this is the title  "+title);
                
                ArrayList<Double> a = new ArrayList<>();
                //ArrayList<ArrayList> all; 
                a = GetData2();
                
                
                
//                double[] arr = {70, 36, 43, 69,82,48,34,62,35,15,
//                59,139,46,37,42,30,55,56,36,82,
//                38,89,54,25,35,24,22,9,55,19};
                
/*                
                double[] arr = {112, 100, 127,120,134,118,105,110,109,112,
                110, 118, 117, 116, 118, 122, 114, 114, 105, 109,
                107, 112, 114, 115, 118, 117, 118, 122, 106, 110,
                116, 108, 110, 121, 113, 120, 119, 111, 104, 111,
                120, 113, 120, 117, 105, 110, 118, 112, 114, 114};
*/                
                
                
                
//                
//                N = arr.length;
//                double t = 0.0;
//                for(int i = 0; i < N; i++){
//                    
//                    
//                    a.add(arr[i]);
//                }
               
                Collections.sort(a);
                System.out.println("sorted a "+a);
                double min = (double) a.get(0);
                double max = (double) a.get(N-1);
                
                System.out.println("Min" +min);
                System.out.println("Max" +max);
                
                double k =  Math.ceil(1 + 3.322*Math.log10(N));
                System.out.println("K "+k);
                
                double range = GetRange(min,max);
                System.out.println("Range "+range);
                
                double width = Math.ceil(range/k);
                System.out.println("Width " +width);
                
                
                ArrayList<Double> cl = new ArrayList<>();
                cl.add(min);
                double rest;
                for(int i = 1; i < k; i++){
                    cl.add(min+=width);                    
                }
                
                
                ArrayList<Double> cl2 = new ArrayList<>();
                double cl2min = cl.get(1) - 1;
                cl2.add(cl2min);
                for(int i = 1; i < k; i++){
                    cl2.add(cl2min+=width);
                }
                
                System.out.println("cl 1 "+cl);
                System.out.println("cl 2 "+cl2);
                
                
                
                ArrayList<Double> tlcl = new ArrayList<>();
                double tlclmin = cl.get(0) - Multiplier(cl.get(0));
                tlcl.add(tlclmin);
                for(int i = 1; i <k; i++){
                    tlcl.add(tlclmin+=width);
                }
                
                ArrayList<Double> tucl = new ArrayList<>();
                double tuclmin = cl2.get(0) + Multiplier(cl2.get(0));
                tucl.add(tuclmin);
                for(int i = 1; i <k; i++){
                    tucl.add(tuclmin+=width);
                }
                System.out.println("tlcl 1 "+tlcl);
                System.out.println("tucl 2 "+tucl);
                System.out.println("N "+N);
                
                ArrayList<Double> midList = new ArrayList<>();
                double mid = (cl.get(0) + cl2.get(0)) / 2;
                midList.add(mid);
                for(int i = 1; i <k; i++){
                    midList.add((tlcl.get(i)+tucl.get(i))/2);
                }
                
                for (int i = 0; i < k; i++){
                    System.out.println((tlcl.get(i)+tucl.get(i))/2);
                }
                
                System.out.println("mid"+midList);
                
                ArrayList<ArrayList<Double>>  freq = new ArrayList<>();
                
                
                double ctr = 0.0;                
                for(int j = 0; j < k; j++){
                    for(int i = 0; i < N; i++){                    
                        if( (a.get(i) >= tlcl.get(j)) && (a.get(i) <= tucl.get(j)) ){
                            freq.add( new ArrayList<Double>() );
                            freq.get(j).add(a.get(i));
                        }                        
                    }                    
                }
                    
                ArrayList<Double>  freqSize = new ArrayList<>();
                double size = 0.0;
                for(int i = 0; i < k; i++){
                    size = (double) freq.get(i).size();
                    freqSize.add(size);
                }
                
                ArrayList<Double>  freqPercent = new ArrayList<>();
                for(int i = 0; i < k; i++){
                                        
                    freqPercent.add(freqSize.get(i)/N*100);
                }
                
                ArrayList<Double> cfs = new ArrayList<>();
                double cf = freqSize.get(0);
                cfs.add(cf);
                for(int i = 1; i < k; i++){
                    cf = freqSize.get(i) + cfs.get(i-1);
                    cfs.add(cf);
                }
                
                double sum = 0.0;
                for (int i = 1; i < cfs.size(); i++){
                    sum+= cfs.get(i);
                }
                
                ArrayList<Double> cps = new ArrayList<>();
                double cp = 0.0;
                for (int i = 0; i < k; i++){
                    cp = (cfs.get(i)/N) * 100;
                    cps.add(cp);
                }
                
                
                System.out.println("T o t a l: "+sum);
                System.out.println(cfs);
                System.out.println(cps);
                
                
                
                System.out.println("frequency list "+freq);
                
                System.out.println("frequency sizes "+freqSize);
                System.out.println("frequency percentages "+freqPercent);
                
                System.out.println();
                System.out.println(title);
                
                System.out.println("CLASS LIMITS"+"\t"+"T CLASS LIMITS"+"\t"+"MID"+"\t"+"FREQ"+"\t"+"PERCENT"+"\t"+"CF"+"\t"+"CP");
                for(int i = 0; i < k; i++){
                    System.out.println(cl.get(i)+" - "+ cl2.get(i)+
                            "\t"+tlcl.get(i)+" - "+tucl.get(i)+
                            "\t"+midList.get(i)+
                            "\t"+freq.get(i).size()+
                            "\t"+new DecimalFormat("#.##").format(freqPercent.get(i))+
                            "\t"+cfs.get(i)+
                            "\t"+new DecimalFormat("#.##").format(cps.get(i)));
                }
                //2
                System.out.println("CLASS LIMITS"+"\t"+"T C L"+"\t"+"MID"+"\t"+"FREQ"+"\t"+"PERCENT"+"\t"+"CF"+"\t"+"CP");
                for(int i = 0; i < k; i++){
                    System.out.println(">="+cl.get(i)+
                            "\t\t"+" - "+
                            "\t"+" - "+
                            "\t"+freq.get(i).size()+
                            "\t"+new DecimalFormat("#.##").format(freqPercent.get(i))+
                            "\t"+cfs.get(i)+
                            "\t"+new DecimalFormat("#.##").format(cps.get(i)));
                }
                //3
                System.out.println("CLASS LIMITS"+"\t"+"T C L"+"\t"+"MID"+"\t"+"FREQ"+"\t"+"PERCENT"+"\t"+"CF"+"\t"+"CP");
                for(int i = 0; i < k; i++){
                    System.out.println("<="+cl2.get(i)+
                            "\t\t"+" - "+
                            "\t"+" - "+
                            "\t"+freq.get(i).size()+
                            "\t"+new DecimalFormat("#.##").format(freqPercent.get(i))+
                            "\t"+cfs.get(i)+
                            "\t"+new DecimalFormat("#.##").format(cps.get(i)));
                }
                
                   
                System.out.println("CLASS LIMITS"+"\t"+"T CLASS LIMITS"+"\t"+"MID"+"\t"+"FREQ"+"\t"+"PERCENT"+"\t"+"CF"+"\t"+"CP");
                for(int i = 0; i < k; i++){
                    System.out.println(">="+cl.get(i)+" and <="+cl2.get(i)+
                            "\t"+" - "+
                            "\t"+" - "+
                            "\t"+freq.get(i).size()+
                            "\t"+new DecimalFormat("#.##").format(freqPercent.get(i))+
                            "\t"+cfs.get(i)+
                            "\t"+new DecimalFormat("#.##").format(cps.get(i)));
                }
                
                
                    
                 
            DisplayTables(k, cl, cl2,
                            tlcl,tucl,midList,freq,
                            freqPercent, cfs, cps,  title );  
               
            int type = 0, testT = 0;
            String typeTest = "";
            do{
            do{ 
               
               
                System.out.println("GENERATE GRAPH?");
                System.out.println("[1] YES");
                System.out.println("[2] NO");     
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
            
            if (type == 1){                   
                int bins = (int) k;
                System.out.println("You may input a label for your X axis:");
                String x = "";
                x = s.nextLine();
                createHistogram(a,bins,title,x);
                      
                int type2 = 0, testT2 = 0;
                String typeTest2 = "";
                do{ 
                System.out.println("REDISPLAY TABLE?");
                System.out.println("[1] YES");
                System.out.println("[2] NO");     
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
            }while( (type2 < 1 || type2 > 2) && type != 2);
            
            if (type2 == 1){  
               
                DisplayTables(k, cl, cl2,
                            tlcl,tucl,midList,freq,
                            freqPercent, cfs, cps,  title );

                }else{
                    //do nothing?
                }
            }
            }while(type!=2);
            
               
                
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
        
               int type = 0, testT = 0;
        String typeTest = "";
                
        do{ 
            System.out.println();
            System.out.println("** INPUT OPTIONS **");           
            System.out.println("[1] Numeric");
            System.out.println("[2] Alphabetic Character ");
            System.out.println("[3] String");
            
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
        
        if(type == 1){
            System.out.println();
            System.out.println("ENTER MEMBERS OF THE SAMPLING FRAME: ");
            sc.nextLine();
            System.out.println("Numbers only");
            
            for(int i = 1; i <= N; i++){
                String test = "";               
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
        } else if (type == 2) {
            System.out.println();
            System.out.println("ENTER MEMBERS OF THE SAMPLING FRAME: ");
            sc.nextLine();
            System.out.println("Alphabetic Characters only");
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
                        System.out.println("Oops. We accept lowercase only");
                    }
                }while (IsNumber(test) || test.length() > 1 || (test.charAt(0) >= 'A' && test.charAt(0) <= 'Z'));

                list.add(test);         
            }            
        } else {
             for(int i = 1; i <= N; i++){
                String test = "";  
                Scanner j = new Scanner(System.in);
                System.out.print("["+i+"]" +" ");                    
                Object member;
                member = j.nextLine();
                test = member.toString();                 
                list.add(test);         
            }
            
        }
         
     
        System.out.println("this is the list"+list);
        return list;
    }
    
       public static ArrayList GetData2(){
        N = 0;
        
        ArrayList<Double> list = new ArrayList<>();        
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
        
           String test = "";  
               
        
        
        for(int i = 1; i <= N; i++){
        do{                     
                    System.out.print("["+i+"]" +" ");                    
                    Object member = sc.next();
                    test = member.toString();                    
                    if(!IsNumber(test)){
                        System.out.println("Oops. Numbers only");
                    }
                }while (!IsNumber(test));
                       
                        
         
        Double converted = 0.0;
        converted = Double.parseDouble(test);
        list.add(converted);
        
        }
       System.out.println("this is the list"+list);
       return list;
    }
    
    
    
    
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
    
    public static double GetPercentage(int total, int partial){
        
        double t = (double) total;
        double p = (double) partial;
        
    
        
        return (p/t * 100);
    }
    
     public static double Multiplier(double d){
        
        
                String text = Double.toString(Math.abs(d));
                int integerPlaces = text.indexOf('.');
                char x = text.charAt(integerPlaces + 1);
                int decimalPlaces = text.length() - integerPlaces - 1;
                
                
                if (decimalPlaces == 1 && x == '0'){
                    return(5 * .1);
                
                }else {
                    return(5 * (Math.pow(10, -1 * (decimalPlaces + 1))));
                    
                }  
    }
  
    public static double GetRange(double min, double max){        
        return max - min;
        
    }
   
    public static JFreeChart createHistogram(ArrayList doubleMatrix, int width, String title, String label){

        // Generate a one dimensional array of the size w*h of the double matrix
        ArrayList<Double> dataArrayList = new ArrayList<Double>();

        for (int i=0; i<doubleMatrix.size(); i++) {
           
                double value =  Double.parseDouble(doubleMatrix.get(i).toString());
                if( Double.isNaN(value))
                    continue;
                else
                    dataArrayList.add(value);
                    System.out.println(value);
            }
        
      double[] data = new double[dataArrayList.size()];

        for(int p = 0; p < dataArrayList.size();p++)
             data[p] = dataArrayList.get(p);


       // int number = data.length;
        HistogramDataset dataset = new HistogramDataset();
        
        dataset.setType(HistogramType.FREQUENCY);
        dataset.addSeries("Hist",data,width); 
        String plotTitle = title;
        String yAxis = "Frequency";
        String xAxis = label;
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        boolean show = false;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xAxis, yAxis,
                dataset, orientation, show, toolTips, urls);

        chart.setBackgroundPaint(Color.white);
        ChartPanel chartPanel = new ChartPanel( chart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 500 , 300 ) );        
        JFrame l = new JFrame();
        l.setContentPane( chartPanel );
        l.setSize(400, 400);
        l.setVisible(true);
       
        return chart;
    }
    
    
    public static void DisplayTable(ArrayList<ArrayList> all, ArrayList percentages, String title){
        JFrame frame = new JFrame();
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        
            JTable table = new JTable();    
            table.setModel(new DefaultTableModel((int) (all.size() + 2),2));

            table.setValueAt("VALUE LABELS", 0, 0);
            table.setValueAt("PERCENTAGE", 0, 1);


            table.setValueAt("TOTAL = 100%", (int) (all.size() + 1), 1);

            for(int i = 0; i < all.size(); i++){
                table.setValueAt(all.get(i).get(0),i+1,0);

                table.setValueAt(new DecimalFormat("#.##").format(percentages.get(i)),i+1,1);

            }

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createTitledBorder (title));
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(300, 150);
            frame.setVisible(true);
    }
    
    
    public static void DisplayTables(double k, ArrayList<Double> cl, ArrayList<Double> cl2,
            ArrayList<Double> tlcl,ArrayList<Double> tucl,ArrayList<Double> midList,ArrayList<ArrayList<Double>> freq,
            ArrayList<Double>  freqPercent, ArrayList<Double> cfs, ArrayList<Double> cps, String title ){
        
     JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        
        JTable table = new JTable();    
        table.setModel(new DefaultTableModel((int) (k + 2), 7));

        table.setValueAt("CLASS LIMIT", 0, 0);
        table.setValueAt("TRUE CLASS LIMIT", 0, 1);
        table.setValueAt("MIDPOINTS", 0, 2);
        table.setValueAt("FREQUENCY", 0, 3);
        table.setValueAt("%", 0, 4);
        table.setValueAt("CF", 0, 5);
        table.setValueAt("C%", 0, 6);
        table.setValueAt("n = " + N, (int) (k + 1), 3);
        table.setValueAt("TOTAL = 100%", (int) (k + 1), 4);
        
        for(int i = 0; i < k; i++){
            table.setValueAt(cl.get(i)+" - "+cl2.get(i),i+1,0);
            table.setValueAt(tlcl.get(i)+" - "+tucl.get(i),i+1,1);
            table.setValueAt(midList.get(i),i+1,2);
            table.setValueAt(freq.get(i).size(),i+1,3);
            table.setValueAt(new DecimalFormat("#.##").format(freqPercent.get(i)),i+1,4);
            table.setValueAt(cfs.get(i),i+1,5);
            table.setValueAt(new DecimalFormat("#.##").format(cps.get(i)),i+1,6);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder (title));
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);
        
        Scanner sc = new Scanner(System.in);
        
        int type = 0, testT = 0;
            String typeTest = "";
            do{ 
                
                System.out.println(); 
                System.out.println("COLLAPSE CLASS LIMITS?");
                System.out.println("[1] COLLAPSE LOWER CLASS LIMIT");
                System.out.println("[2] COLLAPSE UPPPER CLASS LIMIT");    
                System.out.println("[3] COLLAPSE BOTH");  
                System.out.println("[4] DON'T COLLAPSE");  


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
            }while(type < 1 || type > 4);
        
        if (type == 1){
        
            JFrame frame2 = new JFrame();
            //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        
            JTable table2 = new JTable();    
            table2.setModel(new DefaultTableModel((int) (k + 2), 7));

            table2.setValueAt("CLASS LIMIT", 0, 0);
            table2.setValueAt("TRUE CLASS LIMIT", 0, 1);
            table2.setValueAt("MIDPOINTS", 0, 2);
            table2.setValueAt("FREQUENCY", 0, 3);
            table2.setValueAt("%", 0, 4);
            table2.setValueAt("CF", 0, 5);
            table2.setValueAt("C%", 0, 6);
            table2.setValueAt("n = " + N, (int) (k + 1), 3);
            table2.setValueAt("TOTAL = 100%", (int) (k + 1), 4);

            for(int i = 0; i < k; i++){
                table2.setValueAt(" <= "+cl2.get(i),i+1,0);
                table2.setValueAt(" - ",i+1,1);
                table2.setValueAt(" - ",i+1,2);
                table2.setValueAt(freq.get(i).size(),i+1,3);
                table2.setValueAt(new DecimalFormat("#.##").format(freqPercent.get(i)),i+1,4);
                table2.setValueAt(cfs.get(i),i+1,5);
                table2.setValueAt(new DecimalFormat("#.##").format(cps.get(i)),i+1,6);
            }

            JScrollPane scrollPane2 = new JScrollPane(table2);

            scrollPane2.setBorder(BorderFactory.createTitledBorder (title));
            frame2.add(scrollPane2, BorderLayout.CENTER);
            frame2.setSize(300, 150);
            frame2.setVisible(true);
        
        } else if (type == 2){
        
            JFrame frame3 = new JFrame();
            //frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        
            JTable table3 = new JTable();    
            table3.setModel(new DefaultTableModel((int) (k + 2), 7));

            table3.setValueAt("CLASS LIMIT", 0, 0);
            table3.setValueAt("TRUE CLASS LIMIT", 0, 1);
            table3.setValueAt("MIDPOINTS", 0, 2);
            table3.setValueAt("FREQUENCY", 0, 3);
            table3.setValueAt("%", 0, 4);
            table3.setValueAt("CF", 0, 5);
            table3.setValueAt("C%", 0, 6);
            table3.setValueAt("n = " + N, (int) (k + 1), 3);
            table3.setValueAt("TOTAL = 100%", (int) (k + 1), 4);

            for(int i = 0; i < k; i++){
                table3.setValueAt(" >= "+cl.get(i),i+1,0);
                table3.setValueAt(" - ",i+1,1);
                table3.setValueAt(" - ",i+1,2);
                table3.setValueAt(freq.get(i).size(),i+1,3);
                table3.setValueAt(new DecimalFormat("#.##").format(freqPercent.get(i)),i+1,4);
                table3.setValueAt(cfs.get(i),i+1,5);
                table3.setValueAt(new DecimalFormat("#.##").format(cps.get(i)),i+1,6);
            }

            JScrollPane scrollPane3 = new JScrollPane(table3);
            scrollPane3.setBorder(BorderFactory.createTitledBorder (title));
            frame3.add(scrollPane3, BorderLayout.CENTER);

            frame3.setSize(300, 150);
            frame3.setVisible(true);
        }
        
        else if (type == 3){
            JFrame frame4 = new JFrame();
            //frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        
            JTable table4 = new JTable();    
            table4.setModel(new DefaultTableModel((int) (k + 2), 7));
            table4.setValueAt("CLASS LIMIT", 0, 0);
            table4.setValueAt("TRUE CLASS LIMIT", 0, 1);
            table4.setValueAt("MIDPOINTS", 0, 2);
            table4.setValueAt("FREQUENCY", 0, 3);
            table4.setValueAt("%", 0, 4);
            table4.setValueAt("CF", 0, 5);
            table4.setValueAt("C%", 0, 6);
            table4.setValueAt("n = " + N, (int) (k + 1), 3);
            table4.setValueAt("TOTAL = 100%", (int) (k + 1), 4);

            for(int i = 0; i < k; i++){
                table4.setValueAt(" >= "+cl.get(i)+" and <= "+cl2.get(i),i+1,0);
                table4.setValueAt(" - ",i+1,1);
                table4.setValueAt(" - ",i+1,2);
                table4.setValueAt(freq.get(i).size(),i+1,3);
                table4.setValueAt(new DecimalFormat("#.##").format(freqPercent.get(i)),i+1,4);
                table4.setValueAt(cfs.get(i),i+1,5);
                table4.setValueAt(new DecimalFormat("#.##").format(cps.get(i)),i+1,6);
            }

            JScrollPane scrollPane4 = new JScrollPane(table4);
            scrollPane4.setBorder(BorderFactory.createTitledBorder (title));
            frame4.add(scrollPane4, BorderLayout.CENTER);

            frame4.setSize(300, 150);
            frame4.setVisible(true);
        } else{
            
        }
    }
}



/* float.parseFloat(jTextField1.getText)
    jTectField4.setText(String.valueOf(result)

*/
