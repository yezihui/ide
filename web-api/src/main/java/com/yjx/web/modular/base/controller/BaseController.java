package com.yjx.web.modular.base.controller;

import com.yjx.cache.service.IDictService;
import com.yjx.common.bean.ResponseData;
import com.yjx.web.constants.AuthConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/** 
 * <p> 
 *
 * </p> 
 *
 * @author yejx 
 * @date 2019/12/3 10:46
 */ 
@Api(tags = "基础相关接口")
@RestController
public class BaseController {

    @Resource
    private IDictService iDictService;

    @GetMapping("/dict/all")
    @ApiOperation(value = "获取字典列表", notes = "获取字典列表")
    public ResponseData<List<String>> dictAll() {
        return ResponseData.success(iDictService.dictAll());
    }

}
