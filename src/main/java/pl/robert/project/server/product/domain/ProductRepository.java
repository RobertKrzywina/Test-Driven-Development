package pl.robert.project.server.product.domain;

import java.util.List;

import org.springframework.data.repository.Repository;

import pl.robert.project.server.product.domain.exception.ProductNotFoundException;

interface ProductRepository extends Repository<Product, Long> {

    void save(Product product);

    Product findById(long id);

    List<Product> findAll();

    void delete(Product product);

    default Product findOneOrThrow(Long id) {
        Product product = findById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }
}
