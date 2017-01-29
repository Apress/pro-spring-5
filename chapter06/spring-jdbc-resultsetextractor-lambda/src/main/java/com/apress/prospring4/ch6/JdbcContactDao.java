package com.apress.prospring4.ch6;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcContactDao implements ContactDao, InitializingBean {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String findLastNameById(Long id) {
        String sql = "select last_name from contact where id = :contactId";

        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("contactId", id);
    
        return namedParameterJdbcTemplate.queryForObject(sql, 
            namedParameters, String.class); 
    }

    @Override
    public List<Contact> findAllWithDetail() {
        String sql = "select c.id, c.first_name, c.last_name, c.birth_date" + 
                     ", t.id as contact_tel_id, t.tel_type, t.tel_number from contact c " + 
                     "left join contact_tel_detail t on c.id = t.contact_id";

        return namedParameterJdbcTemplate.query(sql, (ResultSet rs) -> {
            Map<Long, Contact> map = new HashMap<Long, Contact>();
            Contact contact = null;

            while (rs.next()) {
                Long id = rs.getLong("id");
                contact = map.get(id);

                if (contact == null) {
                    contact = new Contact();
                    contact.setId(id);
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setBirthDate(rs.getDate("birth_date"));
                    contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
                    map.put(id, contact);
                }

                Long contactTelDetailId = rs.getLong("contact_tel_id");

                if (contactTelDetailId > 0) {
                    ContactTelDetail contactTelDetail = new ContactTelDetail();
                    contactTelDetail.setId(contactTelDetailId);
                    contactTelDetail.setContactId(id);
                    contactTelDetail.setTelType(rs.getString("tel_type"));
                    contactTelDetail.setTelNumber(rs.getString("tel_number"));
                    contact.getContactTelDetails().add(contactTelDetail);
                }
            }

            return new ArrayList<Contact> (map.values());
        });
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = 
            new NamedParameterJdbcTemplate(dataSource);

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on ContactDao");
        }

        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("Null NamedParameterJdbcTemplate on ContactDao");
        }
    }

}
