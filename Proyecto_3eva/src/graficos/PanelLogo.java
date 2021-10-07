package graficos;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class PanelLogo extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelLogo() {
		setBounds(000, 000, 800, 600);
		setLayout(new BorderLayout(0, 0));
		
		JLabel LOGO = new JLabel("");
		LOGO.setIcon(new ImageIcon(PanelLogo.class.getResource("/imagenes/learning-calendar2.png")));
		add(LOGO);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("Principal");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(194, 11, 100, 23);

	}
}
