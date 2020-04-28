package com.info.sms.servie;

import com.info.sms.dto.PaginationDTO;
import com.info.sms.dto.QuestionDTO;
import com.info.sms.mapper.QuestionMapper;
import com.info.sms.mapper.UserMapper;
import com.info.sms.model.Question;
import com.info.sms.model.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke 2020/4/13 17:26
 */
@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        Integer totalPage;

        // 如果总数据数  取余 每页显示数量为0，页数为数据总数除以每页数量的值
        // 如果总数据数  取余 每页显示数量不为0，页数为数据总数除以每页数量的值 + 1
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        // 容错校验，当URL修改page不在有效区间内，赋予对应逻辑默认值
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for(Question question:questions){
            User user = userMapper.findById(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(int userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        Integer totalPage;

        // 如果总数据数  取余 每页显示数量为0，页数为数据总数除以每页数量的值
        // 如果总数据数  取余 每页显示数量不为0，页数为数据总数除以每页数量的值 + 1
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        // 容错校验，当URL修改page不在有效区间内，赋予对应逻辑默认值
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);


        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for(Question question:questions){
            User user = userMapper.findById(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreater());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        User user = new User();
        if(question.getId() == null){
            // 创建新问题
            question.setCreater(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setModifier(user.getId());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            question.setModifier(user.getId());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
