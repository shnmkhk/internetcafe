/*    */ package org.rabbit.netcafe.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ public class FileUtil
/*    */ {
/* 10 */   private static File file = new File("Report.csv");
/*    */ 
/*    */   public static void writeMachineEntry(String machineName, String inTime, String outTime, String fare) throws IOException { FileWriter fw = new FileWriter(file, true);
/* 13 */     PrintWriter out = new PrintWriter(fw);
/*    */ 
/* 15 */     StringBuffer sb = new StringBuffer();
/* 16 */     sb.append("\n").append(machineName);
/* 17 */     sb.append(",").append(inTime);
/* 18 */     sb.append(",").append(outTime);
/* 19 */     sb.append(",").append(fare);
/*    */ 
/* 21 */     out.println(sb.toString());
/* 22 */     out.close();
/* 23 */     fw.close();
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.util.FileUtil
 * JD-Core Version:    0.5.3
 */