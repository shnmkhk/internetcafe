/*    */ package org.rabbit.netcafe.module;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import org.rabbit.netcafe.components.RButton;
/*    */ import org.rabbit.netcafe.components.RTextField;
/*    */ import org.rabbit.netcafe.util.ResourceUtil;
/*    */ 
/*    */ public class LegendBar extends JPanel
/*    */ {
/*    */   private JLabel legendLabel;
/*    */   private RTextField legendText;
/*    */   private RButton legendButton;
/*    */ 
/*    */   public LegendBar()
/*    */   {
/* 30 */     this.legendLabel = new JLabel(ResourceUtil.getMessage("fareforanhourlabel"));
/* 31 */     add(this.legendLabel);
/*    */ 
/* 33 */     this.legendText = new RTextField(true);
/* 34 */     this.legendText.setBlackColor();
/* 35 */     this.legendText.setText(ResourceUtil.getMessage("fareforanhour"));
/* 36 */     add(this.legendText);
/*    */ 
/* 38 */     this.legendButton = new RButton(ResourceUtil.getMessage("legendbuttontext"), true);
/* 39 */     this.legendButton.addActionListener(new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e) {
/* 42 */         org.rabbit.netcafe.Main.fareforhr = Integer.parseInt(LegendBar.this.legendText.getText());
/*    */       }
/*    */     });
/* 45 */     add(this.legendButton);
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.module.LegendBar
 * JD-Core Version:    0.5.3
 */