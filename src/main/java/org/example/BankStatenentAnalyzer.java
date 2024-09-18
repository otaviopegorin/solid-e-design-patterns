package org.example;

import org.example.filters.BankStatementFilterByMonth;
import org.example.model.BankStatement;
import org.example.parser.BankStatementCSVParser;
import org.example.parser.BankStatementParser;
import org.example.parser.BankStatementXMLParser;
import org.example.services.BankStatementProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;

public class BankStatenentAnalyzer {

    private static final String WORKDIR = "C:\\Users\\otavi\\OneDrive\\Área de Trabalho";
    private static final String fileName = "bankStatement.txt";

//    public static void main(String[] args) throws IOException {
//        BankStatementCSVParser csvParser = new BankStatementCSVParser();
//        List<String> lines = Files.readAllLines(Path.of(WORKDIR.concat(File.separator).concat(fileName)));
//        List<BankStatement> bankStatements = csvParser.parseFromLines(lines);
//        BankStatementProcessor processor = new BankStatementProcessor(bankStatements);
//
//        BankStatement statement = processor.filter(new BankStatementFilterByMonth(Month.DECEMBER)).stream().findFirst().get();
//    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File(WORKDIR.concat(File.separator).concat(fileName)));

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("bankStatement");

        BankStatementParser xmlParser = new BankStatementXMLParser();
        List<BankStatement> bankStatements = xmlParser.parseFromLines(nodeList);

        summarize(bankStatements);
    }

    public static void summarize(List<BankStatement> list){
        list.forEach(n-> System.out.println("Date: "+n.getDate()+", Amount: "+n.getValue()+", Description: "+n.getDescription()));
    }
}