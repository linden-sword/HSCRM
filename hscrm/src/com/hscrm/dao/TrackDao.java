package com.hscrm.dao;

import com.hscrm.domain.Track;

import java.util.List;

public interface TrackDao {
    /**
     * 添加新跟踪记录
     * @param track
     * @return
     */
    int addTrack(Track track);

    /**
     * 删除跟踪记录
     * @param t_id
     * @return
     */
    int deleteTrack(int t_id);

    /**
     * 通过c_id删除跟踪记录
     * @param c_id
     * @return
     */
    int deleteTrackByCid(int c_id);

    /**
     * 修改跟踪记录
     * @param track
     * @return
     */
    int updateTrack(Track track);

    /**
     * 查询跟踪记录
     * @return
     */
    List<Track> findAllTrack(String e_id);

    /**
     * 通过编号查询跟踪表
     * @param t_id
     * @return
     */
    Track findTrackById(int t_id);
}
