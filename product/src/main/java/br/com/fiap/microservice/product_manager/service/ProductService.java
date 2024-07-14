package br.com.fiap.microservice.product_manager.service;

import br.com.fiap.microservice.product_manager.ResponseAddAll;
import br.com.fiap.microservice.product_manager.ResponseResult;
import br.com.fiap.microservice.product_manager.dto.AddProduct;
import br.com.fiap.microservice.product_manager.dto.UpdateProductStock;
import br.com.fiap.microservice.product_manager.exception.*;
import br.com.fiap.microservice.product_manager.model.Product;
import br.com.fiap.microservice.product_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public ResponseResult<?> addProduct(AddProduct addProduct) {
        try {
            productRepository.findByName(addProduct.getName()).orElseThrow(ProductFound::new);
            Product product = new Product(
                null,
                addProduct.getName(),
                addProduct.getDescription(),
                addProduct.getQuantityStock(),
                addProduct.getPrice()
            );
            return new ResponseResult<>(productRepository.save(product), "Added successfully!");
        } catch (ProductFound e) {
            return new ResponseResult<>(addProduct, e.getMessage());
        }
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }

    public BigDecimal getPrice(Long idProduct) {
        Product product = this.findProductById(idProduct);
        return product.getPrice();
    }

    public Product updateProduct(Product product) {
        Product productFound = productRepository.findById(product.getProductId()).orElseThrow(ProductNotFound::new);
        productFound.setName(product.getName());
        productFound.setDescription(product.getDescription());
        productFound.setQuantityStock(product.getQuantityStock());
        productFound.setPrice(product.getPrice());
        return productRepository.save(productFound);
    }

    public Product updatePriceProduct(Long productId, BigDecimal price) {
        Product product = this.findProductById(productId);
        product.setPrice(price);
        this.updateProduct(product);
        return product;
    }

    public Product hasProductInStock(Long productId, int totalRequired) {
        Product product = this.findProductById(productId);
        if (product.getQuantityStock() < totalRequired) throw new NoStockProduct(totalRequired);
        return product;
    }

    public ResponseResult<Boolean> reserveProduct(Long productId, int totalRequired) {
        try {
            Product product = this.hasProductInStock(productId, totalRequired);
            product.setQuantityStock(product.getQuantityStock() - totalRequired);
            this.updateStockProduct(product);
            return new ResponseResult<>(true, "Success reserved!");
        } catch (RuntimeException e) {
            return new ResponseResult<>(false, e.getMessage());
        }
    }

    public void updateStockProduct(Product product) {
        Product productUpdated = this.updateStockProduct(product.getProductId(), product.getQuantityStock());
        System.out.println("Stock updated: " + productUpdated);
    }

    public ResponseResult<Product> incrementStockProduct(UpdateProductStock data) {
        try {
            if (data.getAdditionalStock() < 1) {
                throw new IncrementalStockValueInvalid();
            }

            Product product = this.findProductById(data.getProductId());

            if (this.isInvalidInt(product.getQuantityStock(), data.getAdditionalStock())) {
                throw new MaximumNumberOfItemsHigherThanAllowed();
            }

            product.setQuantityStock(product.getQuantityStock() + data.getAdditionalStock());
            productRepository.save(product);
            return new ResponseResult<>(product, "Updated product successfully!");
        } catch (RuntimeException e) {
            return new ResponseResult<>(null, e.getMessage());
        }
    }

    public ResponseAddAll addAllProduct(List<AddProduct> list) {
        List<Product> addedProducts = new ArrayList<>();
        List<AddProduct> noAddedProducts = new ArrayList<>();
        list.forEach(product -> {
            ResponseResult<?> addedPpoduct = this.addProduct(product);
            if (addedPpoduct.getResult() instanceof Product) {
                addedProducts.add((Product) addedPpoduct.getResult());
            }
            else if (addedPpoduct.getResult() instanceof AddProduct) {
                noAddedProducts.add((AddProduct) addedPpoduct.getResult());
            }
        });
        return new ResponseAddAll(addedProducts, noAddedProducts);
    }

    private Product updateStockProduct(Long productId, int quantityStock) {
        Product product = this.findProductById(productId);
        product.setQuantityStock(quantityStock);
        this.updateProduct(product);
        return product;
    }

    private boolean isInvalidInt(int actualValue, int newValue) {
        try {
            int v = actualValue + newValue;
            return v < 0;
        } catch (RuntimeException e) {
            return true;
        }
    }
}
