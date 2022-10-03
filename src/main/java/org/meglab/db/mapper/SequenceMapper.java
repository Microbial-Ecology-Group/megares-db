package org.meglab.db.mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.meglab.api.Sequence;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceMapper implements RowMapper<Sequence> {
    @Override
    public Sequence map(ResultSet rs, StatementContext ctx) throws SQLException {

        Integer id;
        String header;
        String fasta;
        String type;
        String theClass;
        String mechanism;
        String group;

        try{
            id = rs.getInt("id");
        } catch (SQLException e){
            id = null;
        }

        try{
            header = rs.getString("header");
        } catch (SQLException e){
            header = null;
        }

        try{
            fasta = rs.getString("fasta");
        } catch (SQLException e){
            fasta = null;
        }

        try{
            type = rs.getString("type");
        } catch (SQLException e){
            type = null;
        }

        try{
            theClass = rs.getString("class");
        } catch (SQLException e){
            theClass = null;
        }

        try{
            mechanism = rs.getString("mechanism");
        } catch (SQLException e){
            mechanism = null;
        }

        try{
            group = rs.getString("seq_group");
        } catch (SQLException e){
            group = null;
        }

        Sequence sequence = new Sequence()
                .setId(id)
                .setHeader(header)
                .setFasta(fasta)
                .setType(type)
                .setTheclass(theClass)
                .setMechanism(mechanism)
                .setGroup(group);

        return sequence;

    }
}
