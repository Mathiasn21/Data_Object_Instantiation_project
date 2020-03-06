package framework.collectors;

import java.util.List;

public interface ICSV extends ICollector{
    List<String[]> getInformationalRows();
}
