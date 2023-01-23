package com.portaleps.helper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.portaleps.exception.CustomException;
import com.portaleps.model.Document;
import org.apache.fop.apps.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Service
public class PrinterHelper {

    private static final Logger LOGGER = LogManager.getLogger(PrinterHelper.class);

    @Value("${data.path}")
    private String DATA_DIR;
    @Value("${output.path}")
    private String OUTPUT_DIR;
    @Value("${logs.path}")
    private String LOGS_DIR;
    @Value("classpath:templates/template.xsl")
    private Resource xsltFile;

    @Autowired
    private XmlMapper xmlMapper;

    public Resource printPdf(Document document){
        LOGGER.info("printPDF() - start");
        File xmlDataFile = createXMLDataFile(document);

        StreamSource xmlSource = new StreamSource(xmlDataFile);
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        try {
            File file = new File(OUTPUT_DIR + "Verbale-" + new Timestamp(System.currentTimeMillis()).getTime() + ".pdf");
            OutputStream out = new FileOutputStream(file);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile.getInputStream()));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
            out.close();

            Path filePath = Path.of(file.toURI());
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                LOGGER.info("printPDF() - document printed");
                LOGGER.info("printPDF() - end");
                return resource;
            } else {
                LOGGER.error("printPDF() - file not found: " + file.toURI());
                LOGGER.info("printPDF() - end");
                throw new CustomException("File not found: " + file.toURI(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error("printPDF() - document not printed");
            LOGGER.info("printPDF() - end");
            e.printStackTrace();
            throw new CustomException("document not printed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public File createXMLDataFile(Document document){
        LOGGER.info("createXMLDataFile() - start");
        try {
            String xml = xmlMapper.writeValueAsString(document);
            File xmlOutput = new File(DATA_DIR + "data" + new Timestamp(System.currentTimeMillis()).getTime() + ".xml");
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xml);
            fileWriter.close();
            LOGGER.info("createXMLDataFile() - end");
            return xmlOutput;
        } catch (Exception e) {
            LOGGER.error("createXMLDataFile() - XMLData not created");
            LOGGER.info("createXMLDataFile() - end");
            e.printStackTrace();
            throw new CustomException("XMLData not created", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}