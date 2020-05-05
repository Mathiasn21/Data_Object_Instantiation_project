import DTOs.FinalCountdownDTO;
import doiframework.core.collectors.DataCollector;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.extractors.DataExtractor;
import doiframework.core.resource.DataSource;
import doiframework.exceptions.DatasetNotMatchingException;
import doiframework.exceptions.NoSuchColumnException;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.exceptions.UnableToAccessDataException;
import doiframework.statistics.calculations.Correlation;
import doiframework.statistics.report.DataReport;
import doiframework.statistics.report.ReportCollection;
import doiframework.utilities.handlers.CSVHandler;

import java.io.IOException;
import java.util.Arrays;

public class ReportMain {
    public static void main(String[] args) throws NotPrimitiveNumberException, DatasetNotMatchingException, IOException, ReflectiveOperationException, NoSuchColumnException, UnableToAccessDataException {
        double[] data = {1,2,3,4,5,6,6,6};
        double [] data2 = {2,5,6,7,7,8,9,6};

        Double[] dataset1 = new Double[data.length];
        Double[] dataset2 = new Double[data2.length];

        //Parse data1 to Double[]
        for (int i = 0; i<data.length; i++){
            dataset1[i] = data[i];
        }

        //Parse data1 to Double[]
        for (int i = 0; i<data2.length; i++){
            dataset2[i] = data2[i];
        }


        System.out.println("\n-------Full Average ReportCollection with executeReport Method applied-------\n");
        DataReport c1 = new DataReport(ReportCollection.getFullAverageReport(), dataset1);
        System.out.println(c1.executeReport() + "\n");
        c1.prettyPrintReport();

        System.out.println("\n-------Testing Full Simple Statistics ReportCollection with pretty print------\n");
        DataReport c3 = new DataReport(ReportCollection.getFullSimpleStatisticsReport(), dataset1);
        c3.prettyPrintReport();

        System.out.println("\n-------Testing Full Covariance/Correlation ReportCollection with pretty print------\n");
        DataReport c4 = new DataReport(ReportCollection.getFullCovarianceCorrelationReport(), dataset1, dataset2);
        c4.prettyPrintReport();

        System.out.println("\n-------Testing a simple calculation of correlation from population and sample------\n");
        Correlation c = new Correlation(dataset1, dataset2);
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
                dataset1, dataset2);

        c5.prettyPrintReport();

        System.out.println("\n-------Testing making my own report with the ReportCollection builder and pretty printing ------\n");
        DataReport c2 = new DataReport(ReportCollection.getBuilder()
                .calcAverageMean()
                .calcSampleVariance()
                .calcCovarianceFromSample()
                .calcCorrelationFromSample()
                .build(),
                dataset1, dataset2);
        c2.prettyPrintReport();



        String path = System.getProperty("user.dir") + "/files/finalCountdownCSV.csv" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        CSVHandler handler = new CSVHandler();
        handler.setDelimiter(";");
        IDataCollector collector = DataCollector.newCollector(dataSource, handler).build();
        collector.collectData();

        System.out.println(collector.getDataClazz());

        var extractor = new DataExtractor<>(collector);
        Class<FinalCountdownDTO> clazz = FinalCountdownDTO.class;
        var fields = Arrays.asList(clazz.getField("lyrics"), clazz.getField("data1"), clazz.getField("data2"));
        var columnsUsingFieldsMap = extractor.extractColumnsUsingFields(fields);
        var report = extractor.createReport();
        System.out.println(report);


        System.out.println("\n\n\n\n\n");
        extractor.createReport().values().forEach(System.out::println);

    }

}
