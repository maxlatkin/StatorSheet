package ru.wnc.documents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocNumbers {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocNumbers.class);
	private DocumentTypes type;

	public DocNumbers(DocumentTypes type) {
		this.type = type;
	}
	
	public Set<String> getSetOfDocNumbers() {
		try {
			ArrayList<String> listNumbers = new ArrayList<>();
			DocsConnection docsConnection = new DocsConnection(type);
			Elements rows = docsConnection.getTrElements();
			Elements cols = null;
			if (rows != null) {
				for (int i = 1; i < rows.size(); i++) {
					cols = rows.get(i).select("TD");
					listNumbers.add(cols.get(0).text());
				}
			}
			return new LinkedHashSet<>(listNumbers);
		} catch (Exception e) {
			LOG.error("Error getting document numbers", e);
			return Collections.emptySet();
		}
	}
}
