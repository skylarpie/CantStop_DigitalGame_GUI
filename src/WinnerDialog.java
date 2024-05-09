import javax.swing.JOptionPane;

public class WinnerDialog {
    public static void show(String winnerName) {
        String message = "The winner is " + winnerName + "!";
        JOptionPane.showMessageDialog(null, message, "Winner", JOptionPane.INFORMATION_MESSAGE);
        int option = JOptionPane.OK_OPTION;
        JOptionPane.showConfirmDialog(null, "Click OK to close", "Winner", option);

    }
}
