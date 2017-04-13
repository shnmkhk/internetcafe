 package org.rabbit.netcafe;
 
 import java.awt.Color;
 import java.awt.Container;
 import java.awt.GridLayout;
  import java.awt.TrayIcon;

 import java.awt.TrayIcon.MessageType;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import org.rabbit.netcafe.components.RButton;
 import org.rabbit.netcafe.components.RFrame;
 import org.rabbit.netcafe.module.ControlBar;
 import org.rabbit.netcafe.module.LegendBar;
 import org.rabbit.netcafe.module.MachineRow;
import org.rabbit.netcafe.util.ResourceUtil;
 
 public class Main extends JPanel
 {
   private static final int NO_OF_ROWS = Integer.parseInt(ResourceUtil.getMessage("rowcount"));
   private static JLabel msgLabel;
   private final MachineRow[] rows = new MachineRow[NO_OF_ROWS];
 
   public static int fareforhr = Integer.parseInt(ResourceUtil.getMessage("fareforanhour"));
   private ControlBar controlBar;
   private LegendBar legendBar;
 
   private void addRows()
   {
     setLayout(new GridLayout(NO_OF_ROWS + 3, 1));
 
     for (int i = 0; i < NO_OF_ROWS; ++i) {
       this.rows[i] = new MachineRow(i + 1);
       add(this.rows[i]);
     }
   }
 
   private void addControlsAndListeners() {
     this.controlBar = new ControlBar();
     add(this.controlBar);
 
     this.controlBar.addSysNames.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         boolean isAnySystemAdded = false;
         for (MachineRow row : Main.this.rows) {
           isAnySystemAdded = (row.addSystemName()) || (isAnySystemAdded);
         }
 
         if (!(isAnySystemAdded))
           Main.setMessage(ResourceUtil.getMessage("nosysnamegiven"), 
             TrayIcon.MessageType.ERROR);
         else
           Main.setMessage("", null);
       }
     });
     this.controlBar.resetApp.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         for (MachineRow row : Main.this.rows)
           row.removeSysteName();
       }
     });
     this.legendBar = new LegendBar();
     add(this.legendBar);
   }
 
   public Main()
   {
     addRows();
 
     addControlsAndListeners();
 
     msgLabel = new JLabel();
     add(msgLabel);
 
     setMessage(ResourceUtil.getMessage("welcomemsg"), TrayIcon.MessageType.WARNING);
   }
 
   public static void setMessage(String msg, TrayIcon.MessageType messageType) {
     if (messageType == null) {
       messageType = TrayIcon.MessageType.INFO;
     }
     if (msg == null) {
       msg = "";
     }
     switch (messageType)
     {
     case ERROR:
       msgLabel.setForeground(Color.RED);
       break;
     case INFO:
       msgLabel.setForeground(Color.GRAY);
       break;
     case WARNING:
       msgLabel.setForeground(Color.BLUE);
     }
     msgLabel.setText(msg);
   }
 
   public static void main(String[] args) {
     RFrame frame = new RFrame();
 
     String cafeName = ResourceUtil.getMessage("licensedto");
     StringBuffer sb = new StringBuffer();
     if (!("".equals(cafeName))) {
       sb.append(ResourceUtil.getMessage("licensedto")).append(" - ");
     }
 
     sb.append(ResourceUtil.getMessage("appname")).append(" ");
     sb.append(ResourceUtil.getMessage("appversion")).append(" ");
 
     frame.setTitle(sb.toString());
     frame.getContentPane().add(new Main());
     frame.pack();
   }
 }