/*    */ package org.rabbit.netcafe.module;
/*    */ 
/*    */ import javax.swing.JPanel;
/*    */ import org.rabbit.netcafe.components.RButton;
/*    */ import org.rabbit.netcafe.util.ResourceUtil;
/*    */ 
/*    */ public class ControlBar extends JPanel
/*    */ {
/*    */   public RButton addSysNames;
/*    */   public RButton resetApp;
/*    */ 
/*    */   public ControlBar()
/*    */   {
/* 24 */     this.addSysNames = new RButton(ResourceUtil.getMessage("addsystemstoapp"), true);
/* 25 */     add(this.addSysNames);
/*    */ 
/* 27 */     this.resetApp = new RButton(ResourceUtil.getMessage("reloadtheapp"), true);
/* 28 */     add(this.resetApp);
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.module.ControlBar
 * JD-Core Version:    0.5.3
 */