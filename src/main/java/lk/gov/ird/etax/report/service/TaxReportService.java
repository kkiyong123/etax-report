package lk.gov.ird.etax.report.service;

import lk.gov.ird.etax.report.dto.TaxReportDTO;
import lk.gov.ird.etax.report.entity.TaxReport;
import lk.gov.ird.etax.report.repository.TaxReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

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
        summary.put("taxBreakdown", Map.of(
            "PROPERTY_TAX",  Map.of("filings", 3, "paid", "9600.00", "outstanding", "4500.00"),
            "STAMP_DUTY",    Map.of("filings", 2, "paid", "4750.00", "outstanding", "450.00"),
            "LICENSE_TAX",   Map.of("filings", 2, "paid", "3600.00", "outstanding", "0.00"),
            "TRANSFER_TAX",  Map.of("filings", 1, "paid", "0.00",    "outstanding", "12000.00"),
            "REGISTRATION",  Map.of("filings", 1, "paid", "8750.00", "outstanding", "0.00")
        ));
        summary.put("monthlyPayments", Map.of(
            "Jan", "1800.00", "Feb", "0.00",   "Mar", "1000.00",
            "Apr", "0.00",    "May", "0.00",   "Jun", "3750.00",
            "Jul", "3200.00", "Aug", "0.00",   "Sep", "0.00",
            "Oct", "0.00",    "Nov", "0.00",   "Dec", "0.00"
        ));
        return summary;
    }
}
