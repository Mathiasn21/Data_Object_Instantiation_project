package doiframework.statistics.report;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {

    private final List<Report> list = new ArrayList<>();

    public ReportBuilder calcAverageMode(){
        list.add(Report.AVERAGE_MODE);
        return this;
    }
    public ReportBuilder calcAverageMidRange(){
        list.add(Report.AVERAGE_MID_RANGE);
        return this;
    }
    public ReportBuilder calcAverageMean(){
        list.add(Report.AVERAGE_MEAN);
        return this;
    }
    public ReportBuilder calcAverageMedian(){
        list.add(Report.AVERAGE_MEDIAN);
        return this;
    }
    public ReportBuilder calcSum(){
        list.add(Report.TOTAL_SUM);
        return this;
    }

    public ReportBuilder calcSampleVariance(){
        list.add(Report.SAMPLE_VARIANCE);
        return this;
    }
    public ReportBuilder calcPopulationVariance(){
        list.add(Report.POPULATION_VARIANCE);
        return this;
    }
    public ReportBuilder calcStandardDeviationFromPopulation(){
        list.add(Report.STANDARD_DEVIATION_POPULATION);
        return this;
    }
    public ReportBuilder calcStandardDeviationFromSample(){
        list.add(Report.STANDARD_DEVIATION_SAMPLE);
        return this;
    }
    public ReportBuilder calcStandardErrorFromSample(){
        list.add(Report.STANDARD_ERROR_SAMPLE);
        return this;
    }
    public ReportBuilder calcStandardErrorFromPopulation(){
        list.add(Report.STANDARD_ERROR_POPULATION);
        return this;
    }

    public ReportBuilder calcCovarianceFromSample(){
        list.add(Report.COVARIANCE_SAMPLE);
        return this;
    }
    public ReportBuilder calcCovarianceFromPopulation(){
        list.add(Report.COVARIANCE_POPULATION);
        return this;
    }
    public ReportBuilder calcCorrelationFromSample(){
        list.add(Report.CORRELATION_SAMPLE);
        return this;
    }
    public ReportBuilder calcCorrelationFromPopulation(){
        list.add(Report.COVARIANCE_SAMPLE);
        return this;
    }

    public Report[] build(){
        return list.toArray(new Report[0]);
    }
}
