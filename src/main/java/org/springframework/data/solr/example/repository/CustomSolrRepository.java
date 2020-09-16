package org.springframework.data.solr.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.example.model.Product;


public interface CustomSolrRepository {

	Page<Product> findProductsByCustomImplementation(String value, Pageable page);

	void updateProductCategory(String productId, List<String> categories);

}
