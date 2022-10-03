package org.meglab.db;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.meglab.api.Sequence;
import org.meglab.db.mapper.SequenceMapper;

import java.util.List;

@RegisterRowMapper(SequenceMapper.class)
public interface SequenceDAO {

    @SqlQuery("SELECT DISTINCT s.class, s.mechanism, s.seq_group FROM sequences s " +
            "ORDER BY s.class ASC, s.mechanism ASC, s.seq_group ASC")
    List<Sequence> getHierarchy();

    @SqlQuery("SELECT s.class, s.mechanism, s.seq_group, s.header FROM sequences s " +
            "ORDER BY s.class ASC, s.mechanism ASC, s.seq_group ASC")
    List<Sequence> getSearchData();

    @SqlQuery("SELECT DISTINCT s.class, s.mechanism, s.seq_group FROM sequences s " +
            "WHERE s.class LIKE %:term% OR s.mechanism LIKE %:term% OR s.seq_group LIKE %:term% " +
            "ORDER BY s.class ASC, s.mechanism ASC, s.seq_group ASC")
    List<Sequence> searchByClassOrMechanismOrGroup(@Bind("term") String term);

    @SqlQuery("SELECT s.class, s.header, s.mechanism, s.seq_group FROM sequences s " +
            "WHERE s.header LIKE %:term%")
    List<Sequence> searchByHeader(@Bind("term") String term);

    @SqlQuery("SELECT DISTINCT s.mechanism, s.seq_group FROM sequences s WHERE s.class = :class " +
            "ORDER BY s.mechanism ASC, s.seq_group ASC")
    List<Sequence> getMechanisms(@Bind("class") String theClass);

    @SqlQuery("SELECT DISTINCT s.seq_group FROM sequences s WHERE s.class = :class AND s.mechanism = :mechanism " +
            "ORDER BY s.seq_group ASC")
    List<Sequence> getGroupsByClassAndMechanism(@Bind("class") String theClass, @Bind("mechanism") String mechanism);

    @SqlQuery("SELECT DISTINCT * FROM sequences s WHERE s.class = :class")
    List<Sequence> getSequencesByClass(@Bind("class") String theClass);

    @SqlQuery("SELECT DISTINCT * FROM sequences s WHERE s.class = :class AND s.mechanism = :mech")
    List<Sequence> getSequencesByMech(@Bind("class") String theClass, @Bind("mech") String mechanism);

    @SqlQuery("SELECT DISTINCT * FROM sequences s WHERE " +
            "s.class = :class AND s.mechanism = :mech AND s.seq_group = :group")
    List<Sequence> getSequencesByClassAndMechanismAndGroup(@Bind("class") String theClass,
                                                           @Bind("mech") String mechanism, @Bind("group") String group);













}
