package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Season;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SeasonMapper implements RowMapper<Season> {
    @Override
    public Season mapRow(ResultSet rs, int i) throws SQLException {
        Season season = new Season();
        season.setSeasonID(rs.getInt("season_id"));
        season.setTvShowId(rs.getInt("tvShow_id"));
        season.setTitle(rs.getString("title"));
        season.setNote(rs.getString("note"));
        season.setRating(rs.getInt("rating"));
        LocalDate viewDate = rs.getDate("viewDate").toLocalDate();
        season.setViewDate(viewDate);
        season.setWatched(rs.getBoolean("watched"));
        return null;
    }
}
