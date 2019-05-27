package pl.robert.project.product.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import pl.robert.project.product.domain.exception.ProductNotFoundException;

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryProductRepository implements ProductRepository {

    ConcurrentHashMap<Long, Product> map = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        map.put(product.getId(), product);
    }

    @Override
    public Product findById(long id) {
        return map.get(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public void delete(Product product) {
        map.remove(product.getId());
    }

    @Override
    public Product findOneOrThrow(Long id) {
        Product product = map.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return map.get(id);
    }
}
