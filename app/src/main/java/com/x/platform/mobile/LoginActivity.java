package com.x.platform.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.x.platform.mobile.common.Global;
import com.x.platform.mobile.common.UIHelper;
import com.x.platform.mobile.core.BaseActionBarActivity;
import com.x.platform.mobile.core.DefaultActionBarActivity;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends DefaultActionBarActivity implements TextView.OnEditorActionListener {


    @InjectView(R.id.login_account_edittext)
    EditText login_account_edittext;

    @InjectView(R.id.login_passwd_edittext)
     EditText login_passwd_edittext;

    @InjectView(R.id.login_remember_checkbox)
     CheckBox login_remember_checkbox;

    @InjectView(R.id.login_button)
     Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_passwd_edittext.setOnEditorActionListener(this);

        //进行字符验证
        /*login_account_edittext.addTextChangedListener(new TextWatcher() {
            //账号只能输入特定的字符
            String regEx = "[^-._@0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]";
            Pattern p = Pattern.compile(regEx);

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int editEnd = login_account_edittext.getSelectionEnd();
                String text = s.toString();
                String resultText = p.matcher(text).replaceAll("");
                //如果处理后的文本是一样的，则不再需要执行以下
                if(text.equals(resultText)) {
                    return;
                }

                if(editEnd > resultText.length()) {
                    editEnd = resultText.length();
                } else {
                    editEnd = editEnd - 1;
                }
                editEnd = editEnd < 0 ? 0 : editEnd;
                login_account_edittext.removeTextChangedListener(this);
                login_account_edittext.setText(resultText);
                login_account_edittext.setSelection(editEnd);
                login_account_edittext.addTextChangedListener(this);
            }
        });*/


    }

    @OnClick(R.id.login_button)
    public void login_submit(View v){
        checkLogin();
    }

    /** 登录信息检查与登录*/
    private void checkLogin() {
        String account = login_account_edittext.getText().toString();
        String passwd = login_passwd_edittext.getText().toString();
        boolean remember = login_remember_checkbox.isChecked();

        ////检查用户输入的参数
        if(StringUtils.isEmpty(account)){
            UIHelper.ToastMessage(this, getString(R.string.msg_login_email_null));
            return;
        }
        if(StringUtils.isEmpty(passwd)){
            UIHelper.ToastMessage(this, getString(R.string.msg_login_pwd_null));
            return;
        }
        login(account, passwd, remember);
    }

    /**执行登录**/
    private void login(String account, String passwd, boolean remember) {
        //TODO 完成登录过程
        //封装登录提交的JSON对象
        RequestParams params = new RequestParams();
        params.put("credential",account);
        params.put("password",passwd);
        params.put("code","");//TODO 预留验证码字段
        params.setUseJsonStreamer(true);
        showProgressBar(true);
        postNetwork(LOGIN_HOST,params,LOGIN_HOST);
    }

   void  startMain(){
       Intent intent= new Intent();
       intent.setAction("com.x.platform.MAIN");
       startActivity(intent);
       finish();
    }

    @Override
    public void parseJson(int code, JSONObject response, String tag, int pos, Object data) throws JSONException {
        if(tag.contains(LOGIN_HOST)){
            showProgressBar(false);
            if(code==200){
                String type = response.getString("type");
                switch(type){
                    case Global.SUCCESS:
                        startMain();
                        break;
                    case Global.ERROR:
                        showButtomToast("登录失败");
                        break;
                    default:
                        break;
                }
            }else{
                showButtomToast("登录失败");
            }
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //在输入法里点击了“完成”，则去登录
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            checkLogin();
            //将输入法隐藏
            InputMethodManager imm = (InputMethodManager)getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(login_passwd_edittext.getWindowToken(), 0);
            return true;
        }
        return false;
    }


    private static final String  LOGIN_HOST= Global.HOST+"/x_organization_service_authentication/jaxrs/authentication";

}
