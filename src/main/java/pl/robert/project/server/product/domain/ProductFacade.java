package pl.robert.project.server.product.domain;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;

import pl.robert.project.server.product.domain.dto.ProductDto;
import pl.robert.project.server.product.domain.dto.CreateProductDto;

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
        validator.checkInputData(name);
        repository.findOneOrThrow(id).setName(name);
    }

    public void delete(Long id) {
        repository.delete(repository.findOneOrThrow(id));
    }

    public List<ProductDto> readAll() {
        return repository.findAll()
                .stream()
                .map(Product::dto)
                .collect(Collectors.toList());
    }
}
