import DTOs.ComplexDTOCSV;
import DTOs.FinalCountdownDTO;
import doiframework.core.annotations.DataObject;
import doiframework.core.collectors.DataCollector;
import doiframework.core.collectors.DataCollectorPool;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.collectors.IDataCollectorPool;
import doiframework.core.extractors.DataExtractor;
import doiframework.core.extractors.DataExtractorPool;
import doiframework.core.resource.DataSource;
import doiframework.exceptions.DatasetNotMatchingException;
import doiframework.exceptions.NoSuchColumnException;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.exceptions.UnableToAccessDataException;
import doiframework.statistics.calculations.Correlation;
import doiframework.statistics.report.DataReport;
import doiframework.statistics.report.ReportCollection;
import doiframework.utilities.handlers.CSVHandler;
import doiframework.utilities.handlers.IDataHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NotPrimitiveNumberException, DatasetNotMatchingException, IOException, ReflectiveOperationException, NoSuchColumnException, UnableToAccessDataException {
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

        String path = System.getProperty("user.dir") + "/files/finalCountdownCSV.csv" ;
        DataSource source = DataSource.newResource().fromFile(path).build();
        List<DataSource> list = new ArrayList<>();
        list.add(source);
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.setDelimiter(";");
        IDataCollector collector = DataCollector.newCollector(source, csvHandler).build();

        collector.collectData();

        var extractor = new DataExtractor<>(collector);
        var columnsUsingFieldsMap = extractor.extractColumnsUsingFields();
        List<Object> dataset1 = new ArrayList<>(columnsUsingFieldsMap.values());
        var thing = new ArrayList<>(columnsUsingFieldsMap.keySet());
        System.out.println(thing.get(1));

        System.out.println(dataset1.get(1));
        System.out.println("\n\n\n\n\n");
        extractor.extractReport().values().forEach(System.out::println);

        List<Field> l = Collections.singletonList(thing.get(1));
        System.out.println(extractor.extractReportUsingFields(l));
    }

    private static void showcaseAPIDataExtractorFields() throws IOException, ReflectiveOperationException {
        System.out.println("\n-------Showcasing API - DataExtractor - Fields ------");
        var collector = genCollector();
        var extractor = new DataExtractor<>(collector);
        var columnMap = extractor.extractColumnsUsingFields();
        columnMap.values().forEach(System.out::println);
    }

    private static void showcaseAPIDataExtractorMethods() throws IOException, NoSuchColumnException {
        System.out.println("\n-------Showcasing API - DataExtractor - Methods ------");
        var collector = genCollector();
        var extractor = new DataExtractor<>(collector);
        var columnMap = extractor.extractColumnsUsingMethods();
        columnMap.values().forEach(System.out::println);
    }

    @NotNull
    private static IDataCollector genCollector() throws IOException {
        String path = System.getProperty("user.dir") + "/files/showcaseAPI.csv" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataHandler handler = new CSVHandler();
        IDataCollector collector = DataCollector.newCollector(dataSource, handler).build();
        collector.collectData();
        return collector;
    }
}
