/*    */ package org.rabbit.netcafe.util;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class ResourceUtil
/*    */ {
/*  8 */   private static ResourceBundle resouceBundle = ResourceBundle.getBundle("locale", Locale.ENGLISH);
/*    */ 
/*    */   public static String getMessage(String key, int spaces) {
/* 11 */     StringBuffer sb = new StringBuffer();
/* 12 */     for (int i = 0; i < spaces; ++i) {
/* 13 */       sb.append(" ");
/*    */     }
/* 15 */     sb.append(resouceBundle.getString(key));
/* 16 */     for (int i = 0; i < spaces; ++i) {
/* 17 */       sb.append(" ");
/*    */     }
/* 19 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   public static String getMessage(String key) {
/* 23 */     return getMessage(key, 0);
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.util.ResourceUtil
 * JD-Core Version:    0.5.3
 */