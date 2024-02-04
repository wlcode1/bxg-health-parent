package com.itheima.mapper;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReservationSetMapper {

    /**
     * 根据传入的excel表格中的数据进行添加数据
     *
     * @param orderSettings
     */
    void setReservation(List<OrderSetting> orderSettings);

    /**
     * 根据传入的年月来查数据
     *
     * @param year
     * @param month
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(int year, int month);


    void editNumberByOrderDate(String date, int number);
//    void editNumberByOrderDate(OrderSetting orderSetting);
}
