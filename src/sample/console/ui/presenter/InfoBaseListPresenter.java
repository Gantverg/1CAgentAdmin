package sample.console.ui.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import sample.console.ui.view.InfoBaseListView;
import sample.console.ui.view.InfoBasePropertiesDialog;

import com._1c.v8.ibis.admin.IInfoBaseInfo;
import com._1c.v8.ibis.admin.IInfoBaseInfoShort;

public final class InfoBaseListPresenter
{
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private final ConsolePresenter main;
    private final InfoBaseListView view;
    
    private List<IInfoBaseInfoShort> infoBases;

    public InfoBaseListPresenter(ConsolePresenter main, InfoBaseListView view)
    {
        this.main = main;
        this.view = view;
    }

    public void clearInfoBaseList()
    {
        infoBases = Collections.emptyList();
        view.updateInfoBases(convertInfoBaseListToTableData());
    }

    public UUID getSelectedInfoBaseId()
	{
	    int index = view.getSelectedInfoBaseIndex();
	    if (index < 0)
	    {
	        return null;
	    }
	
	    return infoBases.get(index).getInfoBaseId();
	}

    public void setEnabled(boolean enabled)
    {
        view.setComponentsEnabled(enabled);
    }
    
    public void showInfoBaseProperties()
    {
    	UUID infoBaseId = getSelectedInfoBaseId();
    	if (infoBaseId != null)
    	{
	        IInfoBaseInfo infoBaseInfo = main.getInfoBaseInfo(infoBaseId);
	        if (infoBaseInfo != null)
	        {
		        InfoBasePropertiesDialog propertiesDialog = new InfoBasePropertiesDialog();
		        fillInfoBaseProperties(propertiesDialog, infoBaseInfo);
		        if (propertiesDialog.showDialog(view, "Infobase properties"))
		        {
		            fillInfoBaseInfo(infoBaseInfo, propertiesDialog);
		            main.updateInfoBase(infoBaseInfo);
		            view.updateInfoBases(convertInfoBaseListToTableData());
		        }
	        }
    	}
    }

    public void updateInfoBaseList()
    {
    	infoBases = main.getInfoBaseShortInfos();
        view.updateInfoBases(convertInfoBaseListToTableData());
    }

    private String[][] convertInfoBaseListToTableData()
    {
        String[][] data = view.getEmptyInfoBasesTableData(infoBases.size());
        for (int index = 0; index < infoBases.size(); index++)
        {
            data[index][0] = infoBases.get(index).getName();
        }
        return data;
    }

    private void fillInfoBaseProperties(InfoBasePropertiesDialog dialog,
        IInfoBaseInfo infoBaseInfo)
    {
        dialog.setInfoBaseName(infoBaseInfo.getName());
        dialog.setSessionDenied(infoBaseInfo.isSessionsDenied());
        dialog.setDeniedFrom(dateToString(infoBaseInfo.getDeniedFrom()));
        dialog.setDeniedTo(dateToString(infoBaseInfo.getDeniedTo()));
        dialog.setDeniedMessage(infoBaseInfo.getDeniedMessage());
        dialog.setPermissionCode(infoBaseInfo.getPermissionCode());
        dialog.setDeniedParameter(infoBaseInfo.getDeniedParameter());
    }

    private void fillInfoBaseInfo(IInfoBaseInfo infoBaseInfo,
        InfoBasePropertiesDialog dialog)
    {
        infoBaseInfo.setName(dialog.getInfoBaseName());
        infoBaseInfo.setSessionsDenied(dialog.isSessionsDenied());
        infoBaseInfo.setDeniedFrom(stringToDate(dialog.getDeniedFrom()));
        infoBaseInfo.setDeniedTo(stringToDate(dialog.getDeniedTo()));
        infoBaseInfo.setDeniedMessage(dialog.getDeniedMessage());
        infoBaseInfo.setPermissionCode(dialog.getPermissionCode());
        infoBaseInfo.setDeniedParameter(dialog.getDeniedParameter());
    }

    private String dateToString(Date date)
	{
	    if (date.getTime() == 0)
	    {
	        return "";
	    }

        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(date);
	}

	private Date stringToDate(String dateString)
    {
        if (dateString.isEmpty())
        {
            return new Date(0);
        }
        
        try
        {
            return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(dateString);
        }
        catch (ParseException e)
        {
            throw new RuntimeException("Bad date format", e);
        }
    }
}
