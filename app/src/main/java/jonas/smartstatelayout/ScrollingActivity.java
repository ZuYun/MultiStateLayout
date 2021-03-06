package jonas.smartstatelayout;

import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import jonas.jlayout.MultiStateLayout;
import jonas.jlayout.OnStateClickListener;
import jonas.jlayout.revealLayout.JRelativelayout;

public class ScrollingActivity extends AppCompatActivity {

    private MultiStateLayout mMsl;
    private JRelativelayout mJRelativelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mMsl = ( (MultiStateLayout)findViewById(R.id.msl) ).setLoadingCancelAble(true)
//                .showStateLayout(MultiStateLayout.LayoutState.STATE_LOADING)
                .setRevealable(false);
        mMsl.registStateLayout(R.layout.cust_loading2, MultiStateLayout.LayoutState.STATE_LOADING);
        //        mMsl.CustomStateLayout(R.layout.cust_loading2, MultiStateLayout.LayoutState.STATE_EMPTY);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setCustom();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null)
                        .show();
            }
        });
        fab.postDelayed(new Runnable() {
            @Override
            public void run(){
                mMsl.showStateLayout(MultiStateLayout.LayoutState.STATE_EXCEPT);
            }
        }, 3000);

        mMsl.setOnStateClickListener(new OnStateClickListener() {
            @Override
            public void onRetry(@MultiStateLayout.LayoutState int layoutState){
                mMsl.postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        mMsl.showStateLayout(MultiStateLayout.LayoutState.STATE_ERROR);
                    }
                }, 3000);
            }

            @Override
            public void onLoadingCancel(){

            }
        });

        mJRelativelayout = (JRelativelayout)findViewById(R.id.jrl);
        mJRelativelayout.setAlpha(0);
    }

    private void setCustom(){
        TextView textView = new TextView(this);
        textView.setTextSize(39);
        textView.setText("jiaz 成功");
        textView.setBackgroundColor(Color.WHITE);
        mMsl.registStateLayout(textView, MultiStateLayout.LayoutState.STATE_EXCEPT).setLoadingCancelAble(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_loading) {
            mMsl.showStateLayout(MultiStateLayout.LayoutState.STATE_LOADING);
            mMsl.postDelayed(new Runnable() {
                @Override
                public void run(){
                    mMsl.showStateLayout(MultiStateLayout.LayoutState.STATE_EXCEPT);
                }
            }, 4000);
        }else if(id == R.id.action_error) {
            mMsl.showStateLayout(MultiStateLayout.LayoutState.STATE_ERROR);
        }else if(id == R.id.action_empty) {
            mMsl.showStateLayout(MultiStateLayout.LayoutState.STATE_EMPTY);
        }
        return super.onOptionsItemSelected(item);
    }

    public void retry(View v){
        if(mJRelativelayout.getVisibility() != View.VISIBLE) {
            mJRelativelayout.setRealVisibility(View.VISIBLE);
        }else {
            mJRelativelayout.setRealVisibility(View.GONE);
        }
    }

}
