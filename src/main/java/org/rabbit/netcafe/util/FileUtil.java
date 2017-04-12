 package org.rabbit.netcafe.util;
 
 import java.io.File;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 public class FileUtil
 {
   private static File file = new File("Report.csv");
 
   public static void writeMachineEntry(String machineName, String inTime, String outTime, String fare) throws IOException { FileWriter fw = new FileWriter(file, true);
     PrintWriter out = new PrintWriter(fw);
 
     StringBuffer sb = new StringBuffer();
     sb.append("\n").append(machineName);
     sb.append(",").append(inTime);
     sb.append(",").append(outTime);
     sb.append(",").append(fare);
 
     out.println(sb.toString());
     out.close();
     fw.close();
   }
 }