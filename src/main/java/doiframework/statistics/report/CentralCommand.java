package doiframework.statistics.report;

import doiframework.core.observer.EventObserver;
import doiframework.core.observer.events.ExceptionEvent;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.utilities.Parser;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CentralCommand {
    private final List<ReportThings> commands;
    private final List<Number[]> data;

    public CentralCommand(@NotNull ReportThings[] commands, @NotNull Number[] ...data) throws NotPrimitiveNumberException {
        this.commands = Arrays.asList(commands);
        for (Number[] o : data){
            if(!Parser.isPrimitiveNumber(o.getClass().getComponentType())){
                throw new NotPrimitiveNumberException();
            }
        }
        this.data = Arrays.asList(data);
    }
    public CentralCommand(@NotNull ReportThings[] commands, @NotNull List<Number> data) throws NotPrimitiveNumberException {
        this.commands = Arrays.asList(commands);

        List<Number[]> listOfData = new ArrayList<>();
        if(!Parser.isPrimitiveNumber(data.get(0).getClass())){
            throw new NotPrimitiveNumberException();
        }
        listOfData.add(data.toArray(Number[]::new));
        this.data = listOfData;
    }

    @NotNull
    public Map<String, Double> executeReport(){
        StatFactory statFactory = new StatFactory();
        Map<String, Double> res = new HashMap<>();

        commands.forEach((command) -> {
            try {
                var report = command.getIReport();
                var statistic = statFactory.create(report.getMainClass(), data.get(0));
                double d;

                d = report.calculate(statistic);
                res.put(report.getOption(), d);

            } catch (ReflectiveOperationException e) {
                EventObserver.registerEventFrom(new ExceptionEvent(this, e));
                e.printStackTrace();
            }
        });
        return res;
    }

    public void prettyPrintReport(){
        var report = executeReport(); //map<String, double>

        //TODO: Implement method for pretty printing
    }
}
