package sample.console.ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sample.console.ui.presenter.InfoBaseListPresenter;

public final class InfoBaseListView
    extends JPanel
{
    private static final String WARNING = "Warning";
    private InfoBaseListPresenter presenter;
    private TableModel infoBaseTableModel;
    private JTable infoBaseTable;
    private JScrollPane infoBaseTableScrollPane;
    private JButton infoBasePropertiesButton;
    private JButton getInfoBasesButton;

    public InfoBaseListView()
    {
        createPanelComponents();
        setLayout(createGroupLayout());
        setComponentsEnabled(false);
    }

    public void setPresenter(InfoBaseListPresenter presenter)
	{
	    this.presenter = presenter;
	
	}

    public String[][] getEmptyInfoBasesTableData(int rows)
    {
        return new String[rows][infoBaseTableModel.getColumnCount() - 1];
    }

    public int getSelectedInfoBaseIndex()
    {
        return infoBaseTable.getSelectedRow();
    }

    public void setComponentsEnabled(boolean enabled)
    {
        infoBaseTable.setEnabled(enabled);
        infoBaseTableScrollPane.setEnabled(enabled);
        getInfoBasesButton.setEnabled(enabled);
        infoBasePropertiesButton.setEnabled(false);
    }

    public void updateInfoBases(String[][] infoBases)
    {
        infoBaseTableModel.updateTableModelData(infoBases);
        setComponentsEnabled(true);
    }

    private void createGetInfoBasesButton()
    {
        getInfoBasesButton = new JButton("Update infobase list");
        getInfoBasesButton.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    presenter.updateInfoBaseList();
                }
                catch (RuntimeException e)
                {
                    JOptionPane.showMessageDialog(InfoBaseListView.this,
                        e.getMessage(), WARNING, JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private GroupLayout createGroupLayout()
    {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addContainerGap().addGroup(
                groupLayout.createParallelGroup(Alignment.LEADING).addComponent(
                    infoBaseTableScrollPane, GroupLayout.DEFAULT_SIZE, 571,
                    Short.MAX_VALUE).addGroup(
                    groupLayout.createSequentialGroup().addComponent(
                        getInfoBasesButton, GroupLayout.PREFERRED_SIZE, 177,
                        GroupLayout.PREFERRED_SIZE).addPreferredGap(
                        ComponentPlacement.RELATED).addComponent(
                        infoBasePropertiesButton))).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    getInfoBasesButton).addComponent(infoBasePropertiesButton)).addPreferredGap(
                ComponentPlacement.RELATED).addComponent(
                infoBaseTableScrollPane, GroupLayout.DEFAULT_SIZE, 156,
                Short.MAX_VALUE).addContainerGap()));
        return groupLayout;
    }

    private void createInfoBasePropertiesButton()
    {
        infoBasePropertiesButton = new JButton("Infobase properties");
        infoBasePropertiesButton.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    presenter.showInfoBaseProperties();
                }
                catch (RuntimeException e)
                {
                    JOptionPane.showMessageDialog(InfoBaseListView.this,
                        e.getMessage(), WARNING, JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void createPanelComponents()
    {
        createGetInfoBasesButton();
        createInfoBasePropertiesButton();
        createTable();
        setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
            "Cluster infobases", TitledBorder.LEADING, TitledBorder.TOP, null,
            new Color(0, 0, 0)));
    }

    private void createTable()
    {
        infoBaseTableModel = new TableModel(new String[] { "N", "Name" });
        infoBaseTable = new JTable(infoBaseTableModel);
        infoBaseTable.setFillsViewportHeight(true);
        infoBaseTable.getColumnModel().getColumn(0).setMaxWidth(50);
        infoBaseTable.getColumnModel().getColumn(0).setResizable(false);
        ListSelectionModel selectionModel = infoBaseTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                infoBasePropertiesButton.setEnabled(true);
            }
        });
        infoBaseTableScrollPane = new JScrollPane(infoBaseTable);
    }
}
