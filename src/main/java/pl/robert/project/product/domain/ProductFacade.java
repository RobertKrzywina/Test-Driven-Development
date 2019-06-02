package pl.robert.project.product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.project.product.domain.dto.ProductDto;
import pl.robert.project.product.domain.dto.CreateProductDto;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ProductFacade {

    ProductRepository productRepository;
    ProductValidator validator;

    public void create(CreateProductDto dto) {
        validator.checkInputData(dto.getName());
        productRepository.save(ProductFactory.create(dto));
    }

    public ProductDto read(Long id) {
        return productRepository.findOneOrThrow(id).dto();
    }

    public void update(Long id, String name) {
        productRepository.findOneOrThrow(id).setName(name);
    }

    public void delete(Long id) {
        productRepository.delete(productRepository.findOneOrThrow(id));
    }

    public Page<ProductDto> readAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(Product::dto);
    }
}
