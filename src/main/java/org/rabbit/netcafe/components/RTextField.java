package org.rabbit.netcafe.components; 
 
 import java.awt.Color; 
 import java.awt.Font; 
 import java.awt.event.FocusEvent; 
 import java.awt.event.FocusListener; 
 import javax.swing.JTextField; 
 
 public class RTextField extends JTextField {
   public final String defString; 
 
   public final void setGrayColor() {
     setForeground(Color.GRAY); 
   }
 
   public final void setBlackColor() {
     setForeground(Color.BLACK); 
   }
 
   public final boolean validText() {
     return ( ! (this.defString.equals(getText().trim()))); 
   }
 
   public final void judgeText() {
     if (this.defString.equals(getText().trim())) {
       setBlackColor(); 
       setText(""); 
     }else if ("".equals(getText().trim())) {
       setGrayColor(); 
       setText(this.defString); }
   }
 
   public RTextField(boolean editable, int size, String defaultStr) {
     super(size); 
     this.defString = defaultStr; 
 
     setGrayColor(); 
     setText(this.defString); 
 
     setEnabled(editable); 
     setDisabledTextColor(Color.BLACK); 
     if ( ! (editable)) {
       Font font = getFont(); 
       font = new Font(font.getName(), 1, font.getSize()); 
       setFont(font); 
     }
 
     if ((defaultStr != null) && (defaultStr.length() > 0))
       addFocusListener(new FocusListener() {
         public void focusLost(FocusEvent e) {
           RTextField.this.judgeText(); 
         }
 
         public void focusGained(FocusEvent e) {
           RTextField.this.judgeText(); 
         }
       }); 
   }
 
   public RTextField(boolean editable) {
     this(editable, 5, ""); 
   }
 
   public RTextField() {
     this(false, 5, ""); 
   }
 
   public RTextField(int size) {
     this(false, size, ""); 
   }
 }
