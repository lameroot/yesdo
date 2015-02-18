package ru.yesdo.service;

import org.springframework.stereotype.Service;
import ru.yesdo.graph.service.ProductGraphService;
import ru.yesdo.model.Product;
import ru.yesdo.model.data.ProductData;
import ru.yesdo.repository.ProductRepository;

import javax.annotation.Resource;

/**
 * Created by lameroot on 18.02.15.
 */
@Service
public class ProductService {

    @Resource
    private ProductRepository productRepository;
    @Resource
    private ProductGraphService productGraphService;

    public Product create(ProductData productData) {
        Product product = new Product();

        return product;

    }
}
