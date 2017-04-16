package com.apress.prospring5.ch6;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

public class InsertSingerAlbum extends BatchSqlUpdate {
    private static final String SQL_INSERT_SINGER_ALBUM =
        "insert into album (singer_id, title, release_date) values (:singer_id, :title, :release_date)";

    private static final int BATCH_SIZE = 10;

    public InsertSingerAlbum(DataSource dataSource) {
        super(dataSource, SQL_INSERT_SINGER_ALBUM);

        declareParameter(new SqlParameter("singer_id", Types.INTEGER));
        declareParameter(new SqlParameter("title", Types.VARCHAR));
        declareParameter(new SqlParameter("release_date", Types.DATE));

        setBatchSize(BATCH_SIZE);
    }
}
