package org.springframework.data.solr.example.repository;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleField;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.example.model.Product;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;


@NoRepositoryBean
public class SolrProductRepository extends SimpleSolrRepository<Product, String> implements ProductRepository {

	@Override
	public Page<Product> findByPopularity(Integer popularity) {
		Query query = new SimpleQuery(new Criteria(SolrSearchableFields.POPULARITY).is(popularity));
		return getSolrOperations().queryForPage(query, Product.class);
	}

	@Override
	public FacetPage<Product> findByNameStartingWithAndFacetOnAvailable(String namePrefix) {
		FacetQuery query = new SimpleFacetQuery(new Criteria(SolrSearchableFields.NAME).startsWith(namePrefix));
		query.setFacetOptions(new FacetOptions(SolrSearchableFields.AVAILABLE));
		return getSolrOperations().queryForFacetPage(query, Product.class);
	}

	@Override
	public Page<Product> findByAvailableTrue() {
		Query query = new SimpleQuery(new Criteria(new SimpleField(Criteria.WILDCARD)).expression(Criteria.WILDCARD));
		query.addFilterQuery(new SimpleQuery(new Criteria(SolrSearchableFields.AVAILABLE).is(true)));

		return getSolrOperations().queryForPage(query, Product.class);
	}
	
	@Override
	public SolrDocumentList findBySpecificQuery(int value1, int value2) throws SolrServerException {
		String fields = Product.ID_FIELD + " " + Product.NAME_FIELD;
		String PRICESQUERY = "price:[%s TO %s]";
	    String specificQuery = String.format(PRICESQUERY, value1, value2);

	    SolrQuery query = new SolrQuery();
	    query.set("q", specificQuery);
		return getSolrOperations().getSolrServer().query(query).getResults();
	}
}
