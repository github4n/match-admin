package com.match.admin.web;
import com.match.admin.core.Result;
import com.match.admin.core.ResultGenerator;
import com.match.admin.model.Odds;
import com.match.admin.service.OddsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/30.
*/
@RestController
@RequestMapping("/odds")
public class OddsController {
    @Resource
    private OddsService oddsService;

    @PostMapping("/add")
    public Result add(Odds odds) {
        oddsService.save(odds);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        oddsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Odds odds) {
        oddsService.update(odds);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Odds odds = oddsService.findById(id);
        return ResultGenerator.genSuccessResult(odds);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Odds> list = oddsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
