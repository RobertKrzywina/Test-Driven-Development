package pl.robert.project.product.domain;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.robert.project.product.domain.dto.CreateProductDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductFacade {

    InMemoryProductRepository productRepository;

    public void create(CreateProductDto dto) {
        productRepository.create(ProductFactory.create(dto));
    }

    public Product read(Long id) {
        return productRepository.read(id);
    }

    public void update(Long id, String name) {
        productRepository.update(id, name);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    public Page<Product> readAll(Pageable pageable) {
        return productRepository.readAll(pageable);
    }
}
