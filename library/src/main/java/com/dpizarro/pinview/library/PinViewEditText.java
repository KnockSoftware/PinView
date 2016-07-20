package com.dpizarro.pinview.library;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * Created by william on 7/5/16.
 */
public class PinViewEditText extends EditText implements View.OnKeyListener {
    public PinViewEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnKeyListener(this);
    }

    public PinViewEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnKeyListener(this);
    }

    public PinViewEditText(Context context) {
        super(context);
        setOnKeyListener(this);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new PinViewEditTextInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
            notifyListnersOfDelete();
        }
        return false;
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
        public boolean sendKeyEvent(KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                PinViewEditText.this.notifyListnersOfDelete();
            }
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            PinViewEditText.this.notifyListnersOfDelete();

            return super.deleteSurroundingText(beforeLength, afterLength);
        }

    }
}
