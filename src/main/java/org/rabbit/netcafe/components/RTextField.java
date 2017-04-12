/*    */ package org.rabbit.netcafe.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.event.FocusEvent;
/*    */ import java.awt.event.FocusListener;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class RTextField extends JTextField
/*    */ {
/*    */   public final String defString;
/*    */ 
/*    */   public final void setGrayColor()
/*    */   {
/* 29 */     setForeground(Color.GRAY);
/*    */   }
/*    */ 
/*    */   public final void setBlackColor() {
/* 33 */     setForeground(Color.BLACK);
/*    */   }
/*    */ 
/*    */   public final boolean validText()
/*    */   {
/* 39 */     return (!(this.defString.equals(getText().trim())));
/*    */   }
/*    */ 
/*    */   public final void judgeText()
/*    */   {
/* 48 */     if (this.defString.equals(getText().trim())) {
/* 49 */       setBlackColor();
/* 50 */       setText("");
/* 51 */     } else if ("".equals(getText().trim())) {
/* 52 */       setGrayColor();
/* 53 */       setText(this.defString); }
/*    */   }
/*    */ 
/*    */   public RTextField(boolean editable, int size, String defaultStr) {
/* 57 */     super(size);
/* 58 */     this.defString = defaultStr;
/*    */ 
/* 60 */     setGrayColor();
/* 61 */     setText(this.defString);
/*    */ 
/* 63 */     setEnabled(editable);
/* 64 */     setDisabledTextColor(Color.BLACK);
/* 65 */     if (!(editable)) {
/* 66 */       Font font = getFont();
/* 67 */       font = new Font(font.getName(), 1, font.getSize());
/* 68 */       setFont(font);
/*    */     }
/*    */ 
/* 71 */     if ((defaultStr != null) && (defaultStr.length() > 0))
/* 72 */       addFocusListener(new FocusListener()
/*    */       {
/*    */         public void focusLost(FocusEvent e) {
/* 75 */           RTextField.this.judgeText();
/*    */         }
/*    */ 
/*    */         public void focusGained(FocusEvent e) {
/* 79 */           RTextField.this.judgeText();
/*    */         }
/*    */       });
/*    */   }
/*    */ 
/*    */   public RTextField(boolean editable)
/*    */   {
/* 86 */     this(editable, 5, "");
/*    */   }
/*    */ 
/*    */   public RTextField() {
/* 90 */     this(false, 5, "");
/*    */   }
/*    */ 
/*    */   public RTextField(int size) {
/* 94 */     this(false, size, "");
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.components.RTextField
 * JD-Core Version:    0.5.3
 */