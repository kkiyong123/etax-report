package lk.gov.ird.etax.report.repository;

import lk.gov.ird.etax.report.entity.TaxReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaxReportRepository extends JpaRepository<TaxReport, Long> {
    List<TaxReport> findByTin(String tin);
    List<TaxReport> findByTinAndReportYear(String tin, Integer year);
    List<TaxReport> findByReportType(String reportType);
}
