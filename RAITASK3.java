/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package raitask3;

import javax.swing.*;

/**
 *
 * @author auliamarchita
 */

public class RAITASK3 {

	
	public static void main(String [] args){
		
		Object[] selectioValues = {"Server","Client"};
		String initialSection = "Server";
		
		Object selection = JOptionPane.showInputDialog(null, "Login as : ", "Multiclient Chatting Apllication", JOptionPane.QUESTION_MESSAGE, null, selectioValues, initialSection);
		if(selection.equals("Server")){
                   String[] arguments = new String[] {};
			new Server().main(arguments);
		}else if(selection.equals("Client")){
			String Server = JOptionPane.showInputDialog("Enter the server name : ");
                        String[] arguments = new String[] {Server};
			new Client().main(arguments);
		}
		
	}

}
