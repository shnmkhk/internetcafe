 package org.rabbit.netcafe.module;
 
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import org.rabbit.netcafe.components.RButton;
 import org.rabbit.netcafe.components.RTextField;
 import org.rabbit.netcafe.util.ResourceUtil;
 
 public class LegendBar extends JPanel
 {
   private JLabel legendLabel;
   private RTextField legendText;
   private RButton legendButton;
 
   public LegendBar()
   {
     this.legendLabel = new JLabel(ResourceUtil.getMessage("fareforanhourlabel"));
     add(this.legendLabel);
 
     this.legendText = new RTextField(true);
     this.legendText.setBlackColor();
     this.legendText.setText(ResourceUtil.getMessage("fareforanhour"));
     add(this.legendText);
 
     this.legendButton = new RButton(ResourceUtil.getMessage("legendbuttontext"), true);
     this.legendButton.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         org.rabbit.netcafe.Main.fareforhr = Integer.parseInt(LegendBar.this.legendText.getText());
       }
     });
     add(this.legendButton);
   }
 }