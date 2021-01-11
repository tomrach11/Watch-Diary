package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Episode;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EpisodeMapper implements RowMapper<Episode> {
    @Override
    public Episode mapRow(ResultSet rs, int i) throws SQLException {
        Episode episode = new Episode();
        episode.setEpisodeID(rs.getInt("episode_id"));
        episode.setSeasonId(rs.getInt("season_id"));
        episode.setTitle(rs.getString("title"));
        episode.setNote(rs.getString("note"));
        episode.setRating(rs.getInt("rating"));
        LocalDate viewDate = rs.getDate("viewDate").toLocalDate();
        episode.setViewDate(viewDate);
        episode.setWatched(rs.getBoolean("watched"));
        return episode;
    }
}
