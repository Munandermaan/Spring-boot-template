package com.demo.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * It is the class to define service and implementation.
 */
@RestController
public final class ServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);
    private static Map<String, Item> productRepo = new HashMap<>();

    static {
        Item honey = new Item();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Item almond = new Item();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    /**
     * Define simple end point.
     *
     * @return Welcome message.
     */
    @RequestMapping("/")
    public String hello() {
        return "Hello Spring Boot";
    }

    /**
     * Function to delete product.
     *
     * @param id id of item.
     * @return http response.
     */
    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        LOGGER.info("Item deleted for id" + id);
        productRepo.remove(id);
        return new ResponseEntity<>("Item is deleted successsfully", HttpStatus.OK);
    }

    /**
     * Function to update product.
     *
     * @param id id of item.
     * @return http response.
     */
    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Item item) {

        if (!productRepo.containsKey(id)) {
            throw new ItemNotFoundException();
        } else {
            LOGGER.info("Updating item for id" + id);
            productRepo.remove(id);
            item.setId(id);
            productRepo.put(id, item);
            return new ResponseEntity<>("Items is updated successsfully", HttpStatus.OK);
        }
    }

    /**
     * Function to add item.
     *
     * @param item request.
     * @return http response.
     */
    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Item item) {
        LOGGER.info("Creating product for request " + item);
        productRepo.put(item.getId(), item);

        return new ResponseEntity<>("Items is created successfully", HttpStatus.CREATED);
    }

    /**
     * To get products
     *
     * @return http response.
     */
    @RequestMapping(value = "/item")
    public ResponseEntity<Object> getProduct() {
        LOGGER.info("Getting the products");
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

}
