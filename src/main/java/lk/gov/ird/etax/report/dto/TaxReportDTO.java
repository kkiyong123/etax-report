package lk.gov.ird.etax.report.dto;

import lombok.Data;

@Data
public class TaxReportDTO {
    private String tin;
    private String reportType;
    private Integer reportYear;
    private String reportPeriod;
}
