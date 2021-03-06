package pl.robert.project.server.product.domain

import spock.lang.Shared
import spock.lang.Unroll
import spock.lang.Specification

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import pl.robert.project.server.product.domain.dto.CreateProductDto
import pl.robert.project.server.product.domain.exception.InvalidProductException
import pl.robert.project.server.product.domain.exception.ProductNotFoundException

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
        when: 'we add two products to system'
        facade.create(new CreateProductDto(1L, 'Huawei'))
        facade.create(new CreateProductDto(2L, 'Xiaomi'))

        then: 'system has this products'
        facade.readAll().size() == 2
    }

    @Unroll
    def 'Should throw an exception cause given product name is invalid = Product[ name = #name ]'(String name)  {
        when: 'we try to save product'
        facade.create(new CreateProductDto(1L, name))

        then: 'exception is thrown'
        thrown InvalidProductException

        where:
        name                                        |_
        null                                        |_
        1212                                        |_
        '1212'                                      |_
        ''                                          |_
        '  '                                        |_
        'thisNameOfProductIsUnfortunatelyTooLong'   |_
        '!@#$%^&*()'                                |_
        'name!'                                     |_
    }

    def 'Should throw an exception cause given product name must be unique'() {
        when: 'we save a product'
        facade.create(new CreateProductDto(1L, 'Watermelon'))

        and: 'we save a product again with the same name'
        facade.create(new CreateProductDto(2L, 'Watermelon'))

        then: 'exception is thrown'
        InvalidProductException exception = thrown()
        exception.message == InvalidProductException.CAUSE.UNIQUE.message
    }
}
