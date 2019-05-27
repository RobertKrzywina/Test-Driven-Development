package pl.robert.project.product.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import pl.robert.project.product.domain.exception.ProductNotFoundException;

interface ProductRepository extends Repository<Product, Long> {

    void save(Product product);

    Product findById(long id);

    Page<Product> findAll(Pageable pageable);

    void delete(Product product);

    default Product findOneOrThrow(Long id) {
        Product product = findById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }
}
