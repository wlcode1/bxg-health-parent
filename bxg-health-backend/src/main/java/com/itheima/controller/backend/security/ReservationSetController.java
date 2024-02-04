package com.itheima.controller.backend.security;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.ReservationSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ordersetting")
@Slf4j
public class ReservationSetController {
    @Autowired
    ReservationSetService reservationSetService;

    /**
     * 模板下载
     *
     * @param filename
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filename) {
        log.info("模板下载： {}", filename);
        return reservationSetService.downloadFile(filename);
    }

    /**
     * 根据日期修改可预约人数
     *
     * @param orderSetting
     * @return
     */
    @PostMapping("/editNumberByOrderDate")
    public Result editNumberByOrderDate(@RequestBody OrderSetting orderSetting) {
        log.info("根据日期修改可预约人数");
        reservationSetService.editNumberByOrderDate(orderSetting);
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }

    /**
     * 根据年月查询对应的预约设置信息
     *
     * @param month
     * @return
     */
    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(@RequestParam String month) {
        log.info("根据年月查询对应的预约设置信息: {}", month);
        return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, reservationSetService.getOrderSettingByMonth(month));
    }

    /**
     * 模板上传
     *
     * @param excelFile
     * @return
     */
    @PostMapping("/upload")
    public Result uploadFile(@RequestBody MultipartFile excelFile) throws IOException {
        reservationSetService.uploadFile(excelFile);
        return new Result(true, MessageConstant.UPLOAD_SUCCESS);
    }
}




//@Slf4j
//@RestController
//public class UploadController {
//
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,image);
//
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        //构建新的文件名
//        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));//文件扩展名
//        String newFileName = UUID.randomUUID().toString()+extname;//随机名+文件扩展名
//
//        //将文件存储在服务器的磁盘目录
//        image.transferTo(new File("E:/images/"+newFileName));
//
//        return Result.success();
//    }
//
//}





//下载
//@GetMapping("/download")
//public void downloadTemplate(HttpServletResponse response,@RequestParam String filename){
////        String fileName = request.getParameter("fileName");
//    InputStream is = null;
//    OutputStream os = null;
//    try {
//        is = this.getClass().getClassLoader().getResourceAsStream("templates"+ File.separator+filename);
//        os = response.getOutputStream();
//        byte[] bytes = StreamUtils.copyToByteArray(is);
//        response.reset();
//        //下面这两行是为了解决跨域，如果没有跨域这两行可以删除
////            response.addHeader("Access-Control-Allow-Origin", "*");
////            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
////            response.setContentType("application/octet-stream;charset=utf-8");
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
//        response.addHeader("Content-Length", "" + bytes.length);
//        os.write(bytes);
//        os.flush();
//    }catch (Exception e) {
//        log.error("下载出错", e);
//    }finally {
//        try {
//            if(os != null) {
//                os.close();
//            }
//            if (is != null) {
//                is.close();
//            }
//        }catch (Exception e) {
//            log.error("关闭流出错", e);
//        }
//    }
//}