package sample.console.ui.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public final class ConsoleView
    extends JPanel
{
    private ConnectionView connectionView;
    private ClusterListView clusterListView;
    private InfoBaseListView infoBaseListView;

    public ConsoleView(ConnectionView connectionView,
        ClusterListView clusterListView, InfoBaseListView infoBaseListView)
    {
        this.connectionView = connectionView;
        this.clusterListView = clusterListView;
        this.infoBaseListView = infoBaseListView;
        setLayout(createGroupLayout());
    }

    private GroupLayout createGroupLayout()
    {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
            Alignment.TRAILING).addGroup(
            groupLayout.createSequentialGroup().addContainerGap().addGroup(
                groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                    groupLayout.createSequentialGroup().addComponent(
                        connectionView, GroupLayout.PREFERRED_SIZE,
                        605,
                        Short.MAX_VALUE).addContainerGap()).addGroup(
                    Alignment.TRAILING,
                    groupLayout.createSequentialGroup().addGroup(
                        groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(
                            clusterListView, Alignment.LEADING,
                            GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE).addComponent(
                            infoBaseListView,
                            GroupLayout.DEFAULT_SIZE, 605,
                            Short.MAX_VALUE)).addContainerGap()))));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
            Alignment.LEADING).addGroup(
            groupLayout.createSequentialGroup().addGap(6).addComponent(
                connectionView, GroupLayout.PREFERRED_SIZE, 60,
                GroupLayout.PREFERRED_SIZE).addGap(2).addComponent(
                clusterListView, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(
                ComponentPlacement.RELATED).addComponent(
                infoBaseListView,
                GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE).addGap(19)));
        return groupLayout;
    }
}
