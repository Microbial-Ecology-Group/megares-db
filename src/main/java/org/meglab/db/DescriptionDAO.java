package org.meglab.db;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.meglab.api.Description;
import org.meglab.db.mapper.DescriptionMapper;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(DescriptionMapper.class)
public interface DescriptionDAO {

    @SqlQuery("SELECT DISTINCT * FROM descriptions d " +
            "WHERE d.term IN (SELECT DISTINCT s.class FROM sequences s ORDER BY s.class ASC)")
    List<Description> getClassDescriptions();

    @SqlQuery("SELECT * FROM descriptions d WHERE d.term = :term")
    Optional<Description> getDescriptionByTerm(@Bind("term") String term);

}
