package com.match.admin.model;

import java.util.Date;
import javax.persistence.*;

public class Odds {
    @Id
    @Column(name = "odds_id")
    private Integer oddsId;

    /**
     * 比赛id
     */
    @Column(name = "matchs_id")
    private Integer matchsId;

    /**
     * 公司id
     */
    @Column(name = "companys_id")
    private Integer companysId;

    /**
     * 时间
     */
    private Date time;

    /**
     * 胜赔
     */
    private Double win;

    /**
     * 平赔
     */
    private Double draw;

    /**
     * 负赔
     */
    private Double lose;

    /**
     * 凯利胜
     */
    @Column(name = "k_win")
    private Double kWin;

    /**
     * 凯利平
     */
    @Column(name = "k_draw")
    private Double kDraw;

    /**
     * 凯利负
     */
    @Column(name = "k_lose")
    private Double kLose;

    /**
     * @return odds_id
     */
    public Integer getOddsId() {
        return oddsId;
    }

    /**
     * @param oddsId
     */
    public void setOddsId(Integer oddsId) {
        this.oddsId = oddsId;
    }

    /**
     * 获取比赛id
     *
     * @return matchs_id - 比赛id
     */
    public Integer getMatchsId() {
        return matchsId;
    }

    /**
     * 设置比赛id
     *
     * @param matchsId 比赛id
     */
    public void setMatchsId(Integer matchsId) {
        this.matchsId = matchsId;
    }

    /**
     * 获取公司id
     *
     * @return companys_id - 公司id
     */
    public Integer getCompanysId() {
        return companysId;
    }

    /**
     * 设置公司id
     *
     * @param companysId 公司id
     */
    public void setCompanysId(Integer companysId) {
        this.companysId = companysId;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取胜赔
     *
     * @return win - 胜赔
     */
    public Double getWin() {
        return win;
    }

    /**
     * 设置胜赔
     *
     * @param win 胜赔
     */
    public void setWin(Double win) {
        this.win = win;
    }

    /**
     * 获取平赔
     *
     * @return draw - 平赔
     */
    public Double getDraw() {
        return draw;
    }

    /**
     * 设置平赔
     *
     * @param draw 平赔
     */
    public void setDraw(Double draw) {
        this.draw = draw;
    }

    /**
     * 获取负赔
     *
     * @return lose - 负赔
     */
    public Double getLose() {
        return lose;
    }

    /**
     * 设置负赔
     *
     * @param lose 负赔
     */
    public void setLose(Double lose) {
        this.lose = lose;
    }

    /**
     * 获取凯利胜
     *
     * @return k_win - 凯利胜
     */
    public Double getkWin() {
        return kWin;
    }

    /**
     * 设置凯利胜
     *
     * @param kWin 凯利胜
     */
    public void setkWin(Double kWin) {
        this.kWin = kWin;
    }

    /**
     * 获取凯利平
     *
     * @return k_draw - 凯利平
     */
    public Double getkDraw() {
        return kDraw;
    }

    /**
     * 设置凯利平
     *
     * @param kDraw 凯利平
     */
    public void setkDraw(Double kDraw) {
        this.kDraw = kDraw;
    }

    /**
     * 获取凯利负
     *
     * @return k_lose - 凯利负
     */
    public Double getkLose() {
        return kLose;
    }

    /**
     * 设置凯利负
     *
     * @param kLose 凯利负
     */
    public void setkLose(Double kLose) {
        this.kLose = kLose;
    }
}