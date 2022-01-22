package de.gyko.gameofcolors.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import de.gyko.gameofcolors.app.GameOfColors;
import de.gyko.gameofcolors.app.Player;
import de.gyko.gameofcolors.app.PlayerJoinEvent;
import de.gyko.gameofcolors.app.PlayerLeaveEvent;
import de.gyko.gameofcolors.app.PlayerListener;

class ComboBoxRenderer extends JPanel implements ListCellRenderer<Object> {

	private static final long serialVersionUID = 1L;
	JPanel textPanel;
    JLabel text;

    public ComboBoxRenderer(Font font) {

        textPanel = new JPanel();
        textPanel.add(this);
        text = new JLabel();
        text.setOpaque(true);
        text.setFont(font);
        textPanel.add(text);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(list.getSelectionBackground());
        } else {
            setBackground(Color.WHITE);
        }

        text.setBackground(getBackground());

        text.setText(value.toString());
        if (index>-1) {
            text.setForeground((PlayerColor.values()[index]).getColor());
        } 
        return text;
    }

} // class ComboBoxRenderer

public class ConnectingWindow extends JFrame implements PlayerListener {

	/**
	 * Die für eine mögliche Serialisierung benötigte ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Das Spiel
	 */
	GameOfColors game = null;

	private JPanel contentPane;
	private JTextField txtAdressGuest;
	private JTextField txtAdressHost;
	private JTextField txtStatusHost;
	private JTextField txtName;
	private JPanel pnlSouthContent;

	public ConnectingWindow(GameOfColors game) {
		this();
		this.game = game;
		game.addPlayerListener(this);
	}
	
	/**
	 * Initialisiert die Elemente des JFrame.
	 */
	public ConnectingWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new CardLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlCenter.add(splitPane);
		
		JPanel pnlSplitNorth = new JPanel();
		splitPane.setLeftComponent(pnlSplitNorth);
		pnlSplitNorth.setLayout(new BoxLayout(pnlSplitNorth, BoxLayout.Y_AXIS));
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pnlSplitNorth.add(pnlStatus);
		
		JLabel lblStatus = new JLabel("<Status unbekannt>");
		pnlStatus.add(lblStatus);
		
		JPanel pnlHost = new JPanel();
		pnlSplitNorth.add(pnlHost);
		
		JButton btnNewHost = new JButton("Ein Spiel als Gastgeber er\u00F6ffnen");
		pnlHost.add(btnNewHost);
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		pnlHost.add(horizontalStrut);
		
		JLabel lblAdressHost = new JLabel("Adresse:");
		pnlHost.add(lblAdressHost);
		
		txtAdressHost = new JTextField();
		pnlHost.add(txtAdressHost);
		txtAdressHost.setEditable(false);
		txtAdressHost.setText("   ");
		txtAdressHost.setColumns(10);
		
		JLabel lblStatusHost = new JLabel("Status:");
		pnlHost.add(lblStatusHost);
		
		txtStatusHost = new JTextField();
		pnlHost.add(txtStatusHost);
		txtStatusHost.setText("kein Spiel er\u00F6ffnet");
		txtStatusHost.setEditable(false);
		txtStatusHost.setColumns(15);
		
		JPanel pnlSplitSouth = new JPanel();
		splitPane.setRightComponent(pnlSplitSouth);
		pnlSplitSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		pnlSouthContent = new JPanel();
		pnlSplitSouth.add(pnlSouthContent);
		pnlSouthContent.setLayout(new BoxLayout(pnlSouthContent, BoxLayout.Y_AXIS));
		
		JPanel pnlBeitreten = new JPanel();
		pnlBeitreten.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlSouthContent.add(pnlBeitreten);
		pnlBeitreten.setLayout(new BoxLayout(pnlBeitreten, BoxLayout.X_AXIS));
		
		JLabel lblJoin = new JLabel("Einem Spiel als Mitspieler beitreten");
		pnlBeitreten.add(lblJoin);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnlBeitreten.add(horizontalStrut_1);
		
