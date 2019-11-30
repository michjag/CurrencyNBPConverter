package currencyconverternbp;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.json.JSONException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
	
	private JsonParser jsonParser = new JsonParser();
	private JTextField amountTextField;
	private String [] currencies =  {"PLN", "USD", "EUR", "GBP", "CHF"};
	private Choice choice;
	private Choice choice_1;
	private JLabel lblInputAmount;
	private JLabel lblOutputAmount;
	private JLabel lblDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("Currency Converter");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 270);
		getContentPane().setLayout(null);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setBounds(10, 48, 92, 31);
		getContentPane().add(lblAmount);
		
		amountTextField = new DoubleJTextField(); //  new JTextField();
		amountTextField.setText("0.00");
		amountTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountTextField.setBounds(10, 80, 80, 30);
		getContentPane().add(amountTextField);
		amountTextField.setColumns(10);
		
		JButton buttonChange = new JButton("Change");
		buttonChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				double firstValue = Double.parseDouble(amountTextField.getText());
							
				double secondValueExchangeRate = 1.0;
				try {
					secondValueExchangeRate = jsonParser.start(choice.getSelectedItem());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				double thirdValueExchangeRate = 1.0;
				try {
					thirdValueExchangeRate = jsonParser.start(choice_1.getSelectedItem());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				double sum = firstValue * secondValueExchangeRate / thirdValueExchangeRate;
				
				//sum = Math.round(sum);
				String result = String.format("%.2f", sum);
				System.out.println("wartośc: " + result);
				
				
				lblInputAmount.setText(amountTextField.getText() + " " + choice.getSelectedItem());
				lblOutputAmount.setText(result + " " + choice_1.getSelectedItem());
				
				try {
					lblDate.setText("Exchange date: " + jsonParser.exchangeDate(choice_1.getSelectedItem()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		buttonChange.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonChange.setBounds(451, 79, 100, 30);
		getContentPane().add(buttonChange);
		
		JLabel lblInputCurrency = new JLabel("Input currency");
		lblInputCurrency.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInputCurrency.setBounds(96, 56, 100, 14);
		getContentPane().add(lblInputCurrency);
		
		choice = new Choice();
		choice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		choice.setBounds(96, 85, 100, 30);
		getContentPane().add(choice);
		for (String string : currencies) {
			choice.add(string);	
		}
		
		
		choice_1 = new Choice();
		choice_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		choice_1.setBounds(283, 85, 100, 20);
		getContentPane().add(choice_1);
		for (String string : currencies) {
			choice_1.add(string);	
		}
				
		JLabel lblOutputCurrency = new JLabel("Output currency");
		lblOutputCurrency.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOutputCurrency.setBounds(281, 58, 114, 14);
		getContentPane().add(lblOutputCurrency);
		
		lblInputAmount = new JLabel("(Amount)  (PLN)");
		lblInputAmount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInputAmount.setBounds(106, 145, 139, 14);
		getContentPane().add(lblInputAmount);
		
		JLabel labelEquals = new JLabel("=");
		labelEquals.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelEquals.setBounds(295, 145, 16, 14);
		getContentPane().add(labelEquals);
		
		lblOutputAmount = new JLabel("(Amount) (EUR)");
		lblOutputAmount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOutputAmount.setBounds(355, 145, 131, 14);
		getContentPane().add(lblOutputAmount);
		
		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDate.setBounds(433, 196, 239, 34);
		getContentPane().add(lblDate);
	}
}
