package singlePack;

import java.awt.Desktop;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Path;

class CompletionDialogue extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CompletionDialogue(final Path fp)
	{
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 490, 148);
		getContentPane().setLayout(null);

		JLabel lblTheLabel = new JLabel("Your file '" + fp.getFileName().toString() + "' has been created at: '"
				+ fp.getParent().toString() + "'");
		lblTheLabel.setBounds(40, 21, 379, 19);
		getContentPane().add(lblTheLabel);

		JButton btnOpenFile = new JButton("Open the file");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Desktop.getDesktop().open(fp.toFile());
				}
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				setVisible(false);
			}
		});
		btnOpenFile.setBounds(113, 65, 107, 23);
		getContentPane().add(btnOpenFile);

		JButton btnOpenFolder = new JButton("Open folder");
		btnOpenFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Desktop.getDesktop().open(fp.getParent().toFile());
				}
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				setVisible(false);
			}
		});
		btnOpenFolder.setBounds(247, 65, 100, 23);
		getContentPane().add(btnOpenFolder);

		setIconImage(new ImageIcon(getClass().getResource("/images/adcip_favicon.png")).getImage());
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setTitle("Task Completed");
		setResizable(false);
		setVisible(true);
	}
}