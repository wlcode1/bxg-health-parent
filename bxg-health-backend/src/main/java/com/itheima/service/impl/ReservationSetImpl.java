package com.itheima.service.impl;

import com.itheima.mapper.ReservationSetMapper;
import com.itheima.pojo.OrderSetting;
import com.itheima.pojo.OrderVO;
import com.itheima.service.ReservationSetService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationSetImpl implements ReservationSetService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    ReservationSetMapper reservationSetMapper;

    /**
     * 模板下载
     *
     * @param filename
     * @return
     */
    @Override
    public ResponseEntity<Resource> downloadFile(String filename) {
        Resource resource = resourceLoader.getResource("classpath:templates/" + filename);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", filename);
        return ResponseEntity.ok().headers(httpHeaders).body(resource);
    }

    @Override
    public void uploadFile(MultipartFile excelFile) throws IOException {
        //获取原始文件名
        String originalFilename = excelFile.getOriginalFilename();

        //构建新的文件名
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));//文件扩展名
        String newFileName = UUID.randomUUID().toString() + extname;//随机名+文件扩展名

        //将文件存储在服务器的磁盘目录
        String path = "C:/itheima/bxg-health-parent/bxg-health-backend/src/main/resources/templates/tempfile/" + newFileName;

        excelFile.transferTo(new File(path));
        readFile(path);
    }

    public void readFile(String filePath) throws IOException {
        FileInputStream in = new FileInputStream(new File(filePath));
        //通过输入流读取指定的Excel文件
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取Excel文件的第1个Sheet页
        XSSFSheet sheet = excel.getSheetAt(0);

        //获取Sheet页中的最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        List<OrderSetting> orderSettings = new ArrayList<>();

        for (int i = 1; i <= lastRowNum; i++) {
            //获取Sheet页中的行
            XSSFRow titleRow = sheet.getRow(i);
//            //获取行的第1个单元格
            XSSFCell cell1 = titleRow.getCell(0);

            if (cell1 != null && cell1.getCellTypeEnum() != CellType.STRING) {
                double value = cell1.getNumericCellValue(); // 获取单元格的数字值

                // 判断该数字值是否为日期格式
                boolean isDate = DateUtil.isValidExcelDate(value);

                if (isDate) {
                    Date orderDate = DateUtil.getJavaDate(value);

                    //获取行的第3个单元格
                    XSSFCell cell2 = titleRow.getCell(1);
                    //获取单元格中的文本内容
                    cell2.setCellType(CellType.NUMERIC);
                    double cellValue2 = cell2.getNumericCellValue();

                    OrderSetting orderSetting = new OrderSetting(orderDate, (int) cellValue2);
                    orderSettings.add(orderSetting);
//                    setReservation(orderSetting);
                } else {
                    System.out.println("不是有效的日期");
                }
            }
        }
        if (orderSettings.size() > 0) {
            setReservation(orderSettings);
        }
        //关闭资源
        in.close();
        excel.close();
    }

    public void setReservation(List<OrderSetting> orderSettings) {
        reservationSetMapper.setReservation(orderSettings);
    }

    /**
     * 根据传入的日期进行查询数据
     *
     * @param month
     * @return
     */
    @Override
    public List<OrderVO> getOrderSettingByMonth(String month) {
        String[] dateStr = month.split("-");
        List<OrderSetting> orderSettings = reservationSetMapper.getOrderSettingByMonth(Integer.valueOf(dateStr[0]), Integer.valueOf(dateStr[1]));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        List<OrderVO> orderVOList = null;
        if (orderSettings.size() > 0) {
            orderVOList = new ArrayList<>();
            for (OrderSetting orderSetting : orderSettings) {
                OrderVO order = OrderVO.builder().date(simpleDateFormat.format(orderSetting.getOrderDate())).reservations(orderSetting.getReservations()).number(orderSetting.getNumber()).build();
                orderVOList.add(order);
            }
        }
        return orderVOList;
    }

    @Override
    public void editNumberByOrderDate(OrderSetting orderSetting) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        reservationSetMapper.editNumberByOrderDate(simpleDateFormat.format(orderSetting.getOrderDate()), orderSetting.getNumber());
    }
}