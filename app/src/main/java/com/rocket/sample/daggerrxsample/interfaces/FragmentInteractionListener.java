package com.rocket.sample.daggerrxsample.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 *  Contains base methods for performing fragment related operations
 */
public interface FragmentInteractionListener {
    int FRAG_ADD = 1;
    int FRAG_REPLACE = 2;
    int FRAG_ADD_ANIMATE = 3;
    int FRAG_DIALOG = 4;
    int FRAG_REPLACE_WITH_STACK = 5;
    int FRAG_ADD_WITH_STACK = 6;

    void setCurrentFragment(Bundle bundle, int fragmentType, int transType, int frameId);

    void popTopFragment();

    void popAllFromStack();

    String getActiveFragmentTag();

}
