 package org.rabbit.netcafe.module;
 
 import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.io.IOException;
 import java.util.Calendar;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import org.rabbit.netcafe.Main;
 import org.rabbit.netcafe.components.RButton;
 import org.rabbit.netcafe.components.RTextField;
 import org.rabbit.netcafe.util.DateUtil;
 import org.rabbit.netcafe.util.FileUtil;
import org.rabbit.netcafe.util.ResourceUtil;
 
 public class MachineRow extends JPanel
 {
   private int serialNo;
   private String machineName;
   private long lastInTime;
   private long lastOutTime;
   private Calendar cal;
   private JLabel serialNoLabel;
   private RTextField machineNameField;
   private RTextField lastInTimeField;
   private RButton inButton;
   private RTextField lastOutTimeField;
   private RButton outButton;
   private RButton recordButton;
   private double calculatedFare;
 
   private void calculateFareNDisplay()
   {
     StringBuffer sb = new StringBuffer();
     sb.append("  Machine Name: ").append(this.machineName);
     sb.append("  Time: ").append(
       DateUtil.getDateDiff(this.lastInTime, this.lastOutTime));
 
     int[] hrsminsArray = DateUtil.getDateDiffAsArray(this.lastInTime, 
       this.lastOutTime);
     this.calculatedFare = 
       ((hrsminsArray[0] + hrsminsArray[1] / 60.0D) * 
       Main.fareforhr);
 
     sb.append(" Fare: ").append(this.calculatedFare);
     Main.setMessage(sb.toString(), TrayIcon.MessageType.INFO);
   }
 
   public MachineRow()
   {
     this.cal = Calendar.getInstance();
 
     this.calculatedFare = 0.0D;
 
     this.serialNoLabel = new JLabel();
     add(this.serialNoLabel);
 
     this.machineNameField = new RTextField(true, 10, ResourceUtil.getMessage("sysname"));
     add(this.machineNameField);
 
     this.lastInTimeField = new RTextField();
     add(this.lastInTimeField);
 
     this.inButton = new RButton();
     this.inButton.setText(ResourceUtil.getMessage("inbutton"));
     this.inButton.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         MachineRow.this.machineName = MachineRow.this.machineNameField.getText();
         MachineRow.this.lastInTime = System.currentTimeMillis();
 
         MachineRow.this.cal.setTimeInMillis(MachineRow.this.lastInTime);
         MachineRow.this.lastInTimeField.setText(DateUtil.getHoursNMins(MachineRow.this.cal));
 
         MachineRow.this.inButton.setEnabled(false);
         MachineRow.this.outButton.setEnabled(true);
       }
     });
     add(this.inButton);
 
     this.lastOutTimeField = new RTextField();
     add(this.lastOutTimeField);
 
     this.outButton = new RButton();
     this.outButton.setText(ResourceUtil.getMessage("outbutton"));
     this.outButton.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         MachineRow.this.lastOutTime = System.currentTimeMillis();
 
         MachineRow.this.cal.setTimeInMillis(MachineRow.this.lastOutTime);
         MachineRow.this.lastOutTimeField.setText(DateUtil.getHoursNMins(MachineRow.this.cal));
 
         MachineRow.this.outButton.setEnabled(false);
         MachineRow.this.recordButton.setEnabled(true);
       }
     });
     add(this.outButton);
 
     this.recordButton = new RButton();
     this.recordButton.setText(ResourceUtil.getMessage("recordbutton"));
     this.recordButton.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         if (!(ResourceUtil.getMessage("clearbutton").equals(
           e.getActionCommand().trim()))) {
           try {
             FileUtil.writeMachineEntry(MachineRow.this.machineName, MachineRow.this.lastInTimeField
               .getText(), MachineRow.this.lastOutTimeField.getText(), 
               String.valueOf(MachineRow.this.calculatedFare));
           } catch (IOException ioe) {
             ioe.printStackTrace();
           }
           MachineRow.this.calculateFareNDisplay();
           MachineRow.this.recordButton.setText(ResourceUtil.getMessage("clearbutton", 2));
         } else {
           String bkpmachineName = MachineRow.this.machineNameField.getText();
           MachineRow.this.removeSysteName();
           MachineRow.this.machineNameField.setText(bkpmachineName);
           MachineRow.this.addSystemName();
         }
       }
     });
     add(this.recordButton);
   }
 
   public MachineRow(int serialNo)
   {
	this();
     this.serialNo = serialNo;
 
     StringBuffer sb = new StringBuffer(128);
     if ((serialNo >= 0) && (serialNo < 10)) {
       sb.append(0);
     }
     sb.append(serialNo).append(". ");
     this.serialNoLabel.setText(sb.toString());
   }
 
   private void prepareMachineDetails(boolean set) {
     if (!(set)) {
       this.machineNameField.setGrayColor();
       this.machineNameField.setText(this.machineNameField.defString);
     }
     this.machineNameField.setEnabled(!(set));
     this.lastInTimeField.setText("");
     this.inButton.setEnabled(set);
     this.lastOutTimeField.setText("");
     this.outButton.setEnabled(false);
     this.recordButton.setEnabled(false);
     this.recordButton.setText(ResourceUtil.getMessage("recordbutton"));
   }
 
   public boolean addSystemName() {
     if ((!(this.machineNameField.validText())) || (this.machineNameField.getText().length() == 0)) {
       return false;
     }
     prepareMachineDetails(true);
     return true;
   }
 
   public void removeSysteName() {
     prepareMachineDetails(false);
   }
 
   public int getSerialNo() {
     return this.serialNo;
   }
 
   public void setSerialNo(int serialNo) {
     this.serialNo = serialNo;
   }
 
   public String getMachineName() {
     return this.machineName;
   }
 
   public void setMachineName(String machineName) {
     this.machineName = machineName;
   }
 
   public long getLastInTime() {
     return this.lastInTime;
   }
 
   public void setLastInTime(long lastInTime) {
     this.lastInTime = lastInTime;
   }
 
   public long getLastOutTime() {
     return this.lastOutTime;
   }
 
   public void setLastOutTime(long lastOutTime) {
     this.lastOutTime = lastOutTime;
   }
 }