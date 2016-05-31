package com.rakuten.examples.products.bdd.steps;

import com.rakuten.examples.products.ProductsApplication;
import com.rakuten.examples.products.util.dto.ProductDTO;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class ProductsStepDefinitions {
    private static final String PRODUCT_URL = "http://localhost:8080/product";
    private List<ProductDTO> products;
    RestTemplate restTemplate;

    @Given("^Product Web Service is running$")
    public void productWebServiceIsRunning() {
        ProductsApplication.main(new String[]{});
        restTemplate = createRestTemplate();
    }

    @And("^I post following products:$")
    public void postFollowingProducts(DataTable dataTable) throws Throwable {
        List<List<String>> productList = dataTable.asLists(String.class);
        products = productList.stream().map(p -> new ProductDTO(null,
            p.get(0),
            p.get(1),
            Long.parseLong(p.get(2)))).collect(Collectors.toList());
    }

    @When("^I send a request for all products$")
    public void sendRequestForAllProducts() throws Throwable {
        products.stream().forEach(p -> {
            HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(p, createAuthenticationHttpHeaders());
            ResponseEntity<ProductDTO> response = restTemplate.exchange(PRODUCT_URL,
                    HttpMethod.POST, requestEntity, ProductDTO.class);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        });
    }

    @Then("^I retrieve following products:$")
    public void shouldRetrieveAllProducts(DataTable dataTable) throws Throwable {
        HttpEntity<?> requestEntity = new HttpEntity<>(createAuthenticationHttpHeaders());
        ResponseEntity<ProductDTO[]> response = restTemplate.exchange(PRODUCT_URL,
                HttpMethod.GET, requestEntity, ProductDTO[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new FormHttpMessageConverter(),
                new ResourceHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }

    private HttpHeaders createAuthenticationHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String token="admin".concat(":").concat("admin");
        headers.add("Authorization", "Basic " + new String(Base64.encodeBase64(token.getBytes())));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
