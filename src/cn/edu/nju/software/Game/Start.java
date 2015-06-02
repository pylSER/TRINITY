package cn.edu.nju.software.Game;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

	class WelcomeWindow extends JFrame  {
		 private static final long serialVersionUID=1L;
		 private Point loc=null;
		 private Point tmp = null; 
		 private boolean isDragged = false; 

		 public WelcomeWindow() {  
		     int w = (Toolkit.getDefaultToolkit().getScreenSize().width-700 )/2; 
	         int h = (Toolkit.getDefaultToolkit().getScreenSize().height-450)/2; 
		       
	         this.setLocation(w, h);
		     // Ϊ�����������¼� 
		     this.addMouseListener(new java.awt.event.MouseAdapter() { 
		         public void mouseReleased(MouseEvent e) { 
		             isDragged = false; 
		         } 

		         public void mousePressed(MouseEvent e) { 
		             tmp = new Point(e.getX(), e.getY()); 
		             isDragged = true; 	              
		         } 
		     }); 

		     this.addMouseMotionListener(new MouseMotionAdapter() { 
		         // ��갴��������ϰ��²��϶�ʱ���á� 

	             public void mouseDragged(MouseEvent e) { 
		             if (isDragged) { 
		                 loc = new Point(getLocation().x + e.getX() - tmp.x, 
	                         getLocation().y + e.getY() - tmp.y); 
		                     setLocation(loc); 
		             } 
		         } 
		     }); 
		 }
	}
		 	 
	
	class MyDrawPanel1 extends JPanel{
		public void paintComponent(Graphics g){
			Image image=new ImageIcon("welcome.jpg").getImage();
			g.drawImage(image,0,0,this);
		}
	}
	 public class Start{
		 int x=-20;
		 JLabel label=new JLabel();
		
		 
 public void go(){
	 WelcomeWindow welWindow=new WelcomeWindow();
	 JPanel panel=new JPanel(); 
     MyDrawPanel1 icon2=new MyDrawPanel1();
	  welWindow.getContentPane().add(icon2);
	
	  
	 welWindow.setUndecorated(true); 
     welWindow.setSize(700, 432); 
     welWindow.setVisible(true);
		 	Inner inn=new Inner();
		 	welWindow.getContentPane().add(inn);
		 	 welWindow.setVisible(true);
		 	for(int i=0;i<=500;i++){
		 		x++;
		 		inn.repaint();
		 		
		 		try{
		 			Thread.sleep(5);
		 		}catch(Exception ex){}
		 		
		 		}
		 	welWindow.setVisible(false);
		 	welWindow=null;
		 	
		 	
		 }
		 class Inner extends JPanel{
		 	public void paintComponent(Graphics g){
		 		g.setColor(Color.white);
		 		g.fillRect(x-20, 427, 300, 5);
		 	}
		 }
		
			 
	 }
