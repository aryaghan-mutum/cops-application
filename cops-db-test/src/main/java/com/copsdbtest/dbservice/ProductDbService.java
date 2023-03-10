package com.copsdbtest.dbservice;

import com.copsdbtest.constants.DbConstants;
import com.copsdbtest.constants.QueryConstants;
import com.copsdbtest.data.ProductData;
import com.copsdbtest.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class ProductDbService {

    @Description("get total products count")
    public long getAllOrdersCount() {
        return getAllProducts().stream().count();
    }

    @Description("get all the products rows")
    public List<ProductDto> getAllProducts() {
        log.info("ProductDb::getAllProducts");
        List<ProductDto> productDbList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.SELECT_ALL_PRODUCTS)) {
            log.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int supplierId = resultSet.getInt("supplier_id");
                int categoryId = resultSet.getInt("category_id");
                String unit = resultSet.getString("unit");
                double price = resultSet.getDouble("price");
                ProductDto productDto = new ProductDto(productId, productName, supplierId, categoryId, unit, price);
                productDbList.add(productDto);
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return productDbList;
    }

    @Description("get product by product_id")
    public List<ProductDto> getProductByProductId(int productIdPrimaryKey) {
        log.info("ProductDb::getProductByProductId");
        List<ProductDto> productDbList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(String.format("select * from products where product_id = %s", productIdPrimaryKey))) {
            log.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int supplierId = resultSet.getInt("supplier_id");
                int categoryId = resultSet.getInt("category_id");
                String unit = resultSet.getString("unit");
                double price = resultSet.getDouble("price");
                ProductDto productDto = new ProductDto(productId, productName, supplierId, categoryId, unit, price);
                productDbList.add(productDto);
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return productDbList;
    }

    @Description("insert a product to db")
    public List<ProductDto> insertProduct(ProductDto productDto) {
        log.info("ProductDb::insertProduct");
        log.info(productDto.toString());
        List<ProductDto> productDtoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.INSERT_PRODUCT)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, productDto.getProductId());
            preparedStatement.setString(2, productDto.getProductName());
            preparedStatement.setInt(3, productDto.getSupplierId());
            preparedStatement.setInt(4, productDto.getCategoryId());
            preparedStatement.setString(5, productDto.getUnit());
            preparedStatement.setDouble(6, productDto.getPrice());
            productDtoList.add(productDto);
            log.info(String.format("Total Rows Inserted: %s for product_id: %s", preparedStatement.executeUpdate(), productDto.getProductId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return productDtoList;
    }

    @Description("insert multiple products to db")
    public List<ProductDto> insertMultipleProducts(int productsCount) {
        log.info("ProductDb::insertMultipleProducts");
        List<ProductDto> productDbList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        IntStream.rangeClosed(1, productsCount).forEach(product -> {
            ProductData productData = new ProductData();
            ProductDto productDto = productData.createProductData();
            insertProduct(productDto);
            productDbList.add(productDto);
            count.getAndIncrement();
        });
        log.info("---------------------------------------------------");
        log.info(String.format("Total Rows Inserted: %s in Products Table", count.get()));
        log.info("---------------------------------------------------");
        return productDbList;
    }

    @Description("delete a product in db")
    public void deleteProduct(ProductDto productDto) {
        log.info("ProductDb::deleteProduct");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_PRODUCT)) {
            log.info(preparedStatement.toString());
            preparedStatement.setInt(1, productDto.getProductId());
            log.info(String.format("Total Rows Deleted: %s for product_id: %s", preparedStatement.executeUpdate(), productDto.getProductId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }

    @Description("delete a product in db")
    public void deleteProduct(List<ProductDto> productDtoList) {
        log.info("ProductDb::deleteProduct");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_PRODUCT)) {
            log.info(preparedStatement.toString());

            for (ProductDto productDto : productDtoList) {
                preparedStatement.setInt(1, productDto.getProductId());
                log.info(String.format("Total Rows Deleted: %s for product_id: %s", preparedStatement.executeUpdate(), productDto.getProductId()));
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }

    @Description("check if a product is present in db")
    public boolean isProductPresent(List<ProductDto> productDtoList) {
        log.info("ProductDb::isProductPresent");
        boolean isProductIdPresent = false;
        for (ProductDto productDto : productDtoList) {
            try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(String.format("select * from products where product_id = %s", productDto.getProductId()))) {
                log.info(preparedStatement.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                isProductIdPresent = resultSet.next();
            } catch (SQLException e) {
                ConfigDbService.printSQLException(e);
            }
        }
        return isProductIdPresent;
    }

    @Description("delete all products in db")
    public void deleteAllProducts() {
        log.info("ProductDb::deleteAllProducts");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_ALL_PRODUCTS)) {
            log.info(preparedStatement.toString());
            log.info(String.format("Total Rows Deleted: %s in Products Table", preparedStatement.executeUpdate()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }
}
