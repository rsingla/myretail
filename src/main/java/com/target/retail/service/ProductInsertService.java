package com.target.retail.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.api.call.RetrieveProductName;
import com.target.retail.exception.ApiException;
import com.target.retail.model.Product;
import com.target.retail.product.service.ProductRepository;

@Component
public class ProductInsertService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductRetrieveService productRetrieveService;

  @Autowired
  RetrieveProductName retrieveProductName;

  /**
   * This method insert the data in the mongodb. Please visit {@link Product} for the request and response data.
   * @param product
   * @return
   * @throws ApiException
   * @throws URISyntaxException
   */
  public Product insertProduct(Product product) throws ApiException, URISyntaxException {

    String productId = product.getProductId();
    Product productValue = productRepository.findById(productId);

    // Check for mongo Id
    if(productValue != null && productValue.getId() != null) {
      throw new ApiException("Product already exist", "RETAIL-101");
    }

    // Call ProductName
    String productName = retrieveProductName.retrieveProductName(productId);
    
    if(productName == null) {
      throw new ApiException("Not valid product in system: This product ID does not represent a valid product", "RETAIL-103");
    }
    // Set productName
    product.setName(productName);
    return insertProductData(product);
  }

  public Product insertProductData(Product product) {

    Product productDB = productRepository.save(product);

    return productDB;
  }

}
