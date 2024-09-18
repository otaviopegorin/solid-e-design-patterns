package org.example.parser;

import org.example.model.BankStatement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.Statement;
import java.beans.XMLDecoder;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankStatementXMLParser implements BankStatementParser<Node, NodeList>{

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankStatement parseFrom(Node line) {
        if (line.getNodeType() != Node.ELEMENT_NODE) {
            throw new IllegalArgumentException("Xml parser error");
        }

        Element element = (Element) line;
        BankStatement statement = new BankStatement();

        statement.setDate(LocalDate.parse(getElementByTag(element,"date").getTextContent(), formatter));
        statement.setValue(Double.parseDouble(getElementByTag(element,"amount").getTextContent()));
        statement.setDescription(getElementByTag(element,"description").getTextContent());

        return statement;
    }

    @Override
    public List<BankStatement> parseFromLines(NodeList lines) {
        List<BankStatement> statements = new ArrayList<BankStatement>();
        for(int aux = 0; aux < lines.getLength(); aux++){
            statements.add(parseFrom(lines.item(aux)));
        }
        return statements;
    }

    private Node getElementByTag(Element element, String tag){
        if(Objects.isNull(element))
            throw new IllegalArgumentException("Xml parser error");

        Node node = element.getElementsByTagName(tag).item(0);

        if(Objects.isNull(node))
            throw new IllegalArgumentException("Xml parser error");

        return node;
    }
}
