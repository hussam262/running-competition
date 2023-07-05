import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

public class CompListGUI extends JFrame implements ActionListener {
	private ArrayList<HARCompetitor> compList;
	private ArrayList<HARCompetitor> sh;
	private ArrayList<HARCompetitor> m;
	private ArrayList<HARCompetitor> l;
	private ArrayList<HARCompetitor> h;
	private ArrayList<HARCompetitor> o;
	private CompetitorList list;
	// GUI components
	public JButton shortButton, middleButton, longButton, hurdleButton, otherButton, allButton, reportButton;

	public CompListGUI(CompetitorList list) {
		this.list = list;
		this.compList = list.getCompList();
		this.sh = list.getSh();
		this.m = list.getM();
		this.l = list.getL();
		this.h = list.getH();
		this.o = list.getO();
		// set up window title
		setTitle("Competitor List");
		// disable standard close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setUndecorated(true);

		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));
		this.setLayout(new BorderLayout(0, 0));
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();

		// pack and set visible
		pack();
		setSize(480, 500);

		setVisible(true);

	}

	private void setupCenterPanel() {
		JPanel centerPanel = new JPanel();
		JLabel label = new JLabel("Short Distance");
		label.setFont(new Font("Calibri", Font.BOLD, 15));
		JLabel label2 = new JLabel("Middle Distance");
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		JLabel label3 = new JLabel("Long Distance");
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		JLabel label4 = new JLabel("Hurdle Distance");
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		JLabel label5 = new JLabel("Other");
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		JLabel label6 = new JLabel("All Competitors");
		label6.setFont(new Font("Calibri", Font.BOLD, 15));

		shortButton = new JButton("Competitor List");
		shortButton.addActionListener(this);

		middleButton = new JButton("Competitor List");
		middleButton.addActionListener(this);

		longButton = new JButton("Competitor List");
		longButton.addActionListener(this);

		hurdleButton = new JButton("Competitor List");
		hurdleButton.addActionListener(this);

		otherButton = new JButton("Competitor List");
		otherButton.addActionListener(this);

		allButton = new JButton("Competitor List");
		allButton.addActionListener(this);

		centerPanel.setLayout(new GridLayout(6, 3, 150, 50));
		centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		centerPanel.add(label);
		centerPanel.add(label2);
		centerPanel.add(shortButton);
		centerPanel.add(middleButton);
		centerPanel.add(label3);
		centerPanel.add(label4);
		centerPanel.add(longButton);
		centerPanel.add(hurdleButton);
		centerPanel.add(label5);
		centerPanel.add(label6);
		centerPanel.add(otherButton);
		centerPanel.add(allButton);
		this.add(centerPanel, BorderLayout.CENTER);

	}

	private void setupNorthPanel() {

		JPanel northPanel = new JPanel();

		JLabel label = new JLabel("Running Competition");
		label.setFont(new Font("Calibri", Font.BOLD, 20));
		northPanel.add(label);
		northPanel.setSize(200, 30);

		this.add(northPanel, BorderLayout.NORTH);
	}

	private void setupSouthPanel() {

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 2, 500, 500));

		reportButton = new JButton("Create Report");
		reportButton.addActionListener(this);
		southPanel.add(reportButton);

		this.add(southPanel, BorderLayout.SOUTH);
	}

	public void compReport() throws FileNotFoundException {

		PrintStream out = new PrintStream(new FileOutputStream("compReport.txt"));

		for (int i = 0; i < compList.size(); i++) {
			out.println(compList.get(i).getShortDetails());

		}
		out.println();
		out.println("The Winner between all competitors is : ");
		out.println(list.highestScore().getFullDetails());

		out.close();
	}

	// come here when button is clicked
	// find which button and act accordingly
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == shortButton) {
			RaceGUI gui = new RaceGUI(sh);
			gui.setVisible(true);

		} else if (e.getSource() == middleButton) {
			RaceGUI gui = new RaceGUI(m);
			gui.setVisible(true);

		} else if (e.getSource() == longButton) {
			RaceGUI gui = new RaceGUI(l);
			gui.setVisible(true);
		} else if (e.getSource() == hurdleButton) {
			RaceGUI gui = new RaceGUI(h);
			gui.setVisible(true);
		} else if (e.getSource() == otherButton) {
			RaceGUI gui = new RaceGUI(o);
			gui.setVisible(true);
		} else if (e.getSource() == allButton) {
			RaceGUI gui = new RaceGUI(compList);
			gui.setVisible(true);
		} else if (e.getSource() == reportButton) {
			try {
				compReport();

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Report Created Successfully");
			System.exit(0);
		}
	}

}
