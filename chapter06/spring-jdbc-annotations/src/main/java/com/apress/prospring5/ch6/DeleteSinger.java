package com.apress.prospring5.ch6;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteSinger extends SqlUpdate {
    private static final String SQL_DELETE_SINGERS =
        "delete from singer where id = :id";

    public DeleteSinger(DataSource dataSource) {
        super(dataSource, SQL_DELETE_SINGERS);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
