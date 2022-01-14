package com.example.demo.repository;

import com.example.demo.model.Shipper;
import com.example.demo.queries.ShipperQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ShipperRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ShipperRepository.class);

    public List<Shipper> getShippers() {
        List<Shipper> shippers = jdbcTemplate.query(ShipperQueries.GET_SHIPPERS, new ShippersRowMapper());
        logger.info("Au fost returnati transportatorii {}", shippers);
        return shippers;
    }

    public Optional<Shipper> getShipperById(int id) {
        List<Shipper> shippers = jdbcTemplate.query(ShipperQueries.GET_SHIPPERS_BY_ID, new ShippersRowMapper(), id);
        Optional<Shipper> shipper = getShipperFromResults(shippers);
        logger.info("A fost returnat transportatorul {}", shipper);
        return shipper;
    }

    public Optional<Shipper> getShipperByName(String companyName) {
        List<Shipper> shippers = jdbcTemplate.query(ShipperQueries.GET_SHIPPERS_BY_NAME, new ShippersRowMapper(), companyName);
        Optional<Shipper> shipper = getShipperFromResults(shippers);
        logger.info("A fost returnat transportatorul {}", shipper);
        return shipper; }

    public Shipper addShipper(Shipper shipper) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipperQueries.ADD_SHIPPER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, shipper.getCompanyName());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        shipper.setId(generatedKeyHolder.getKey().intValue());
        logger.info("A fost adaugat transportatorul {}", shipper);
        return shipper;
    }

    public Shipper editShipper(Shipper newShipper) {
        jdbcTemplate.update(ShipperQueries.UPDATE_SHIPPER, newShipper.getCompanyName(), newShipper.getId());
        logger.info("A fost editat transportatorul {}", newShipper);
        return newShipper;
    }

    public Optional<Shipper> deleteShipper(int id){
        Optional<Shipper> shipper = getShipperById(id);
        jdbcTemplate.update(ShipperQueries.DELETE_SHIPPER, id);
        logger.info("A fost sters transportatorul {}", shipper);
        return shipper;
    }

    // utils
    private Optional<Shipper> getShipperFromResults(List<Shipper> shippers) {
        if (shippers != null && !shippers.isEmpty()) {
            return Optional.of(shippers.get(0));
        }
        return Optional.empty();
    }

    public static class ShippersRowMapper implements RowMapper<Shipper> {

        @Override
        public Shipper mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Shipper(
                    resultSet.getInt("id"),
                    resultSet.getString("companyName")
            );
        }
    }
}
