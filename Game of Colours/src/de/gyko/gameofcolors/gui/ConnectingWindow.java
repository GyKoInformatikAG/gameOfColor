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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
import de.gyko.gameofcolors.app.State;

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

public class ConnectingWindow extends JFrame implements PlayerListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	/** Der Text des Buttons Spiel als Host öffnen, wenn der Host auf Mitspieler wartet. */
	private static final String BE_HOST_START_TEXT = "Spiel starten!";
	
	/** Der Text des Buttons Spiel als Host öffnen, bevor der Spieler Host sein möchte. */
	private static final String BE_HOST_OPEN_TEXT = "Ein Spiel als Gastgeber er\u00F6ffnen...";
	
	/**
	 * Das Spiel
	 */
	GameOfColors game = null;

	private JPanel contentPane;
	private JTextField txtAdressGuest;
	private JTextField txtAdressHost;
	private JTextField txtName;
	private JPanel pnlSouthContent;
	private JLabel lblStatus;
	private JButton btnBeHost;
	private JComboBox<PlayerColor> cBxColor;

	public ConnectingWindow(GameOfColors game) {
		this();
		this.game = game;
		game.addPlayerListener(this);
		game.addPropertyChangeListener(this);
		displayGameState();
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
		
		lblStatus = new JLabel("<Status unbekannt>");
		pnlStatus.add(lblStatus);
		
		JPanel pnlSelectColor = new JPanel();
		pnlSelectColor.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pnlSplitNorth.add(pnlSelectColor);
		
		JLabel lblSelectColor = new JLabel("Farbe auswählen: ");
		pnlSelectColor.add(lblSelectColor);
		
		cBxColor = new JComboBox<PlayerColor>();
		pnlSelectColor.add(cBxColor);
		cBxColor.setModel(new DefaultComboBoxModel<PlayerColor>(PlayerColor.values()));
		cBxColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> cb = (JComboBox<?>)e.getSource();
		        cb.setBackground((PlayerColor.values()[cb.getSelectedIndex()]).getColor());	
			}
		});
		cBxColor.setRenderer(new ComboBoxRenderer(cBxColor.getFont()));
		cBxColor.setBackground((PlayerColor.values()[cBxColor.getSelectedIndex()]).getColor());
		
		JLabel lblName = new JLabel(" Name: ");
		pnlSelectColor.add(lblName);
		
		txtName = new JTextField();
		pnlSelectColor.add(txtName);
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
		txtName.setColumns(10);
		
		JPanel pnlHost = new JPanel();
		pnlSplitNorth.add(pnlHost);
		
		btnBeHost = new JButton(BE_HOST_OPEN_TEXT);
		btnBeHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBeHostActionPerformed();
			}
		});
		pnlHost.add(btnBeHost);
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		pnlHost.add(horizontalStrut);
		
		JLabel lblAdressHost = new JLabel("Adresse:");
		pnlHost.add(lblAdressHost);
		
		txtAdressHost = new JTextField();
		pnlHost.add(txtAdressHost);
		txtAdressHost.setEditable(false);
		txtAdressHost.setText("   ");
		txtAdressHost.setColumns(10);
		
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
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		pnlBeitreten.add(horizontalStrut_2);
		
		JButton btnJoin = new JButton("Beitreten");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnJoinActionPerformed();
			}
		});
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

	private void displayGameState() {
		String displayText = game.getState().toString(); 
		if (game.getState()!=State.NO_STATE_YET) {
			displayText += ". Du bist " + (game.isHost()?"Gastgeber.":"Mitspieler.");
		}
		lblStatus.setText(displayText);
	}

	/**
	 * Ändert den Dialog so, dass ein neuer Mitspieler angezeigt wird.
	 * 
	 * @param name - Der Name des Spielers
	 * @param color - Die Farbe des Spielers
	 */
    private void addPlayer(String name, Color color) {

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
	public void onPlayerJoin(PlayerJoinEvent pje) {
		Player p = game.getPlayerByColor(pje.getPlayerColor());
		addPlayer(p.getName(), p.getColor());		
	}

	@Override
	public void onPlayerLeave(PlayerLeaveEvent ple) {
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		// Es sind nur Statusänderungen des Spiels implementiert.
		if (!evt.getPropertyName().equals("state")) return;
		
		displayGameState();
		State state = (State)evt.getNewValue();
		
		// TODO
		switch (state) {
		case WAITING_FOR_START: {
			if (game.isHost()) {
				btnBeHost.setText(BE_HOST_START_TEXT);
			} else {
				
			}				
			break; }
		case NO_STATE_YET: {
			break; }
		case GAMING: {
			break; }
		}
	}

	/** 
	 * Die Methode, wenn der btnBeHost angeklickt wird.
	 * 
	 * @see ConnectingWindow#BE_HOST_OPEN_TEXT
	 * @see ConnectingWindow#BE_HOST_START_TEXT
	 */
	private void btnBeHostActionPerformed() {
		// Der Button ändert seine Bedeutung je nach Status des Spiels.
		if (game.getState()==State.NO_STATE_YET) {
			game.openAsHost(((PlayerColor)cBxColor.getSelectedItem()).getColor(), txtName.getText());
		} else {
			game.sendStart();
		}
	}
	
	/**
	 * Die Methode, wenn der btnJoin angeklickt wird.
	 */
	private void btnJoinActionPerformed() {
		// TODO Auto-generated method stub
		game.joinHost(((PlayerColor)cBxColor.getSelectedItem()).getColor(), txtName.getText());
	}	
	
}

