package lk.gov.ird.etax.report.service;

import lk.gov.ird.etax.report.dto.TaxReportDTO;
import lk.gov.ird.etax.report.entity.TaxReport;
import lk.gov.ird.etax.report.repository.TaxReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaxReportService {

    @Autowired
    private TaxReportRepository taxReportRepository;

    public TaxReport generateReport(TaxReportDTO dto) {
        TaxReport report = new TaxReport();
        report.setTin(dto.getTin());
        report.setReportType(dto.getReportType());
        report.setReportYear(dto.getReportYear());
        report.setReportPeriod(dto.getReportPeriod());
        report.setTotalFilings(12);
        report.setTotalPaid(new BigDecimal("26700.00"));
        report.setTotalOutstanding(new BigDecimal("16500.00"));
        return taxReportRepository.save(report);
    }

    public List<TaxReport> getReportsByTin(String tin) {
        return taxReportRepository.findByTin(tin);
    }

    public Map<String, Object> getSummary(String tin, Integer year) {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("tin", tin);
        summary.put("year", year);
        summary.put("totalFilings", 12);
        summary.put("totalPaid", new BigDecimal("26700.00"));
        summary.put("totalOutstanding", new BigDecimal("16500.00"));

        Map<String, Object> taxBreakdown = new LinkedHashMap<>();
        taxBreakdown.put("PROPERTY_TAX", createBreakdown(3, "9600.00", "4500.00"));
        taxBreakdown.put("STAMP_DUTY",   createBreakdown(2, "4750.00", "450.00"));
        taxBreakdown.put("LICENSE_TAX",  createBreakdown(2, "3600.00", "0.00"));
        taxBreakdown.put("TRANSFER_TAX", createBreakdown(1, "0.00",    "12000.00"));
        taxBreakdown.put("REGISTRATION", createBreakdown(1, "8750.00", "0.00"));
        summary.put("taxBreakdown", taxBreakdown);

        Map<String, String> monthly = new LinkedHashMap<>();
        monthly.put("Jan", "1800.00");
        monthly.put("Feb", "0.00");
        monthly.put("Mar", "1000.00");
        monthly.put("Apr", "0.00");
        monthly.put("May", "0.00");
        monthly.put("Jun", "3750.00");
        monthly.put("Jul", "3200.00");
        monthly.put("Aug", "0.00");
        monthly.put("Sep", "0.00");
        monthly.put("Oct", "0.00");
        monthly.put("Nov", "0.00");
        monthly.put("Dec", "0.00");
        summary.put("monthlyPayments", monthly);

        return summary;
    }

    private Map<String, Object> createBreakdown(int filings, String paid, String outstanding) {
        Map<String, Object> map = new HashMap<>();
        map.put("filings", filings);
        map.put("paid", paid);
        map.put("outstanding", outstanding);
        return map;
    }
}
