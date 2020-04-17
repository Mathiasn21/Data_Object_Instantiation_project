package DOIFramework.statistics.report;

import DOIFramework.core.observer.EventObserver;
import DOIFramework.core.observer.events.ExceptionEvent;
import DOIFramework.exceptions.NotPrimitiveNumber;
import DOIFramework.statistics.calculations.Average;
import DOIFramework.statistics.calculations.IStatistics;
import DOIFramework.statistics.calculations.SimpleStatistics;
import DOIFramework.utilities.Parser;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentralCommand {
    private final List<ReportThings> commands;
    private final List<Double[]> data;

    public CentralCommand(@NotNull ReportThings[] commands, @NotNull Double[]... data) throws NotPrimitiveNumber {
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
        Map<String, Double> res = new HashMap<>();

        commands.forEach((command) -> {
            try {
                IReport report = command.getIReport();
                IStatistics statistic = statFactory.create(report.getMainClass(), data.get(0));
                double d;
                if(report.getClass() == AverageReport.class){
                    d = ((AverageReport) report).calculate.execute((Average) statistic);
                    res.put(((AverageReport) report).option, d);

                } else if(report.getClass() == SimpleStatisticalReport.class){
                    d = ((SimpleStatisticalReport) report).calculate.execute((SimpleStatistics) statistic);
                    res.put(((SimpleStatisticalReport) report).option, d);
                }

            } catch (ReflectiveOperationException e) {
                EventObserver.registerEventFrom(new ExceptionEvent(this, e));
            }
        });
        return res;
    }

    public void prettyPrintReport(){
        var report = executeReport(); //map<String, double>

        //TODO: Implement method for pretty printing
    }
}
