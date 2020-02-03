package framework.dataCollection;

import java.io.File;
import java.util.Collection;

public abstract class Collector implements ICollector{
    private Collection<Object> columns;


    protected void setPrimaryColumns(Collection<Object> columns){
        this.columns = columns;
    }

    @Override
    public void getAllPrimaryColumns() {

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
