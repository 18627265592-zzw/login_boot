package com.eastday.demo;

import com.eastday.demo.dao.IMobileDao;
import com.eastday.demo.dao.IUserDao;
import com.eastday.demo.entity.Mobile;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

    @Autowired
   private IUserDao userDao;

    @Autowired
    private IMobileDao mobileDao;

    @Test
    public void testMapper() {
        //通用mapper的Example方法
        Example example = new Example(Mobile.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("mobilePhone","18627265592");
        criteria.andEqualTo("mobileCode","297772");
        List<Mobile> mobiles = mobileDao.selectByExample(example);
        System.out.println(mobiles.toString());
    }

    public void contextLoads(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String txt = "";
        String strs = "";
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        while (((txt = br.readLine()) != null)) {
            if (!"".equals(txt)) {
                list.add(txt);
            }
        }
        br.close();
        System.out.println("读完");
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list2.add(list.get(0));//标题
            } else if (i == 1) {
                list2.add(list.get(1));//期数
            } else if (i == 3) {
                String[] arr = list.get(3).split("签发：");
                list2.add(arr[0].trim());//时间
                list2.add("签发：" + arr[1].trim());//签发
            } else if (i == list.size() - 1) {
                if(list.get(i).indexOf("制作")!=-1){
                    System.out.println("存在");
                    if(list.get(i).indexOf("审核：")!=-1){
                        String[] arr = list.get(i).split("审核：");
                        list2.add(arr[0].trim());//制作
                        String[] arr2 = arr[1].split("联系电话");
                        list2.add("审核：" + arr2[0].trim());//审核
                    }else{
                        String[] arr = list.get(i).split("联系电话：");
                        list2.add(arr[0].trim());//制作
                        list2.add("审核：");
                    }
                }else{
                    strs += list.get(i)+"<br>";
                    System.out.println("不存在");
                    list2.add("制作：");
                    list2.add("审核：");
                }
            } else {
                strs += list.get(i)+"<br>";
            }
        }
        list2.add(strs);//内容
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\admin\\Desktop\\新建文件夹\\专报TXT\\2014\\"+file.getName()));
        bw.write(JSONArray.fromObject(list2).toString());
        bw.close();
        System.out.println("写完");
        //System.out.println(JSONArray.fromObject(list2));
    }

    public void create(File fi) throws Exception{
        File[] Files = fi.listFiles();
        for(File file:Files){
            if(fi.isDirectory()){  //是文件夹
                create(file);
            }else if(fi.isFile() && file.getName().endsWith(".txt")){  //是文件
                contextLoads(file);
            }

        }
    }

    @Test
    public void star() throws Exception{
        File file = new File("C:\\Users\\admin\\Desktop\\专报TXT\\2014");
        create(file);
    }


    //遍历文件夹
    @Test
    public void star2() throws Exception{
        String oldPath="C:\\Users\\admin\\Desktop\\快报TXT\\2015年\\灿鸿";//
        String newPath="C:\\Users\\admin\\Desktop\\新建文件夹\\快报TXT\\2015年\\灿鸿";
        copyDir(oldPath,newPath);
    }

    public static void copyDir(String oldPath, String newPath) throws IOException {
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

    public static void copyFile(String oldPath, String newPath) throws IOException {
        //int a=0;
        if(oldPath.indexOf("重要气象信息")!=-1){     //过滤文件
            BufferedReader br = new BufferedReader(new FileReader(oldPath));
            String txt = "";
            String strs = "";//内容体
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<Integer> list3 = new ArrayList<>();
            while (((txt = br.readLine()) != null)) {
                if (!"".equals(txt) && !"\uFEFF ".equals(txt) && !"\uFEFF".equals(txt) && !"急件".equals(txt.trim()) && txt.indexOf("速报")==-1
                        && txt.indexOf("更正")==-1 ) {
                    list.add(txt);
                    if(txt.equals("\u0007")){
                        list3.add(list.size()-1);//特殊字符的索引
                    }
                }
            }
            br.close();
            /*if(a>0){
                System.out.println(a+"---------"+oldPath);
            }*/
            int a=0;
            int b=0;
            if(list3.size()>0){
                //System.out.println(list.size());
                a=list3.get(0)-2;//删除开始位索引：8
                b=list3.get(list3.size()-1);//删除结束位索引：41
                //System.out.println("a-------"+a);
                //System.out.println("b-------"+b);
                list.subList(a, b+1).clear();
                //System.out.println(list.size());
            }
            System.out.println("读完");
            System.out.println(oldPath);
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    list2.add(list.get(i));//标题
                } else if (i == 1) {
                    if(list.get(i).indexOf("201")!=-1){  //有期数
                        list2.add(list.get(i));//期数
                    }else{                               //没期数
                        list2.add("");//期数
                    }

                }else if (i == 3) {
                    System.out.println(list.get(0));
                    System.out.println(list.get(1));
                    System.out.println(list.get(2));
                    System.out.println(list.get(3));
                    if(list.get(1).indexOf("201")!=-1){  //有期数
                        String[] arr = list.get(i).split("签发：");
                        list2.add(arr[0].trim());//时间
                        list2.add("签发：" + arr[1].trim());//签发
                    }else{
                        String[] arr = list.get(2).split("签发：");
                        list2.add(arr[0].trim());//时间
                        list2.add("签发：" + arr[1].trim());//签发
                    }

                } else if (i == list.size() - 1) {
                    if(list.get(i).indexOf("制作")!=-1){  //有制作信息
                        System.out.println("存在");
                        if(list.get(i).indexOf("审核：")!=-1){
                            String[] arr = list.get(i).split("审核：");
                            list2.add(arr[0].trim());//制作
                            String[] arr2 = arr[1].split("联系电话");
                            list2.add("审核：" + arr2[0].trim());//审核
                        }else{
                            String[] arr = list.get(i).split("联系电话：");
                            list2.add(arr[0].trim());//制作
                            list2.add("审核：");
                        }
                    }else{
                        strs += list.get(i)+"<br>";
                        System.out.println("不存在");
                        list2.add("制作：");
                        list2.add("审核：");
                    }
                } else {
                    strs += list.get(i)+"<br>";
                }
            }
            list2.add(strs);//内容
            BufferedWriter bw = new BufferedWriter(new FileWriter(newPath));
            bw.write(JSONArray.fromObject(list2).toString());
            bw.close();
            System.out.println("写完");
        }
    }



    //特殊文件
    @Test
    public void create3() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\专报TXT\\2019\\2019年5月上海降水概况（截止到5月23日）.txt"));
        String txt = "";
        List<String> list = new ArrayList<>();
        String str="";
        while (((txt = br.readLine()) != null)) {
            if(!"".equals(txt) && !"\uFEFF ".equals(txt) && !"\uFEFF".equals(txt)){
                str+=txt+"<br>";
            }
        }
        list.add(str);
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\admin\\Desktop\\新建文件夹\\专报TXT\\2019\\2019年5月上海降水概况（截止到5月23日）.txt"));
        bw.write(JSONArray.fromObject(list).toString());
        bw.close();
    }

}

