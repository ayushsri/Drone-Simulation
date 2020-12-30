package edu.neu.csye6200.droneUI;

import edu.neu.csye6200.droneBackEnd.Drone;
import edu.neu.csye6200.droneBackEnd.Packages;
import edu.neu.csye6200.droneBackEnd.Simulation;
import edu.neu.csye6200.droneBackEnd.Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;

public class MyCanvas extends JPanel implements Observer {
	private int ctr = 0;
	private Simulation mySim;
	private ArrayList<Integer>arg1;
	int x=0;int y=0;
	private Drone dr;
	private Packages pk;
	// local copy of a display map
	public boolean flag=false;
	Test t= Test.instance();
	int currDroneX=0;
	int currDroneY=0;
	int currPacPickUpX=0;
	int currPacPickUpY=0;
	int curPacDropUpX=0;
	int curPacDropUpY=0;
	public static boolean flag1=false;
	String outPut;


	
	public MyCanvas() {
	}
	public ArrayList<Drone> returnDrone()
	{
		return t.getArrDrones();
	}
	public ArrayList<Packages> returnPackages()
	{
		return t.getArrPackages();
	}
	Color col1=Color.BLUE,col2=Color.BLUE,col3=Color.BLUE,col4=Color.BLUE,col5=Color.BLUE;
	public void paint(Graphics g) {

		drawCanvas(g);



	}

