/*    */ package org.rabbit.netcafe.util;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ 
/*    */ public class DateUtil
/*    */ {
/* 10 */   private static DateFormat df = new SimpleDateFormat("hh:mm");
/*    */   public static final int MILLISECOND = 1;
/*    */   public static final int SECOND = 1000;
/*    */   public static final int MINUTE = 60000;
/*    */   public static final int HOUR = 3600000;
/*    */ 
/*    */   public static String getHoursNMins(Calendar cal)
/*    */   {
/* 22 */     return df.format(cal.getTime());
/*    */   }
/*    */ 
/*    */   public static int[] getDateDiffAsArray(long stMillis, long endMillis)
/*    */   {
/* 27 */     long diff = endMillis - stMillis;
/* 28 */     int hrs = (int)diff / 3600000;
/* 29 */     int mins = (int)(diff % 3600000L) / 60000;
/*    */ 
/* 31 */     return new int[] { hrs, mins };
/*    */   }
/*    */ 
/*    */   public static String getDateDiff(long stMillis, long endMillis) {
/* 35 */     StringBuffer sb = new StringBuffer();
/*    */ 
/* 37 */     int[] hrsminsArray = getDateDiffAsArray(stMillis, endMillis);
/* 38 */     if ((hrsminsArray[0] > -1) && (hrsminsArray[0] < 10)) {
/* 39 */       sb.append(0);
/*    */     }
/* 41 */     sb.append(hrsminsArray[0]).append(":");
/* 42 */     if ((hrsminsArray[1] > -1) && (hrsminsArray[1] < 10)) {
/* 43 */       sb.append(0);
/*    */     }
/* 45 */     sb.append(hrsminsArray[1]);
/*    */ 
/* 47 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.util.DateUtil
 * JD-Core Version:    0.5.3
 */