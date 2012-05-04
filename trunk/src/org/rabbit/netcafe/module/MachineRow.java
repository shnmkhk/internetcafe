/*     */ package org.rabbit.netcafe.module;
/*     */ 
/*     */ import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.rabbit.netcafe.Main;
/*     */ import org.rabbit.netcafe.components.RButton;
/*     */ import org.rabbit.netcafe.components.RTextField;
/*     */ import org.rabbit.netcafe.util.DateUtil;
/*     */ import org.rabbit.netcafe.util.FileUtil;
import org.rabbit.netcafe.util.ResourceUtil;
/*     */ 
/*     */ public class MachineRow extends JPanel
/*     */ {
/*     */   private int serialNo;
/*     */   private String machineName;
/*     */   private long lastInTime;
/*     */   private long lastOutTime;
/*     */   private Calendar cal;
/*     */   private JLabel serialNoLabel;
/*     */   private RTextField machineNameField;
/*     */   private RTextField lastInTimeField;
/*     */   private RButton inButton;
/*     */   private RTextField lastOutTimeField;
/*     */   private RButton outButton;
/*     */   private RButton recordButton;
/*     */   private double calculatedFare;
/*     */ 
/*     */   private void calculateFareNDisplay()
/*     */   {
/*  59 */     StringBuffer sb = new StringBuffer();
/*  60 */     sb.append("  Machine Name: ").append(this.machineName);
/*  61 */     sb.append("  Time: ").append(
/*  62 */       DateUtil.getDateDiff(this.lastInTime, this.lastOutTime));
/*     */ 
/*  64 */     int[] hrsminsArray = DateUtil.getDateDiffAsArray(this.lastInTime, 
/*  65 */       this.lastOutTime);
/*  66 */     this.calculatedFare = 
/*  67 */       ((hrsminsArray[0] + hrsminsArray[1] / 60.0D) * 
/*  67 */       Main.fareforhr);
/*     */ 
/*  69 */     sb.append(" Fare: ").append(this.calculatedFare);
/*  70 */     Main.setMessage(sb.toString(), TrayIcon.MessageType.INFO);
/*     */   }
/*     */ 
/*     */   public MachineRow()
/*     */   {
/*  47 */     this.cal = Calendar.getInstance();
/*     */ 
/*  56 */     this.calculatedFare = 0.0D;
/*     */ 
/*  75 */     this.serialNoLabel = new JLabel();
/*  76 */     add(this.serialNoLabel);
/*     */ 
/*  79 */     this.machineNameField = new RTextField(true, 10, ResourceUtil.getMessage("sysname"));
/*  80 */     add(this.machineNameField);
/*     */ 
/*  83 */     this.lastInTimeField = new RTextField();
/*  84 */     add(this.lastInTimeField);
/*     */ 
/*  87 */     this.inButton = new RButton();
/*  88 */     this.inButton.setText(ResourceUtil.getMessage("inbutton"));
/*  89 */     this.inButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  92 */         MachineRow.this.machineName = MachineRow.this.machineNameField.getText();
/*  93 */         MachineRow.this.lastInTime = System.currentTimeMillis();
/*     */ 
/*  95 */         MachineRow.this.cal.setTimeInMillis(MachineRow.this.lastInTime);
/*  96 */         MachineRow.this.lastInTimeField.setText(DateUtil.getHoursNMins(MachineRow.this.cal));
/*     */ 
/*  98 */         MachineRow.this.inButton.setEnabled(false);
/*  99 */         MachineRow.this.outButton.setEnabled(true);
/*     */       }
/*     */     });
/* 102 */     add(this.inButton);
/*     */ 
/* 105 */     this.lastOutTimeField = new RTextField();
/* 106 */     add(this.lastOutTimeField);
/*     */ 
/* 109 */     this.outButton = new RButton();
/* 110 */     this.outButton.setText(ResourceUtil.getMessage("outbutton"));
/* 111 */     this.outButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 114 */         MachineRow.this.lastOutTime = System.currentTimeMillis();
/*     */ 
/* 116 */         MachineRow.this.cal.setTimeInMillis(MachineRow.this.lastOutTime);
/* 117 */         MachineRow.this.lastOutTimeField.setText(DateUtil.getHoursNMins(MachineRow.this.cal));
/*     */ 
/* 119 */         MachineRow.this.outButton.setEnabled(false);
/* 120 */         MachineRow.this.recordButton.setEnabled(true);
/*     */       }
/*     */     });
/* 123 */     add(this.outButton);
/*     */ 
/* 126 */     this.recordButton = new RButton();
/* 127 */     this.recordButton.setText(ResourceUtil.getMessage("recordbutton"));
/* 128 */     this.recordButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 131 */         if (!(ResourceUtil.getMessage("clearbutton").equals(
/* 132 */           e.getActionCommand().trim()))) {
/*     */           try {
/* 134 */             FileUtil.writeMachineEntry(MachineRow.this.machineName, MachineRow.this.lastInTimeField
/* 135 */               .getText(), MachineRow.this.lastOutTimeField.getText(), 
/* 136 */               String.valueOf(MachineRow.this.calculatedFare));
/*     */           } catch (IOException ioe) {
/* 138 */             ioe.printStackTrace();
/*     */           }
/* 140 */           MachineRow.this.calculateFareNDisplay();
/* 141 */           MachineRow.this.recordButton.setText(ResourceUtil.getMessage("clearbutton", 2));
/*     */         } else {
/* 143 */           String bkpmachineName = MachineRow.this.machineNameField.getText();
/* 144 */           MachineRow.this.removeSysteName();
/* 145 */           MachineRow.this.machineNameField.setText(bkpmachineName);
/* 146 */           MachineRow.this.addSystemName();
/*     */         }
/*     */       }
/*     */     });
/* 150 */     add(this.recordButton);
/*     */   }
/*     */ 
/*     */   public MachineRow(int serialNo)
/*     */   {
	this();
/* 155 */     this.serialNo = serialNo;
/*     */ 
/* 157 */     StringBuffer sb = new StringBuffer(128);
/* 158 */     if ((serialNo >= 0) && (serialNo < 10)) {
/* 159 */       sb.append(0);
/*     */     }
/* 161 */     sb.append(serialNo).append(". ");
/* 162 */     this.serialNoLabel.setText(sb.toString());
/*     */   }
/*     */ 
/*     */   private void prepareMachineDetails(boolean set) {
/* 166 */     if (!(set)) {
/* 167 */       this.machineNameField.setGrayColor();
/* 168 */       this.machineNameField.setText(this.machineNameField.defString);
/*     */     }
/* 170 */     this.machineNameField.setEnabled(!(set));
/* 171 */     this.lastInTimeField.setText("");
/* 172 */     this.inButton.setEnabled(set);
/* 173 */     this.lastOutTimeField.setText("");
/* 174 */     this.outButton.setEnabled(false);
/* 175 */     this.recordButton.setEnabled(false);
/* 176 */     this.recordButton.setText(ResourceUtil.getMessage("recordbutton"));
/*     */   }
/*     */ 
/*     */   public boolean addSystemName() {
/* 180 */     if ((!(this.machineNameField.validText())) || (this.machineNameField.getText().length() == 0)) {
/* 181 */       return false;
/*     */     }
/* 183 */     prepareMachineDetails(true);
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   public void removeSysteName() {
/* 188 */     prepareMachineDetails(false);
/*     */   }
/*     */ 
/*     */   public int getSerialNo() {
/* 192 */     return this.serialNo;
/*     */   }
/*     */ 
/*     */   public void setSerialNo(int serialNo) {
/* 196 */     this.serialNo = serialNo;
/*     */   }
/*     */ 
/*     */   public String getMachineName() {
/* 200 */     return this.machineName;
/*     */   }
/*     */ 
/*     */   public void setMachineName(String machineName) {
/* 204 */     this.machineName = machineName;
/*     */   }
/*     */ 
/*     */   public long getLastInTime() {
/* 208 */     return this.lastInTime;
/*     */   }
/*     */ 
/*     */   public void setLastInTime(long lastInTime) {
/* 212 */     this.lastInTime = lastInTime;
/*     */   }
/*     */ 
/*     */   public long getLastOutTime() {
/* 216 */     return this.lastOutTime;
/*     */   }
/*     */ 
/*     */   public void setLastOutTime(long lastOutTime) {
/* 220 */     this.lastOutTime = lastOutTime;
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.module.MachineRow
 * JD-Core Version:    0.5.3
 */