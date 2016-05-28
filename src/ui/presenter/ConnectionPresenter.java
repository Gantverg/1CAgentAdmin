package ui.presenter;

import sample.console.ui.view.ConnectionView;

public final class ConnectionPresenter
{
    private final ConsolePresenter main;
    private final ConnectionView view;

    public ConnectionPresenter(ConsolePresenter main, ConnectionView view)
    {
        this.main = main;
        this.view = view;
    }

    public void connectToServer()
    {
        main.connect(view.getServer(), view.getPort(), view.getTimeout());
        view.setConnectedStatus();
    }

    public void disconnectFromServer()
    {
        main.disconnect();
        view.setDisconnectedStatus();
    }
}
