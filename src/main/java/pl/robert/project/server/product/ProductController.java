package pl.robert.project.server.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.robert.project.server.product.domain.ProductFacade;
import pl.robert.project.server.product.domain.dto.CreateProductDto;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    ProductFacade facade;

    @PostMapping
    public HttpEntity<?> create(@RequestBody CreateProductDto dto) {
        facade.create(dto);

        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("{id}")
    public HttpEntity<?> read(@PathVariable Long id) {
        return ResponseEntity.ok(facade.read(id));
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody CreateProductDto dto) {
        facade.update(dto.getId(), dto.getName());

        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        facade.delete(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
