package com.tao.demo.controller.film;

import com.tao.demo.domain.entity.Comment;
import com.tao.demo.service.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tao.demo.core.controller.BaseController;

/**
 * 评论表 前端控制器
 *
 * @author LiTao
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController<CommentService, Comment> {

}