	public void drawCanvas(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Dimension size = getSize(); // width and height
		
		g2d.setColor(Color.black); // pen is black
		g2d.fillRect(0, 0, size.width, size.height);
		

		
		//g2d.setColor(Color.red);
		//g2d.drawLine(0, 0, size.width, size.height); // draw a line - corner to corner
		
		int boxSize = 19;
		int maxRows = size.height / boxSize;
		int maxCols = size.width /boxSize;

		int r, gr, b;

		int x1=0,y1=0 ,x2=0,y2=0, x3=0,y3=0, x4=0,y4=0, x5=0,y5=0;
		int A1=0,B1=0 ,A2=0,B2=0, A3=0,B3=0, A4=0,B4=0, A5=0,B5=0;
		int L1=0,K1=0 ,L2=0,K2=0, L3=0,K3=0, L4=0,K4=0, L5=0,K5=0;

		if(!flag1)
		{
			col1=Color.BLUE;
			col2=Color.BLUE;
			col3=Color.BLUE;
			col4=Color.BLUE;
			col5=Color.BLUE;
		}

		if(flag)
		{ArrayList<Drone>D=returnDrone();
		 ArrayList<Packages>P=returnPackages();
		 	x1=(int)D.get(0).getCurPosX();
		 	y1=(int)D.get(0).getCurPosY();
			x2=(int)D.get(1).getCurPosX();
			y2=(int)D.get(1).getCurPosY();
			x3=(int)D.get(2).getCurPosX();
			y3=(int)D.get(2).getCurPosY();
			x4=(int)D.get(3).getCurPosX();
			y4=(int)D.get(3).getCurPosY();
			x5=(int)D.get(4).getCurPosX();
			y5=(int)D.get(4).getCurPosY();

			A1=(int)P.get(0).getPickUpX();
			B1=(int)P.get(0).getPickUpY();
			A2=(int)P.get(1).getPickUpX();
			B2=(int)P.get(1).getPickUpY();
			A3=(int)P.get(2).getPickUpX();
			B3=(int)P.get(2).getPickUpY();
			A4=(int)P.get(3).getPickUpX();
			B4=(int)P.get(3).getPickUpY();
			A5=(int)P.get(4).getPickUpX();
			B5=(int)P.get(4).getPickUpY();

			L1=(int)P.get(0).getDropOffX();
			K1=(int)P.get(0).getDropOffY();
			L2=(int)P.get(1).getDropOffX();
			K2=(int)P.get(1).getDropOffY();
			L3=(int)P.get(2).getDropOffX();
			K3=(int)P.get(2).getDropOffY();
			L4=(int)P.get(3).getDropOffX();
			K4=(int)P.get(3).getDropOffY();
			L5=(int)P.get(4).getDropOffX();
			K5=(int)P.get(4).getDropOffY();

		}
		for (int i = 0; i < maxCols; i++) { // columns
			for (int j = 0; j < maxRows; j++) { // rows
				r = validColor(255 - j*10);
				gr = validColor(50 + i*10);
				b = 50;


				Color col = new Color(gr, r, b);
				if (flag)
				{
					 if (((i == x) && (j == y)))
				{g2d.setColor(Color.GRAY);
					g2d.drawOval(i*20,j*20, boxSize+10, boxSize+10);
					g2d.fillOval(i*20, j*20, boxSize+10, boxSize+10);
					if(((i==currPacPickUpX && j==currPacPickUpY) &&(i==x && j==y))&&(i==A1&&j==B1))
					{col1=col;}
					if(((i==currPacPickUpX && j==currPacPickUpY) &&(i==x && j==y))&&(i==A2&&j==B2))
					{col2=col;}
					if(((i==currPacPickUpX && j==currPacPickUpY) &&(i==x && j==y))&&(i==A3&&j==B3))
					{col3=col;}
					if(((i==currPacPickUpX && j==currPacPickUpY) &&(i==x && j==y))&&(i==A4&&j==B4))
					{col4=col;}
					if(((i==currPacPickUpX && j==currPacPickUpY) &&(i==x && j==y))&&(i==A5&&j==B5))
					{col5=col;}
				}
				else if(((i == x1) && (j == y1))||((i == x2) && (j == y2))||((i == x3) && (j == y3))||((i == x4) && (j == y4))||((i == x5) && (j == y5)))
				{
					g2d.setColor(Color.GRAY);
					g2d.drawOval(i*20,j*20, boxSize+2, boxSize+2);
					//setForeground(Color.RED);
					g2d.fillOval(i*20, j*20, boxSize+2, boxSize+2);
				}
				else if((i == A1) && (j == B1))
					{
						g2d.setColor(col1);
					}
					 else if((i == A2) && (j == B2))
					 {
						 g2d.setColor(col2);
					 }
					 else if((i == A3) && (j == B3))
					 {
						 g2d.setColor(col3);
					 }
					 else if((i == A4) && (j == B4))
					 {
						 g2d.setColor(col4);
					 }
					 else if((i == A5) && (j == B5))
					 {
						 g2d.setColor(col5);
					 }

				else if(((i == L1) && (j == K1))||((i == L2) && (j == K2))||((i == L3) && (j == K3))||((i == L4) && (j == K4))||((i == L5) && (j == K5)))
				{
					g2d.setColor(Color.RED);}



					else
						g2d.setColor(col);}
				else
				{g2d.setColor(col);}
				g2d.fillRect(i*20, j*20, boxSize-2, boxSize-2);

			}
		}
		
		g2d.setColor(Color.red);

		flag=true;
		flag1=true;


	}

	
	private int validColor(int val) {
		if (val > 255) return 255;
		if (val < 0) return 0;
		return val;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Simulation)
			mySim=(Simulation)arg;
		else if(arg instanceof ArrayList)
		{arg1=(ArrayList<Integer>)arg;
           this.x=arg1.get(0);
           this.y=arg1.get(1);}
	    else if(arg instanceof Drone)
		{dr=(Drone)arg;
		currDroneX=(int)dr.getCurPosX();
		currDroneY=(int)dr.getCurPosY();}
		else if(arg instanceof Packages)
		{ pk=(Packages)arg;
		currPacPickUpX=pk.getPickUpX();
		currPacPickUpY=pk.getPickUpY();
		curPacDropUpX=pk.getDropOffX();
		curPacDropUpY=pk.getDropOffX();
		 }
		else if(arg instanceof String)
		{
			 outPut=(String)arg;
		}
		repaint();
	}

}
