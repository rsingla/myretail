package com.target.api.call.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.target.api.call.JsonParserUtil;
import com.target.api.test.util.EnvironmentUrl;
import com.target.api.test.util.FileReader;
import com.target.retail.exception.ApiError;
import com.target.retail.model.Product;

public class ProductInsertAll {

  public Product productAdd(String jsonName) throws IOException {

    Product product = JsonParserUtil.serializeStr2JsonModel(Product.class, FileReader.readFile(jsonName));

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/";

    Product productAdded = RestAssured.given().accept("application/json").contentType("application/json").when()
        .body(product).post(url).as(Product.class);

    return productAdded;
  }

  @Test
  public void insertProduct1Data() throws IOException {

    Product product = productAdd("request1.json");

    assertNotNull(product.getId());

  }

  @Test
  public void insertProduct3Data() throws IOException {

    Product product = productAdd("request3.json");

    assertNotNull(product.getId());

  }

  @Test
  public void insertProduct4Data() throws IOException {

    Product product = productAdd("request4.json");

    assertNotNull(product.getId());

  }

  @Test
  public void insertProduct5Data() throws IOException {

    Product product = productAdd("request5.json");

    assertNotNull(product.getId());

  }

  @Test
  public void insertProduct6Data() throws IOException {

    Product product = productAdd("request6.json");

    assertNotNull(product.getId());

  }

}
