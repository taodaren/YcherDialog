package ycher.taodaren.selfdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ycher.taodaren.dialoglib.YDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnStandard, mBtnNoBtn, mBtnShowOne, mBtnNoEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mBtnStandard = findViewById(R.id.btn_standard);
        mBtnNoBtn = findViewById(R.id.btn_no_btn);
        mBtnShowOne = findViewById(R.id.btn_show_one);
        mBtnNoEdit = findViewById(R.id.btn_no_edit);
    }

    private void initListener() {
        mBtnStandard.setOnClickListener(this);
        mBtnNoBtn.setOnClickListener(this);
        mBtnShowOne.setOnClickListener(this);
        mBtnNoEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_standard:
                standardDialog();
                break;
            case R.id.btn_no_btn:
                noBtnDialog();
                break;
            case R.id.btn_show_one:
                noYesOrNoDialog();
                break;
            case R.id.btn_no_edit:
                noEditDialog();
                break;
        }
    }

    private void standardDialog() {
        YDialog dialog = new YDialog(this);
        dialog.setEtInputType(InputType.TYPE_CLASS_TEXT)
                .setTitle("标准模式")
                .setMessage("提示信息")
                .setHint("输入框提示信息")
                .setYesOnclickListener("确认", dialog::dismiss)
                .setNoOnclickListener("取消", dialog::dismiss)
                .show();
    }

    private void noBtnDialog() {
        YDialog dialog = new YDialog(this);
        dialog.hideButton().hideEnter()
                .setTitle("无按钮模式")
                .setMessage("提示信息")
                .show();
    }

    private void noYesOrNoDialog() {
        YDialog dialog = new YDialog(this);
        dialog.setEtInputType(InputType.TYPE_CLASS_TEXT)
                .hideNo()
                .setTitle("只显示一个按钮")
                .setMessage("提示信息")
                .setHint("输入框提示信息")
                .setYesOnclickListener("获取输入文本", () -> {
                    Toast.makeText(this, dialog.getEditTextStr(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                })
                .show();
    }

    private void noEditDialog() {
        YDialog dialog = new YDialog(this);
        dialog.hideEnter()
                .setTitle("无输入框模式")
                .setMessage("提示信息")
                .setYesOnclickListener("确认", dialog::dismiss)
                .setNoOnclickListener("取消", dialog::dismiss)
                .show();
    }
}
