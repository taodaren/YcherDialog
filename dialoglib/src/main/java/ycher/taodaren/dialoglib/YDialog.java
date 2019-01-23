package ycher.taodaren.dialoglib;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

/**
 * Imitation ios dialog
 */

public class YDialog extends Dialog {

    private LinearLayout btnLayout;
    private Button btnYes, btnNo;
    private TextView tvTitle, tvMessage;
    private ClearableEditText etEnter;
    private View line;

    // 从外界设置的 title 文本、消息文本、提示文本
    private String strTitle, strMessage, strHint;
    // 从外界设置的输入类型
    private int typeInput = -1;
    // 确定文本和取消文本的显示内容
    private String strYes, strNo;

    // 取消按钮被点击了的监听器
    private onNoOnclickListener noOnclickListener;
    // 确定按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;

    private boolean isShowTitle = true, isShowMessage = true,
                    isShowYes   = true, isShowNo      = true,
                    isShowEnter = true, isShowButton  = true;

    public YDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cust_dialog);
        // 设置全屏显示
        setFullScreenDisplay();
        // 按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        // 初始化界面控件
        initView();
        // 初始化界面数据
        initData();
        // 初始化界面控件的事件
        initEvent();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancel();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        btnLayout = findViewById(R.id.dialog_button);
        btnYes    = findViewById(R.id.yes);
        btnNo     = findViewById(R.id.no);
        tvTitle   = findViewById(R.id.title);
        tvMessage = findViewById(R.id.message);
        etEnter   = findViewById(R.id.edit_dialog);
        line      = findViewById(R.id.view_line);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        // 如果用户自定了 title 和 message
        if (strTitle != null) {
            tvTitle.setText(strTitle);
        }
        if (strMessage != null) {
            tvMessage.setText(strMessage);
        }
        if (strHint != null) {
            etEnter.setHint(strHint);
        }
        if (typeInput != -1) {
            etEnter.setInputType(typeInput);
        }

        // 如果设置按钮的文字
        if (strYes != null) {
            btnYes.setText(strYes);
        }
        if (strNo != null) {
            btnNo.setText(strNo);
        }

        // 显示/隐藏控件
        tvTitle  .setVisibility(isShowTitle   ? View.VISIBLE : View.GONE);
        tvMessage.setVisibility(isShowMessage ? View.VISIBLE : View.GONE);
        etEnter  .setVisibility(isShowEnter   ? View.VISIBLE : View.GONE);
        btnYes   .setVisibility(isShowYes     ? View.VISIBLE : View.GONE);
        btnNo    .setVisibility(isShowNo      ? View.VISIBLE : View.GONE);
        if (isShowButton) {
            btnLayout.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        } else {
            btnLayout.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }
    }

    /**
     * 获取输入文本
     *
     * @return 字符串类型输入文本
     */
    public String getEditTextStr() {
        return Objects.requireNonNull(etEnter.getText()).toString().trim();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        // 设置确定按钮被点击后，向外界提供监听
        btnYes.setOnClickListener(v -> {
            if (yesOnclickListener != null) {
                yesOnclickListener.onYesClick();
            }
        });
        // 设置取消按钮被点击后，向外界提供监听
        btnNo.setOnClickListener(v -> {
            if (noOnclickListener != null) {
                noOnclickListener.onNoClick();
            }
        });
    }

    /**
     * 隐藏标题
     */
    public YDialog hideTitle() {
        isShowTitle = false;
        return this;
    }

    /**
     * 隐藏信息提示
     */
    public YDialog hideMessage() {
        isShowMessage = false;
        return this;
    }

    /**
     * 隐藏输入框
     */
    public YDialog hideEnter() {
        isShowEnter = false;
        return this;
    }

    /**
     * 隐藏 YES 按钮
     */
    public YDialog hideYes() {
        isShowYes = false;
        return this;
    }

    /**
     * 隐藏 NO 按钮
     */
    public YDialog hideNo() {
        isShowNo = false;
        return this;
    }

    /**
     * 隐藏所有按钮
     */
    public YDialog hideButton() {
        isShowButton = false;
        return this;
    }

    /**
     * 为 Dialog 设置标题
     */
    public YDialog setTitle(String title) {
        strTitle = title;
        return this;
    }

    /**
     * 设置 dialog 的 message
     */
    public YDialog setMessage(String message) {
        strMessage = message;
        return this;
    }

    /**
     * 设置 EditText 的 hint
     */
    public YDialog setHint(String hint) {
        strHint = hint;
        return this;
    }

    /**
     * 设置编辑输入类型
     */
    public YDialog setEtInputType(int type) {
        typeInput = type;
        return this;
    }

    /**
     * 设置取消按钮的显示内容和监听
     */
    public YDialog setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            strNo = str;
        }
        this.noOnclickListener = onNoOnclickListener;
        return this;
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public YDialog setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            strYes = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
        return this;
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onNoOnclickListener {
        void onNoClick();
    }

    /**
     * 设置全屏显示
     */
    private void setFullScreenDisplay() {
        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.setAttributes(params);
    }
}
