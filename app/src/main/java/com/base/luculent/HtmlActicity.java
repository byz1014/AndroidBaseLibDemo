package com.base.luculent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.TextView;

import com.luculent.base.BaseActivity;
import com.luculent.base.bind.FindviewbyId;

import net.nightwhistler.htmlspanner.HtmlSpanner;

public class HtmlActicity extends BaseActivity {

    @FindviewbyId(value = R.id.tv_html, click = false)
    TextView tv_html;

    @Override
    protected int onLayout() {
        return R.layout.activity_html_acticity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        HtmlSpanner htmlSpanner = new HtmlSpanner();
        Spannable spannable = htmlSpanner.fromHtml(tag2);
        tv_html.setText(spannable);
    }


    @Override
    protected void onPermissionResult(int code) {

    }




    @Override
    public void onClick(View v) {

    }


    String tag2 = "<p style=\"text-indent: 32px; text-align: center;\"><span style=\"font-family: 宋体; font-size: 20px;\"></span></p><p style=\"text-indent: 32px; text-align: center;\"><span style=\"font-family: 宋体; font-size: 20px;\"><img src=\"http://pinganfubeijing.oss-cn-beijing.aliyuncs.com/upload/upload/image/2019-07-31/20190731/1564564310914.png?Expires=1879924306&amp;OSSAccessKeyId=LTAI1B9EH2OL4Yte&amp;Signature=Gl0oRiFvlUT89FnSlkCakWQ%2Fwjg%3D\" title=\"\" alt=\"image.png\" width=\"328\" height=\"76\"></span></p><p style=\"text-indent: 32px; line-height: 1.5em;\"><span style=\"font-family: 宋体; font-size: 20px;\">内蒙古朗坤科<br></span></p><p><br></p>";

}
