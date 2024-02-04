package com.itheima.service;

import com.itheima.pojo.OrderSetting;
import com.itheima.pojo.OrderVO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReservationSetService {
    /**
     * 模板下载
     *
     * @param filename
     * @return
     */
    ResponseEntity<Resource> downloadFile(String filename);

    /**
     * 上传excle表格获取其中的数据进行数据的插入
     *
     * @param excelFile
     * @throws IOException
     */
    void uploadFile(MultipartFile excelFile) throws IOException;

    /**
     * 根据传入的日期进行查询数据
     *
     * @param month
     * @return
     */
    List<OrderVO> getOrderSettingByMonth(String month);

    void editNumberByOrderDate(OrderSetting orderSetting);
}