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
	
	public Set<String> getSetOfDocNumbers(String numberFilter) {
		try {
			ArrayList<String> listNumbers = new ArrayList<>();
			DocsConnection.getInstance(type).setTrElemsByNumberFilter(numberFilter);
			Elements rows = DocsConnection.getInstance(type).getTrElems();
			Elements cols = null;
			if (rows != null) {
				for (int i = 1; i < rows.size(); i++) {
					cols = rows.get(i).select("TD");
					listNumbers.add(cols.get(0).text());
				}
			}
			Collections.sort(listNumbers);
			return new LinkedHashSet<>(listNumbers);
		} catch (Exception e) {
			LOG.error("Error getting document numbers", e);
			return Collections.emptySet();
		}
	}
}
