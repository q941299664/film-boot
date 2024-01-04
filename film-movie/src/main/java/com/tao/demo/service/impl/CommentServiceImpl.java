package com.tao.demo.service.impl;

import com.tao.demo.domain.entity.Comment;
import com.tao.demo.mapper.CommentMapper;
import com.tao.demo.service.CommentService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Service
public class CommentServiceImpl extends MPJBaseServiceImpl<CommentMapper, Comment> implements CommentService {

}
