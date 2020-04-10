package DOIFramework.statistics;


import DOIFramework.exceptions.NotPrimitiveNumber;
import DOIFramework.extractors.IReport;
import DOIFramework.observer.EventObserver;
import DOIFramework.observer.events.ExceptionEvent;
import DOIFramework.utilities.Parser;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CentralCommand {
    private final List<ReportThings> commands;
    private final List<Double[]> data;
    private static final Map<ReportThings, IReport> cmdMappedToReport = new HashMap<>();


    CentralCommand(@NotNull ReportThings[] commands, @NotNull Double[]... data) throws NotPrimitiveNumber {
        this.commands = Arrays.asList(commands);
        for (Object o : data){
            if(!Parser.isPrimitiveNumber(o.getClass().getComponentType())){
                throw new NotPrimitiveNumber();
            }
        }
        this.data = Arrays.asList(data);
    }

    @NotNull
    public Map<String, Double> executeReport(){
        StatFactory statFactory = new StatFactory();
        commands.forEach((command) -> {
            try {
                IStatistics statistic = statFactory.create(command.getIReport().getMainClass(), data.get(0));
                
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                EventObserver.registerEventFrom(new ExceptionEvent(this, e));
            }
        });
        return null;
    }
}
