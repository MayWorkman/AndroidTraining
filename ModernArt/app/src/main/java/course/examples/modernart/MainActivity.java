package course.examples.modernart;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity {

    private SeekBar colorControl = null;
    private static final String TAG = "ModernArt";
    private int toTransparent = 255;
    private int toOpaque = 105;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setBackgroundAlphaColors(toOpaque, toTransparent);

        colorControl = (SeekBar) findViewById(R.id.seek);
        colorControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;


            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG, "onProgressChanged: " + progressChanged);

                progressChanged = progress;
                toTransparent = (int) (255 - (progressChanged * 1.5));
                toOpaque = (int) (105 + (progressChanged * 1.5));

                setBackgroundAlphaColors(toOpaque, toTransparent);
             }

            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStartTrackingTouch");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStopTrackingTouch");
            }
        });



    }


   /*  Menu functionality  */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_info_menu_item:
                showMoreInfoDialog();
                return true;
            default:
                return false;
        }
    }


    /*  Dialog box for more information  */

    private void showMoreInfoDialog() {
        DialogFragment newFragment = MoreInfoDialog.newInstance(R.string.more_info_title);
        newFragment.show(getFragmentManager(), "dialog");
    }
        public void doPositiveClick() {
            Log.i("FragmentAlertDialog", "Positive click!");

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.moma_url)));
            startActivity(intent);
        }

        public void doNegativeClick() {
            Log.i("FragmentAlertDialog", "Negative click!");
        }



    /*  Dynamically setting the Alpha variable for the color depending on the seek bar  */

    private void setBackgroundAlphaColors(int toOpaque, int toTransparent)
    {
        findViewById(R.id.row0Col0).getBackground().setAlpha(toTransparent);
        findViewById(R.id.row0Col2).getBackground().setAlpha(toOpaque);
        findViewById(R.id.row1Col0).getBackground().setAlpha(toTransparent);
        findViewById(R.id.row1Col1).getBackground().setAlpha(toOpaque);
        findViewById(R.id.row2Col0).getBackground().setAlpha(toOpaque);
        findViewById(R.id.row2Col1).getBackground().setAlpha(toOpaque);
        findViewById(R.id.row3Col1).getBackground().setAlpha(toTransparent);
        findViewById(R.id.row3Col2).getBackground().setAlpha(toTransparent);
    }

}


