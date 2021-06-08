package io.dbychkowsky.globaltempstats.models;

import java.time.LocalDate;

public class TempStats {
    private String dataSource;
    private LocalDate recordedDate;
    private double meanTemp;

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    public double getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(double meanTemp) {
        this.meanTemp = meanTemp;
    }

    @Override
    public String toString() {
        return "TempStats{"
                + " dataSource='" + dataSource + '\''
                + ", recordedDate=" + recordedDate
                + ", meanTemp=" + meanTemp
                + '}';
    }
}
