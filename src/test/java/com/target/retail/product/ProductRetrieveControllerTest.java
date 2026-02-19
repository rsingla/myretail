package com.target.retail.product;

import com.target.retail.model.CurrentPrice;
import com.target.retail.model.Product;
import com.target.retail.service.ProductRetrieveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class ProductRetrieveControllerTest {

  @Container
  static MongoDBContainer mongo = new MongoDBContainer("mongo:7");

  @DynamicPropertySource
  static void mongoProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl);
  }

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductRetrieveService productRetrieveService;

  @Test
  void getProduct_returnsOk_whenProductExists() throws Exception {
    Product product = new Product();
    product.setProductId("13860428");
    product.setName("Test Product");
    product.setCurrentPrice(new CurrentPrice());
    product.getCurrentPrice().setValue(13.49);
    product.getCurrentPrice().setCurrencyCode("USD");

    when(productRetrieveService.retrieveProduct(anyString())).thenReturn(product);

    mockMvc.perform(get("/v1/product/13860428"))
        .andExpect(status().isOk());
  }
}
