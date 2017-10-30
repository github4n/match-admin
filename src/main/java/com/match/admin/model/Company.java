package com.match.admin.model;

import javax.persistence.*;

public class Company {
    @Id
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 国家
     */
    @Column(name = "company_country")
    private String companyCountry;

    /**
     * @return company_id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取国家
     *
     * @return company_country - 国家
     */
    public String getCompanyCountry() {
        return companyCountry;
    }

    /**
     * 设置国家
     *
     * @param companyCountry 国家
     */
    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }
}