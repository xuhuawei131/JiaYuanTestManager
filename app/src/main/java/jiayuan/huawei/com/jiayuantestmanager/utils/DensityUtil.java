package jiayuan.huawei.com.jiayuantestmanager.utils;


import android.content.Context;
import android.view.WindowManager;

import jiayuan.huawei.com.jiayuantestmanager.MyApp;

public class DensityUtil {

	public static int dip2px(float dpValue) {
		final float scale = MyApp.context.getResources()
				.getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}


	public static int px2dipfloat(float pxValue) {
		final float scale = MyApp.context.getResources()
				.getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}


	public static int[] getWindowWH() {
		int[] wh = new int[2];
		wh[0] = MyApp.context.getResources().getDisplayMetrics().widthPixels;
		wh[1] = MyApp.context.getResources().getDisplayMetrics().heightPixels;
		return wh;
	}


	public static int autoResizeWidth(int len) {
		if (len < 1)
			len = 1;
		/* ͷ��ͼ��������������ռ�õ�����ֵ */
		int w = DensityUtil.dip2px(120);
		/* ��Ļ�Ͽ�ȵ��ܵ�����ֵ */
		int widthPixels = MyApp.context.getResources()
				.getDisplayMetrics().widthPixels;
		/* ʣ������ֵ,���������� */
		int wPixel = widthPixels - w;
		/* ������������С�������ֵ������ʾ���� */
		int minWidth = DensityUtil.dip2px(60);
		/* �����������ȼ���Ŀ������ֵ ����һ����С�ĳ���������ʾ���� Ȼ����ʣ��ĳ����ﰴ��n/60���ӳ��� */
		int width = (int) ((wPixel - minWidth) * len / 59.0f + 0.5f) + minWidth;
		/* ���С����С���ֵ,�򷵻���С���ֵ,��֮���ؼ�������Ŀ��ֵ(��λ:����) */
		return width < minWidth ? minWidth : width;
		// д���Ŀ�ȼ���
	}

}
