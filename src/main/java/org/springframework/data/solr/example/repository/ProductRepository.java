package org.springframework.data.solr.example.repository;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.example.model.Product;


public interface ProductRepository extends CrudRepository<Product, String> {

	Page<Product> findByPopularity(Integer popularity);

	FacetPage<Product> findByNameStartingWithAndFacetOnAvailable(String namePrefix);

	Page<Product> findByAvailableTrue();

	SolrDocumentList findBySpecificQuery(int value1, int value2) throws SolrServerException;

}
