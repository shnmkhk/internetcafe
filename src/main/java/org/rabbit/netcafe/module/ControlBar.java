 package org.rabbit.netcafe.module;
 
 import javax.swing.JPanel;
 import org.rabbit.netcafe.components.RButton;
 import org.rabbit.netcafe.util.ResourceUtil;
 
 public class ControlBar extends JPanel
 {
   public RButton addSysNames;
   public RButton resetApp;
 
   public ControlBar()
   {
     this.addSysNames = new RButton(ResourceUtil.getMessage("addsystemstoapp"), true);
     add(this.addSysNames);
 
     this.resetApp = new RButton(ResourceUtil.getMessage("reloadtheapp"), true);
     add(this.resetApp);
   }
 }