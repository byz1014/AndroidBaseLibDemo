package com.luculent.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public interface FragmentProvider {
    Fragment provide(Bundle args);
}
