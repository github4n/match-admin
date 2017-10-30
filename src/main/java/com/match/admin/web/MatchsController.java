package com.match.admin.web;
import com.match.admin.core.Result;
import com.match.admin.core.ResultGenerator;
import com.match.admin.model.Matchs;
import com.match.admin.service.MatchsService;
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
@RequestMapping("/matchs")
public class MatchsController {
    @Resource
    private MatchsService matchsService;

    @PostMapping("/add")
    public Result add(Matchs matchs) {
        matchsService.save(matchs);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        matchsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Matchs matchs) {
        matchsService.update(matchs);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Matchs matchs = matchsService.findById(id);
        return ResultGenerator.genSuccessResult(matchs);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Matchs> list = matchsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
