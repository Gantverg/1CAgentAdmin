package sample.console.ui.presenter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import sample.console.AgentAdminOperation;
import sample.console.AgentAdminUtil;
import sample.console.ui.operations.ClusterAuthenticatedOperation;
import sample.console.ui.operations.InfoBaseAuthenticatedOperation;
import sample.console.ui.view.ConsoleView;

import com._1c.v8.ibis.admin.IClusterInfo;
import com._1c.v8.ibis.admin.IInfoBaseInfo;
import com._1c.v8.ibis.admin.IInfoBaseInfoShort;

public final class ConsolePresenter
{
	private final AgentAdminUtil adminUtil;

	private ConsoleView view;
	private ClusterListPresenter clusterListPresenter;
	private InfoBaseListPresenter infoBaseListPresenter;

	public ConsolePresenter(AgentAdminUtil adminUtil)
	{
		this.adminUtil = adminUtil;
	}

	public void clearInfoBaseList()
	{
		infoBaseListPresenter.clearInfoBaseList();
		infoBaseListPresenter.setEnabled(true);
	}

	public void setClusterListPresenter(ClusterListPresenter presenter)
	{
		this.clusterListPresenter = presenter;
	}

	public void setInfobaseListPresenter(InfoBaseListPresenter presenter)
	{
		this.infoBaseListPresenter = presenter;
	}

	public void setView(ConsoleView view)
	{
		this.view = view;
	}

	public void connect(String server, int port, long timeout)
	{
		adminUtil.connect(server, port, timeout);

		clusterListPresenter.setComponentsEnabled(true);
	}

	public void disconnect()
	{
		adminUtil.disconnect();

		clusterListPresenter.clearClusterList();
		clusterListPresenter.setComponentsEnabled(false);

		infoBaseListPresenter.clearInfoBaseList();
		infoBaseListPresenter.setEnabled(false);
	}

	public List<IClusterInfo> getClusterInfoList()
	{
		return adminUtil.getClusterInfoList();
	}

	public IInfoBaseInfo getInfoBaseInfo(final UUID infoBaseId)
	{
		final UUID clusterId = clusterListPresenter.getSelectedClusterId();
		if (clusterId != null)
		{
			return new InfoBaseAuthenticatedOperation<IInfoBaseInfo>(view, adminUtil, clusterId,
					new AgentAdminOperation<IInfoBaseInfo>()
					{
						@Override
						public IInfoBaseInfo call() 
						{
							return adminUtil.getInfoBaseInfo(clusterId,
									infoBaseId);
						}

					}).call();
		}
		return null;
	}

	public List<IInfoBaseInfoShort> getInfoBaseShortInfos()
	{
		final UUID clusterId = clusterListPresenter.getSelectedClusterId();
		if (clusterId != null)
		{
			return 
				new ClusterAuthenticatedOperation<List<IInfoBaseInfoShort>>(view, adminUtil, clusterId, 
					new AgentAdminOperation<List<IInfoBaseInfoShort>>()
					{
						@Override
						public List<IInfoBaseInfoShort> call()
						{
							return adminUtil.getInfoBaseShortInfos(clusterId);
						}
					}).call();
		}

		return Collections.emptyList();
	}

	public void updateInfoBase(final IInfoBaseInfo info)
	{
		if (info == null)
		{
			throw new IllegalArgumentException("infoBaseInfo");
		}

		final UUID clusterId = clusterListPresenter.getSelectedClusterId();
		if (clusterId != null)
		{
			new InfoBaseAuthenticatedOperation<Object>(view, adminUtil, clusterId,
					new AgentAdminOperation<Object>()
					{

						@Override
						public Object call()
						{
							adminUtil.updateInfoBase(clusterId, info);
							return null;
						}

					}).call();
		}
	}

	public void terminateSelectedClusterSessions()
	{
		final UUID clusterId = clusterListPresenter.getSelectedClusterId();
		if (clusterId != null)
		{
			new ClusterAuthenticatedOperation<Object>(view, adminUtil, clusterId, 
					new AgentAdminOperation<Object>()
					{
						@Override
						public Object call()
						{
							adminUtil.terminateAllSessions(clusterId);
							return null;
						}
					}).call();
		}
	}
}
