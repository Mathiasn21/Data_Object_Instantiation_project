package framework.dataCollection;

import java.util.Collection;

public abstract class Collector implements ICollector{
    private String[] primaryColumns;


    protected void setPrimaryColumns(String[] primaryColumns){ this.primaryColumns = primaryColumns; }

    @Override
    public String[] getAllPrimaryColumns() {
        return primaryColumns;
    }

    @Override
    public void getAllFilledColumns() {

    }

    @Override
    public void getCategoryBy(String name) {

    }

    @Override
    public void setSetting(Setting setting) {

    }

    @Override
    public void setSettings(Collection<Setting> settingCollection) {

    }
}
