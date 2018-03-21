package com.example.yasirnazir.sky.utils.display;

/**
 * Created by yasirnazir on 3/21/18.
 */

import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewEditorActionEvent;

import rx.Observable;

import static android.view.KeyEvent.ACTION_UP;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;
import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;
import static com.jakewharton.rxbinding.widget.RxTextView.editorActionEvents;

public class BindingExtensions {

    public static Observable<String> textChangesAsString(TextView textView) {
        return RxTextView.textChanges(textView).map(CharSequence::toString);
    }

    public static Observable<Void> doneButtonClicks(TextView textView) {
        return editorActionEvents(textView)
                .filter(event -> event.actionId() == IME_ACTION_DONE)
                .map(event -> null);
    }

    public static Observable<Void> searchButtonClicks(TextView textView) {
        return editorActionEvents(textView)
                .filter(event -> isSearchButton(event) || isEnterKey(event))
                .map(event -> null);
    }

    private static boolean isSearchButton(TextViewEditorActionEvent event) {
        return event.actionId() == IME_ACTION_SEARCH;
    }

    private static boolean isEnterKey(TextViewEditorActionEvent event) {
        return event.keyEvent() != null && event.keyEvent().getAction() == ACTION_UP && event.keyEvent().getKeyCode() == KEYCODE_ENTER;
    }
}