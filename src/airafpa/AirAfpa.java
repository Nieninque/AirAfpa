/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airafpa;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import view.FlightView;

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
        
      FlightView flightView =  new FlightView();
      
      frame.setTitle("C'est plus, C'est moins");
        frame.setSize(1000, 691);
//        this.setMinimumSize(new Dimension(340, 480));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLocation(450, 110);
        
        frame.add(flightView);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
