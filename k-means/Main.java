package kmean2;

import java.util.Vector;
import java.util.Iterator;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main (String args[]) throws IOException
    {
        
        Vector dataPoints = new Vector();
    
        String [][] numbers = new String [34602][4];
double Cordx[] =new double[34602];
double Cordy[] =new double[34602];
double Cordz[] =new double[34602];
File file = new File("1Underexpressed.csv");
BufferedReader bufRdr = new BufferedReader(new FileReader(file));
String line = null;
int row = 0;
int col = 0;

while((line = bufRdr.readLine()) != null && row<34602)
{ 
StringTokenizer st = new StringTokenizer(line,",");
while (st.hasMoreTokens())
{
//get next token and store it in the array
numbers[row][col] = st.nextToken();
col++;
}
col = 0;
row++;
}


for(row=0;row<34602;row++)
{
Cordx[row]=Double.parseDouble(numbers[row][0]);
Cordy[row]=Double.parseDouble(numbers[row][1]);
Cordz[row]=Double.parseDouble(numbers[row][2]);
}

for(int q=0;q<=34601;q++){
     dataPoints.add(new DataPoint(Cordx[q],Cordy[q],Cordz[q],numbers[q][3]));
    
}


        JCA jca = new JCA(5,1,dataPoints);
        jca.startAnalysis();

        Vector[] v = jca.getClusterOutput();
        

        for (int i=0; i<v.length; i++){
            Vector tempV = v[i];
            System.out.println("-----------Cluster"+i+"---------");
            Iterator iter = tempV.iterator();
            while(iter.hasNext()){
                DataPoint dpTemp = (DataPoint)iter.next();
                 System.out.println(dpTemp.getObjName());
               /* System.out.println(dpTemp.getObjName()+"["+dpTemp.getX()+","+dpTemp.getY()+","+dpTemp.getZ()+"]");*/
            }
            
        }
       
    }
}

