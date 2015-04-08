// Generated code from Butter Knife. Do not modify!
package com.x.platform.mobile;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class LoginActivity$$ViewInjector<T extends com.x.platform.mobile.LoginActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296319, "field 'login_account_edittext'");
    target.login_account_edittext = finder.castView(view, 2131296319, "field 'login_account_edittext'");
    view = finder.findRequiredView(source, 2131296320, "field 'login_passwd_edittext'");
    target.login_passwd_edittext = finder.castView(view, 2131296320, "field 'login_passwd_edittext'");
    view = finder.findRequiredView(source, 2131296321, "field 'login_remember_checkbox'");
    target.login_remember_checkbox = finder.castView(view, 2131296321, "field 'login_remember_checkbox'");
    view = finder.findRequiredView(source, 2131296322, "field 'login_button' and method 'login_submit'");
    target.login_button = finder.castView(view, 2131296322, "field 'login_button'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login_submit(p0);
        }
      });
  }

  @Override public void reset(T target) {
    target.login_account_edittext = null;
    target.login_passwd_edittext = null;
    target.login_remember_checkbox = null;
    target.login_button = null;
  }
}
