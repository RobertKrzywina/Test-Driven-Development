package pl.robert.project.product.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.robert.project.product.domain.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryProductRepository {

    ConcurrentHashMap<Long, Product> map = new ConcurrentHashMap<>();

    void create(Product product) {
        map.put(product.getId(), product);
    }

    Product read(Long id) {
        Product product = map.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return map.get(id);
    }

    void update(Long id, String name) {
        if (isProductIdNotNull(id)) {
            map.put(id, Product.builder()
                    .name(name)
                    .build());
        }
    }

    void delete(Long id) {
        if (isProductIdNotNull(id)) {
            map.remove(id);
        }
    }

    Page<Product> readAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    private boolean isProductIdNotNull(Long id) {
        if (map.get(id) == null) {
            throw new ProductNotFoundException(id);
        }
        return true;
    }
}
