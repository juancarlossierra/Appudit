package com.umb.appudit.features.evaluation.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.umb.appudit.R;
import com.umb.appudit.features.evaluation.data.entities.Knowledge;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class QuestionDialog2 extends Fragment {

    private String question;
    private List options;

    public static Knowledge knowledge;

    @SuppressLint("ValidFragment")
    public QuestionDialog2(final Context context, String buottonText) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.question_dialog);

        final EditText etQuestion = dialog.findViewById(R.id.etQuestion);
        final EditText etOptionOne = dialog.findViewById(R.id.etOptionOne);
        final EditText etOptionTwo = dialog.findViewById(R.id.etOptionTwo);
        final EditText etOptionThree = dialog.findViewById(R.id.etOptionThree);
        final EditText etOptionFour = dialog.findViewById(R.id.etOptionFour);

        Button btnAccept = dialog.findViewById(R.id.btnAccept);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnAccept.setText(buottonText);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question = etQuestion.getText().toString();
                options = new ArrayList<>();
                options.add(etOptionOne.getText().toString());
                options.add(etOptionTwo.getText().toString());
                options.add(etOptionThree.getText().toString());
                options.add(etOptionFour.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
