package jiayuan.huawei.com.jiayuantestmanager.beans;

import java.io.Serializable;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MenuBean implements Serializable {
    public String itemName;
    public boolean isSelected=false;
    public int index;
    public int num;

    @Override
    public boolean equals(Object o) {
        MenuBean other=(MenuBean)o;
        return index==other.index;
    }
}
