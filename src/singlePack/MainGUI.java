package singlePack;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
					frame.requestFocus();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 525, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JRadioButton rdbtnFiles = new JRadioButton("Files", true);
		rdbtnFiles.setBounds(17, 55, 109, 23);
		contentPane.add(rdbtnFiles);

		final JRadioButton rdbtnDirectories = new JRadioButton("Directories");
		rdbtnDirectories.setBounds(17, 81, 109, 23);
		contentPane.add(rdbtnDirectories);

		final JRadioButton rdbtnBothFD = new JRadioButton("Files & directories");
		rdbtnBothFD.setBounds(17, 107, 139, 23);
		contentPane.add(rdbtnBothFD);

		ButtonGroup radioGroup1 = new ButtonGroup();
		radioGroup1.add(rdbtnFiles);
		radioGroup1.add(rdbtnDirectories);
		radioGroup1.add(rdbtnBothFD);

		final JRadioButton rdbtnDirect = new JRadioButton("Direct");
		rdbtnDirect.setBounds(193, 55, 109, 23);
		contentPane.add(rdbtnDirect);

		final JRadioButton rdbtnIndirect = new JRadioButton("Indirect");
		rdbtnIndirect.setBounds(193, 81, 109, 23);
		contentPane.add(rdbtnIndirect);

		final JRadioButton rdbtnBothDI = new JRadioButton("Direct & indirect", true);
		rdbtnBothDI.setBounds(193, 107, 119, 23);
		contentPane.add(rdbtnBothDI);

		ButtonGroup radioGroup2 = new ButtonGroup();
		radioGroup2.add(rdbtnDirect);
		radioGroup2.add(rdbtnIndirect);
		radioGroup2.add(rdbtnBothDI);

		// EXTRA OPTION
		final JRadioButton rdbtnDIFDD = new JRadioButton("<html>Direct-indirect files<br/>& direct directories (Extra option)</html>");

		rdbtnDIFDD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0)
			{
				rdbtnDirect.setEnabled(!rdbtnDIFDD.isSelected());
				rdbtnIndirect.setEnabled(!rdbtnDIFDD.isSelected());
				rdbtnBothDI.setEnabled(!rdbtnDIFDD.isSelected());
			}
		});

		rdbtnDIFDD.setBounds(17, 145, 333, 41);
		contentPane.add(rdbtnDIFDD);
		radioGroup1.add(rdbtnDIFDD);
		
		final JRadioButton rdbtnNormal = new JRadioButton("Normal", true);
		rdbtnNormal.setBounds(362, 55, 109, 23);
		contentPane.add(rdbtnNormal);

		final JRadioButton rdbtnHidden = new JRadioButton("Hidden");
		rdbtnHidden.setBounds(362, 81, 109, 23);
		contentPane.add(rdbtnHidden);

		final JRadioButton rdbtnBothHN = new JRadioButton("Normal & hidden");
		rdbtnBothHN.setBounds(362, 107, 168, 23);
		contentPane.add(rdbtnBothHN);

		ButtonGroup radioGroup3 = new ButtonGroup();
		radioGroup3.add(rdbtnHidden);
		radioGroup3.add(rdbtnNormal);
		radioGroup3.add(rdbtnBothHN);

		JButton btnSelectADirectory = new JButton("Select a directory...");
		btnSelectADirectory.setToolTipText("<html>Select a directory from local file system<br/>to get detailed information about its contents,<br/>according to your inputs selected above.</html>");
		btnSelectADirectory.setBounds(190, 215, 146, 23);
		contentPane.add(btnSelectADirectory);

		JLabel lblListOfFiles = new JLabel("NAMES & LOCATIONS OF:");
		lblListOfFiles.setBounds(17, 34, 146, 14);
		contentPane.add(lblListOfFiles);

		JLabel lblTypeOfFilesdirectories = new JLabel("RELATION TYPE:");
		lblTypeOfFilesdirectories.setBounds(196, 34, 96, 14);
		contentPane.add(lblTypeOfFilesdirectories);

		JLabel lblAttribute = new JLabel("ATTRIBUTE:");
		lblAttribute.setBounds(366, 34, 73, 14);
		contentPane.add(lblAttribute);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(185, 34, 2, 104);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(185, 137, 316, 2);
		contentPane.add(separator_1);

		JLabel lblHelp = new JLabel("<html><u>Help</u></html>");
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				try
				{
					Desktop.getDesktop().browse(new URI("https://google.com"));
				}
				catch (IOException | URISyntaxException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cannot open help link", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblHelp.setBounds(25, 280, 46, 14);
		contentPane.add(lblHelp);
		lblHelp.setFont(new Font("Georgia", Font.ITALIC, 12));
		lblHelp.setForeground(Color.BLUE);
		lblHelp.setCursor(new Cursor((Cursor.HAND_CURSOR)));

		JLabel lblAboutThisSoftware = new JLabel("<html><u>About this software</u></html>");
		lblAboutThisSoftware.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				new Info();
			}
		});
		lblAboutThisSoftware.setBounds(390, 280, 120, 14);
		contentPane.add(lblAboutThisSoftware);
		lblAboutThisSoftware.setFont(new Font("Georgia", Font.ITALIC, 12));
		lblAboutThisSoftware.setForeground(Color.BLUE);
		lblAboutThisSoftware.setCursor(new Cursor((Cursor.HAND_CURSOR)));

		btnSelectADirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{

				JFileChooser directoryChooser = new JFileChooser();
				directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int openDialogSelectedOption = directoryChooser.showOpenDialog(MainGUI.this);

				if (openDialogSelectedOption == JFileChooser.APPROVE_OPTION)
				{
					// File selectedDirectory =
					// directoryChooser.getSelectedFile();
					Path selectedDirectoryPath = directoryChooser.getSelectedFile().toPath();

					FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
					JFileChooser nameAndLocationChooser = new JFileChooser(); // to
																				// specify
																				// the
																				// name
																				// and
																				// location
																				// of
																				// the
																				// new
																				// file
																				// to
																				// be
																				// created.
					nameAndLocationChooser.setDialogTitle("Save the list in a text file");
					nameAndLocationChooser.setFileFilter(filter);
					Path textFilePath = null;
					boolean fileCreationStatus = false;
					while (!fileCreationStatus)
					{
						int saveDialogSelectedOption = nameAndLocationChooser.showSaveDialog(MainGUI.this);
						if (saveDialogSelectedOption == JFileChooser.APPROVE_OPTION)
						{
							textFilePath = nameAndLocationChooser.getSelectedFile().toPath();
							
							String pathString = textFilePath.toString();
							if (!pathString.endsWith(".txt"))
							{
								pathString = pathString + ".txt";
								textFilePath = Paths.get(pathString);
							}
							
							fileCreationStatus = FileOperations.createTheFile(textFilePath);
						}
						else
							return;
					}

					Traversar traversar = new Traversar();
					ResultFilter resultFilter = new ResultFilter(traversar);

					String directOrIndirectFile = null;
					String directOrIndirectDirectory = null;
					String fileOrDirectory = null;
					String hiddenOrNot = "";

					//////////// DIRECT-INDIRECT & INDIRECT PART//////////////

					if (rdbtnIndirect.isSelected() || rdbtnBothDI.isSelected() || rdbtnDIFDD.isSelected())
					{
						if (rdbtnDIFDD.isSelected())
						{
							directOrIndirectFile = "Both kinds";
							directOrIndirectDirectory = "Direct";
						}
						else if (rdbtnIndirect.isSelected())
						{
							directOrIndirectFile = "Indirect";
							directOrIndirectDirectory = "Indirect";
						}
						else
						// if(rdbtnBothDI.isSelected())
						{
							directOrIndirectFile = "Both kinds";
							directOrIndirectDirectory = "Both kinds";
						}

						try
						{
							Files.walkFileTree(selectedDirectoryPath, traversar);
						}
						catch (IOException e1)
						{
							JOptionPane.showMessageDialog(null, "Sorry, The directory '"
									+ selectedDirectoryPath.toString()
									+ "' can't be visited becuase of some problem.", "Traversal Problem", JOptionPane.ERROR_MESSAGE);
							FileOperations.deleteTheCreatedFile(textFilePath);
							return;
						}

						////////////// DIRECT-INDIRECT PART////////////

						if (rdbtnFiles.isSelected() && rdbtnBothDI.isSelected())
						{
							fileOrDirectory = "file";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterDirectIndirectHiddenFiles(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterDirectIndirectNonHiddenFiles(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterDirectIndirectFiles(selectedDirectoryPath);
							}
						}

						else if (rdbtnDirectories.isSelected() && rdbtnBothDI.isSelected())
						{
							fileOrDirectory = "directory";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterDirectIndirectHiddenDirectories(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterDirectIndirectNonHiddenDirectories(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterDirectIndirectDirectories(selectedDirectoryPath);
							}
						}

						else if (rdbtnBothFD.isSelected() && rdbtnBothDI.isSelected())
						{
							fileOrDirectory = "both";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterDirectIndirectHiddenFilesDirectories(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterDirectIndirectNonHiddenFilesDirectories(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterDirectIndirectFilesDirectories(selectedDirectoryPath);
							}
						}

						/////////////// INDIRECT PART////////////////

						else if (rdbtnFiles.isSelected() && rdbtnIndirect.isSelected())
						{
							fileOrDirectory = "file";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterIndirectHiddenFiles(selectedDirectoryPath);
							}

							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterIndirectNonHiddenFiles(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterIndirectFiles(selectedDirectoryPath);
							}
						}

						else if (rdbtnDirectories.isSelected() && rdbtnIndirect.isSelected())
						{
							fileOrDirectory = "directory";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterIndirectHiddenDirectories(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterIndirectNonHiddenDirectories(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterIndirectDirectories(selectedDirectoryPath);
							}
						}

						else if (rdbtnBothFD.isSelected() && rdbtnIndirect.isSelected())
						{
							fileOrDirectory = "both";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterIndirectHiddenFilesDirectories(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterIndirectNonHiddenFilesDirectories(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterIndirectFilesDirectories(selectedDirectoryPath);
							}
						}

						////////////// EXTRA OPTION//////////////////

						else
						// if(rdbtnDIFDD.isSelected())
						{
							fileOrDirectory = "both";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterDirectHiddenDirectories(selectedDirectoryPath);
								resultFilter.filterDirectIndirectHiddenFiles(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterDirectNonHiddenDirectories(selectedDirectoryPath);
								resultFilter.filterDirectIndirectNonHiddenFiles(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterDirectDirectories(selectedDirectoryPath);
								resultFilter.filterDirectIndirectFiles(selectedDirectoryPath);
							}
						}
					}

					//////////////////// DIRECT
					//////////////////// PART/////////////////////////////

					else
					// if(rdbtnDirect.isSelected())
					{
						directOrIndirectFile = "Direct";
						directOrIndirectDirectory = "Direct";
						if (rdbtnFiles.isSelected() && rdbtnDirect.isSelected())
						{
							fileOrDirectory = "file";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterDirectHiddenFiles(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterDirectNonHiddenFiles(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterDirectFiles(selectedDirectoryPath);
							}
						}

						else if (rdbtnDirectories.isSelected() && rdbtnDirect.isSelected())
						{
							fileOrDirectory = "directory";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterDirectHiddenDirectories(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterDirectNonHiddenDirectories(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterDirectDirectories(selectedDirectoryPath);
							}
						}

						else
						// rdbtnBothFD.isSelected() && rdbtnDirect.isSelected()
						{
							fileOrDirectory = "both";
							if (rdbtnHidden.isSelected())
							{
								hiddenOrNot = "Hidden";
								resultFilter.filterDirectHiddenFilesDirectories(selectedDirectoryPath);
							}
							else if (rdbtnNormal.isSelected())
							{
								hiddenOrNot = "Normal";
								resultFilter.filterDirectNonHiddenFilesDirectories(selectedDirectoryPath);
							}
							else
							{
								hiddenOrNot = "Both kinds";
								resultFilter.filterDirectFilesDirectories(selectedDirectoryPath);
							}
						}

						if (resultFilter.directoryStreamStatus == "failed")
						{
							JOptionPane.showMessageDialog(null, "Sorry, The directory '"
									+ selectedDirectoryPath.toString()
									+ "' can't be visited becuase of some problem.", "Directory Stream Problem", JOptionPane.ERROR_MESSAGE);
							FileOperations.deleteTheCreatedFile(textFilePath);
							return;
						}
						else
							resultFilter.closeDirectoryStream();
					}

					// Path textFilePath;

					boolean writingStatus = FileOperations.writeToTheFile(selectedDirectoryPath, textFilePath, resultFilter.desiredList, resultFilter.numberOfFiles, resultFilter.numberOfDirectories, directOrIndirectFile, directOrIndirectDirectory, hiddenOrNot, fileOrDirectory);

					if (!writingStatus)
					{
						FileOperations.deleteTheCreatedFile(textFilePath);
						return;
					}

					new CompletionDialogue(textFilePath); // if the last catch
															// is
															// not catched.

				}

				else
					// if (ret!=JFileChooser.APPROVE_OPTION)
					return;

			}
		});

		setTitle("Advanced Directory Contents Information Provider");
		setIconImage(new ImageIcon(getClass().getResource("/images/adcip_favicon.png")).getImage());
		setResizable(false);
	}
}