		JLabel lblAdressGuest = new JLabel("Adresse des Spiels: ");
		pnlBeitreten.add(lblAdressGuest);
		
		txtAdressGuest = new JTextField();
		pnlBeitreten.add(txtAdressGuest);
		txtAdressGuest.setColumns(10);
		
		JLabel lblName = new JLabel(" Name: ");
		pnlBeitreten.add(lblName);
		
		txtName = new JTextField();
		txtName.setDocument(new PlainDocument() {

			private static final long serialVersionUID = 1L;
			private int maxLength = 20; // Todo: Die Maximale Länge eines Spielernamens muss Konstante in der Klasse Player werden.

		    /**
		     * Fügt den String nur dann ein, wenn die maximale Anzahl noch nicht überschritten ist.
		     * 
		     * @param offs Offset (Position, an der der String eingefuegt werden soll)
		     * @param str  einzufuegender String
		     * @param a    Attribut-Set (werden hier nicht weiter beachtet)
		     * @throws BadLocationException wird "von oben" durchgereicht
		     * @see javax.swing.text.AttributeSet
		     * @Override
		     */
		    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		        if (str == null) {
		            return;
		        }
		        int actualLength = this.getLength();
		        if (actualLength + str.length() <= this.maxLength) {
		            super.insertString(offs, str, a);
		        } else {
		            // kein beep() hier.
		        }
		    }

		});
		pnlBeitreten.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblFarbe = new JLabel(" Farbe: ");
		pnlBeitreten.add(lblFarbe);
		
		JComboBox<PlayerColor> cBxColor = new JComboBox<PlayerColor>();
		cBxColor.setModel(new DefaultComboBoxModel<PlayerColor>(PlayerColor.values()));
		cBxColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
		        cb.setBackground((PlayerColor.values()[cb.getSelectedIndex()]).getColor());	
			}
		});
		cBxColor.setRenderer(new ComboBoxRenderer(cBxColor.getFont()));
		cBxColor.setBackground((PlayerColor.values()[cBxColor.getSelectedIndex()]).getColor());	
		pnlBeitreten.add(cBxColor);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		pnlBeitreten.add(horizontalStrut_2);
		
		JButton btnJoin = new JButton("Beitreten");
		pnlBeitreten.add(btnJoin);
		
		JButton btnLeave = new JButton("Austreten");
		pnlBeitreten.add(btnLeave);
		
		JPanel pnlMitspieler = new JPanel();
		pnlMitspieler.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlSouthContent.add(pnlMitspieler);
		pnlMitspieler.setLayout(new BoxLayout(pnlMitspieler, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnlMitspieler.add(verticalStrut);
		
		JLabel lblMitspieler = new JLabel("Mitspieler:");
		pnlMitspieler.add(lblMitspieler);
		
		pack();
		
		
	}

	/**
	 * Ändert den Dialog so, dass ein neuer Mitspieler angezeigt wird.
	 * 
	 * @param name - Der Name des Spielers
	 * @param color - Die Farbe des Spielers
	 */
    public void addPlayer(String name, Color color) {

		JPanel pnlMS = new JPanel();
		pnlMS.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlSouthContent.add(pnlMS);
		pnlMS.setLayout(new BoxLayout(pnlMS, BoxLayout.X_AXIS));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		pnlMS.add(horizontalStrut_3);
		
		JLabel lblName_1 = new JLabel(name);
		pnlMS.add(lblName_1);
		
		Component rigidArea = Box.createRigidArea(new Dimension(30, 30));
		pnlMS.add(rigidArea);
		
		JPanel pnlColor = new JPanel();
		pnlColor.setBackground(color);
		pnlColor.add(Box.createHorizontalStrut(70));
		pnlMS.add(pnlColor);    	
    	
		this.pack();
    	
    }
    
	@Override
	public void playerJoins(PlayerJoinEvent pje) {
		Player p = game.getPlayerByColor(pje.getPlayerColor());
		addPlayer(p.getName(), p.getColor());		
	}

	@Override
	public void playerLeave(PlayerLeaveEvent ple) {
		
	}	
	
}

