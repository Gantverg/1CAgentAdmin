package sample.console.ui.presenter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import sample.console.ui.view.ClusterListView;

import com._1c.v8.ibis.admin.IClusterInfo;

public final class ClusterListPresenter
{
    private final ConsolePresenter main;
    private final ClusterListView view;
    
    private List<IClusterInfo> clusters;

    public ClusterListPresenter(ConsolePresenter main, ClusterListView view)
    {
        this.main = main;
        this.view = view;
    }

    public void clearClusterList()
    {
        clusters = Collections.emptyList();
        view.updateClusters(convertClusterListToTableData());
    }

    public void clusterValueChanged()
    {
        main.clearInfoBaseList();
    }

    public void updateClusters()
    {
    	clusters = main.getClusterInfoList();
        view.updateClusters(convertClusterListToTableData());
    }

    public UUID getSelectedClusterId()
    {
        int index = view.getSelectedClusterIndex();
        if (index < 0)
        {
            return null;
        }

        return clusters.get(index).getClusterId();
    }

    public void setComponentsEnabled(boolean enabled)
    {
        view.setComponentsEnabled(enabled);
    }

    public void terminateSelectedClusterSessions()
    {
        main.terminateSelectedClusterSessions();
    }

    private String[][] convertClusterListToTableData()
    {
        String[][] data = view.getEmptyClustersTableData(clusters.size());
        for (int index = 0; index < clusters.size(); index++)
        {
            data[index][0] = clusters.get(index).getName();
            data[index][1] = clusters.get(index).getHostName();
        }
        return data;
    }
}
