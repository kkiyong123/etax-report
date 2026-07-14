package lk.gov.ird.etax.report.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tax_reports")
@Data
public class TaxReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tin;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "report_year")
    private Integer reportYear;

    @Column(name = "report_period")
    private String reportPeriod;

    @Column(name = "total_filings")
    private Integer totalFilings;

    @Column(name = "total_paid")
    private BigDecimal totalPaid;

    @Column(name = "total_outstanding")
    private BigDecimal totalOutstanding;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }
}
