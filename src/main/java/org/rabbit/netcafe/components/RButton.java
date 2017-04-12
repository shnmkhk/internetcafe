 package org.rabbit.netcafe.components;
 
 import javax.swing.JButton;
 
 public class RButton extends JButton
 {
   public RButton(String name, boolean editable)
   {
     super(name);
     setEnabled(editable);
   }
 
   public RButton() {
     this("", false);
   }
 
   public RButton(String name) {
     this(name, false);
   }
 
   public RButton(boolean editable) {
     this("", editable);
   }
 }