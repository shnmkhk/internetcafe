/*    */ package org.rabbit.netcafe.components;
/*    */ 
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ public class RButton extends JButton
/*    */ {
/*    */   public RButton(String name, boolean editable)
/*    */   {
/* 16 */     super(name);
/* 17 */     setEnabled(editable);
/*    */   }
/*    */ 
/*    */   public RButton() {
/* 21 */     this("", false);
/*    */   }
/*    */ 
/*    */   public RButton(String name) {
/* 25 */     this(name, false);
/*    */   }
/*    */ 
/*    */   public RButton(boolean editable) {
/* 29 */     this("", editable);
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.components.RButton
 * JD-Core Version:    0.5.3
 */