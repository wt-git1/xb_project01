package com.wangt.dao;

import com.wangt.entity.Meeting;
import com.wangt.entity.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/22 17:57
 */
public class MeetDao extends BaseDao{
    public Integer count(Meeting meeting) {
        String sql ="select count(*) from meeting where title like ? ";
        if(meeting.getStatus()==null){
            return template.queryForObject(sql,Integer.class,"%"+meeting.getTitle()+"%");
        }
        sql +="and status = ?";
        return template.queryForObject(sql,Integer.class,"%"+meeting.getTitle()+"%",meeting.getStatus());
    }

    public List<Meeting> list(Meeting meeting, Page page) {
        String sql="SELECT " +
                "m.id id, " +
                "m.dept_id deptID, " +
                "d.name deptName, " +
                "m.title title, " +
                "m.content content, " +
                "m.publish_date publishDate, " +
                "m.start_time startTime, " +
                "m.end_time endTime, " +
                "m.status status, " +
                "m.make_user makeUser " +
                "FROM " +
                "meeting m left join dept d on m.dept_id = d.id " +
                "WHERE " +
                "m.title LIKE ?  ";
        if(meeting.getStatus()==null){
            sql+="LIMIT ?,?";
            return template.query(sql,new BeanPropertyRowMapper<>(Meeting.class),"%"+meeting.getTitle()+"%",page.getFirstResult(),page.getPageSize());
        }
        sql+="and m.status = ? LIMIT ?,?";
        return template.query(sql,new BeanPropertyRowMapper<>(Meeting.class),"%"+meeting.getTitle()+"%",meeting.getStatus(),page.getFirstResult(),page.getPageSize());
    }
}
