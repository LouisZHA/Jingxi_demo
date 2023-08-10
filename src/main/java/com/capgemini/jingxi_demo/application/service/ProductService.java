package com.capgemini.jingxi_demo.application.service;

import com.capgemini.jingxi_demo.infrastructure.entity.ProductItem;
import com.capgemini.jingxi_demo.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 当添加商品时，输入商品的名称，描述，价格，系统应该创建商品，同时创建对应的库存数量为0。
    public void SaveProduct(ProductItem productItem){
        productRepository.save(productItem);
    }

    // 当查询商品时，输入查询文本，系统应该对商品的名称和描述进行模糊查询，并返回包含查询文本的商品。
    public List<ProductItem> GetByKeyword(String keyword){
        List<ProductItem> L1 = productRepository.findByNameContaining(keyword);
        List<ProductItem> L2 = productRepository.findByDescriptionContaining(keyword);
        return Stream.concat(L1.stream(), L2.stream()).collect(Collectors.toList());
    }

    // 当修改商品时，输入商品的名称，描述，价格，系统应该对商品信息进行修改。
    public void ModifyProduct(ProductItem productItem, int id){
        if (!productItem.getName().isEmpty()){
            productRepository.ModifyProductNameById(id, productItem.getName());
        }
        if (productItem.getDescription().isEmpty()){
            productRepository.ModifyProductDescriptionById(id,productItem.getDescription());
        }
        productRepository.ModifyProductQuantityById(id,productItem.getQuantity());
        productRepository.ModifyProductPriceById(id, productItem.getPrice());
    };




}
