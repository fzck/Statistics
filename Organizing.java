package organizing2;

//todo collapse

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

/**
 *
 * @author localuser
 */

/** TODO PIE CHART
**/


  
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
 
               
               DefaultPieDataset dataset = new DefaultPieDataset( );
               for(int i = 0; i < all.size(); i++){
                   dataset.setValue(all.get(i).get(0).toString()+" = "+percentages.get(i).toString()+"%",percentages.get(i));
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
                                
            } else if (choice == 2){
             
            /*    
                System.out.println("*** NUMERICAL ***");
                System.out.println();
                System.out.println("TITLE(title of data set)");
                title = s.nextLine();
                
                System.out.println("this is the title  "+title);
                
                ArrayList<Double> a = new ArrayList<>();
                //ArrayList<ArrayList> all; 
                a = GetData2();
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
                
                System.out.println(cl);
                ArrayList<Double> cl2 = new ArrayList<>();
                double cl2min = cl.get(1) - 1;
                cl2.add(cl2min);
                for(int i = 1; i <k; i++){
                    cl2.add(cl2min+=width);
                }
                
                System.out.println(cl2);
                
                
                 ArrayList<Double> tlcl = new ArrayList<>();
                double tlclmin = cl.get(0) - .5;
                tlcl.add(tlclmin);
                for(int i = 1; i <k; i++){
                    tlcl.add(tlclmin+=width);
                }
                
                ArrayList<Double> tucl = new ArrayList<>();
                double tuclmin = cl2.get(0) + .5;
                tucl.add(tuclmin);
                for(int i = 1; i <k; i++){
                    tucl.add(tuclmin+=width);
                }
                
                ArrayList<Double> midList = new ArrayList<>();
                double mid = (cl.get(0) + cl2.get(0)) / 2;
                midList.add(mid);
                for(int i = 1; i <k; i++){
                    midList.add(mid += width);
                }
                
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
                Double size = 0.0;
                for(int i = 0; i < k; i++){
                    size = (double) freq.get(i).size();
                    freqSize.add(size);
                }
                
                ArrayList<Double>  freqPercent = new ArrayList<>();
                for(int i = 0; i < k; i++){
                    
                    freqPercent.add(freqSize.get(i)/N*100);
                }
                
                
                
                System.out.println("frequency list "+freq);
                
                System.out.println("frequency sizes "+freqSize);
                System.out.println("frequency percentages "+freqPercent);
                
                System.out.println("CLASS LIMITS"+"\t"+"T CLASS LIMITS"+"\t"+"MID"+"\t"+"FREQ"+"\t"+"PERCENT");
                for(int i = 0; i < k; i++){
                    System.out.println(cl.get(i)+" - "+ cl2.get(i)+"\t"+tlcl.get(i)+" - "+tucl.get(i)+"\t"+midList.get(i)+"\t"+freq.get(i).size()+"\t"+freqPercent.get(i));
                }
                
               
               */
                double marks[]={70, 36, 43, 69, 82, 48, 34, 62, 35, 15,
                                59, 139, 46, 37, 42, 30, 55, 56, 36, 82, 
                                38, 89, 54, 25, 35, 24, 22, 9, 55, 19};
                createHistogram(marks);
                
                
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
    
    
    public static double GetK(int n){
        n = N;
        double k =  Math.ceil(1 + 3.322*Math.log(N));
        
        return k;
    }
   
    public static double GetRange(double min, double max){
        
        return max - min;
        
    }
    
    
    public static void GenerateHistogram(){
              
            /*
                
                
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.setValue(80, "Mark", "Student1");
                dataset.setValue(50, "Mark", "Student2");
                dataset.setValue(75, "Mark", "Student3");
                dataset.setValue(95, "Mark", "Student4");
                
                
                
                //JFreeChart chart = ChartFactory.createBarChart("S SCORE", "NAME", "MARKS", dataset,PlotOrientation.VERTICAL, false, true, false);
                //JFreeChart chart = ChartFactory.createHistogram("SCORE", "NAME", "MARKS", (IntervalXYDataset) dataset, PlotOrientation.VERTICAL, false, true, false);
                
               
                */
                
       double[] value = new double[100];
       Random generator = new Random();
       for (int i=1; i < 100; i++) {
       value[i] = generator.nextDouble();
           int number = 10;
       HistogramDataset dataset = new HistogramDataset();
       dataset.setType(HistogramType.RELATIVE_FREQUENCY);
       dataset.addSeries("Histogram",value,number);
       String plotTitle = "Histogram"; 
       String xaxis = "number";
       String yaxis = "value"; 
       PlotOrientation orientation = PlotOrientation.VERTICAL; 
       boolean show = false; 
       boolean toolTips = false;
       boolean urls = false; 
       JFreeChart chart = ChartFactory.createHistogram( plotTitle, xaxis, yaxis, 
                dataset, orientation, show, toolTips, urls);
       
       int width = 500;
       int height = 300; 
       /*
        ChartPanel chartPanel = new ChartPanel( chart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 500 , 300 ) );        
        JFrame l = new JFrame();
        l.setContentPane( chartPanel );
        l.setSize(400, 400);
        l.setVisible(true);
       */
       
       
        try {
        
            ChartUtilities.saveChartAsPNG(new File("histogram.PNG"), chart, width, height);
        } catch (IOException e) {}
        
        
       }          
                
          
        
    }
    
    
    public static JFreeChart createHistogram(double[] doubleMatrix){

        // Generate a one dimensional array of the size w*h of the double matrix
        ArrayList<Double> dataArrayList = new ArrayList<Double>();

        for (int i=0; i<doubleMatrix.length; i++) {
           
                double value =  doubleMatrix[i];
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
        //dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        //HistogramBin bin = new
        dataset.setType(HistogramType.FREQUENCY);
        dataset.addSeries("Hist",data,8); // Number of bins is 50
        String plotTitle = "HISTOGRAM";
        String yAxis = "Frequency";
        String xAxis = "Mass Error (Da)";
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
        
}



/* float.parseFloat(jTextField1.getText)
    jTectField4.setText(String.valueOf(result)
*/
