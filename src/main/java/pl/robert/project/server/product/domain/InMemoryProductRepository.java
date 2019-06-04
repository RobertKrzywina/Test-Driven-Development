package pl.robert.project.server.product.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import pl.robert.project.server.product.domain.exception.ProductNotFoundException;

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
    public List<Product> findAll() {
        return new LinkedList<>(map.values());
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
