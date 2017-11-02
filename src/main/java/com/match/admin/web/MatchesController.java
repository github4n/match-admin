package com.match.admin.web;
import com.match.admin.core.Result;
import com.match.admin.core.ResultGenerator;
import com.match.admin.model.Matches;
import com.match.admin.service.MatchesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/11/02.
*/
@RestController
@RequestMapping("/matches")
public class MatchesController {
    @Resource
    private MatchesService matchesService;

    @PostMapping("/add")
    public Result add(Matches matches) {
        matchesService.save(matches);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        matchesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Matches matches) {
        matchesService.update(matches);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Matches matches = matchesService.findById(id);
        return ResultGenerator.genSuccessResult(matches);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Matches> list = matchesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
