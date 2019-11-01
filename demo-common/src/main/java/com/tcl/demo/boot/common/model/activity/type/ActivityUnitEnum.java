package com.tcl.demo.boot.common.model.activity.type;

import com.tcl.demo.boot.common.model.activity.rule.city.ActivityCityRule;
import com.tcl.demo.boot.common.model.activity.rule.time.ActivityTimeRule;
import lombok.Getter;

/**
 * 活动单元
 */
@Getter
public enum ActivityUnitEnum {

    ACTIVITY_TIME_UNIT(LvTwo.PARTICIPATE_TIME.getLvParent(), LvTwo.PARTICIPATE_TIME.getLv(), ActivityTimeRule.class),

    ACTIVITY_CITY_UNIT(LvTwo.PARTICIPATE_CITY.getLvParent(), LvTwo.PARTICIPATE_CITY.getLv(), ActivityCityRule.class);


    ActivityUnitEnum(Integer lvOne, Integer lvTwo, Class unitClass) {
        this.lvOne = lvOne;
        this.lvTwo = lvTwo;
        this.unitClass = unitClass;
    }

    private Integer lvOne;

    private Integer lvTwo;

    private Class unitClass;


    enum LvOne {

        BASE(1, "活动基础信息"),

        PARTICIPATE(2, "活动参与信息"),

        GRANT(3, "活动发放信息"),

        PAGE(4, "活动页面信息");

        LvOne(Integer lv, String desc) {
            this.lv = lv;
            this.desc = desc;
        }

        private Integer lv;

        private String desc;

        public Integer getLv() {
            return lv;
        }

        public String getDesc() {
            return desc;
        }

    }


    enum LvTwo {

        BASE_DEFAULT(LvOne.BASE.getLv(), 1, "活动名称，时间，描述等默认信息单元"),

        PARTICIPATE_TIME(LvOne.PARTICIPATE.getLv(), 1, "活动参与时间、时段、每月、每周等信息单元"),

        PARTICIPATE_TIMES(LvOne.PARTICIPATE.getLv(), 2, "活动参与次数信息单元"),

        PARTICIPATE_CITY(LvOne.PARTICIPATE.getLv(), 3, "活动参与城市信息单元"),

        GRANT_DEFAULT(LvOne.GRANT.getLv(), 1, "默认发放信息单元"),

        PAGE_DEFAULT(LvOne.PAGE.getLv(), 1, "默认页面信息单元");

        LvTwo(Integer lvParent, Integer lv, String desc) {
            this.lvParent = lvParent;
            this.lv = lv;
            this.desc = desc;
        }

        private Integer lvParent;

        private Integer lv;

        private String desc;

        public Integer getLvParent() {
            return lvParent;
        }

        public Integer getLv() {
            return lv;
        }

        public String getDesc() {
            return desc;
        }
    }
}
