package com.copsdbtest.dbservice;

import com.copsdbtest.constants.DbConstants;
import com.copsdbtest.constants.QueryConstants;
import com.copsdbtest.data.SupplierData;
import com.copsdbtest.dto.SupplierDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class SupplierDbService {

    @Description("get total suppliers count")
    public long getAllSuppliersCount() {
        log.info("SupplierDb::getAllSuppliersCount");
        return getAllSuppliers().stream().count();
    }

    @Description("get all the supplier rows")
    public List<SupplierDto> getAllSuppliers() {
        log.info("SupplierDb::getAllSuppliers");
        List<SupplierDto> supplierDtoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.SELECT_ALL_SUPPLIERS)) {
            log.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int supplierId = resultSet.getInt("supplier_id");
                String supplierName = resultSet.getString("supplier_name");
                String contactName = resultSet.getString("contact_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postalCode = resultSet.getString("postal_code");
                String country = resultSet.getString("country");
                SupplierDto supplierDto = new SupplierDto(supplierId, supplierName, contactName, address, city, postalCode, country);
                supplierDtoList.add(supplierDto);
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return supplierDtoList;
    }

    @Description("get supplier by supplier_id")
    public List<SupplierDto> getSupplierBySupplierId(int supplierIdPrimaryKey) {
        log.info("SupplierDb::getSupplierBySupplierId");
        List<SupplierDto> supplierDbList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(String.format("select * from suppliers where supplier_id = %s", supplierIdPrimaryKey))) {
            log.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int supplierId = resultSet.getInt("supplier_id");
                String supplierName = resultSet.getString("supplier_name");
                String contactName = resultSet.getString("contact_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postalCode = resultSet.getString("postal_code");
                String country = resultSet.getString("country");
                SupplierDto supplierDto = new SupplierDto(supplierId, supplierName, contactName, address, city, postalCode, country);
                supplierDbList.add(supplierDto);
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return supplierDbList;
    }

    @Description("insert a supplier to db")
    public List<SupplierDto> insertSupplier(SupplierDto supplierDto) {
        log.info("SupplierDb::insertSupplier");
        log.info(supplierDto.toString());
        List<SupplierDto> supplierDtoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.INSERT_SUPPLIER)) {
            log.info(preparedStatement.toString());
            preparedStatement.setInt(1, supplierDto.getSupplierId());
            preparedStatement.setString(2, supplierDto.getSupplierName());
            preparedStatement.setString(3, supplierDto.getContactName());
            preparedStatement.setString(4, supplierDto.getAddress());
            preparedStatement.setString(5, supplierDto.getCity());
            preparedStatement.setString(6, supplierDto.getPostalCode());
            preparedStatement.setString(7, supplierDto.getCountry());
            supplierDtoList.add(supplierDto);
            log.info(String.format("Total Rows Inserted: %s for supplier_id: %s", preparedStatement.executeUpdate(), supplierDto.getSupplierId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return supplierDtoList;
    }

    @Description("insert multiple suppliers to db")
    public List<SupplierDto> insertMultipleSuppliers(int suppliersCount) {
        log.info("SupplierDb::insertMultipleSuppliers");
        List<SupplierDto> supplierDbList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        IntStream.rangeClosed(1, suppliersCount).forEach(supplier -> {
            SupplierData supplierData = new SupplierData();
            SupplierDto supplierDto = supplierData.createSupplierData();
            insertSupplier(supplierDto);
            supplierDbList.add(supplierDto);
            count.getAndIncrement();
        });
        log.info("---------------------------------------------------");
        log.info(String.format("Total Rows Inserted: %s in Suppliers Table", count.get()));
        log.info("---------------------------------------------------");
        return supplierDbList;
    }

    @Description("delete a supplier in db")
    public void deleteSupplier(SupplierDto supplierDto) {
        log.info("SupplierDb::deleteSupplier");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_SUPPLIER)) {
            log.info(preparedStatement.toString());
            preparedStatement.setInt(1, supplierDto.getSupplierId());
            log.info(String.format("Total Rows Deleted: %s for supplier_id: %s", preparedStatement.executeUpdate(), supplierDto.getSupplierId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }

    @Description("delete a supplier in db")
    public void deleteSupplier(List<SupplierDto> supplierDtoList) {
        log.info("SupplierDb::deleteSupplier");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_SUPPLIER)) {
            log.info(preparedStatement.toString());

            for (SupplierDto supplierDto : supplierDtoList) {
                preparedStatement.setInt(1, supplierDto.getSupplierId());
                log.info(String.format("Total Rows Deleted: %s for supplier_id: %s", preparedStatement.executeUpdate(), supplierDto.getSupplierId()));
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }

    @Description("check if a supplier is present in db")
    public boolean isSupplierPresent(List<SupplierDto> supplierDtoList) {
        log.info("SupplierDb::isSupplierPresent");
        boolean isSupplierIdPresent = false;
        for (SupplierDto supplierDto : supplierDtoList) {
            try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(String.format("select * from suppliers where supplier_id = %s", supplierDto.getSupplierId()))) {
                log.info(preparedStatement.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                isSupplierIdPresent = resultSet.next();
            } catch (SQLException e) {
                ConfigDbService.printSQLException(e);
            }
        }
        return isSupplierIdPresent;
    }

    @Description("delete all suppliers in db")
    public void deleteAllSuppliers() {
        log.info("ProductDb::deleteAllSuppliers");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_ALL_SUPPLIERS)) {
            log.info(preparedStatement.toString());
            log.info(String.format("Total Rows Deleted: %s in Suppliers Table", preparedStatement.executeUpdate()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }
}
