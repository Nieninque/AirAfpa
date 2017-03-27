/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airafpa;

import controller.NewFlightController;
import dao.FlightDAO;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import view.FlightView;
import view.NewFlightView;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class AirAfpa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
      FlightDAO flightDAO = new FlightDAO();
        NewFlightController nfc = new NewFlightController(flightDAO);
      NewFlightView flightView =  new NewFlightView(nfc);
      
        frame.setSize(1000, 691);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLocation(450, 110);
        
        frame.add(flightView);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
