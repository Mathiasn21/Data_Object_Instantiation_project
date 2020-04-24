package doiframework.statistics.report;

import doiframework.core.observer.EventObserver;
import doiframework.core.observer.events.ExceptionEvent;
import doiframework.exceptions.DatasetNotMatchingException;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.statistics.calculations.AdvancedStatistics;
import doiframework.statistics.calculations.Statistics;
import doiframework.utilities.Parser;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * Class with the central command that excecutes the report
 */
public class DataReport {
    private final List<Report> commands = new ArrayList<>();
    private final List<Report> advancedCommands = new ArrayList<>();
    private final List<Number[]> data;

    public DataReport(@NotNull Report[] commands, @NotNull Number[] ...data) throws NotPrimitiveNumberException {
        for (Report report : commands) {
            if(report.getIReport() instanceof IAdvancedReport){
                advancedCommands.add(report);
                continue;
            }
            this.commands.add(report);
        }
        for (Number[] o : data){
            if(!Parser.isPrimitiveNumber(o.getClass().getComponentType())){
                throw new NotPrimitiveNumberException();
            }
        }
        this.data = Arrays.asList(data);
    }

    public DataReport(@NotNull Report[] commands, @NotNull List<Number> data) throws NotPrimitiveNumberException {
        this.commands.addAll(Arrays.asList(commands));
        List<Number[]> listOfData = new ArrayList<>();
        if(!Parser.isPrimitiveNumber(data.get(0).getClass())){
            throw new NotPrimitiveNumberException();
        }
        listOfData.add(data.toArray(Number[]::new));
        this.data = listOfData;
    }

    @NotNull
    public Map<String, Double> executeReport()  {
        Map<String, Double> res = new HashMap<>(executeReportOnSimpleStatistics());
        res.putAll(executeReportOnAdvancedStatistics());
        return res;
    }

    private Map<String, Double> executeReportOnSimpleStatistics(){
        StatFactory statFactory = new StatFactory();
        Map<String, Double> res = new HashMap<>();
        String msg = ", Dataset: ";

        commands.forEach((command) -> {
            try {
                var report = command.getIReport();
                var mainClazz = report.getMainClass();

                for (int i = 0; i < data.size(); i++) {
                    Number[] number = data.get(i);
                    var statistic = statFactory.create(mainClazz, number);
                    double d;
                    d = report.calculate(statistic);
                    res.put(report.getOption() + (i == 0 ? msg + (i + 1) : msg + (i + 1)), d);
                }

            } catch (ReflectiveOperationException e) {
                EventObserver.registerEventFrom(new ExceptionEvent(this, e));
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return res;
    }

    @NotNull
    private Map<String, Double> executeReportOnAdvancedStatistics()  {
        StatFactory statFactory = new StatFactory();
        Map<String, Double> res = new HashMap<>();

        advancedCommands.forEach((command) -> {
            try {
                var report = command.getIReport();
                var mainClazz = report.getMainClass();

                if(((IAdvancedReport) report).getNumbSupportedDataSets() != data.size()){
                    return;
                }
                var statistic = statFactory.create(mainClazz, data.toArray(new Number[0][0]));

                double d;
                d = report.calculate(statistic);
                res.put(report.getOption(), d);

            } catch (ReflectiveOperationException e) {
                EventObserver.registerEventFrom(new ExceptionEvent(this, e));
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return res;
    }

    public void prettyPrintReport() {
        var report = executeReport();
        String s= "";
        Double d;

        TreeMap<String, Double> sorted = new TreeMap<>(report);
        Set<Map.Entry<String, Double>> mappings = sorted.entrySet();

        System.out.println("---------------------------------Report---------------------------------\n");
        for(Map.Entry<String, Double> mapping : mappings){
            s = mapping.getKey();
            d = mapping.getValue();

            String a = String.format("%-52s", s);
            System.out.println(a + d);
        }
        System.out.println("------------------------------------------------------------------------");
    }
    
}
