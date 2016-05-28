package sample.console.ui.view;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

public final class InfoBasePropertiesDialog
    extends JPanel
{
    private JTextField deniedFromTextField;
    private JTextField deniedToTextField;
    private JLabel deniedFromLabel;
    private JLabel deniedToLabel;
    private JButton okButton;
    private JButton cancelButton;
    private JDialog dialog;
    private boolean ok;
    private JTextField deniedMessageTextField;
    private JTextField permissionCodeTextField;
    private JTextField deniedParameterTextField;
    private JLabel deniedMessageLabel;
    private JLabel permissionCodeLabel;
    private JLabel deniedParameterLabel;
    private JCheckBox deniedCheckBox;
    private JLabel deniedLabel;
    private JTextField nameTextField;
    private JLabel nameLabel;

    public InfoBasePropertiesDialog()
    {
        createPanelComponents();
        setLayout(createGroupLayout());
    }

    public String getDeniedFrom()
    {
        return deniedFromTextField.getText();
    }

    public String getDeniedMessage()
    {
        return deniedMessageTextField.getText();
    }

    public String getDeniedParameter()
    {
        return deniedParameterTextField.getText();
    }

    public String getDeniedTo()
    {
        return deniedToTextField.getText();
    }

    public String getInfoBaseName()
    {
        return nameTextField.getText();
    }

    public String getPermissionCode()
    {
        return permissionCodeTextField.getText();
    }

    public boolean isSessionsDenied()
    {
        return deniedCheckBox.isSelected();
    }

    public void setDeniedFrom(String deniedFrom)
    {
        deniedFromTextField.setText(deniedFrom);

    }

    public void setDeniedMessage(String deniedMessage)
    {
        deniedMessageTextField.setText(deniedMessage);
    }

    public void setDeniedParameter(String deniedParameter)
    {
        deniedParameterTextField.setText(deniedParameter);
    }

    public void setDeniedTo(String deniedTo)
    {
        deniedToTextField.setText(deniedTo);

    }

    public void setInfoBaseName(String name)
    {
        nameTextField.setText(name);

    }

    public void setPermissionCode(String permissionCode)
    {
        permissionCodeTextField.setText(permissionCode);
    }

    public void setSessionDenied(boolean sessionsDenied)
    {
        deniedCheckBox.setSelected(sessionsDenied);
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
        dialog.setSize(400, 260);
        dialog.setVisible(true);
        return ok;
    }

    private GroupLayout createGroupLayout()
    {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
            Alignment.TRAILING).addGroup(
            groupLayout.createSequentialGroup().addContainerGap().addGroup(
                groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                    Alignment.TRAILING,
                    groupLayout.createSequentialGroup().addGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING).addComponent(
                            permissionCodeLabel).addComponent(
                            deniedMessageLabel).addComponent(deniedFromLabel).addComponent(
                            deniedToLabel).addComponent(deniedParameterLabel).addGroup(
                            groupLayout.createSequentialGroup().addComponent(
                                okButton).addPreferredGap(
                                ComponentPlacement.RELATED).addComponent(
                                cancelButton)).addComponent(nameLabel)).addPreferredGap(
                        ComponentPlacement.RELATED).addGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING).addComponent(
                            deniedFromTextField, Alignment.TRAILING,
                            GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE).addComponent(
                            deniedToTextField, Alignment.TRAILING,
                            GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE).addComponent(
                            deniedMessageTextField, GroupLayout.DEFAULT_SIZE,
                            278, Short.MAX_VALUE).addComponent(
                            permissionCodeTextField, GroupLayout.DEFAULT_SIZE,
                            278, Short.MAX_VALUE).addComponent(
                            deniedParameterTextField, GroupLayout.DEFAULT_SIZE,
                            278, Short.MAX_VALUE).addComponent(nameTextField,
                            GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))).addGroup(
                    groupLayout.createSequentialGroup().addComponent(
                        deniedLabel).addGap(67).addComponent(deniedCheckBox))).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addContainerGap(15,
                Short.MAX_VALUE).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    nameLabel).addComponent(nameTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)).addGroup(
                groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                    groupLayout.createSequentialGroup().addGap(6).addComponent(
                        deniedLabel, GroupLayout.PREFERRED_SIZE, 17,
                        GroupLayout.PREFERRED_SIZE)).addGroup(
                    groupLayout.createSequentialGroup().addGap(3).addComponent(
                        deniedCheckBox))).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    deniedFromLabel).addComponent(deniedFromTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    deniedToLabel).addComponent(deniedToTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    deniedMessageLabel).addComponent(deniedMessageTextField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    permissionCodeTextField, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(
                    permissionCodeLabel)).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    deniedParameterTextField, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(
                    deniedParameterLabel)).addPreferredGap(
                ComponentPlacement.RELATED).addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    okButton).addComponent(cancelButton)).addContainerGap(24,
                Short.MAX_VALUE)));
        setLayout(groupLayout);
        return groupLayout;
    }

    private void createPanelComponents()
    {
        nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        nameTextField.setEditable(false);
        nameTextField.setColumns(10);
        deniedLabel = new JLabel("Sessions denied:");
        deniedCheckBox = new JCheckBox("");
        deniedFromLabel = new JLabel("From (yyyy-mm-dd hh:mm:ss):");
        deniedFromTextField = new JTextField();
        deniedFromTextField.setColumns(10);
        deniedToLabel = new JLabel("To (yyyy-mm-dd hh:mm:ss):");
        deniedToTextField = new JTextField();
        deniedToTextField.setColumns(10);
        deniedMessageLabel = new JLabel("Message:");
        deniedMessageTextField = new JTextField();
        deniedMessageTextField.setColumns(10);
        permissionCodeLabel = new JLabel("Permission code:");
        permissionCodeTextField = new JTextField();
        permissionCodeTextField.setColumns(10);
        deniedParameterLabel = new JLabel("Parameter:");
        deniedParameterTextField = new JTextField();
        deniedParameterTextField.setColumns(10);
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
