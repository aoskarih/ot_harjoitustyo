/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.ui;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author hyarhyar
 */
public class NumberOnlyTextField extends TextField {

    public NumberOnlyTextField() {
        
        DecimalFormat format = new DecimalFormat("#.0");
        
        this.setTextFormatter(new TextFormatter<>( c -> 
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(c.getControlNewText(), parsePosition);

            if (object == null || parsePosition.getIndex() < c.getControlNewText().length())
            {
                return null;
            }
            else
            {
                return c;
            }
        }));
    }
    
    
    
}
