package pl.robert.project.product.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import pl.robert.project.product.domain.dto.CreateProductDto
import pl.robert.project.product.domain.dto.ProductDto
import pl.robert.project.product.domain.exception.ProductNotFoundException

import spock.lang.Specification
import spock.lang.Shared

class ProductSpec extends Specification {

    @Shared
    ProductFacade facade

    def setupSpec() {
        facade = new ProductConfiguration().facade()
    }

    def 'Should create product'() {
        when: 'we create a product'
        facade.create(new CreateProductDto(1L, 'iPhoneX'))

        then: 'system has this product'
        facade.read(1L).name == 'iPhoneX'
    }

    def 'Should update products name'() {
        when: 'we update a product'
        facade.update(1L, 'Huawei')

        then: 'system has updated products name'
        facade.read(1L).name == 'Huawei'
    }

    def 'Should delete product'() {
        when: 'we delete a product'
        facade.delete(1L)

        and: 'check if system has this product'
        facade.read(1L)

        then: 'we throw an exception'
            thrown ProductNotFoundException
    }

    def 'Should list products'() {
        given: 'we add two products to system'
        facade.create(new CreateProductDto(1L, 'Huawei'))
        facade.create(new CreateProductDto(2L, 'Xiaomi'))

        when: 'we ask for all products'
        Page<ProductDto> foundProducts = facade.readAll(new PageRequest(0, 5))

        then: 'system has this products'
        foundProducts.stream().count() == 2
    }
}
