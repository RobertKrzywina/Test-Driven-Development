package pl.robert.project.product.domain;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.project.product.domain.dto.CreateProductDto;
import pl.robert.project.product.domain.dto.ProductDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductFacade {

    InMemoryProductRepository productRepository;

    public void create(CreateProductDto dto) {
        productRepository.create(dto);
    }

    public ProductDto read(Long id) {
        return productRepository.read(id);
    }

    public void update(Long id, String name) {
        productRepository.update(id, name);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    public Page<ProductDto> readAll(Pageable pageable) {
        return productRepository.readAll(pageable);
    }
}
