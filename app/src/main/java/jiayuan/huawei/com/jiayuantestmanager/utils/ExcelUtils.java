package jiayuan.huawei.com.jiayuantestmanager.utils;

import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jiayuan.huawei.com.jiayuantestmanager.beans.ExcelBean;
import jiayuan.huawei.com.jiayuantestmanager.beans.MenuBean;
import jiayuan.huawei.com.jiayuantestmanager.constants.Constants;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class ExcelUtils {

    public static Map<MenuBean, List<ExcelBean>> getExcelMapByFile(String path) {
        Map<MenuBean, List<ExcelBean>> excelMap = new ConcurrentHashMap<>();

        jxl.Workbook readwb = null;
        try {
            InputStream instream = new FileInputStream(path);
            readwb = Workbook.getWorkbook(instream);
            int sheetNums = readwb.getNumberOfSheets();
            for (int i = 0; i < sheetNums; i++) {

                MenuBean sheetBean = new MenuBean();
                List<ExcelBean> excelList = new ArrayList<>();
                excelMap.put(sheetBean, excelList);
                Sheet readsheet = readwb.getSheet(i);
                //获取Sheet表中所包含的总行数
                int rsRows = readsheet.getRows();
                //获取Sheet表中所包含的总列数
                int rsCols = readsheet.getColumns();

                sheetBean.index = i;
                sheetBean.itemName = readsheet.getName();
                sheetBean.num = rsRows;

                String sex = null;
                int sexType = sex2Int(sheetBean.itemName);
                if (sexType == 1) {
                    sex = Constants.SEX_MAN;
                } else if (sexType == 2) {
                    sex = Constants.SEX_WOMAN;
                }

                int idnexName = -1;
                int indexCode = -1;
                int indexUid = -1;
                int indexSex = -1;

                for (int col = 0; col < rsCols; col++) {
                    Cell cell = readsheet.getCell(col, 0);
                    String content = cell.getContents();
                    if (content2Name(content)) {
                        idnexName = col;
                    } else if (content2Code(content)) {
                        indexCode = col;
                    } else if (content2UID(content)) {
                        indexUid = col;
                    } else if (content2Sex(content)) {
                        indexSex = col;
                    }
                }

                //获取指定单元格的对象引用
                for (int row = 1; row < rsRows; row++) {
                    ExcelBean excelBean = new ExcelBean();
                    for (int col = 0; col < rsCols; col++) {
                        Cell cell = readsheet.getCell(col, row);
                        String content = cell.getContents();
                        if (col == idnexName) {
                            excelBean.userName = content;
                        } else if (col == indexCode) {
                            excelBean.password = content;
                        } else if (col == indexUid) {
                            excelBean.uid = content;
                        } else if (col == indexSex) {
                            excelBean.sex = content;
                        }
                    }

                    if (TextUtils.isEmpty(excelBean.sex)) {
                        //如果单元标题中 有性别了 那么就统一设置成单元的性别
                        if (!TextUtils.isEmpty(sex)) {
                            excelBean.sex = sex;
                        } else {
                            excelBean.sex = Constants.SEX_UNKNOWN;
                        }
                    }

                    excelList.add(excelBean);
                }
            }
            instream.close();
            return excelMap;
        } catch (FileNotFoundException e) {
            return null;
        } catch (BiffException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            if (readwb != null) {
                readwb.close();
            }
        }
    }

    public static boolean content2Name(String content) {
        if ("账户".equals(content)) {
            return true;
        } else if ("用户名".equals(content)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean content2Code(String content) {
        if ("密码".equals(content)) {
            return true;
        } else if ("password".equalsIgnoreCase(content)) {
            return true;
        } else if ("code".equalsIgnoreCase(content)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean content2UID(String content) {
        if ("UID".equalsIgnoreCase(content)) {
            return true;
        } else if ("id".equalsIgnoreCase(content)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean content2Sex(String content) {
        if ("性别".equalsIgnoreCase(content)) {
            return true;
        } else if ("sex".equalsIgnoreCase(content)) {
            return true;
        } else {
            return false;
        }
    }

    public static int sex2Int(String sex) {
        if (TextUtils.isEmpty(sex)) {
            return 0;
        } else if (sex.contains(Constants.SEX_MAN)) {
            return 1;
        } else if (sex.contains(Constants.SEX_WOMAN)) {
            return 2;
        } else {
            return 0;
        }
    }


}