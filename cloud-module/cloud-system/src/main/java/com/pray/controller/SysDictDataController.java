package com.pray.controller;


import com.pray.annotation.RequiresPermissions;
import com.pray.entity.Result;
import com.pray.entity.sys.SysDictData;
import com.pray.poi.ExcelUtil;
import com.pray.service.ISysDictDataService;
import com.pray.service.ISysDictTypeService;
import com.pray.utils.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.pray.entity.Result.success;
import static com.pray.entity.Result.convertResult;

/**
 * 数据字典信息
 *
 * @author 春江花朝秋月夜
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Resource
    private ISysDictDataService dictDataService;

    @Resource
    private ISysDictTypeService dictTypeService;


    @GetMapping("/varList")
    public Result varList() {
        return success(dictDataService.selectDictDataList());
    }

    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @RequiresPermissions("system:dict:query")
    @GetMapping(value = "/{dictCode}")
    public Result getInfo(@PathVariable Long dictCode) {
        return success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public Result dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<SysDictData>();
        }
        return success(data);
    }

    /**
     * 新增字典类型
     */
    @RequiresPermissions("system:dict:add")
    @PostMapping
    public Result add(@Validated @RequestBody SysDictData dict) {
        return convertResult(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @PutMapping
    public Result edit(@Validated @RequestBody SysDictData dict) {
        dict.setUpdateBy("默认用户");
        return convertResult(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @RequiresPermissions("system:dict:remove")
    @DeleteMapping("/{dictCodes}")
    public Result remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}
