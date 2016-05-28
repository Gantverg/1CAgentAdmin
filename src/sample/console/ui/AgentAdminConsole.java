package sample.console.ui;

import java.awt.EventQueue;

import javax.swing.UIManager;

import sample.console.AgentAdminUtil;
import sample.console.ui.presenter.ClusterListPresenter;
import sample.console.ui.presenter.ConnectionPresenter;
import sample.console.ui.presenter.ConsolePresenter;
import sample.console.ui.presenter.InfoBaseListPresenter;
import sample.console.ui.view.ClusterListView;
import sample.console.ui.view.ConnectionView;
import sample.console.ui.view.ConsoleView;
import sample.console.ui.view.InfoBaseListView;
import sample.console.ui.view.MainFrame;

import com._1c.v8.ibis.admin.client.AgentAdminConnectorFactory;

/**
 * 1C:Enterprise 8 Remote Administration Utility Sample
 */
public final class AgentAdminConsole
{
    private static final String TITLE =
        "1C:Enterprise 8 Remote Administration Utility Sample";

    public static void main(String[] args)
    {
        setSystemLookAndFeel();

        ConsolePresenter mainPresenter = 
        		new ConsolePresenter(new AgentAdminUtil(new AgentAdminConnectorFactory()));
        
        ConnectionView connectionView = new ConnectionView();
        ConnectionPresenter connectionPresenter =
            new ConnectionPresenter(mainPresenter, connectionView);
        connectionView.setPresenter(connectionPresenter);
        ClusterListView clusterListView = new ClusterListView();
        ClusterListPresenter clusterListPresenter =
            new ClusterListPresenter(mainPresenter, clusterListView);
        clusterListView.setPresenter(clusterListPresenter);

        InfoBaseListView infoBaseListView = new InfoBaseListView();
        InfoBaseListPresenter infoBaseListPresenter =
            new InfoBaseListPresenter(mainPresenter, infoBaseListView);
        infoBaseListView.setPresenter(infoBaseListPresenter);
        ConsoleView mainView =
            new ConsoleView(connectionView, clusterListView, infoBaseListView);
        mainPresenter.setView(mainView);
        mainPresenter.setClusterListPresenter(clusterListPresenter);
        mainPresenter.setInfobaseListPresenter(infoBaseListPresenter);
        EventQueue.invokeLater(new MainFrame(mainView, TITLE));
    }

    private static void setSystemLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
