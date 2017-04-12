/*     */ package org.rabbit.netcafe;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.GridLayout;
import java.awt.TrayIcon;
/*     */ import java.awt.TrayIcon.MessageType;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.rabbit.netcafe.components.RButton;
/*     */ import org.rabbit.netcafe.components.RFrame;
/*     */ import org.rabbit.netcafe.module.ControlBar;
/*     */ import org.rabbit.netcafe.module.LegendBar;
/*     */ import org.rabbit.netcafe.module.MachineRow;
import org.rabbit.netcafe.util.ResourceUtil;
/*     */ 
/*     */ public class Main extends JPanel
/*     */ {
/*  30 */   private static final int NO_OF_ROWS = Integer.parseInt(ResourceUtil.getMessage("rowcount"));
/*     */   private static JLabel msgLabel;
/*  34 */   private final MachineRow[] rows = new MachineRow[NO_OF_ROWS];
/*     */ 
/*  36 */   public static int fareforhr = Integer.parseInt(ResourceUtil.getMessage("fareforanhour"));
/*     */   private ControlBar controlBar;
/*     */   private LegendBar legendBar;
/*     */ 
/*     */   private void addRows()
/*     */   {
/*  43 */     setLayout(new GridLayout(NO_OF_ROWS + 3, 1));
/*     */ 
/*  45 */     for (int i = 0; i < NO_OF_ROWS; ++i) {
/*  46 */       this.rows[i] = new MachineRow(i + 1);
/*  47 */       add(this.rows[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addControlsAndListeners() {
/*  52 */     this.controlBar = new ControlBar();
/*  53 */     add(this.controlBar);
/*     */ 
/*  55 */     this.controlBar.addSysNames.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  58 */         boolean isAnySystemAdded = false;
/*  59 */         for (MachineRow row : Main.this.rows) {
/*  60 */           isAnySystemAdded = (row.addSystemName()) || (isAnySystemAdded);
/*     */         }
/*     */ 
/*  63 */         if (!(isAnySystemAdded))
/*  64 */           Main.setMessage(ResourceUtil.getMessage("nosysnamegiven"), 
/*  65 */             TrayIcon.MessageType.ERROR);
/*     */         else
/*  67 */           Main.setMessage("", null);
/*     */       }
/*     */     });
/*  72 */     this.controlBar.resetApp.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  75 */         for (MachineRow row : Main.this.rows)
/*  76 */           row.removeSysteName();
/*     */       }
/*     */     });
/*  81 */     this.legendBar = new LegendBar();
/*  82 */     add(this.legendBar);
/*     */   }
/*     */ 
/*     */   public Main()
/*     */   {
/*  90 */     addRows();
/*     */ 
/*  93 */     addControlsAndListeners();
/*     */ 
/*  96 */     msgLabel = new JLabel();
/*  97 */     add(msgLabel);
/*     */ 
/*  99 */     setMessage(ResourceUtil.getMessage("welcomemsg"), TrayIcon.MessageType.WARNING);
/*     */   }
/*     */ 
/*     */   public static void setMessage(String msg, TrayIcon.MessageType messageType) {
/* 103 */     if (messageType == null) {
/* 104 */       messageType = TrayIcon.MessageType.INFO;
/*     */     }
/* 106 */     if (msg == null) {
/* 107 */       msg = "";
/*     */     }
/* 109 */     switch (messageType)
/*     */     {
/*     */     case ERROR:
/* 111 */       msgLabel.setForeground(Color.RED);
/* 112 */       break;
/*     */     case INFO:
/* 114 */       msgLabel.setForeground(Color.GRAY);
/* 115 */       break;
/*     */     case WARNING:
/* 117 */       msgLabel.setForeground(Color.BLUE);
/*     */     }
/* 119 */     msgLabel.setText(msg);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 123 */     RFrame frame = new RFrame();
/*     */ 
/* 125 */     String cafeName = ResourceUtil.getMessage("licensedto");
/* 126 */     StringBuffer sb = new StringBuffer();
/* 127 */     if (!("".equals(cafeName))) {
/* 128 */       sb.append(ResourceUtil.getMessage("licensedto")).append(" - ");
/*     */     }
/*     */ 
/* 131 */     sb.append(ResourceUtil.getMessage("appname")).append(" ");
/* 132 */     sb.append(ResourceUtil.getMessage("appversion")).append(" ");
/*     */ 
/* 134 */     frame.setTitle(sb.toString());
/* 135 */     frame.getContentPane().add(new Main());
/* 136 */     frame.pack();
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Rangs 5\Desktop\netcafe.jar
 * Qualified Name:     org.rabbit.netcafe.Main
 * JD-Core Version:    0.5.3
 */