package ru.yesdo.graph.service;

import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import ru.yesdo.graph.data.ProductData;
import ru.yesdo.graph.repository.ProductGraphRepository;
import ru.yesdo.model.Product;

import javax.annotation.Resource;

/**
 * Created by lameroot on 28.01.15.
 */
@Service
public class ProductGraphService {

    @Resource
    private ProductGraphRepository productGraphRepository;


    @Resource
    private Neo4jTemplate neo4jTemplate;

    public Product create(ProductData productData) {
        Product product = new Product();
        product.setTitle(productData.getTitle());
        product.setAmount(productData.getAmount());
        product.setCreatedAt(productData.getCreatedAt());
        product.setLocation(productData.getLocation());
        product.setProductType(productData.getProductType());
        product.setTimeProduct(productData.getTimeProduct());

        return productGraphRepository.save(product);
    }


}
