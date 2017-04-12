package org.rabbit.netcafe.util; 
 
 import java.text.DateFormat; 
 import java.text.SimpleDateFormat; 
 import java.util.Calendar; 
 
 public class DateUtil {
   private static DateFormat df = new SimpleDateFormat("hh:mm"); 
   public static final int MILLISECOND = 1; 
   public static final int SECOND = 1000; 
   public static final int MINUTE = 60000; 
   public static final int HOUR = 3600000; 
 
   public static String getHoursNMins(Calendar cal) {
     return df.format(cal.getTime()); 
   }
 
   public static int[] getDateDiffAsArray(long stMillis, long endMillis) {
     long diff = endMillis - stMillis; 
     int hrs = (int)diff / 3600000; 
     int mins = (int)(diff % 3600000L)/60000; 
 
     return new int[] {hrs, mins }; 
   }
 
   public static String getDateDiff(long stMillis, long endMillis) {
     StringBuffer sb = new StringBuffer(); 
 
     int[] hrsminsArray = getDateDiffAsArray(stMillis, endMillis); 
     if ((hrsminsArray[0] > -1) && (hrsminsArray[0] < 10)) {
       sb.append(0); 
     }
     sb.append(hrsminsArray[0]).append(":"); 
     if ((hrsminsArray[1] > -1) && (hrsminsArray[1] < 10)) {
       sb.append(0); 
     }
     sb.append(hrsminsArray[1]); 
 
     return sb.toString(); 
   }
 }