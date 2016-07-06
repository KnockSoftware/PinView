package com.dpizarro.pinview.library;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * Created by william on 7/5/16.
 */
public class PinViewEditText extends EditText {
    public PinViewEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PinViewEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PinViewEditText(Context context) {
        super(context);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new PinViewEditTextInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }

    public void notifyListnersOfDelete() {
        if (this.getText().toString().isEmpty()) {
            super.setText("");
        }
    }

    private class PinViewEditTextInputConnection extends InputConnectionWrapper {

        public PinViewEditTextInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            PinViewEditText.this.notifyListnersOfDelete();

            return super.deleteSurroundingText(beforeLength, afterLength);
        }

    }
}
