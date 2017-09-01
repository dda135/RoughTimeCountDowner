package fanjh.mine.roughtimecountdowner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fanjh.mine.library.RoughTimeCountDowner;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.textview)
    TextView textview;
    private RoughTimeCountDowner timeCountDowner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        timeCountDowner = new RoughTimeCountDowner(60000, 1000, new RoughTimeCountDowner.OnTickListener() {
            @Override
            public void onTick(long restTime, long initTime, long intervalTime) {
                textview.setText(restTime+"s");
            }

            @Override
            public void onDone() {
                textview.setText("done");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeCountDowner.start();
    }

}
