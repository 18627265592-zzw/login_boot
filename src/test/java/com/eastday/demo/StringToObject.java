package com.eastday.demo;

import com.eastday.demo.entity.News;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StringToObject {

    private ObjectMapper objectMapper = new ObjectMapper();
    private JSONArray json = new JSONArray();
    /**
     * String字符串转List
     *
     * @param str 字符串
     * @param c   需要转换的类
     * @return
     */
    public List<String> StringToList(String str, Class c) {
        json = JSONArray.fromObject(str);
        List list=new ArrayList();
        try {
            list = JSONArray.toList(json, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Object转String
     *
     * @param o
     * @return
     */
    public String ObjectToString(Object o) {
        String outstr = null;
        try {
            outstr = objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outstr;
    }

    @Test
    public void test(){
        String a="[\"重要气象信息市领导专报\",\"2012年第47期\",\"2012年08月08日21时\",\"签发：汤 绪\",\"制作：\",\"审核：\",\"上 海 市 气 象 局<br>“海葵”最新动态<br>【“海葵”动态】<br>今年第11号强热带风暴“海葵”，今天20时中心已经进入安徽省宣城宁国市境内，就是在北纬30.5度、东经119.2度，中心附近最大风力10级（25米/秒），中心最低气压为985百帕。七级大风圈半径350公里。目前“海葵”正以每小时15公里左右的速度向西北方向移动。<br>【风雨实况】<br>受“海葵”外围环流影响，本市昨天白天普遍出现阵雨天气。随着“海葵”西北行并在浙江北部登陆，昨天半夜至今天白天，“海葵”本体对本市带来严重的风雨影响，从7日20时至8日20时，本市普遍出现大暴雨，其中虹口区的鲁迅公园229.3毫米为最大，宝山区上大附中223.3毫米次之（见表1）。<br>受“海葵”影响，8月7日20时至8日20时全市普降大暴雨，11个标准测站全市日平均降水量为127.9毫米，位列1961年以来受台风影响的强降水第4位。其中，嘉定单站日降水量为205.6毫米，为该站1961年以来历史次高值。此次“海葵”台风引起的全市性单日大范围暴雨是近三十年来仅次于2005年8月7日“麦莎”台风日平均最大降雨量（128.9毫米）。<br>受“海葵”影响，本市最大风力8～10级，沿江沿海地区和长江口区10～12级，洋山港区大于12级。<br>【趋势分析】<br>由于“海葵”仍处于大陆高压和海上副高之间的弱环境场中，引导气流偏弱，导致其移速缓慢，预计明后天“海葵”将在安徽南部、江苏西南部、浙江西北部交界附近徘徊少动，强度缓慢减弱。预计明天本市的局部地区有大雨到暴雨，降雨强度较今天明显偏弱，强降雨范围亦偏小，10日受“海葵”残余低压环流和北方扩散弱冷空气的共同影响，本市仍可能有大雨到暴雨。<br>目前本市仍处于“海葵”七级大风圈半径内，上海市台风紧急警报和台风红色预警信号维持，预计今天半夜起“海葵”对本市的风雨影响趋于减小，经与市防汛指挥部磋商，我局可能于明天早晨解除上海市台风紧急警报，维持上海市台风警报，并将台风红色预警信号降级为台风橙色或黄色预警信号。<br>我们将密切关注，及时报告“海葵”的最新动态。<br>报：市人大办公厅  市政协办公厅<br>\"]";
        List<String> list=StringToList(a,String.class);
        News news=new News();
        news.setTitle(list.get(0));
        news.setStage(list.get(1));
        news.setTime(list.get(2));
        String [] arr1=list.get(3).split("：");
        if(arr1.length==2){
            news.setPublisher(arr1[1]);
        }else{
            news.setPublisher("");
        }
        String [] arr2=list.get(4).split("：");

        if(arr2.length==2){
            news.setEditor(arr2[1]);
        }else{
            news.setEditor("");
        }
        String [] arr3=list.get(5).split("：");

        if(arr3.length==2){
            news.setAuditor(arr3[1]);
        }else{
            news.setAuditor("");
        }
        news.setContent(list.get(6));
        System.out.println(news.toString());
    }


    //遍历文件夹
    @Test
    public void star2() throws Exception{
        String oldPath="C:\\Users\\admin\\Desktop\\新建文件夹\\快报TXT";
        String newPath="C:\\Users\\admin\\Desktop\\新建文件夹\\对象类型\\快报TXT";
        copyDir(oldPath,newPath);
    }

    public void copyDir(String oldPath, String newPath) throws IOException {
        File file = new File(oldPath);
        //文件名称列表
        String[] filePath = file.list();

        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }

        for (int i = 0; i < filePath.length; i++) {
            if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
                copyDir(oldPath  + file.separator  + filePath[i], newPath  + file.separator + filePath[i]);
            }

            if (new File(oldPath  + file.separator + filePath[i]).isFile()) {
                copyFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }

        }
    }

    public void copyFile(String oldPath, String newPath) throws IOException {
            //BufferedReader br = new BufferedReader(new FileReader(oldPath));
           BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(oldPath), "UTF-8"));
            //
            String txt = "";
            List<String> list=new ArrayList<String>();
            while (((txt = br.readLine()) != null)) {
                if(txt.indexOf("\uFEFF")!=-1){
                    txt=txt.replace("\uFEFF","");
                }
                list=StringToList(txt,String.class);
            }
            br.close();
            System.out.println(oldPath);

            News news=new News();
            news.setTitle(list.get(0));
            news.setStage(list.get(1));
            news.setTime(list.get(2));
            String [] arr1=list.get(3).split("：");
            if(arr1.length==2){
                news.setPublisher(arr1[1]);
            }else{
                news.setPublisher("");
            }
            String [] arr2=list.get(4).split("：");

            if(arr2.length==2){
                news.setEditor(arr2[1]);
            }else{
                news.setEditor("");
            }
            String [] arr3=list.get(5).split("：");

            if(arr3.length==2){
                news.setAuditor(arr3[1]);
            }else{
                news.setAuditor("");
            }
            news.setContent(list.get(6));

            BufferedWriter bw = new BufferedWriter(new FileWriter(newPath));
            bw.write(ObjectToString(news));
            bw.close();
            news=null;
            System.out.println("写完");

    }
}
