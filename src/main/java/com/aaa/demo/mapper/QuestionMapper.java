package com.aaa.demo.mapper;

import com.aaa.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (TITLE,DESCRIPTION, GMT_CREATE, GMT_MODIFIED,CREATOR, COMMENT_COUNT, VIEW_COUNT, LIKE_COUNT, TAG) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    int insert(Question question);

}
