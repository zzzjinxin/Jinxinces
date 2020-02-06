package com.aaa.demo.mapper;

import com.aaa.demo.model.Question;
import com.aaa.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (TITLE,DESCRIPTION, GMT_CREATE, GMT_MODIFIED,CREATOR, COMMENT_COUNT, VIEW_COUNT, LIKE_COUNT, TAG) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    int insert(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);
    @Select("select count(1) from question")
    Integer Count();

}
