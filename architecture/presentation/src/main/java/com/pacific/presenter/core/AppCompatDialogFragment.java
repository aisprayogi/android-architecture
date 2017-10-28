package com.pacific.presenter.core;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.internal.functions.ObjectHelper;
import javax.annotation.CheckForNull;
import javax.inject.Inject;

public abstract class AppCompatDialogFragment extends
    android.support.v7.app.AppCompatDialogFragment {

  private ViewModel realViewModel;

  @Inject
  protected ViewModelFactory modelFactory;

  @Override
  public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (isAttachViewModel()) {
      switch (modelProvider()) {
        case ACTIVITY:
          realViewModel = ViewModelProviders.of(getActivity(), modelFactory)
              .get(modelClass());
          break;
        case PARENT_FRAGMENT:
          realViewModel = ViewModelProviders.of(getParentFragment(), modelFactory)
              .get(modelClass());
          break;
        case NONE:
          realViewModel = ViewModelProviders.of(this, modelFactory)
              .get(modelClass());
          break;
        default:
          throw new UnsupportedOperationException();
      }
    }
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (!view.isClickable()) {
      view.setClickable(true);
    }
  }

  protected ViewModelSource modelProvider() {
    return ViewModelSource.ACTIVITY;
  }

  @CheckForNull
  protected final <T extends ViewModel> T fetchViewModel() {
    return (T) ObjectHelper.requireNonNull(realViewModel, "realViewModel = null");
  }

  protected boolean isAttachViewModel() {
    return false;
  }

  protected abstract Class<? extends ViewModel> modelClass();
}