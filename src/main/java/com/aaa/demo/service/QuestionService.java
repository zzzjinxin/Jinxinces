package com.aaa.demo.service;

import com.aaa.demo.dto.PaginationDTO;
import com.aaa.demo.dto.QuestionDTO;
import com.aaa.demo.mapper.QuestionMapper;
import com.aaa.demo.mapper.UserMapper;
import com.aaa.demo.model.Question;
import com.aaa.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 金鑫
 * @Description: TODO
 * @Author yinwe
 * @Date 2020/2/6
 * @Version V1.0
 **/
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size){
        //分页    从第几条开始展示offset
        Integer offset=size*(page-1);



        //查询出所有的问题,分页展示
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDtoList=new ArrayList<>();
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalcount = questionMapper.Count();
        paginationDTO.setPagination(totalcount,page,size);
        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        for (Question question:questions
             ) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDto=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDTO.setQuestions(questionDtoList);

        return paginationDTO;
    }
}
