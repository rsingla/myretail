package com.target.retail.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.api.call.RetrieveProductName;
import com.target.retail.exception.ApiException;
import com.target.retail.model.Product;
import com.target.retail.product.service.ProductRepository;

@Component
public class ProductUpdateService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductRetrieveService productRetrieveService;

  @Autowired
  RetrieveProductName retrieveProductName;

  public Product updateProductPrice(Product product) throws URISyntaxException, ApiException {

    String productId = product.getProductId();
    Product productValue = productRepository.findById(productId);
    Product productDB = null;
    if(productValue == null) {
      productDB = productRepository.save(product);
    }
    else if(productValue != null) {
      productValue.setCurrentPrice(product.getCurrentPrice());
      productDB = productRepository.save(productValue);
    }

    return productDB;
  }

}
