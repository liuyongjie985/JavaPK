package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

public class RemindUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemindUI dialog = new RemindUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemindUI() {
		path = System.getProperty("user.dir");
		setBounds(100, 100, 538, 252);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("大声告诉我你是不是真要删除");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(166, 44, 289, 88);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(path + "\\ima\\2.png"));
		lblNewLabel_1.setBounds(69, 54, 72, 72);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 181, 522, 39);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton ok = new JButton("是(大声地)");
				ok.setBounds(262, 5, 93, 23);
				ok.setActionCommand("OK");
				buttonPane.add(ok);
				getRootPane().setDefaultButton(ok);
			}
			{
				JButton cancel = new JButton("让我再想想");
				cancel.setBounds(360, 5, 133, 23);
				cancel.setActionCommand("Cancel");
				buttonPane.add(cancel);
			}
		}
	}
}
