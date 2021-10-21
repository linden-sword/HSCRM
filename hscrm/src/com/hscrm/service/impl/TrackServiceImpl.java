package com.hscrm.service.impl;

import com.hscrm.dao.TrackDao;
import com.hscrm.dao.impl.TrackDaoImpl;
import com.hscrm.domain.Track;
import com.hscrm.service.TrackService;

import java.util.List;

public class TrackServiceImpl implements TrackService {
    TrackDao trackDao = new TrackDaoImpl();
    @Override
    public int addTrack(Track track) {
        return trackDao.addTrack(track);
    }

    @Override
    public int deleteTrack(int t_id) {
        return trackDao.deleteTrack(t_id);
    }

    @Override
    public int updateTrack(Track track) {
        return trackDao.updateTrack(track);
    }

    @Override
    public List<Track> findAllTrack(String e_id) {
        return trackDao.findAllTrack(e_id);
    }

    @Override
    public Track findTrackById(int t_id) {
        return trackDao.findTrackById(t_id);
    }
}
