package com.kiran2kirve.gojekassignment.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;

import com.kiran2kirve.gojekassignment.R;

public class DialogUtils {
    private Context ctn;

    public DialogUtils() {
    }

    public DialogUtils(Context context) {
        this.ctn = context;
    }

    public static void showOkTitleMsgDialog(Context context, int titleResId, int msgResId) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dailog_ok_message);
        ((TextView) dialog.findViewById(R.id.title_tv)).setText(titleResId);
        ((TextView) dialog.findViewById(R.id.message_tv)).setText(msgResId);
        TextView okBtn = (TextView) dialog.findViewById(R.id.ok_btn);
        okBtn.setTag(dialog);
        okBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showOkMessageDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dailog_ok_message);
        ((TextView) dialog.findViewById(R.id.title_tv)).setVisibility(View.VISIBLE);
        ((TextView) dialog.findViewById(R.id.message_tv)).setText(msg);
        TextView okBtn = (TextView) dialog.findViewById(R.id.ok_btn);
        okBtn.setTag(dialog);
        okBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void openDialog(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon((int) R.drawable.ic_launcher);
        alertDialog.setButton(-3, (CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    @SuppressLint({"ResourceAsColor"})
    public void openDialog(Context context, String title, String message, String oneButton) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        TextView titleTextView = new TextView(context);
        titleTextView.setText(title);
        titleTextView.setPadding(10, 20, 10, 60);
        titleTextView.setGravity(17);
        titleTextView.setTextColor(Color.parseColor("#1976d2"));
        titleTextView.setTextSize(20.0f);
        alertDialog.setCustomTitle(titleTextView);
        TextView msg = new TextView(context);
        msg.setText(message);
        msg.setGravity(1);
        msg.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        alertDialog.setView(msg);
        alertDialog.setButton(-3, (CharSequence) oneButton, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        new Dialog(context);
        alertDialog.show();
        Button okBT = alertDialog.getButton(-3);
        LayoutParams neutralBtnLP = (LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = 1;
        okBT.setPadding(20, 20, 20, 20);
        okBT.setTextColor(Color.parseColor("#FFFFFF"));
        okBT.setBackgroundColor(-16776961);
        okBT.setGravity(1);
        okBT.setLayoutParams(neutralBtnLP);
    }

    @SuppressLint({"ResourceType"})
    public void openDialog(Context context, String title, String message, String oneButton, String twoButton) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        TextView titleTextView = new TextView(context);
        titleTextView.setText(title);
        titleTextView.setPadding(10, 20, 10, 60);
        titleTextView.setGravity(17);
        titleTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        titleTextView.setTextSize(20.0f);
        alertDialog.setCustomTitle(titleTextView);
        TextView msg = new TextView(context);
        msg.setText(message);
        msg.setGravity(1);
        msg.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        alertDialog.setView(msg);
        alertDialog.setButton(-3, (CharSequence) oneButton, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setButton(-2, (CharSequence) twoButton, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        new Dialog(context);
        alertDialog.show();
        Button okBT = alertDialog.getButton(-3);
        LayoutParams neutralBtnLP = (LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = 7;
        okBT.setPadding(50, 10, 10, 10);
        okBT.setTextColor(Color.parseColor("#FFFFFF"));
        okBT.setBackgroundResource(context.getResources().getColor(R.color.colorPrimary));
        okBT.setLayoutParams(neutralBtnLP);
        Button cancelBT = alertDialog.getButton(-2);
        LayoutParams negBtnLP = (LayoutParams) okBT.getLayoutParams();
        negBtnLP.gravity = 7;
        cancelBT.setTextColor(SupportMenu.CATEGORY_MASK);
        cancelBT.setLayoutParams(negBtnLP);
    }
}
