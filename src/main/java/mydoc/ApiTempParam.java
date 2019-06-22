package mydoc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiTempParam {
    private String point;
    private String remark;
    private String scene;
    private String type;
    private String in;
    private String out;

    private static String isNull(String s){
        if (s == null || s.length() == 0 ) return "/";
        return s;
    }

    public void setPoint(String point) {

        this.point = isNull(point);
    }

    public void setRemark(String remark) {

        this.remark = isNull(remark);
    }

    public void setScene(String scene) {
        this.scene = isNull(scene);
    }

    public void setType(String type) {
        this.type = isNull(type);
    }

    public void setIn(String in) {
        this.in = isNull(in);
    }

    public void setOut(String out) {
        this.out = isNull(out);
    }

    public String getPoint() {
        return point;
    }

    public String getRemark() {
        return remark;
    }

    public String getScene() {
        return scene;
    }

    public String getType() {
        return type;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    @Override
    public String toString() {
        return "ApiTempParam{" +
                "point='" + point + '\'' +
                ", remark='" + remark + '\'' +
                ", scene='" + scene + '\'' +
                ", type='" + type + '\'' +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                '}';
    }

    public static void genApi(List<ApiTempParam> list){
        try {
            String path = ApiTempParam.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File dir = new File(path).getParentFile();
            Configuration configuration = new Configuration(Configuration.getVersion());
            // 指定configuration对象模板文件存放的路径
            configuration.setDirectoryForTemplateLoading(dir);
            // 设置config的默认字符集，一般是UTF-8
            configuration.setDefaultEncoding("UTF-8");
            // 设置错误控制器
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
            // 获取模版
            Template template = configuration.getTemplate("api.flt");
            Writer writer = new FileWriter(new File(dir,"api.html"));
            Map<String, Object> map = new HashMap<>();
                map.put("docList",list);
            template.process(map, writer);
            //关闭流
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
