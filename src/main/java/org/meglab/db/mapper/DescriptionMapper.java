package org.meglab.db.mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.meglab.api.Description;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DescriptionMapper implements RowMapper<Description> {
    @Override
    public Description map(ResultSet rs, StatementContext ctx) throws SQLException {

        Integer id;
        String term;
        String description;
        String links;

        try{
            id = rs.getInt("id");
        } catch (SQLException e){
            id = null;
        }

        try{
            term = rs.getString("term");
        } catch (SQLException e){
            term = null;
        }

        try{
            description = rs.getString("description");
        } catch (SQLException e){
            description = null;
        }

        try{
            links = rs.getString("links");
        } catch (SQLException e){
            links = null;
        }

        Description theDescription = new Description()
                .setId(id)
                .setTerm(term)
                .setDescription(description)
                .setLinks(links);

        return theDescription;
    }
}
