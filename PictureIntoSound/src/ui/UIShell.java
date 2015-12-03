package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soundGenerator.SoundGenerator;

public class UIShell extends JPanel implements ActionListener {

	JLabel picture;
	JLabel shapeText;
	JLabel sizeText;
	JLabel colorText;
	JLabel directionText;
	JLabel histogramText;
	JButton executeButton;
	JComboBox imageList;
	JComboBox shapeMeans;
	JComboBox sizeMeans;
	JComboBox colorMeans;
	JComboBox directionMeans;
	JComboBox histogramMeans;
	SoundGenerator sg;
	
	public UIShell() {
		super(new BorderLayout());
		FlowLayout flowLayout = new FlowLayout();

		// add combobox for image selection
		String[] ImageStrings = { "geocolorpage.jpg", "geofab.jpg",
				"georetropattern.jpg", "SimilarGeometricShapes.png",
				"stainedGlass.jpg" };
		imageList = new JComboBox(ImageStrings);
		imageList.setSelectedIndex(0);
		imageList.setAlignmentX(CENTER_ALIGNMENT);
		imageList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				String imageName = (String) cb.getSelectedItem();
				updateLabel(imageName);
			}
		});

		// Set up the geometric picture.
		picture = new JLabel();
		picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
		picture.setHorizontalAlignment(JLabel.CENTER);
		updateLabel(ImageStrings[imageList.getSelectedIndex()]);
		picture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		picture.setPreferredSize(new Dimension(300, 300));
		picture.setAlignmentX(CENTER_ALIGNMENT);

		// add shape TextLabel
		shapeText = new JLabel("What should shape mean?");
		shapeText.setFont(new Font("Verdana", 1, 20));
		shapeText.setAlignmentX(CENTER_ALIGNMENT);

		// add shape combobox selection
		String[] shapeStrings = { "Instrument", "Scale", "Note Length" };
		shapeMeans = new JComboBox(shapeStrings);
		shapeMeans.setSelectedIndex(0);
		shapeMeans.setAlignmentX(CENTER_ALIGNMENT);
		shapeMeans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spagett = "spagh";
			}
		});

		// add size TextLabel
		sizeText = new JLabel("What should size mean?");
		sizeText.setFont(new Font("Verdana", 1, 20));
		sizeText.setAlignmentX(CENTER_ALIGNMENT);

		// add size combobox selection
		String[] sizeStrings = { "Note Amplitude", "Instrument", "Note Length" };
		sizeMeans = new JComboBox(sizeStrings);
		sizeMeans.setSelectedIndex(0);
		sizeMeans.setAlignmentX(CENTER_ALIGNMENT);
		sizeMeans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spagett = "spagh";
			}
		});

		// add color TextLabel
		colorText = new JLabel("What should color mean?");
		colorText.setFont(new Font("Verdana", 1, 20));
		colorText.setAlignmentX(CENTER_ALIGNMENT);

		// add color combobox selection
		String[] colorStrings = { "Scale", "Instrument", "Note Length" };
		colorMeans = new JComboBox(colorStrings);
		colorMeans.setSelectedIndex(0);
		colorMeans.setAlignmentX(CENTER_ALIGNMENT);
		colorMeans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spagett = "spagh";
			}
		});

		// add direction TextLabel
		directionText = new JLabel("What direction would you like to traverse the image?");
		directionText.setFont(new Font("Verdana", 1, 20));
		directionText.setAlignmentX(CENTER_ALIGNMENT);

		// add color combobox selection
		String[] directionStrings = { "Top-Bottom", "Bottom-Top", "Left-Right", "Right-Left" };
		directionMeans = new JComboBox(directionStrings);
		directionMeans.setSelectedIndex(0);
		directionMeans.setAlignmentX(CENTER_ALIGNMENT);
		directionMeans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spagett = "spagh";
			}
		});


		// add histogram TextLabel
		histogramText = new JLabel("What would you like the grayscale histogram to mean?");
		histogramText.setFont(new Font("Verdana", 1, 20));
		histogramText.setAlignmentX(CENTER_ALIGNMENT);

		// add color combobox selection
		String[] histogramStrings = { "Scale", "Instrument", "Note Length" };
		histogramMeans = new JComboBox(histogramStrings);
		histogramMeans.setSelectedIndex(0);
		histogramMeans.setAlignmentX(CENTER_ALIGNMENT);
		histogramMeans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spagett = "spagh";
			}
		});

		//Execute Button
		executeButton = new JButton("Execute");
		executeButton.setAlignmentX(CENTER_ALIGNMENT);
		executeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sg = new SoundGenerator();				
			}
		});
		// add all of the stuff to the window
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(imageList);
		add(picture);
		add(shapeText);
		add(shapeMeans);
		add(sizeText);
		add(sizeMeans);
		add(sizeText);
		add(sizeMeans);
		add(colorText);
		add(colorMeans);
		add(directionText);
		add(directionMeans);
		add(histogramText);
		add(histogramMeans);
		add(executeButton);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	protected void updateLabel(String name) {
		String pathToImage = "images/" + name;
		ImageIcon icon = createImageIcon(pathToImage);
		picture.setIcon(icon);
		picture.setToolTipText(name.toLowerCase());
		if (icon != null) {
			picture.setText(null);
		} else {
			picture.setText("Image not found");
		}
	}

	/* Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		if (path != null) {
			return new ImageIcon(path);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Pict-Ear");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new UIShell();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}