package com.match.admin.model;

import java.util.Date;
import javax.persistence.*;

public class Matchs {
    @Id
    @Column(name = "matchs_id")
    private Integer matchsId;

    /**
     * 联赛
     */
    private String league;

    /**
     * 比赛时间
     */
    private Date time;

    /**
     * 主队
     */
    private String home;

    /**
     * 客队
     */
    private String guest;

    /**
     * 主队全场进球
     */
    @Column(name = "home_goal")
    private Integer homeGoal;

    /**
     * 客队全场进球
     */
    @Column(name = "guest_goal")
    private Integer guestGoal;

    /**
     * 主队半场进球
     */
    @Column(name = "home_goal_h")
    private Integer homeGoalH;

    /**
     * 客队半场进球
     */
    @Column(name = "guest_goal_h")
    private Integer guestGoalH;

    /**
     * 亚盘
     */
    private Double points;

    /**
     * 大小盘
     */
    private Double goals;

    /**
     * 主队角球
     */
    @Column(name = "home_corner")
    private Integer homeCorner;

    /**
     * 客队角球
     */
    @Column(name = "guest_corner")
    private Integer guestCorner;

    /**
     * 主队红牌
     */
    @Column(name = "home_red")
    private Integer homeRed;

    /**
     * 客队红牌
     */
    @Column(name = "guest_red")
    private Integer guestRed;

    /**
     * 主队黄牌
     */
    @Column(name = "home_yellow")
    private Integer homeYellow;

    /**
     * 客队黄牌
     */
    @Column(name = "guest_yellow")
    private Integer guestYellow;

    /**
     * @return matchs_id
     */
    public Integer getMatchsId() {
        return matchsId;
    }

    /**
     * @param matchsId
     */
    public void setMatchsId(Integer matchsId) {
        this.matchsId = matchsId;
    }

    /**
     * 获取联赛
     *
     * @return league - 联赛
     */
    public String getLeague() {
        return league;
    }

    /**
     * 设置联赛
     *
     * @param league 联赛
     */
    public void setLeague(String league) {
        this.league = league;
    }

    /**
     * 获取比赛时间
     *
     * @return time - 比赛时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置比赛时间
     *
     * @param time 比赛时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取主队
     *
     * @return home - 主队
     */
    public String getHome() {
        return home;
    }

    /**
     * 设置主队
     *
     * @param home 主队
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * 获取客队
     *
     * @return guest - 客队
     */
    public String getGuest() {
        return guest;
    }

    /**
     * 设置客队
     *
     * @param guest 客队
     */
    public void setGuest(String guest) {
        this.guest = guest;
    }

    /**
     * 获取主队全场进球
     *
     * @return home_goal - 主队全场进球
     */
    public Integer getHomeGoal() {
        return homeGoal;
    }

    /**
     * 设置主队全场进球
     *
     * @param homeGoal 主队全场进球
     */
    public void setHomeGoal(Integer homeGoal) {
        this.homeGoal = homeGoal;
    }

    /**
     * 获取客队全场进球
     *
     * @return guest_goal - 客队全场进球
     */
    public Integer getGuestGoal() {
        return guestGoal;
    }

    /**
     * 设置客队全场进球
     *
     * @param guestGoal 客队全场进球
     */
    public void setGuestGoal(Integer guestGoal) {
        this.guestGoal = guestGoal;
    }

    /**
     * 获取主队半场进球
     *
     * @return home_goal_h - 主队半场进球
     */
    public Integer getHomeGoalH() {
        return homeGoalH;
    }

    /**
     * 设置主队半场进球
     *
     * @param homeGoalH 主队半场进球
     */
    public void setHomeGoalH(Integer homeGoalH) {
        this.homeGoalH = homeGoalH;
    }

    /**
     * 获取客队半场进球
     *
     * @return guest_goal_h - 客队半场进球
     */
    public Integer getGuestGoalH() {
        return guestGoalH;
    }

    /**
     * 设置客队半场进球
     *
     * @param guestGoalH 客队半场进球
     */
    public void setGuestGoalH(Integer guestGoalH) {
        this.guestGoalH = guestGoalH;
    }

    /**
     * 获取亚盘
     *
     * @return points - 亚盘
     */
    public Double getPoints() {
        return points;
    }

    /**
     * 设置亚盘
     *
     * @param points 亚盘
     */
    public void setPoints(Double points) {
        this.points = points;
    }

    /**
     * 获取大小盘
     *
     * @return goals - 大小盘
     */
    public Double getGoals() {
        return goals;
    }

    /**
     * 设置大小盘
     *
     * @param goals 大小盘
     */
    public void setGoals(Double goals) {
        this.goals = goals;
    }

    /**
     * 获取主队角球
     *
     * @return home_corner - 主队角球
     */
    public Integer getHomeCorner() {
        return homeCorner;
    }

    /**
     * 设置主队角球
     *
     * @param homeCorner 主队角球
     */
    public void setHomeCorner(Integer homeCorner) {
        this.homeCorner = homeCorner;
    }

    /**
     * 获取客队角球
     *
     * @return guest_corner - 客队角球
     */
    public Integer getGuestCorner() {
        return guestCorner;
    }

    /**
     * 设置客队角球
     *
     * @param guestCorner 客队角球
     */
    public void setGuestCorner(Integer guestCorner) {
        this.guestCorner = guestCorner;
    }

    /**
     * 获取主队红牌
     *
     * @return home_red - 主队红牌
     */
    public Integer getHomeRed() {
        return homeRed;
    }

    /**
     * 设置主队红牌
     *
     * @param homeRed 主队红牌
     */
    public void setHomeRed(Integer homeRed) {
        this.homeRed = homeRed;
    }

    /**
     * 获取客队红牌
     *
     * @return guest_red - 客队红牌
     */
    public Integer getGuestRed() {
        return guestRed;
    }

    /**
     * 设置客队红牌
     *
     * @param guestRed 客队红牌
     */
    public void setGuestRed(Integer guestRed) {
        this.guestRed = guestRed;
    }

    /**
     * 获取主队黄牌
     *
     * @return home_yellow - 主队黄牌
     */
    public Integer getHomeYellow() {
        return homeYellow;
    }

    /**
     * 设置主队黄牌
     *
     * @param homeYellow 主队黄牌
     */
    public void setHomeYellow(Integer homeYellow) {
        this.homeYellow = homeYellow;
    }

    /**
     * 获取客队黄牌
     *
     * @return guest_yellow - 客队黄牌
     */
    public Integer getGuestYellow() {
        return guestYellow;
    }

    /**
     * 设置客队黄牌
     *
     * @param guestYellow 客队黄牌
     */
    public void setGuestYellow(Integer guestYellow) {
        this.guestYellow = guestYellow;
    }
}