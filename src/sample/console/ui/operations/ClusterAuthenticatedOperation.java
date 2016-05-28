package sample.console.ui.operations;

import java.awt.Component;
import java.util.UUID;

import sample.console.AgentAdminOperation;
import sample.console.AgentAdminUtil;
import sample.console.ui.view.PasswordChooser;

import com._1c.v8.ibis.admin.AgentAdminAuthenticationException;

/**
 * Administrative operation that requires cluster authentication
 * 
 */
public final class ClusterAuthenticatedOperation<V>
    implements AgentAdminOperation<V>
{
	private final Component view;
	private final AgentAdminUtil adminUtil;
	private final UUID clusterId;
	private final AgentAdminOperation<V> callable;

    public ClusterAuthenticatedOperation(Component view,
        AgentAdminUtil adminUtil, UUID clusterId,
        AgentAdminOperation<V> operation)
	{
		this.view = view;
		this.adminUtil = adminUtil;
		this.clusterId = clusterId;
		this.callable = operation;
	}

	@Override
	public V call()
	{
		while (true)
		{
			try
			{
				return callable.call();
			}
			catch (AgentAdminAuthenticationException e)
			{
				PasswordChooser dialog = new PasswordChooser();
				if (!dialog.showDialog(view, "Cluster administrator")) 
				{
					throw e;
				}

				adminUtil.authenticateCluster(clusterId, dialog.getLogin(),
						String.valueOf(dialog.getPassword()));
			} 
		}
	}
}
