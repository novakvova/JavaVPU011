package shop.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import shop.dto.product.ProductCreateDTO;
import shop.dto.product.ProductItemDTO;
import shop.entities.CategoryEntity;
import shop.entities.ProductEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T09:17:31+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductItemDTO ProductItemDTOByProduct(ProductEntity product) {
        if ( product == null ) {
            return null;
        }

        ProductItemDTO productItemDTO = new ProductItemDTO();

        productItemDTO.setCategory( productCategoryName( product ) );
        productItemDTO.setId( product.getId() );
        productItemDTO.setName( product.getName() );
        productItemDTO.setPrice( product.getPrice() );
        productItemDTO.setDescription( product.getDescription() );

        return productItemDTO;
    }

    @Override
    public ProductEntity ProductByProductCreateDTO(ProductCreateDTO product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( product.getName() );
        productEntity.setPrice( product.getPrice() );
        productEntity.setDescription( product.getDescription() );

        return productEntity;
    }

    private String productCategoryName(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }
        CategoryEntity category = productEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
