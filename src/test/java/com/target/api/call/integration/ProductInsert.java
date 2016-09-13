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

public class ProductInsert {

  @Test
  public void insertApplicationData() throws IOException {

    Product product = JsonParserUtil.serializeStr2JsonModel(Product.class, FileReader.readFile("request1.json"));

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/";

    Product productAdded = RestAssured.given().accept("application/json").contentType("application/json").when()
        .body(product).post(url).as(Product.class);

    System.out.println(productAdded);

    assertNotNull(productAdded.getId());

    Boolean response = RestAssured.given().accept("application/json").contentType("application/json").when()
        .delete(url + productAdded.getId()).as(Boolean.class);

    assertTrue(response);

  }

  @Test
  public void insertApplicationWhenApplicationIsMissing() throws IOException {

    Product product = JsonParserUtil.serializeStr2JsonModel(Product.class, FileReader.readFile("request2.json"));

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/";

    ApiError productAdded = RestAssured.given().accept("application/json").contentType("application/json").when()
        .body(product).post(url).as(ApiError.class);

    System.out.println(productAdded);

    assertNotNull(productAdded.getMessage());
    assertNotNull(productAdded.getCode());
    assertEquals("RETAIL-103", productAdded.getCode());

  }

}
