package mydoc;


import com.sun.javadoc.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiDoclet {

   private static List<ApiTempParam> data = new ArrayList<>();

    public static boolean start(RootDoc root) {
        execute(root.classes());
        ApiTempParam.genApi(data);
        return true;
    }
    //javadoc -private -doclet mydoc.ApiDoclet -docletpath out/production/classes src/main/java/mydoc/HelloWord.java -encoding utf-8

 //javadoc -locale zh_CH -private -doclet mydoc.ApiDoclet -docletpath out/production/classes src/main/java/mydoc/HelloWord.java -encoding utf-8


    //javadoc -encoding utf-8 -private -doclet mydoc.ApiDoclet -docletpath C:/Users/Administrator/IdeaProjects/docletJson/build/libs src/main/java/mydoc/HelloWord.java src/main/java/mydoc/HelloWord2.java


    private static void execute(ClassDoc[] classes) {

        for (int i = 0; i < classes.length; i++) {
            String moduleName = classes[i].name();
            Tag[] tags = classes[i].tags("服务名");
            String serverName = "未知服务名";
            if (tags.length == 1){
                serverName = tags[0].text();
            }
            MethodDoc[] methods = classes[i].methods();
            for (int j = 0; j < methods.length; j++) {
                String methedName = methods[j].name();
                String point = serverName+" , " + moduleName+" , "+methedName;
                genApiBean(point ,methods[j]);

            }
        }
    }

    private static void genApiBean(String point, MethodDoc doc) {
        ApiTempParam param = new ApiTempParam();
            param.setPoint(point);
            param.setRemark(docGetKey(doc,"接口摘要"));
            param.setScene(docGetKey(doc,"业务场景"));
            param.setType(docGetKey(doc,"传参类型"));
            param.setIn(docGetKey(doc,"传参列表"));
            param.setOut(docGetKey(doc,"返回列表"));
        data.add(param);
    }

    private static String docGetKey(MethodDoc doc, String key) {
        Tag[] tags = doc.tags(key);
        if (tags.length == 1){
            return tags[0].text();
        }
        return null;
    }


}
