package lk.gov.ird.etax.report.controller;

import lk.gov.ird.etax.report.dto.TaxReportDTO;
import lk.gov.ird.etax.report.entity.TaxReport;
import lk.gov.ird.etax.report.service.TaxReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*")
public class TaxReportController {

    @Autowired
    private TaxReportService taxReportService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "etax-report",
            "version", "1.0.0"
        ));
    }

    @PostMapping("/generate")
    public ResponseEntity<TaxReport> generate(@RequestBody TaxReportDTO dto) {
        return ResponseEntity.ok(taxReportService.generateReport(dto));
    }

    @GetMapping("/reports/{tin}")
    public ResponseEntity<List<TaxReport>> getReports(@PathVariable String tin) {
        return ResponseEntity.ok(taxReportService.getReportsByTin(tin));
    }

    @GetMapping("/summary/{tin}/{year}")
    public ResponseEntity<Map<String, Object>> getSummary(
            @PathVariable String tin,
            @PathVariable Integer year) {
        return ResponseEntity.ok(taxReportService.getSummary(tin, year));
    }
}
