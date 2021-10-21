package com.hscrm.dao.impl;

import com.hscrm.dao.TrackDao;
import com.hscrm.domain.Track;
import com.hscrm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDaoImpl implements TrackDao {
    private final DBUtil dbUtil = new DBUtil();
    @Override
    public int addTrack(Track track) {
        String sql = "insert into track values (null,?,?,?,?)";
        Object[] objs = {track.getCustomer().getC_id(),track.getEmp().getE_id(),track.getRecord(),track.getIntention()};
        int flag = dbUtil.update(sql,objs);
        dbUtil.close();
        return flag;
    }

    @Override
    public int deleteTrack(int t_id) {
        String sql = "delete from track where t_id = ?";
        Object[] objs = {t_id};
        int flag = dbUtil.update(sql,objs);
        dbUtil.close();
        return flag;
    }

    @Override
    public int deleteTrackByCid(int c_id) {
        String sql = "delete from track where c_id = ?";
        Object[] objs = {c_id};
        int flag = dbUtil.update(sql,objs);
        dbUtil.close();
        return flag;
    }

    @Override
    public int updateTrack(Track track) {
        String sql = "update track set record = ?,intention = ? where t_id = ?";
        Object[] objs = {track.getRecord(),track.getIntention(),track.getT_id()};
        int flag = dbUtil.update(sql,objs);
        dbUtil.close();
        return flag;
    }

    @Override
    public List<Track> findAllTrack(String e_id) {
        String sql = "select * from track where e_id = ?";
        Object[] objs = {e_id};
        ResultSet resultSet = dbUtil.select(sql,objs);
        List<Track> trackList = new ArrayList<>();
        try {
            while (resultSet.next()){
                Track track = new Track();
                track.setT_id(resultSet.getInt("t_id"));
                track.setCustomer(new CustomerDaoImpl().findCustomerById(resultSet.getInt("c_id")));
                track.setEmp(new EmpDaoImpl().findEmpById(resultSet.getInt("e_id")));
                track.setRecord(resultSet.getString("record"));
                track.setIntention(resultSet.getString("intention"));
                trackList.add(track);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.close();
        }
        return trackList;
    }

    @Override
    public Track findTrackById(int t_id) {
        String sql = "select * from track where t_id = ?";
        Object[] objs = {t_id};
        ResultSet resultSet = dbUtil.select(sql,objs);
        Track track = null;
        try {
            while (resultSet.next()){
                track = new Track();
                track.setT_id(t_id);
                track.setCustomer(new CustomerDaoImpl().findCustomerById(resultSet.getInt("c_id")));
                track.setEmp(new EmpDaoImpl().findEmpById(resultSet.getInt("e_id")));
                track.setRecord(resultSet.getString("record"));
                track.setIntention(resultSet.getString("intention"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbUtil.close();
        }
        return track;
    }
}
