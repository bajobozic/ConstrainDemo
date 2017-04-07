package com.example.bajob.constraindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private InputMethodManager inputMethodManager;
    private RecyclerView recyclerView;
    private ImageButton rightImage;
    private EditText commentText;

    private RecyclerView.Adapter adapter;
    private List<String> commentList = new ArrayList<>();
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        ImageView waterfall = (ImageView) findViewById(R.id.waterfall);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rightImage = (ImageButton) findViewById(R.id.right_image);
        commentText = (EditText) findViewById(R.id.comment_text);
        ImageView leftImage = (ImageView) findViewById(R.id.left_image);

        adapter = new MyAdapter(commentList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

       //when usig adjustResize and when ther is no fixed height image in a layout
        //for example if we have pure chat app layout with only recycler view and chat box fixed at bottom of layout
        //then we use belowe code for scrolling and showing keyboard
        /*recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (i3 < i7) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.smoothScrollToPosition(commentList.size());
                        }
                    });
                }
            }
        });*/
        commentText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            void duringTextChange(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    rightImage.setEnabled(true);
                } else {
                    rightImage.setEnabled(false);
                }
            }
        });
        rightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputMethodManager.hideSoftInputFromWindow(commentText.getWindowToken(), 0);
                text = commentText.getText().toString().trim();
                commentList.add(text);
                adapter.notifyItemInserted(commentList.size());
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*final int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                        final int size = commentList.size();
                        Log.d("LAST VISIBLE", " " + firstVisibleItemPosition);
                        Log.d("SIZE", " " + size);
                        if ( firstVisibleItemPosition != 0) {
                            Toast.makeText(MainActivity.this,"You have unread message",Toast.LENGTH_LONG).show();
                        } else {
                            recyclerView.smoothScrollToPosition(commentList.size());
                        }*/
                        recyclerView.smoothScrollToPosition(commentList.size());
                    }
                }, 100);
                resetChatBox();
            }
        });
    }

    private void resetChatBox() {
        text = null;
        commentText.setText(null);
        commentText.clearFocus();
        rightImage.setEnabled(false);
    }
}
