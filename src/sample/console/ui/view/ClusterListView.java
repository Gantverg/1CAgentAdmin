package sample.console.ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sample.console.ui.presenter.ClusterListPresenter;

public final class ClusterListView
    extends JPanel
{
    private static final String WARNING = "Warning";
    private ClusterListPresenter presenter;
    private JTable clusterTable;
    private TableModel clusterTableModel;
    private JScrollPane clusterTableScrollPane;
    private JButton clustersGetButton;
    private JButton terminateSessionsButton;

    public ClusterListView()
    {
        createPanelComponents();
        setComponentsEnabled(false);
        setLayout(createGroupLayout());
    }

    public void setPresenter(ClusterListPresenter presenter)
	{
	    this.presenter = presenter;
	}

    public String[][] getEmptyClustersTableData(int rows)
    {
        return new String[rows][clusterTableModel.getColumnCount() - 1];
    }

    public int getSelectedClusterIndex()
    {
        return clusterTable.getSelectedRow();
    }

    public void setComponentsEnabled(boolean enabled)
    {
        clustersGetButton.setEnabled(enabled);
        clusterTable.setEnabled(enabled);
        clusterTableScrollPane.setEnabled(enabled);
        terminateSessionsButton.setEnabled(false);
    }

    public void updateClusters(String[][] clusters)
    {
        clusterTableModel.updateTableModelData(clusters);
    }

    private void createGetClustersButton()
    {
        clustersGetButton = new JButton("Update cluster list");
        clustersGetButton.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    presenter.updateClusters();
                }
                catch (RuntimeException e)
                {
                    JOptionPane.showMessageDialog(ClusterListView.this,
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
                    clusterTableScrollPane, GroupLayout.DEFAULT_SIZE, 573,
                    Short.MAX_VALUE).addGroup(
                    groupLayout.createSequentialGroup().addComponent(
                        clustersGetButton).addPreferredGap(
                        ComponentPlacement.RELATED).addComponent(
                        terminateSessionsButton))).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addGroup(
                groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(
                    clustersGetButton).addComponent(terminateSessionsButton)).addPreferredGap(
                ComponentPlacement.RELATED).addComponent(
                clusterTableScrollPane, GroupLayout.PREFERRED_SIZE, 92,
                GroupLayout.PREFERRED_SIZE).addContainerGap(
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        return groupLayout;
    }

    private void createPanelComponents()
    {
        createGetClustersButton();
        createTerminateSessionsButton();
        createTable();
        setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
            "Clusters", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0,
                0, 0)));
    }

    private void createTable()
    {
        clusterTableModel =
            new TableModel(new String[] { "N", "Name", "Host" });
        clusterTable = new JTable(clusterTableModel);
        clusterTable.setFillsViewportHeight(true);
        clusterTable.getColumnModel().getColumn(0).setMaxWidth(50);
        clusterTable.getColumnModel().getColumn(0).setResizable(false);
        clusterTable.getSelectionModel().addListSelectionListener(
            new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    presenter.clusterValueChanged();
                    terminateSessionsButton.setEnabled(true);
                }
            });
        clusterTableScrollPane = new JScrollPane(clusterTable);
    }

    private void createTerminateSessionsButton()
    {
        terminateSessionsButton =
            new JButton("Terminate cluster sessions");
        terminateSessionsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    presenter.terminateSelectedClusterSessions();
                }
                catch (RuntimeException e)
                {
                    JOptionPane.showMessageDialog(ClusterListView.this,
                        e.getMessage(), WARNING, JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
