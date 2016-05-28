package sample.console.ui.view;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

public final class PasswordChooser
    extends JPanel
{
    private JTextField administratorTextField;
    private JPasswordField passwordTextField;
    private JLabel administratorLabel;
    private JLabel passwordLabel;
    private JButton okButton;
    private JButton cancelButton;
    private JDialog dialog;
    private boolean ok;

    public PasswordChooser()
    {
        createPanelComponents();
        setLayout(createGroupLayout());
    }

    public String getLogin()
    {
        return administratorTextField.getText();
    }

    public char[] getPassword()
    {
        return passwordTextField.getPassword();
    }

    public boolean showDialog(Component parent, String title)
    {
        ok = false;
        Frame owner = null;
        if (parent instanceof Frame)
        {
            owner = (Frame)parent;
        }
        else
        {
            owner =
                (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);
        }
        if (dialog == null || dialog.getOwner() != owner)
        {
            dialog = new JDialog(owner, true);
            dialog.getContentPane().add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }
        dialog.setTitle(title);
        dialog.setLocationByPlatform(true);
        dialog.setSize(240, 135);
        dialog.setVisible(true);
        return ok;
    }

    private GroupLayout createGroupLayout()
    {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addContainerGap().addGroup(
                groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                    groupLayout.createSequentialGroup().addGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING).addComponent(
                            administratorLabel).addComponent(passwordLabel)).addPreferredGap(
                        ComponentPlacement.RELATED).addGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING).addComponent(
                            passwordTextField, GroupLayout.DEFAULT_SIZE, 137,
                            Short.MAX_VALUE).addComponent(
                            administratorTextField, GroupLayout.DEFAULT_SIZE,
                            137, Short.MAX_VALUE))).addGroup(
                    groupLayout.createSequentialGroup().addComponent(okButton).addPreferredGap(
                        ComponentPlacement.RELATED).addComponent(cancelButton))).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addContainerGap().addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    administratorLabel).addComponent(administratorTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    passwordLabel).addComponent(passwordTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    okButton).addComponent(cancelButton)).addContainerGap(
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        setLayout(groupLayout);
        return groupLayout;
    }

    private void createPanelComponents()
    {
        administratorLabel = new JLabel("Administrator:");
        administratorTextField = new JTextField();
        administratorTextField.setColumns(10);
        passwordLabel = new JLabel("Password:");
        passwordTextField = new JPasswordField();
        passwordTextField.setColumns(10);
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ok = true;
                dialog.setVisible(false);
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dialog.setVisible(false);
            }
        });
    }
}
