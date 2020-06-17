package com.xiaoliu.common.utils.excelutils;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 监听类，可以自定义
 * @author: FuBiaoLiu
 * @date: 2018/9/12
 */
public class ExcelListener extends AnalysisEventListener {

    /**
     * 自定义用于暂时存储data
     * 可以通过实例获取该值
     */
    private List<Object> listData = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        // 数据存储到list，供批量处理，或后续自己业务逻辑处理。
        listData.add(object);
        // 根据业务自行 do something
        doSomething();

        // 如数据过大，可以进行定量分批处理
       /*if(listData.size()<=100){
            listData.add(object);
        }else {
            doSomething();
            listData = new ArrayList<Object>();
        }
         */
    }

    /**
     * 根据业务自行实现该方法
     */
    private void doSomething() {

    }

    /**
     * 根据业务自行实现该方法
     */
    public InputStream doSomething(AnalysisContext context) {
        InputStream inputStream = context.getInputStream();
        return inputStream;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        /*  doSomething();
            datas.clear();
            解析结束销毁不用的资源
         */
    }

    public List<Object> getListData() {
        return listData;
    }

    public void setListData(List<Object> listData) {
        this.listData = listData;
    }
}
