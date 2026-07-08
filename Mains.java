import javax.swing.SwingUtilities;
import frontend.LoginFrame;
import backend.bankmanagement;

public class Mains {

    public static void main(String[] args) {

        bankmanagement bank = new bankmanagement();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame(bank);
        });

    }
}