package com.fiphiker.catalog.repository;

import com.fiphiker.catalog.entity.Product;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {
    private static final int UNIQ_ID_INDEX = 0;
    private static final int SKU_INDEX = 1;
    private static final int NAME_TITLE_INDEX = 2;
    private static final int DESCRIPTION_INDEX = 3;
    private static final int LIST_PRICE_INDEX = 4;
    private static final int SALE_PRICE_INDEX = 5;
    private static final int CATEGORY_INDEX = 6;
    private static final int CATEGORY_TREE_INDEX = 7;
    private static final int AVERAGE_PRODUCT_RATING_INDEX = 8;
    private static final int PRODUCT_URL_INDEX = 9;
    private static final int PRODUCT_IMAGE_URLS_INDEX = 10;
    private static final int BRAND_INDEX = 11;
    private static final int TOTAL_NUMBER_REVIEWS_INDEX = 12;
    private static final int REVIEWS_INDEX = 13;


    public static final String PRODUCTS_CSV_PATH = "classpath:static/jcpenney_com-ecommerce_sample.csv";
    private List<Product> productList;

    @PostConstruct
    void initProductList(){
        productList = loadProductsFromCsv();
    }

    @Override
    public Optional<Product> findProductByUniqId(String uniqId) {
        return productList.stream().filter(product -> uniqId.equals(product.getUniqId())).findFirst();
    }

    @Override
    public List<Product> findProductsBySku(String sku) {
        return productList.stream().filter(product -> sku.equals(product.getSku())).collect(Collectors.toList());
    }

    private List<Product> loadProductsFromCsv() {
        List<Product> products = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(ResourceUtils.getFile(PRODUCTS_CSV_PATH)));) {
            String[] values = null;
            csvReader.skip(1);

            while ((values = csvReader.readNext()) != null) {
                Product product = new Product();
                product.setUniqId(values[UNIQ_ID_INDEX]);
                product.setSku(values[SKU_INDEX]);
                product.setNameTitle(values[NAME_TITLE_INDEX]);
                product.setDescription(values[DESCRIPTION_INDEX]);
                product.setListPrice(values[LIST_PRICE_INDEX]);
                product.setSalePrice(values[SALE_PRICE_INDEX]);
                product.setCategory(values[CATEGORY_INDEX]);
                product.setCategoryTree(values[CATEGORY_TREE_INDEX]);
                product.setAverageProductRating(values[AVERAGE_PRODUCT_RATING_INDEX]);
                product.setProductUrl(values[PRODUCT_URL_INDEX]);
                product.setProductImageUrls(values[PRODUCT_IMAGE_URLS_INDEX]); // Assuming image URLs are separated by semicolons
                product.setBrand(values[BRAND_INDEX]);
                product.setTotalNumberReviews(values[TOTAL_NUMBER_REVIEWS_INDEX]);
                product.setReviews(values[REVIEWS_INDEX]); // Assuming reviews are separated by semicolons
                products.add(product);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

        return products;
    }
}
