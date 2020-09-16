package org.springframework.data.solr.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.PartialUpdate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.example.model.Product;
import org.springframework.data.solr.example.model.SearchableProduct;

public class CustomSolrRepositoryImpl implements CustomSolrRepository {

	private SolrOperations solrTemplate;

	public CustomSolrRepositoryImpl() {
		super();
	}

	public CustomSolrRepositoryImpl(SolrOperations solrTemplate) {
		super();
		this.solrTemplate = solrTemplate;
	}

	@Override
	public Page<Product> findProductsByCustomImplementation(String value, Pageable page) {
		return solrTemplate.queryForPage(new SimpleQuery(new SimpleStringCriteria("name:" + value)).setPageRequest(page),
				Product.class);
	}

	@Override
	public void updateProductCategory(String productId, List<String> categories) {
		PartialUpdate update = new PartialUpdate(SearchableProduct.ID_FIELD, productId);
		update.setValueOfField(SearchableProduct.CATEGORY_FIELD, categories);

		solrTemplate.saveBean(update);
		solrTemplate.commit();
	}

}
