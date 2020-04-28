package doiframework.statistics.report;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {

    private final List<ReportCollection> list = new ArrayList<>();

    public ReportBuilder calcAverageMode(){
        list.add(ReportCollection.AVERAGE_MODE);
        return this;
    }
    public ReportBuilder calcAverageMidRange(){
        list.add(ReportCollection.AVERAGE_MID_RANGE);
        return this;
    }
    public ReportBuilder calcAverageMean(){
        list.add(ReportCollection.AVERAGE_MEAN);
        return this;
    }
    public ReportBuilder calcAverageMedian(){
        list.add(ReportCollection.AVERAGE_MEDIAN);
        return this;
    }
    public ReportBuilder calcSum(){
        list.add(ReportCollection.TOTAL_SUM);
        return this;
    }

    public ReportBuilder calcSampleVariance(){
        list.add(ReportCollection.SAMPLE_VARIANCE);
        return this;
    }
    public ReportBuilder calcPopulationVariance(){
        list.add(ReportCollection.POPULATION_VARIANCE);
        return this;
    }
    public ReportBuilder calcStandardDeviationFromPopulation(){
        list.add(ReportCollection.STANDARD_DEVIATION_POPULATION);
        return this;
    }
    public ReportBuilder calcStandardDeviationFromSample(){
        list.add(ReportCollection.STANDARD_DEVIATION_SAMPLE);
        return this;
    }
    public ReportBuilder calcStandardErrorFromSample(){
        list.add(ReportCollection.STANDARD_ERROR_SAMPLE);
        return this;
    }
    public ReportBuilder calcStandardErrorFromPopulation(){
        list.add(ReportCollection.STANDARD_ERROR_POPULATION);
        return this;
    }

    public ReportBuilder calcCovarianceFromSample(){
        list.add(ReportCollection.COVARIANCE_SAMPLE);
        return this;
    }
    public ReportBuilder calcCovarianceFromPopulation(){
        list.add(ReportCollection.COVARIANCE_POPULATION);
        return this;
    }
    public ReportBuilder calcCorrelationFromSample(){
        list.add(ReportCollection.CORRELATION_SAMPLE);
        return this;
    }
    public ReportBuilder calcCorrelationFromPopulation(){
        list.add(ReportCollection.COVARIANCE_SAMPLE);
        return this;
    }

    public ReportCollection[] build(){
        return list.toArray(new ReportCollection[0]);
    }
}
