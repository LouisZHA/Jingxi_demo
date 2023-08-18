package com.capgemini.jingxi_demo.application.products;

import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.application.products.dto.ProductDTO;
import com.capgemini.jingxi_demo.application.products.dto.mapping.ProductMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapping productMapping;

    // 当添加商品时，输入商品的名称，描述，价格，系统应该创建商品，同时创建对应的库存数量为0。
    // 先查询商品名称是否存在在商品表里
    public void saveProduct(ProductAddDTO productAddDTO){

        Optional<ProductEntity> p = productRepository.findByName(productAddDTO.getName());
        if (p.isEmpty()){
            ProductEntity productEntity = productMapping.toProductEntity(productAddDTO);
            productRepository.save(productEntity);
        }else{
            System.out.println("商品名称已存在");
        }
    }

    // 当查询商品时，输入查询文本，系统应该对商品的名称和描述进行模糊查询，并返回包含查询文本的商品。
    public List<ProductEntity> getByKeyword(String keyword){
        List<ProductEntity> L1 = productRepository.findByNameContaining(keyword);
        List<ProductEntity> L2 = productRepository.findByDescriptionContaining(keyword);
        return Stream.concat(L1.stream(), L2.stream()).collect(Collectors.toList());
    }

    // 当修改商品时，输入商品的名称，描述，价格，系统应该对商品信息进行修改。 先判断商品id是否存在
    public void ModifyProduct(ProductDTO productDTO){
        Long id = productDTO.getId();
        Optional<ProductEntity> p = productRepository.findById(id);
        Optional<ProductEntity> p1 = productRepository.findByName(productDTO.getName());
        if (p1.isEmpty()){
            if (p.isPresent()){
                ProductEntity productEntity = productMapping.toProductEntityFromDto(productDTO);
                productRepository.ModifyProductPriceById(id, productEntity.getPrice());
                productRepository.ModifyProductNameById(id, productEntity.getName());
                productRepository.ModifyProductDescriptionById(id, productEntity.getDescription());
                productRepository.ModifyProductImageById(id, productEntity.getImage());
            }else{
                System.out.println("商品id不存在");
            }
        }else{
            System.out.println("商品名称已存在");
        }

    }

    // 根据 ID查询商品数据
    public Optional<ProductEntity> findById(long id){ return productRepository.findById(id);}

    // 根据 name查询商品数据
    public Optional<ProductEntity> findByName(String name){ return productRepository.findByName(name);}

    //  根据 ID删除商品数据
    public void deleteById(long id){ productRepository.deleteById(id);}

    // 修改商品库存
    public void ModifyQuantity_locked(long id, Integer Quantity_locked){
        Optional<ProductEntity> p = productRepository.findById(id);
        if (p.isPresent()){
            productRepository.ModifyProductQuantity_lockedById(id, Quantity_locked);
        }else{
            System.out.println("商品id不存在");
        }
    }

    //修改锁定商品库存
    public void ModifyQuantity(long id, Integer Quantity){
        Optional<ProductEntity> p = productRepository.findById(id);
        if (p.isPresent()){
            productRepository.ModifyProductQuantityById(id, Quantity);
        }else{
            System.out.println("商品id不存在");
        }
    }

}
