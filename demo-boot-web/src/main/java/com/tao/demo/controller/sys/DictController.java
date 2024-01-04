package com.tao.demo.controller.sys;

import com.tao.demo.core.controller.BaseController;
import com.tao.demo.domain.entity.Dict;
import com.tao.demo.service.DictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统字典表 前端控制器
 *
 * @author LiTao
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController<DictService, Dict> {

}
