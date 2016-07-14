package jiayuan.huawei.com.jiayuantestmanager.utils;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class Utils {

    /**
     * 获取指定文件的文件名
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath){
        int lastIndexOf = filePath.lastIndexOf("/");
        String fileName = filePath.substring(lastIndexOf + 1, filePath.length());
        return fileName;
    }
}
