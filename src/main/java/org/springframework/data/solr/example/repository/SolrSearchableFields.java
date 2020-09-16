package org.springframework.data.solr.example.repository;

import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.example.model.SearchableProduct;


public enum SolrSearchableFields implements Field {

	ID(SearchableProduct.ID_FIELD), NAME(SearchableProduct.NAME_FIELD), PRICE(SearchableProduct.PRICE_FIELD), AVAILABLE(
			SearchableProduct.AVAILABLE_FIELD), CATEGORY(SearchableProduct.CATEGORY_FIELD), WEIGHT(
			SearchableProduct.WEIGHT_FIELD), POPULARITY(SearchableProduct.POPULARITY_FIELD);

	private final String fieldName;

	private SolrSearchableFields(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String getName() {
		return fieldName;
	}

}
