package com.wangt.service;

import com.wangt.dao.MeetDao;
import com.wangt.entity.Meeting;
import com.wangt.entity.Page;

import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/22
 */
public class MeetService {
    private MeetDao meetDao=new MeetDao();
    public Integer count(Meeting meeting) {
        return meetDao.count(meeting);
    }

    public List<Meeting> list(Meeting meeting, Page page) {
        return meetDao.list(meeting,page);
    }
}
