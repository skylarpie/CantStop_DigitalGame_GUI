import java.awt.Color;

import javax.swing.*;

public class SumDialogBox {

    public static int showSumDialogBox(int firstSum, int secondSum) {
    	
        // create the dialog box
        Object[] options = {firstSum, secondSum};
        int selectedOption = JOptionPane.showOptionDialog(null,
                "Please select a sum:",
                "Sum Dialog Box",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        return selectedOption == 0 ? firstSum : secondSum;
    }
}
