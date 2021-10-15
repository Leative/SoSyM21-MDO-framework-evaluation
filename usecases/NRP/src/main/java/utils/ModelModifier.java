package utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ModelModifier {

	private final static String MODEL_DIR = "src/test/resources";

	public static void main(String[] args) throws IOException {
		Files.walkFileTree(Paths.get(MODEL_DIR), new SimpleFileVisitor<Path>() {	
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if (file.toString().contains(".xmi")) {
					try {
						showSolutionsWithSelectedAttributes(file);
					} catch (XPathExpressionException e) {
						throw new IOException(e);
					}
				}					
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private static void showSolutionsWithSelectedAttributes(Path file) throws XPathExpressionException {
		Document doc = readDocument(file);
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expr = "//solutions[@selectedArtifacts]";
		NodeList nodes = (NodeList) xpath.compile(expr).evaluate(doc, XPathConstants.NODESET);
		if (nodes.getLength() > 0) {
			System.out.println(file + " has selected attributes.");
		}
	}

	private static Document readDocument(Path file) {
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		Document input = null;
		try {
			fac.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			fac.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			input = fac.newDocumentBuilder().parse(file.toFile());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;		
	}	
}
