package calculator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.ComputeRates;

/**
 * 
 * @author Dev Kavathekar
 *
 */

//Graphical User Interface for the interest calculator program
//uses ComputeRates static methods for calculations.
public class InterestGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	//panels to separate GUI
	private JPanel entryPanels;
	private JPanel buttonPanels;
	private JPanel labelPanels;
	
	//entry fields for principle, rate(%), and years
	private JTextField principleEntry;
	private JTextField rateEntry;
	private JTextField yearsEntry;
	
	//buttons
	private JButton simpleInterest;
	private JButton compoundInterest;
	
	private JLabel resultLab;
	
	public InterestGUI(){
		
		this.setLayout(new BorderLayout());
		

		//makes 3 panels for entries, buttons and display labels
		entryPanels = new JPanel(new FlowLayout());
		buttonPanels = new JPanel(new FlowLayout());
		labelPanels = new JPanel(new FlowLayout());
		
		//creates and widgets
		makeEntryPanel();
		makeButtonPanel();
		
		//creates and adds the result display JLabel
		resultLab = new JLabel("");
		resultLab.setVisible(false);
		labelPanels.add(resultLab);
	
		//adds to main panel
		this.add(entryPanels,BorderLayout.NORTH);
		this.add(buttonPanels,BorderLayout.CENTER);
		this.add(labelPanels,BorderLayout.SOUTH);
				
	}

	//creates 2nd panel with logic for simple interest
	private void makeButtonPanel() {
		
		simpleInterest = new JButton("Compute Simple Interest");
		compoundInterest = new JButton(	"Compute Compound Interest");
		
		//anonymous action listener
		simpleInterest.addActionListener(new ActionListener() {

			//listens and performs when button 
			//for simple interest is clicked
			public void actionPerformed(ActionEvent e) {
				
				//if simple button is pressed..
				if(e.getSource() == simpleInterest){
					
					//checks for empty strings
					if(ComputeRates.checkEmptyStrings(
					   principleEntry, rateEntry, yearsEntry)){
						
						hideWidgets();
						showDisplayBox();
					
					} else{
						
						//casts, computes and formats result
						double principal = Double.parseDouble(principleEntry.getText());
						double rate = Double.parseDouble(rateEntry.getText());
						double years = Double.parseDouble(yearsEntry.getText());
						
						double finalResult = ComputeRates.simpleInterest(principal, rate, years);
						
						String formattedValue = NumberFormat.getCurrencyInstance().format(finalResult);
						
						resultLab.setVisible(true);
						resultLab.setText("Computed Simple Interest is:" + formattedValue);

						
					}
						
				}
				
			}
		
		});
		
		//adds inner class action listener
		compoundInterest.addActionListener(new ButtonListener());
		
		buttonPanels.add(simpleInterest);
		buttonPanels.add(compoundInterest);
		
	}
	
	//creates 1st panel
	private void makeEntryPanel() {
	
		principleEntry = new JTextField(20);
		rateEntry = new JTextField(10);
		yearsEntry = new JTextField(4);
		
		entryPanels.add(new JLabel("Principal:"));
		entryPanels.add(principleEntry);
		entryPanels.add(new JLabel("Rate(Percentage):"));
		entryPanels.add(rateEntry);
		entryPanels.add(new JLabel("Years:"));
		entryPanels.add(yearsEntry);
		
	}

	//shows a box if empty entries
	protected void showDisplayBox() {
		
		JOptionPane.showMessageDialog(this,
			    "One or more entries are empty",
			    "Input Error!",
			    JOptionPane.ERROR_MESSAGE);
		
		unHideWidgets();
		
	}


	//used for invalid input, hides widgets
	protected void hideWidgets() {
	
		principleEntry.setEnabled(false);
		rateEntry.setEnabled(false);
		yearsEntry.setEnabled(false);
		simpleInterest.setEnabled(false);
		compoundInterest.setEnabled(false);
		
	}
	
	//used for invalid input, un hides widgets
	private void unHideWidgets(){
		
		principleEntry.setEnabled(true);
		rateEntry.setEnabled(true);
		yearsEntry.setEnabled(true);
		simpleInterest.setEnabled(true);
		compoundInterest.setEnabled(true);
		
	}

	//inner class for compound listener
	private class ButtonListener implements ActionListener{


		//listens for compound button click
		//and performs arithmetic 
		public void actionPerformed(ActionEvent arg0) {
			
			//if compound button is pressed..
			if(arg0.getSource() == compoundInterest){
				
				//checks for empty strings
				if(ComputeRates.checkEmptyStrings(
				   principleEntry, rateEntry, yearsEntry)){
					
					hideWidgets();
					showDisplayBox();
	
				}else{
					
					//casts, computes and formats result
					double principal = Double.parseDouble(principleEntry.getText());
					double rate = Double.parseDouble(rateEntry.getText());
					double years = Double.parseDouble(yearsEntry.getText());
					
					double finalResult = ComputeRates.compoundInterst(principal, rate, years);
					
					String formattedValue = NumberFormat.getCurrencyInstance().format(finalResult);
					
					resultLab.setVisible(true);
					resultLab.setText("Computed Compound Interest is:" + formattedValue);
					
				}
				
				
			}
			
			
		}
		
		
	}
	
	/**
	 * ------------------------------------------------------------------------------------
	 * ---------------------------Code to start GUI below----------------------------------
	 * ------------------------------------------------------------------------------------
	 */
	
	//used to build framework for current JPanel
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Interest Calculator");
		frame.setContentPane(new InterestGUI());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	//creates new thread for GUI
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	

}
