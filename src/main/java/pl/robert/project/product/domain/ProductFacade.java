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

    ProductRepository repository;
    ProductValidator validator;

    public void create(CreateProductDto dto) {
        validator.checkInputData(dto.getName());
        repository.save(ProductFactory.create(dto));
    }

    public ProductDto read(Long id) {
        return repository.findOneOrThrow(id).dto();
    }

    public void update(Long id, String name) {
        repository.findOneOrThrow(id).setName(name);
    }

    public void delete(Long id) {
        repository.delete(repository.findOneOrThrow(id));
    }

    public Page<ProductDto> readAll(Pageable pageable) {
        return repository.findAll(pageable).map(Product::dto);
    }
}
