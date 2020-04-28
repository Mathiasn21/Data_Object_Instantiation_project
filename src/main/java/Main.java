import DTOs.ComplexDTOCSV;
import doiframework.core.collectors.DataCollectorPool;
import doiframework.core.collectors.IDataCollectorPool;
import doiframework.core.extractors.DataExtractorPool;
import doiframework.core.resource.DataSource;
import doiframework.exceptions.DatasetNotMatchingException;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.statistics.calculations.Correlation;
import doiframework.statistics.report.DataReport;
import doiframework.statistics.report.ReportCollection;
import doiframework.utilities.handlers.CSVHandler;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NotPrimitiveNumberException, DatasetNotMatchingException, IOException, IllegalAccessException {
        Double[] data = new Double[]{1d,2d,3d,4d,5d,6d,6d,6d};
        Double [] data2 = new Double[]{2d,5d,6d,7d,7d,8d,9d,6d};

        System.out.println("\n-------Testing Full Average ReportCollection with Execute ReportCollection-------\n");
        DataReport c1 = new DataReport(ReportCollection.getFullAverageReport(), data);
        var res1 = c1.executeReport();
        System.out.println(res1);

        System.out.println("\n-------Testing Full Simple Statistics ReportCollection with pretty print------\n");
        DataReport c3 = new DataReport(ReportCollection.getFullSimpleStatisticsReport(), data);
        c3.prettyPrintReport();

        System.out.println("\n-------Testing Full Covariance/Correlation ReportCollection with pretty print------\n");
        DataReport c4 = new DataReport(ReportCollection.getFullCovarianceCorrelationReport(), data, data2);
        c4.prettyPrintReport();

        System.out.println("\n-------Testing a simple calculation of correlation from population and sample------\n");
        Correlation c = new Correlation(data, data2);
        System.out.println(c.calcCorrelationCoefficientFromPopulation() + " " + c.calcCorrelationCoefficientFromSample());

        System.out.println("\n-------Testing making my own report with the ReportCollection builder and pretty printing ------\n");
        DataReport c5 = new DataReport(ReportCollection.getBuilder()
                .calcAverageMean()
                .calcAverageMedian()
                .calcAverageMidRange()
                .calcAverageMode()
                .calcSum()
                .calcSampleVariance()
                .calcPopulationVariance()
                .calcStandardDeviationFromSample()
                .calcStandardDeviationFromPopulation()
                .calcStandardErrorFromSample()
                .calcStandardErrorFromPopulation()
                .calcCovarianceFromSample()
                .calcCovarianceFromPopulation()
                .calcCorrelationFromPopulation()
                .calcCorrelationFromSample()
                .build(),
                data, data2);

        c5.prettyPrintReport();
        
        String path = System.getProperty("user.dir") + "/files/simpleCSV.csv" ;
        DataSource source = DataSource.newResource().fromFile(path).build();
        List<DataSource> list = new ArrayList<>();
        list.add(source);
        IDataCollectorPool collectorPool = DataCollectorPool.newCollectors(list, new CSVHandler()).buildAll();

        collectorPool.collectAllData();

        DataExtractorPool extractorPool = new DataExtractorPool(collectorPool);
        Map<Class<?>, List<Field>> map = new HashMap<>();
        List<Field> ll = Arrays.asList(ComplexDTOCSV.class.getFields());
        map.put(ComplexDTOCSV.class, ll);
        var res = extractorPool.extractAllColumnsFromFields(map);
        System.out.println(res);
    }
}
