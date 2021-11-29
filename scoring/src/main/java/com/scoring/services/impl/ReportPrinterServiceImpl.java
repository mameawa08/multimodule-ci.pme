package com.scoring.services.impl;

import com.scoring.exceptions.ReportPrinterException;
import com.scoring.services.IReportPrinterService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Map;

import static com.scoring.services.IReportPrinterService.TypeFile.PDF;

@Service
public class ReportPrinterServiceImpl implements IReportPrinterService {

    @Value("${app.ged.report.dossier}")
    private String gedRepositoryPath;

    @Override
    public String print(String jasperFile, String filename, TypeFile type, Map params) throws ReportPrinterException {
        try {
            JasperPrint jasperPrint;
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

            Path path = Paths.get(jasperFile).toAbsolutePath();
            InputStream is = new FileInputStream(path.toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(is);

            jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource(1));
            Calendar c = Calendar.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append(filename.toLowerCase().replaceAll(" ", "_")).append("_").
                    append(c.get(Calendar.DATE)).append("_").
                    append(c.get(Calendar.MONTH)+1).append("_").
                    append(c.get(Calendar.YEAR)).append("_").
                    append(c.get(Calendar.HOUR)).append("_").
                    append(c.get(Calendar.MINUTE)).append("_").
                    append(c.get(Calendar.SECOND))
                    .append(type == PDF ?".pdf": ".xlsx");

            path = Paths.get(gedRepositoryPath).toAbsolutePath().resolve(sb.toString());
            OutputStream fos = new FileOutputStream(path.toFile());
            switch (type) {
                case PDF:
                    JasperExportManager.exportReportToPdfStream(jasperPrint, fos);
                    break;
                default:
                    break;
            }
            return sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ReportPrinterException(e.getMessage(), e);
        }
    }
}
