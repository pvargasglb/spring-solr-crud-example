package org.springframework.data.solr.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.example.model.Product;
import org.springframework.data.solr.example.model.SearchableProduct;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface DerivedSolrProductRepository extends CustomSolrRepository, SolrCrudRepository<Product, String> {

	Page<Product> findByPopularity(Integer popularity, Pageable page);

	List<Product> findByNameStartingWith(String name);

	Page<Product> findByAvailableTrue(Pageable page);

	@Query(SearchableProduct.AVAILABLE_FIELD + ":false")
	Page<Product> findByAvailableFalseUsingAnnotatedQuery(Pageable page);

}
