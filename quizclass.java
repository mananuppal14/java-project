import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
public class quizclass extends JFrame implements ActionListener{
	
	Label lques,lcount,lout; 	//creating labels
	JRadioButton bA,bB,bC,bD;	// creating radio buttons
	JButton bN,bP;	//creating buttons.
	Panel p1;	//creating panel
	int c=0,s=0;
	Problem[] queslist = new Problem[5];	//creating object queslist of class Problem.
	Problem p;
	quizclass()	//constructor
	{
		GridLayout g=new GridLayout(0,1);
		setLayout(g);
		setTitle("MORNING BOOST");
		setSize(900,500);
		setLocationRelativeTo(null);
		
		//DEFINING VARIOUS BUTTONS AND LABELS 
		
		lques=new Label("lques");
		bA=new JRadioButton("ans a");
		bB=new JRadioButton("ans b");
		bC=new JRadioButton("ans c");
		bD=new JRadioButton("ans d");
		bP=new JButton("PREVIOUS");
		bN=new JButton("NEXT");
		
		//ADDING BUTTONS AND LABELS
		
		add(lques);
		add(bA);
		add(bB);
		add(bC);
		add(bD);
		
		//SETTING UP FONT FOR lques LABEL
		
		Font myFont = new Font("Serif",Font.BOLD,18);
        lques.setFont(myFont);
		
        //CREATING PANEL 
        
		p1=new Panel();
			//ADDING BUTTONS TO PANEL
		p1.add(bP);
		p1.add(bN);
		p1.setLayout(new GridLayout (1,2, 3, 2) );
		add(p1);	//ADDING PANEL TO WINDOW
		
		//GROUPING RADIOBUTTONS
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(bA);
		bg.add(bB);
		bg.add(bC);
		bg.add(bD);
		
		setVisible(true);
		
		//EVENT HANDLING
		bA.addActionListener(this);
		bB.addActionListener(this);
		bC.addActionListener(this);
		bD.addActionListener(this);
		bP.addActionListener(this);
		bN.addActionListener(this);	
	}		
	
	public void problems()
	{
		//SETTING UP QUESTIONS
		
		queslist[0]=new Problem(
				  "Question 1) Which of these is a mechanism for naming and visibility control of a class and its content?",
				  "a) Object",
				  "b) Packages",
				  "c) Interfaces",
				  "d) none of the mentioned",
				  "B");
		queslist[1]=new Problem(
				  "Question 2) A good reason to use an object oriented language is that. : ",
				  "a) You can define your data type",
				  "b) program statements are simpler than in procedural languages",
				  "c) an object oriented program can correct its own mistakes",
				  "d) it is easier to conceptualize an object-oriented program and reuse code.",
				  "D");
		queslist[2]=new Problem(
				  "Question 3)  Which class cannot be subclassed (or extended) in java?",
				  "a) abstract class",
				  "b) parent class",
				  "c) final class",
				  "d) none of above",
				  "C");
		queslist[3]=new Problem(
				"Question 4) pow() is associated with which class",
				  "a) Math class",
				  "b) Input stream class",
				  "c) Object class",
				  "d) none of above",
				  "A");
		queslist[4]=new Problem(
				"Question 5)  Which of these are selection statements in Java?",
				  "a) if()",
				  "b) for()",
				  "c) continue",
				  "d) break",
				  "A");
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == bA)
			checkAns("A");
		else if (ae.getSource() == bB)
			checkAns("B");
		else if (ae.getSource() == bC)
			checkAns("C");
		else if (ae.getSource() == bD)
			checkAns("D");
		else if (ae.getSource() == bP)
		{
			c--;
			if (c<0)
				c=0;
			changePage(); 
		}
		else if(ae.getSource() == bN)
		{
			c++;
			if(c>queslist.length-1)
			{
				setVisible(false);
				scoreEvaluation();	
			}
			changePage();
		}
		else
			lout.setText("something went wrong");
		
	}
	public void checkAns(String guess)
	{
		String correct = queslist[c].getCorrect();
		if (guess.equals(correct))
			s++;						 
	}
	public void changePage()
	{
		p = new Problem(null, null, null, null, null, null);
		p = queslist[c];
		
			//GETTING TEXTS FOR RADIOBUTTONS AND LABEL
		lques.setText(p.getQuestion());
		bA.setText(p.getAnsA());
		bB.setText(p.getAnsB());
		bC.setText(p.getAnsC());
		bD.setText(p.getAnsD());
	}
	public void scoreEvaluation()
	{
		Frame f=new Frame("score");   //ADDING SCORE FRAME
		Label sc=new Label("YOUR SCORE IS "+s);
		f.add(sc);
		f.setSize(250, 100);
		f.setLocationRelativeTo(null);
		Font myFont = new Font("Serif",Font.BOLD,16);
        sc.setFont(myFont);
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				System.out.println("Window listener activated");
			}
			public void windowDectivated(WindowEvent e)
			{
				System.out.println("Window listener Deactivated");
			}
			public void windowClosing(WindowEvent e)
			{
				System.out.println("Window Closed");
				System.exit(0);
			}
		});
	}
	public static void main(String args[])
	{
		quizclass a=new quizclass();
		a.problems();
		a.changePage();
	}
}
class Problem
{
	public String Question,ansA,ansB,ansC,ansD,correct;
	
	//CREATING CONSTRUCTOR FOR PROBLEM CLASS
	
	public Problem(String Question, String tAnsA, String tAnsB,String tAnsC,String tAnsD,String tCorrect){
		this.Question = Question;
		ansA = tAnsA;
		ansB = tAnsB;
		ansC = tAnsC;
		ansD = tAnsD;
		correct = tCorrect;
	}	
	public String getQuestion(){
		return Question;
	} 
	String getAnsA(){
		return ansA;
	} 
	String getAnsB(){
		return ansB;
	}
	String getAnsC(){
		return ansC;
	} 
	String getAnsD(){
		return ansD;
	}
	String getCorrect(){
		return correct;
	}
}
