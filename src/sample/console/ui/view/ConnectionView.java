package sample.console.ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import sample.console.ui.presenter.ConnectionPresenter;

public final class ConnectionView
    extends JPanel
{
    private static final String DEFAULT_CONNECTION_TIMEOUT = "0";
    private static final String RAS_DEFAULT_IP_PORT = "1545";
    private static final String LOCALHOST = "127.0.0.1";
    public static String DISCONNECTED = "Disconnected";
    public static String CONNECTED = "Connected";
    public static String BORDER_TITLE = "Connection";
    private ConnectionPresenter presenter;
    private JLabel serverLable;
    private JTextField serverTextField;
    private JLabel serverPortLable;
    private JTextField serverPortTextField;
    private JLabel timeoutLable;
    private JTextField timeoutTextField;
    private JButton connectButton;
    private JButton disconnectButton;
    private TitledBorder panelBorder;

    public ConnectionView()
    {
        createComponents();
        init();
        setComponentsEnabled(false);
        setLayout(createGroupLayout());
    }

    public void setPresenter(ConnectionPresenter presenter)
	{
	    this.presenter = presenter;
	}

    public int getPort()
    {
    	try
    	{
    		return Integer.valueOf(serverPortTextField.getText()).intValue();
    	}
    	catch (NumberFormatException e)
    	{
    		throw new RuntimeException("Illegal port value", e);
    	}
    }

    public String getServer()
    {
        return serverTextField.getText();
    }

    public long getTimeout()
    {
    	try
    	{
    		return Long.valueOf(timeoutTextField.getText()).longValue();
		}
		catch (NumberFormatException e)
		{
			throw new RuntimeException("Illegal timeout value", e);
		}
    }

    public void setComponentsEnabled(boolean enabled)
    {
        disconnectButton.setEnabled(enabled);
        connectButton.setEnabled(!enabled);
    }
    
    public void setConnectedStatus()
    {
        setBorderTitle(CONNECTED);
        setComponentsEnabled(true);
    }

    public void setDisconnectedStatus()
    {
        setBorderTitle(DISCONNECTED);
        setComponentsEnabled(false);
    }

    private void createBorder()
    {
        panelBorder =
            new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                BORDER_TITLE, TitledBorder.LEFT, TitledBorder.TOP, null,
                new Color(0, 0, 0));
        setBorder(panelBorder);
    }

    private void createComponents()
    {
        createConnectFields();
        createConnectButton();
        createDisconnectButton();
        createBorder();
    }

    private void createConnectButton()
    {
        connectButton = new JButton("Connect");
        connectButton.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    presenter.connectToServer();
                }
                catch (RuntimeException e)
                {
                    JOptionPane.showMessageDialog(ConnectionView.this,
                        e.getMessage(), "Connector error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private void createConnectFields()
    {
        serverLable = new JLabel("Server:");
        serverTextField = new JTextField(15);
        serverPortLable = new JLabel("Port:");
        serverPortTextField = new JTextField(5);
        serverPortTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        timeoutLable = new JLabel("Timeout (ms):");
        timeoutTextField = new JTextField(5);
        timeoutTextField.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void createDisconnectButton()
    {
        disconnectButton = new JButton("Disconnect");
        disconnectButton.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                presenter.disconnectFromServer();
            }
        });
    }

    private GroupLayout createGroupLayout()
    {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
            Alignment.TRAILING).addGroup(
            groupLayout.createSequentialGroup().addContainerGap().addComponent(
                serverLable).addGap(12).addComponent(serverTextField, 91, 91,
                91).addPreferredGap(ComponentPlacement.RELATED).addComponent(
                serverPortLable).addGap(6).addComponent(serverPortTextField,
                GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addPreferredGap(
                ComponentPlacement.RELATED).addComponent(timeoutLable).addPreferredGap(
                ComponentPlacement.RELATED).addComponent(timeoutTextField,
                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE).addPreferredGap(
                ComponentPlacement.RELATED).addComponent(connectButton,
                GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE).addGap(
                3).addComponent(disconnectButton).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addGap(2).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    serverLable, GroupLayout.PREFERRED_SIZE, 18,
                    GroupLayout.PREFERRED_SIZE).addComponent(serverTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE).addComponent(timeoutTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE).addComponent(connectButton).addComponent(
                    serverPortLable).addComponent(serverPortTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE).addComponent(timeoutLable).addComponent(
                    disconnectButton)).addContainerGap(
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        return groupLayout;
    }

    private void init()
    {
        serverTextField.setText(LOCALHOST);
        serverPortTextField.setText(RAS_DEFAULT_IP_PORT);
        timeoutTextField.setText(DEFAULT_CONNECTION_TIMEOUT);
        setDisconnectedStatus();
    }

    private void setBorderTitle(String status)
    {
        panelBorder.setTitle(BORDER_TITLE + " (" + status + ")");
        repaint();
    }
}
